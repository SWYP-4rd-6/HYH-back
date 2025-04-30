package com.example.hyh.global;

import org.jetbrains.annotations.Nullable;
import org.springframework.util.StringUtils;

public class Utils {

    public static boolean isNumeric(@Nullable String str) {
        return StringUtils.hasText(str) && str.chars().allMatch(Character::isDigit);
    }

}
