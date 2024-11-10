package com.smart.booking.common.util;

import java.util.Random;

public class CommonUtil {
    public static int createRandomNumber() {
        // Random 객체 생성
        Random random = new Random();

        // 100000에서 999999 사이의 랜덤 숫자 생성
        return 10000000 + random.nextInt(90000000);
    }
}
