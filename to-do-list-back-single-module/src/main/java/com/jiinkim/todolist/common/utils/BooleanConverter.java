package com.jiinkim.todolist.common.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BooleanConverter {

    public boolean convertToBoolean(final int value) {
        return value != 0;
    }

    public int convertToInt(final boolean value) {
        return value ? 1 : 0;
    }

}
