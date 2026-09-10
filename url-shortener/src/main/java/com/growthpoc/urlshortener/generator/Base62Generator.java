package com.growthpoc.urlshortener.generator;

import org.springframework.stereotype.Component;

@Component
public class Base62Generator {

    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String encode(long number) {

        if (number == 0) {
            return "0";
        }

        StringBuilder result =
                new StringBuilder();

        while (number > 0) {

            int remainder =
                    (int) (number % 62);

            result.append(
                    BASE62.charAt(remainder)
            );

            number /= 62;
        }

        return result.reverse().toString();
    }
}