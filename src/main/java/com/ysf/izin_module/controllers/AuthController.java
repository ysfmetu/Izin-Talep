package com.ysf.izin_module.controllers;

import com.ysf.izin_module.enums.RoleEnum;
import com.ysf.izin_module.models.dto.KullaniciDTO;
import com.ysf.izin_module.models.dto.response.JwtResponse;
import com.ysf.izin_module.models.dto.response.LoginRequest;
import com.ysf.izin_module.models.dto.response.MessageResponse;
import com.ysf.izin_module.models.entity.KullaniciEntity;
import com.ysf.izin_module.models.entity.Role;
import com.ysf.izin_module.repository.KullaniciRepository;
import com.ysf.izin_module.repository.RoleRepository;
import com.ysf.izin_module.security.jwt.JwtUtils;
import com.ysf.izin_module.security.services.UserDetailsImpl;
import com.ysf.izin_module.service.KullaniciService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Api(value = "Auhentication API", description = "Kullanıcı kayıt oluşturma , kimlik doğrulama,JWT token oluşturma")
public class AuthController {

    private  AuthenticationManager authenticationManager;

    private final KullaniciService kullaniciService;
    private KullaniciRepository userRepository;

    private  RoleRepository roleRepository;

    private  PasswordEncoder encoder;

    private  JwtUtils jwtUtils;

    @PostMapping("/signup")
    @ApiOperation(value = "Kullanıcı kaydetme işlemi yapar"  )
    public ResponseEntity<?> registerUser( @RequestBody KullaniciDTO signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Böyle bir kullanıcı mevcuttur.kullanıcı adını değiştiriniz.!"));
        }

        // Yeni kullanıcıyı kaydetme kısmı
        KullaniciEntity user = new KullaniciEntity();
        user.setStartDate(signUpRequest.getBaslangicDate());
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Hata: Böyle bir role tanımı yapılmamış..."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Hata: Böyle bir role tanımı yapılmamışx..."));
                        roles.add(adminRole);

                        break;
                    case "manager":
                        Role modRole = roleRepository.findByName(RoleEnum.ROLE_MANAGER)
                                .orElseThrow(() -> new RuntimeException("Hata: Böyle bir role tanımı yapılmamış..."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Hata: Böyle bir role tanımı yapılmamış..."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        kullaniciService.add(user);

        return ResponseEntity.ok(new MessageResponse("Kullanıcı Başarılı bir şekilde kayıt edilmiştir...!"));
    }

    @PostMapping("/signin")
    @ApiOperation(value = "kimlik doğrulama,Güvenli giriş ve JWT token oluşturur."  )
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getStartDate(),
                roles));
    }
}
