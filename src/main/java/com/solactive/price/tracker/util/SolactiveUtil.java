package com.solactive.price.tracker.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.time.Duration;
import java.time.Instant;

public  class SolactiveUtil {


    public static boolean isValidPriceRecord(long tickTimestamp) {
        Instant sixtySecBefore = Instant.now().minus(Duration.ofSeconds(Constant.TIME_LIMIT_SECS));
        Instant tickTimeInstant = Instant.ofEpochMilli(tickTimestamp);
        return tickTimeInstant.isAfter(sixtySecBefore) && !isFuturePriceRecord(tickTimestamp);
    }

    private static boolean isFuturePriceRecord(long tickTimestamp) {
        Instant now = Instant.now();
        Instant tickTimeInstant = Instant.ofEpochMilli(tickTimestamp);
        return tickTimeInstant.isAfter(now);
    }





    public static <T> HttpEntity<T> getEntityHttpHeader(T requestObject) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<T> request = new HttpEntity<>(requestObject, headers);

        return request;
    }

    public static HttpEntity<?> getHttpHeader() {
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<?> request = new HttpEntity<>(headers);

        return request;
    }
}
