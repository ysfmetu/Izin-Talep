package com.ysf.izin_module.service.impl;

import com.ysf.izin_module.enums.IzinStatusEnum;
import com.ysf.izin_module.enums.StatusEnum;
import com.ysf.izin_module.models.dto.TalepDTO;
import com.ysf.izin_module.models.entity.IzinHakedisEntity;
import com.ysf.izin_module.models.entity.IzinTalepEntity;
import com.ysf.izin_module.models.entity.KullaniciEntity;
import com.ysf.izin_module.repository.IzinHakedisRepository;
import com.ysf.izin_module.repository.IzinTalepRepository;
import com.ysf.izin_module.repository.KullaniciRepository;
import com.ysf.izin_module.service.TalepService;
import com.ysf.izin_module.utils.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class TalepServiceImpl implements TalepService {
    private  KullaniciRepository kullaniciRepository;
    private  IzinHakedisRepository izinRepo;
    private  IzinTalepRepository izinTalepRepo;
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

    @Override
    public Result<IzinTalepEntity> saveIzin(TalepDTO talepDTO) throws ParseException {
        KullaniciEntity kullaniciEntity=kullaniciRepository.findByUsername(talepDTO.getUsername());
        IzinTalepEntity izinTalepEntity =new IzinTalepEntity();
        int talep_edilen_izin=izinGunSayisi(talepDTO);
        int kontrolCompletedDay=ControlGunSayisi(talep_edilen_izin,talepDTO);
          if(kontrolCompletedDay>0){
              izinTalepEntity.setUsername(talepDTO.getUsername());
              izinTalepEntity.setIzinBaslangicTarihi(talepDTO.getStartDate());
              izinTalepEntity.setIzinBitisTarihi(talepDTO.getEndDate());
              izinTalepEntity.setIzinGunSayisi(talep_edilen_izin);
              izinTalepEntity.setDurum(IzinStatusEnum.beklemede);
              izinTalepEntity.setKullaniciEntity(kullaniciEntity);
              IzinTalepEntity savedTalep= izinTalepRepo.save(izinTalepEntity);

              IzinHakedisEntity izinHakedis=izinRepo.findByKullaniciEntity_Id(kullaniciEntity.getId());
              izinHakedis.setIzinCompleted(kontrolCompletedDay);
              izinRepo.save(izinHakedis);

        return new Result<>(savedTalep, StatusEnum.success,"İzin talebiniz oluşturulmuştur, yönetici onayı beklemektedir.");
          }
          return new Result<>(StatusEnum.failed,"izin talebiniz olumsuzdur.yıllık izin miktarını aştınız...");


    }

    @Override
    public Result<IzinTalepEntity> updateTalep(TalepDTO talepDTO) {
       IzinTalepEntity izinTalepEntity= izinTalepRepo.findById(talepDTO.getId()).orElse(null);
       if (izinTalepEntity!=null){
           if(talepDTO.getDurum().toString()=="onaylandı"){
               izinTalepEntity.setDurum(IzinStatusEnum.onaylandı);
               IzinTalepEntity savedIzinTalep=izinTalepRepo.save(izinTalepEntity);
               return new Result<>(savedIzinTalep,StatusEnum.success,"izniniz yönetici tarafından onaylanmıştır");
           }
           else if (talepDTO.getDurum().toString()=="red"){
               izinTalepEntity.setDurum(IzinStatusEnum.red);
               IzinHakedisEntity izinHakedis=izinRepo.findByKullaniciEntity_Id(izinTalepEntity.getKullaniciEntity().getId());
               izinHakedis.setIzinCompleted(izinHakedis.getIzinGunSayisi()-izinTalepEntity.getIzinGunSayisi());
               izinRepo.save(izinHakedis);
             IzinTalepEntity savedTalep= izinTalepRepo.save(izinTalepEntity);
             return  new Result<>(savedTalep,StatusEnum.failed,"Talebiniz yönetici tarafından onaylanmamıştır.");
           }
       }
        return new Result<>(StatusEnum.failed, "Böyle bir talep bulunamamıştır.");
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

    public int ControlGunSayisi(int talepEdilenGunSayisi,TalepDTO talepDTO){
        KullaniciEntity kullaniciEntity=kullaniciRepository.findByUsername(talepDTO.getUsername());
        IzinHakedisEntity izinHakedis=izinRepo.findByKullaniciEntity_Username(kullaniciEntity.getUsername());
        if((izinHakedis.getIzinCompleted()+talepEdilenGunSayisi)<=izinHakedis.getIzinGunSayisi()){
            int toplamizingunsayisi=izinHakedis.getIzinCompleted()+talepEdilenGunSayisi;
            return toplamizingunsayisi;
        }
        return 0;
    }


}




