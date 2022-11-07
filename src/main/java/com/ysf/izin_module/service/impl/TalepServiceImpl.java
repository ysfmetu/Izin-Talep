package com.ysf.izin_module.service.impl;

import com.ysf.izin_module.models.dto.TalepDTO;
import com.ysf.izin_module.service.TalepService;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
@Service
public class TalepServiceImpl implements TalepService {
    @Override
    public int numberOfDays(TalepDTO talepDTO) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = df.parse(String.valueOf(talepDTO.getStartDate()));
        Date date2 = df.parse(String.valueOf(talepDTO.getEndDate()));
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        int numberOfDays = 0;
        while (cal1.before(cal2)) {
            if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                    && (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                numberOfDays++;
            }
            cal1.add(Calendar.DATE, 1);
        }
        System.out.println(numberOfDays);
        return numberOfDays;
    }

}
