package com.thejessehelller.symboltracker.dao;

import com.thejessehelller.symboltracker.model.DailyData;

public interface DailyDataDAO {

    boolean add(DailyData data, int stockId);
}
