package com.thejessehelller.symboltracker.dao;

import com.thejessehelller.symboltracker.model.DailyData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;

@Service
public class DailyDataSqlDAO implements DailyDataDAO {

    private JdbcTemplate jdbcTemplate;

    public DailyDataSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(DailyData data, int stockId) {

        boolean dataAdded = false;

        String addData = "INSERT INTO daily_data (date, open, high, low, close, volume, stock_id) VALUES(?, ?, ?, ?, ?, ?, ?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "daily_data_id";
        dataAdded = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(addData, new String[]{id_column});
            ps.setString(1, data.getDate());
            ps.setString(2, data.getOpen());
            ps.setString(3, data.getHigh());
            ps.setString(4, data.getLow());
            ps.setString(5, data.getClose());
            ps.setString(6, data.getVolume());
            ps.setInt(7, stockId);
            return ps;

        }, keyHolder) == 1;


        return dataAdded;
    }

    @Override
    public DailyData getMostRecent(String symbol) {
        DailyData dd = new DailyData();
        String sql = "SELECT date, open, high, low, close, volume FROM daily_data " +
                     "INNER JOIN stocks ON daily_data.stock_id = stocks.stock_id " +
                     "WHERE symbol = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, symbol);

        if (results.next()){
            dd = mapRowToDailyData(results);
        }

        return dd;
    }

    private DailyData mapRowToDailyData(SqlRowSet rs){
        DailyData dd = new DailyData();
        dd.setDate(rs.getString("date"));
        dd.setOpen(rs.getString("open"));
        dd.setHigh(rs.getString("high"));
        dd.setLow(rs.getString("low"));
        dd.setClose(rs.getString("close"));
        dd.setVolume(rs.getString("volume"));
        return dd;
    }

}
