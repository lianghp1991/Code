package com.lhp.copy.basicsample.db.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * @author lianghp
 * @Date 2018/10/23
 **/
public class DataConverter {
    @TypeConverter
    public static Date toDate(Long timetamp) {
        return timetamp == null ? null : new Date(timetamp);
    }

    @TypeConverter
    public static Long toTimetamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
