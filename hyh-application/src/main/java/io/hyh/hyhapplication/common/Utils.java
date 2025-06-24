package io.hyh.hyhapplication.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    public static boolean isNumeric(@Nullable String str) {
        return StringUtils.hasText(str) && str.chars().allMatch(Character::isDigit);
    }

}
