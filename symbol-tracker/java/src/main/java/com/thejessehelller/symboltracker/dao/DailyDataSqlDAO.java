package com.thejessehelller.symboltracker.dao;

import com.thejessehelller.symboltracker.model.DailyData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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

}
