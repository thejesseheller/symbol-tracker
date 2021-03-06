package com.thejessehelller.symboltracker.controller;

import com.thejessehelller.symboltracker.dao.DailyDataDAO;
import com.thejessehelller.symboltracker.dao.StockDAO;
import com.thejessehelller.symboltracker.model.Stock;
import com.thejessehelller.symboltracker.model.DailyData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StockController {

    private StockDAO stockDAO;
    private DailyDataDAO dailyDataDAO;

    public StockController(StockDAO stockDAO, DailyDataDAO dailyDataDAO) {
        this.stockDAO = stockDAO;
        this.dailyDataDAO = dailyDataDAO;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/add/{symbol}", method = RequestMethod.POST)
    public void addStock(@PathVariable String symbol, @RequestBody DailyData dailyData) {
        
        try {
            Stock stock = stockDAO.findBySymbol(symbol);
        } catch (Exception e) {
            boolean newStockSymbolAdded = stockDAO.add(symbol);
            if (newStockSymbolAdded) {
                int neededId = stockDAO.getIdBySymbol(symbol);
                dailyDataDAO.add(dailyData, neededId);
            }
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "stocks", method = RequestMethod.GET)
    public List<Stock> getAllTrackedStocks() {
        return stockDAO.getAllBeingTracked();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "stocks/{symbol}", method = RequestMethod.GET)
    public Stock getSingleStock(@PathVariable String symbol) {
        Stock stock = new Stock();
        try {
            stock = stockDAO.findBySymbol(symbol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stock;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "stocks/{symbol}/most-recent", method = RequestMethod.GET)
    public DailyData getMostRecent(@PathVariable String symbol) {
        return dailyDataDAO.getMostRecent(symbol);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/remove/{symbol}", method = RequestMethod.PUT)
    public boolean stopTrackingStock(@PathVariable String symbol) {
        Stock stock = stockDAO.findBySymbol(symbol);
        return stockDAO.remove(stock);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/stocks/{symbol}", method = RequestMethod.PUT)
    public boolean restartTracking(@PathVariable String symbol) {
        Stock stock = stockDAO.findBySymbol(symbol);
        return stockDAO.startTrackingAgain(stock);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "stocks/{symbol}/add-name", method = RequestMethod.PUT)
    public boolean addCompanyName(@PathVariable String symbol, @RequestBody Stock stock) {
        Stock toBeUpdated = stockDAO.findBySymbol(symbol);
        toBeUpdated.setCompanyName(stock.getCompanyName());
        toBeUpdated.setId(stockDAO.getIdBySymbol(symbol));
        return stockDAO.addName(toBeUpdated);
    }
}
