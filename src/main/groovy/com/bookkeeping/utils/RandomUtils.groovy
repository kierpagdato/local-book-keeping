package com.bookkeeping.utils

class RandomUtils {

    public static final Random RANDOM = new Random()

    static def randomize(int max) {
        RANDOM.nextInt(max)
    }
}
