package com.solactive.price.tracker.util;

import com.solactive.price.tracker.model.Tick;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    public static boolean isBadRequest(Tick tick)
    {
        if (tick==null || tick.getInstrument()==null || tick.getPrice()<=0 || tick.getInstrument().isBlank() || tick.getTimestamp()==null)
        {
            return true;
        }
        return false;
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
