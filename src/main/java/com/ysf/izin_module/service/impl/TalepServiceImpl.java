package com.ysf.izin_module.service.impl;

import com.ysf.izin_module.models.dto.TalepDTO;
import com.ysf.izin_module.models.entity.IzinHakedisEntity;
import com.ysf.izin_module.models.entity.IzinTalepEntity;
import com.ysf.izin_module.models.entity.KullaniciEntity;
import com.ysf.izin_module.repository.IzinHakedisRepository;
import com.ysf.izin_module.repository.KullaniciRepository;
import com.ysf.izin_module.service.TalepService;
import javafx.util.converter.LocalDateStringConverter;
import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class TalepServiceImpl implements TalepService {
    private final KullaniciRepository kullaniciRepository;
    private final IzinHakedisRepository izinRepo;
    @Override
    public int izinGunSayisi(TalepDTO talepDTO) throws ParseException {
        int a=0;
        System.out.println("Çalışılan günler!");
        LocalDate today = LocalDate.of(talepDTO.getStartDate().getYear(),talepDTO.getStartDate().getMonth(),talepDTO.getStartDate().getDayOfMonth());
        LocalDate lastDay = LocalDate.of(talepDTO.getEndDate().getYear(), talepDTO.getEndDate().getMonth(), talepDTO.getEndDate().getDayOfMonth());

        // test için tatil günleri ekliyoruz
        List<LocalDate> holidays = new ArrayList<>();
        holidays.add(LocalDate.of(2022, 11, 15));
        holidays.add(LocalDate.of(2022, 11, 18));

        for (LocalDate x : BusinessDaysBetween(today, lastDay, Optional.of(holidays)))
        { a=a+1;
            System.out.println(x.toString());
        }
        System.out.println("toplam gün sayısı : " +a);
        return  a;
    }

    @Override
    public int ToplamHizmetSuresi(TalepDTO talepDTO) {
        KullaniciEntity kullaniciEntity=kullaniciRepository.findByUsername(talepDTO.getUsername());
        LocalDate baslangicTarih=kullaniciEntity.getStartDate();
        LocalDate nowDate=LocalDate.now();

        Period period = baslangicTarih.until(nowDate);
        int yearsBetween = period.getYears();
        LocalDate x =LocalDate.now();
        System.out.println("Toplam yıl:"+yearsBetween);

         if(yearsBetween <1 ){
            IzinHakedisEntity izinHakedisEntity=izinRepo.findByKullaniciEntity_Username(kullaniciEntity.getUsername());
            izinHakedisEntity.setIzinGunSayisi(5);
            izinHakedisEntity.setKullaniciEntity(kullaniciEntity);
            izinHakedisEntity.setIzinCompleted(izinHakedisEntity.getIzinCompleted());
            izinRepo.save(izinHakedisEntity);
        }

        else if(yearsBetween>=1 & yearsBetween<6){
            IzinHakedisEntity izinHakedisEntity=izinRepo.findByKullaniciEntity_Username(kullaniciEntity.getUsername());
            izinHakedisEntity.setIzinGunSayisi(15);
            izinHakedisEntity.setKullaniciEntity(kullaniciEntity);
            izinHakedisEntity.setIzinCompleted(izinHakedisEntity.getIzinCompleted());
            izinRepo.save(izinHakedisEntity);
        }
        else if(yearsBetween>=6 & yearsBetween<11){
            IzinHakedisEntity izinHakedisEntity=izinRepo.findByKullaniciEntity_Username(kullaniciEntity.getUsername());
            izinHakedisEntity.setIzinGunSayisi(18);
            izinHakedisEntity.setKullaniciEntity(kullaniciEntity);
            izinHakedisEntity.setIzinCompleted(izinHakedisEntity.getIzinCompleted());
            izinRepo.save(izinHakedisEntity);
        }
        else if(yearsBetween>=11 ){
            IzinHakedisEntity izinHakedisEntity=izinRepo.findByKullaniciEntity_Username(kullaniciEntity.getUsername());
            izinHakedisEntity.setIzinGunSayisi(24);
            izinHakedisEntity.setKullaniciEntity(kullaniciEntity);
            izinHakedisEntity.setIzinCompleted(izinHakedisEntity.getIzinCompleted());
            izinRepo.save(izinHakedisEntity);
        }



        return yearsBetween;
    }

    private static List<LocalDate> BusinessDaysBetween(LocalDate startDate, LocalDate endDate,
                                                       Optional<List<LocalDate>> holidays)
    {
        if (startDate == null || endDate == null ) {
            throw new IllegalArgumentException("Geçerli tarih girilmemiştir. başlangıç veya bitiş tarihini kontrol ediniz...(" + startDate
                    + "," + endDate + "," + holidays + ")");
        }

        Predicate<LocalDate> isHoliday = date -> holidays.map(localDates -> localDates.contains(date)).orElse(false);

        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        return Stream.iterate(startDate, date -> date.plusDays(1)).limit(daysBetween)
                .filter(isHoliday.or(isWeekend).negate()).collect(Collectors.toList());
    }
    }




