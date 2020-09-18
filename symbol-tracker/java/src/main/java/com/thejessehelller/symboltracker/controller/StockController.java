package com.thejessehelller.symboltracker.controller;

import com.thejessehelller.symboltracker.dao.StockDAO;
import com.thejessehelller.symboltracker.model.Stock;
import com.thejessehelller.symboltracker.model.StockAlreadyExistsException;
import com.thejessehelller.symboltracker.model.TimeSeries;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StockController {

    private StockDAO stockDAO;

    public StockController(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/add/{symbol}", method = RequestMethod.POST)
    public void addStock(@PathVariable String symbol) {
        try {
            Stock stock = stockDAO.findBySymbol(symbol);
            throw new StockAlreadyExistsException();
        } catch (Exception e) {
            stockDAO.add(symbol);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "stocks", method = RequestMethod.GET)
    public List<Stock> getAllTrackedStocks() {
        System.out.println("Working");
        return stockDAO.getAllBeingTracked();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "stocks/{symbol}/update", method = RequestMethod.PUT)
    public void updateStock(@RequestBody TimeSeries timeSeries, @PathVariable String symbol) {
        stockDAO.update(symbol, timeSeries);
    }
}
