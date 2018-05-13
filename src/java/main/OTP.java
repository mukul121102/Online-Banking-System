package main;

import java.util.Random;

class OTP {

    public static int otpGenerator() {
        int otp;
        Random random = new Random();
        otp = random.nextInt(9000) + 1000;
        return otp;
    }
}
