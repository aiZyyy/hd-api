package com.hd.kbswarn.modules.common.utils;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * ClassName WarnLevelUtil
 * Description
 * Create by ZY
 * Date 2020/12/1 14:12
 */
public class WarnLevelUtil {

    public static Integer getWarnLevel(BigDecimal val, BigDecimal level1, BigDecimal level2, BigDecimal level3) {
        //获取所有小时的雨量信息
        if (Objects.nonNull(val)) {
            if (Objects.nonNull(level1) && Objects.nonNull(level2) && Objects.nonNull(level3)) {
                if (val.compareTo(level1) == -1) {
                    return 0;
                } else if ((val.compareTo(level1) == 1 && val.compareTo(level2) == -1) || val.compareTo(level1) == 0) {
                    return 1;
                } else if ((val.compareTo(level2) == 1 && val.compareTo(level3) == -1) || val.compareTo(level2) == 0) {
                    return 2;
                } else if ((val.compareTo(level3) == 1) || val.compareTo(level3) == 0) {
                    return 3;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
