package com.solactive.price.tracker.controller;

import com.solactive.price.tracker.model.Tick;
import com.solactive.price.tracker.dto.TickResponse;
import com.solactive.price.tracker.service.TickPriceService;
import com.solactive.price.tracker.util.SolactiveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TickController {

    @Autowired
    TickPriceService tickPriceService;

    @PostMapping(value="/tick")
    public ResponseEntity<String> updateTickrPrice(@RequestBody Tick tick){

        // Check if request is bad
        if (SolactiveUtil.isBadRequest(tick))
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // if price is within last 60 seconds and not after current timestamp then only save the record
        if(SolactiveUtil.isValidPriceRecord(tick.getTimestamp())) {
            TickResponse tickResponse = tickPriceService.save(tick);
            return new ResponseEntity<>(tickResponse.getStatusCode());

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(value="/")
    public String home(){
        return "Hello World";
    }



}



