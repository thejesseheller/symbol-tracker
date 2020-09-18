package com.thejessehelller.symboltracker.dao;

import com.thejessehelller.symboltracker.model.Stock;
import com.thejessehelller.symboltracker.model.TimeSeries;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockSqlDAO implements StockDAO {

    private JdbcTemplate jdbcTemplate;

    public StockSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(String symbol) {
        boolean symbolCreated = false;

        if (!alreadyBeingTracked(symbol)) {

        String addSymbol = "INSERT INTO stocks (symbol) VALUES(?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "stock_id";
        symbolCreated = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(addSymbol, new String[]{id_column});
            ps.setString(1, symbol);
            return ps;

        }, keyHolder) == 1;

        }

        return symbolCreated;
    }

    @Override
    public Stock findBySymbol(String symbol) {
        return jdbcTemplate.queryForObject("SELECT * FROM stocks WHERE symbol = ?", Stock.class, symbol);
    }

    @Override
    public boolean alreadyBeingTracked(String symbol) {
        String sql = "SELECT COUNT(*) FROM stocks WHERE symbol = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[] {symbol}, Integer.class);
        return count > 0;
    }

    @Override
    public List<Stock> getAllBeingTracked() {
        List<Stock> tracked = new ArrayList<>();
        String sql = "SELECT * FROM stocks";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            Stock s = mapRowToStock(results);
            tracked.add(s);
        }
        return tracked;
    }

    @Override
    public int getIdBySymbol(String symbol) {
        return jdbcTemplate.queryForObject("SELECT stock_id FROM stocks WHERE symbol = ?", Integer.class, symbol);
    }

    @Override
    public boolean update(String symbol, TimeSeries ts) {
        int stockId = getIdBySymbol(symbol);
        String sql = "UPDATE stocks SET open = ?, high = ?, low = ?, close = ?, volume = ? WHERE stock_id = ?";
        return jdbcTemplate.update(sql, ts.getOpen(), ts.getHigh(), ts.getLow(), ts.getClose(), ts.getVolume(), stockId) == 1;
    }

    private Stock mapRowToStock(SqlRowSet rs){
        Stock stock = new Stock();
        stock.setId(rs.getInt("stock_id"));
        stock.setSymbol(rs.getString("symbol"));
        stock.setPreviousOpen(rs.getString("open"));
        stock.setPreviousHigh(rs.getString("high"));
        stock.setPreviousLow(rs.getString("low"));
        stock.setPreviousClose(rs.getString("close"));
        stock.setPreviousVolume(rs.getString("volume"));
        return stock;
    }
}
