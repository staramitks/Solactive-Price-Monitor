package com.solactive.price.tracker.service;

import com.solactive.price.tracker.model.Statistics;

import java.util.Optional;

public interface StatisticsService {

    public Optional<Statistics> getAllStatistics();
    public Optional<Statistics> getStatisticsForTick(String instrument);
}
