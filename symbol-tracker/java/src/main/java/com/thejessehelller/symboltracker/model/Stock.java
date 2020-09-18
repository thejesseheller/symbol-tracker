package com.thejessehelller.symboltracker.model;

public class Stock {
    private int id;
    private String symbol;
    private String previousOpen;
    private String previousHigh;
    private String previousLow;
    private String previousClose;
    private String previousVolume;

    public Stock() {
    }

    public Stock(String symbol) {
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPreviousOpen() {
        return previousOpen;
    }

    public void setPreviousOpen(String previousOpen) {
        this.previousOpen = previousOpen;
    }

    public String getPreviousHigh() {
        return previousHigh;
    }

    public void setPreviousHigh(String previousHigh) {
        this.previousHigh = previousHigh;
    }

    public String getPreviousLow() {
        return previousLow;
    }

    public void setPreviousLow(String previousLow) {
        this.previousLow = previousLow;
    }

    public String getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(String previousClose) {
        this.previousClose = previousClose;
    }

    public String getPreviousVolume() {
        return previousVolume;
    }

    public void setPreviousVolume(String previousVolume) {
        this.previousVolume = previousVolume;
    }
}
