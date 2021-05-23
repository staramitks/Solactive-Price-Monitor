package com.solactive.price.tracker.service.impl;

import com.solactive.price.tracker.model.Statistics;
import com.solactive.price.tracker.model.Tick;
import com.solactive.price.tracker.service.StatisticsService;
import com.solactive.price.tracker.service.TickPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {



    private static final ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    private Statistics latestStatistics;

    private Map<String, Statistics> tickStatisticsMap = new ConcurrentHashMap<String, Statistics>();


    @Override
    public Optional<Statistics> getAllStatistics() {
       rwlock.readLock().lock();
        try{
            return latestStatistics == null || latestStatistics.getCount() == 0 ? Optional.empty() : Optional.of(latestStatistics);
        }finally {
            rwlock.readLock().unlock();
        }
    }

    @Override
    public Optional<Statistics> getStatisticsForTick(String instrument) {
        return Optional.ofNullable(tickStatisticsMap.getOrDefault(instrument, null));
    }

    @Async(value = "statisticsTaskExecutor")
    public void updateStatistics(Map<String, List<Tick>> tickPriceMultiMap) {

        if(tickPriceMultiMap.isEmpty()) {
            tickStatisticsMap.clear();
        }

        Statistics computedStat = Statistics.createStatistics(tickPriceMultiMap.values().stream().flatMap(x->x.stream()));

        rwlock.writeLock().lock();
        try {
            latestStatistics = computedStat;
            tickStatisticsMap.entrySet().removeIf(x-> !tickPriceMultiMap.keySet().contains(x.getKey()));
        }finally {
            rwlock.writeLock().unlock();
        }
        for(Map.Entry<String, List<Tick>> entry: tickPriceMultiMap.entrySet()) {
            tickStatisticsMap.put(entry.getKey(), Statistics.createStatistics(entry.getValue().stream()));
        }

    }

}
