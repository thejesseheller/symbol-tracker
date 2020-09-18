package com.thejessehelller.symboltracker.dao;

import com.thejessehelller.symboltracker.model.Stock;
import com.thejessehelller.symboltracker.model.DailyData;

import java.util.List;

public interface StockDAO {

    boolean add(String symbol);

    Stock findBySymbol(String symbol);

    boolean alreadyBeingTracked(String symbol);

    List<Stock> getAllBeingTracked();

    int getIdBySymbol(String symbol);

    boolean update(String symbol, DailyData ts);
}
