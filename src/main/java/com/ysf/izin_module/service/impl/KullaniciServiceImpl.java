package com.ysf.izin_module.service.impl;

import com.ysf.izin_module.enums.StatusEnum;
import com.ysf.izin_module.models.dto.KullaniciDTO;
import com.ysf.izin_module.models.entity.IzinHakedisEntity;
import com.ysf.izin_module.models.entity.KullaniciEntity;
import com.ysf.izin_module.repository.IzinHakedisRepository;
import com.ysf.izin_module.repository.KullaniciRepository;
import com.ysf.izin_module.service.KullaniciService;
import com.ysf.izin_module.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KullaniciServiceImpl implements KullaniciService {

    private final KullaniciRepository kullaniciRepository;
    private final IzinHakedisRepository izinHakedisRepository;

    @Override
    public Result<KullaniciEntity> add(KullaniciDTO kullaniciDTO) {
        if(kullaniciDTO.getId()==null||kullaniciDTO.getId()==0){
            KullaniciEntity kullaniciEntity=new KullaniciEntity();

            kullaniciEntity.setUsername(kullaniciDTO.getUsername());
            kullaniciEntity.setRoleEnum(kullaniciDTO.getRole());
            kullaniciEntity.setStartDate(kullaniciDTO.getBaslangicDate());

            KullaniciEntity savedKullanici=kullaniciRepository.save(kullaniciEntity);

            IzinHakedisEntity izinHakedis=new IzinHakedisEntity();
            izinHakedis.setIzinGunSayisi(5);
            izinHakedis.setIzinCompleted(0);
            izinHakedis.setKullaniciEntity(savedKullanici);

            izinHakedisRepository.save(izinHakedis);

            return new Result<>(savedKullanici, StatusEnum.success,"Yeni kayıt başarıyla gerçekleşmiştir.");
        }
        return updateKullanici(kullaniciDTO);
    }

    public Result<KullaniciEntity> updateKullanici(KullaniciDTO kullaniciDTO){
        Optional<KullaniciEntity> kullaniciEntity=kullaniciRepository.findById(kullaniciDTO.getId());
        if (kullaniciEntity.isPresent()){
            kullaniciEntity.get().setUsername(kullaniciDTO.getUsername());
            kullaniciEntity.get().setRoleEnum(kullaniciDTO.getRole());
            kullaniciEntity.get().setStartDate(kullaniciDTO.getBaslangicDate());
            return new Result<>(kullaniciRepository.save(kullaniciEntity.get()),StatusEnum.success,"güncelleme yapılmıştır.");
        }
        return new Result<>(StatusEnum.failed,"böyle bir kayda rastlanmamıştır.");
    }
    @Override
    public Result<List<KullaniciEntity>> getAll() {

        return null;
    }

}
