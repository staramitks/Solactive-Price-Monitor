package com.solactive.price.tracker.controller;
import com.solactive.price.tracker.model.Statistics;
import com.solactive.price.tracker.service.impl.StatisticsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class StatisticsController {

    @Autowired
    private StatisticsServiceImpl statisticsService;

    @GetMapping(value="/statistics")
    public ResponseEntity<Statistics> getAllStats(){
        Optional<Statistics> oStats=statisticsService.getAllStatistics();
        Statistics response=oStats.isPresent()?oStats.get():new Statistics();
        return getStatisticsResponseEntity(response);

    }

    @GetMapping(value="/statistics/{instrumentId}")
    public ResponseEntity<Statistics> getStatsForInstrument(@PathVariable String instrumentId){
        Optional<Statistics> oStats=statisticsService.getStatisticsForTick(instrumentId);
        Statistics response=oStats.isPresent()?oStats.get():new Statistics();
        return getStatisticsResponseEntity(response);


    }

    private ResponseEntity<Statistics> getStatisticsResponseEntity(Statistics response) {
        if (response.getCount() > 0) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Statistics(), HttpStatus.NOT_FOUND);
    }

}
