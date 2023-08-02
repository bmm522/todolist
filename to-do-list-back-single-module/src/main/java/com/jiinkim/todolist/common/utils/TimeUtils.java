package com.jiinkim.todolist.common.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class TimeUtils {

        public LocalDateTime getNow() {

                LocalDateTime now = LocalDateTime.now();
                now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return now;
        }

}
