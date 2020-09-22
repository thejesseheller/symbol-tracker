package com.thejessehelller.symboltracker.dao;

import com.thejessehelller.symboltracker.model.Stock;

import java.util.List;

public interface StockDAO {

    boolean add(String symbol);

    Stock findBySymbol(String symbol);

    List<Stock> getAllBeingTracked();

    int getIdBySymbol(String symbol);

    boolean startTrackingAgain(Stock stock);

    boolean remove(Stock stock);

    boolean addName(Stock stock);
}
