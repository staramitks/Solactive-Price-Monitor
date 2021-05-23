package com.solactive.price.tracker.api;

import com.solactive.price.tracker.PriceTrackerApplication;
import com.solactive.price.tracker.model.Statistics;

import com.solactive.price.tracker.model.Tick;

import com.solactive.price.tracker.service.TickPriceService;
import com.solactive.price.tracker.util.SolactiveUtil;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PriceTrackerApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = { "test" })
public class TestTickPriceMonitorAPI {

    private static String[] tickrArray = new String[] {"IBM_N", "TCS", "MICROSOFT", "FORD", "MERCEDES", "TESLA"
            , "NASA", "AMAZON","EBAY", "GOOGLE", "RBS"};



    private static ExecutorService executor = Executors.newFixedThreadPool(4);

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    TickPriceService tickPriceService;

    @Test
    public void testUpdateTickrPrice() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+"/tick/";
        URI uri = new URI(baseUrl);
        Tick tick = new Tick( System.currentTimeMillis(),"TCS",100.0);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Tick> request = new HttpEntity<>(tick, headers);
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assert.assertEquals(201, result.getStatusCodeValue());

    }

    @Test
    public void testUpdateTickrPriceForOldTime() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+"/tick/";
        URI uri = new URI(baseUrl);
        Instant instant=Instant.now().minus(800, ChronoUnit.SECONDS);
        Tick tick = new Tick( instant.getEpochSecond()*1000,"TCS",100.0);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Tick> request = new HttpEntity<>(tick, headers);
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assert.assertEquals(204, result.getStatusCodeValue());

    }



    @Test
    public void testGetAllStatistics() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+"/statistics/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<Statistics> result = this.restTemplate.getForEntity(uri,  Statistics.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());

    }


    @Test
    public void testStatisticsForOneTickr() throws URISyntaxException {
        testUpdateTickrPrice();
        HashMap<String, Object> uriVariables = new HashMap<>();

        uriVariables.put("instrumentId", "TCS");
        ResponseEntity<Statistics> getexchange = this.restTemplate.exchange(
                UriComponentsBuilder.fromUriString("/statistics/{instrumentId}").buildAndExpand(uriVariables).toUri()
                , HttpMethod.GET
                , SolactiveUtil.getHttpHeader(), Statistics.class);

        Matcher<HttpStatus> statusOk = Matchers.equalTo(HttpStatus.OK);
        // Matcher<HttpStatus> statusNoContent = Matchers.equalTo(HttpStatus.NO_CONTENT);
        Statistics stat = getexchange.getBody();
        Assert.assertEquals(200,getexchange.getStatusCode().value());


    }
}