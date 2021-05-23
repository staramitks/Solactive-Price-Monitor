package com.solactive.price.tracker.service.impl;

import com.solactive.price.tracker.model.Tick;
import com.solactive.price.tracker.dto.TickResponse;

import com.solactive.price.tracker.service.TickPriceService;
import com.solactive.price.tracker.util.SolactiveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


@Service("tickPriceService")
public class TickPriceServiceImpl implements TickPriceService {


    private Map<String, List<Tick>> tickPriceMultiMap = new ConcurrentHashMap<String, List<Tick>>();

    @Autowired
    private StatisticsServiceImpl statService;


    public TickResponse save(Tick tick) {

        tickPriceMultiMap.computeIfAbsent(tick.getInstrument(), k -> new CopyOnWriteArrayList<Tick>()).add(tick);

        //update statistics
        refreshStatistics();
        return new TickResponse().setStatusCode(HttpStatus.CREATED);
    }

    @Scheduled(fixedRate = 1 * 1000)
    public void refreshStatistics() {
        clearStaleData();
        statService.updateStatistics(tickPriceMultiMap);
    }

    private void clearStaleData() {

        Iterator<List<Tick>> iterator = tickPriceMultiMap.values().iterator();
        while(iterator.hasNext()) {
            iterator.next().removeIf(x-> !SolactiveUtil.isValidPriceRecord(x.getTimestamp()));
        }

        // remove entry if x is null
        tickPriceMultiMap.values().removeIf(x-> x == null || x.isEmpty()) ;
        System.out.println("After Size is "+ tickPriceMultiMap.keySet().size());
    }

}
