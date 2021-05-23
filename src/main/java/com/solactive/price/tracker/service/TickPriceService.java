package com.solactive.price.tracker.service;

import com.solactive.price.tracker.model.Tick;
import com.solactive.price.tracker.dto.TickResponse;

public interface TickPriceService {

    public TickResponse save(Tick request);
    public void refreshStatistics();
}
