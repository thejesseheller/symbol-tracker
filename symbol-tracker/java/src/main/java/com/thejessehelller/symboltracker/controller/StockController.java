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
        // this needs to be rewritten because it's bad
        try {
            Stock stock = stockDAO.findBySymbol(symbol);
        } catch (Exception e) {
            boolean testBool = stockDAO.add(symbol);
            if (testBool) {
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
//
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @RequestMapping(path = "stocks/{symbol}/update", method = RequestMethod.PUT)
//    public void updateStock(@RequestBody DailyData dailyData, @PathVariable String symbol) {
//        stockDAO.update(symbol, dailyData);
//    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "stocks/{symbol}/most-recent", method = RequestMethod.GET)
    public DailyData getMostRecent(@PathVariable String symbol) {
        return dailyDataDAO.getMostRecent(symbol);
    }
}
