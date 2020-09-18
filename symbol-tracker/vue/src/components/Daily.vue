<template>
  <div class="daily">
    <div class="all-stock-info">      
      <div class="most-recent-time-series" v-for="stock in mostRecentTimeSeries" :key="stock.id">
        <h2 class="stock-symbol">{{stock.symbol}}</h2>
        <div class="previous-close-info">
          <p>Previous Open: {{stock.previousOpen}}</p>
          <p>Previous High: {{stock.previousHigh}}</p>
          <p>Previous Low: {{stock.previousLow}}</p>
          <p>Previous Close: {{stock.previousClose}}</p>
          <p>Previous Volume: {{stock.previousVolume}}</p>
        </div>
        <button @click="getIntradayInfo(stock.symbol)">Show Intraday Activity</button>
        <button v-on:click="isHidden = !isHidden">Hide / Show</button>
        <div class="intraday-info" v-if="!isHidden">
          <p>Price at Start of Hour: {{intraDayInformation['1. open']}}</p>
          <p>Highest Price of Past Hour: {{intraDayInformation['2. high']}}</p>
          <p>Lowest Price of Past Hour: {{intraDayInformation['3. low']}}</p>
          <p>Price at End of Hour: {{intraDayInformation['4. close']}}</p>
          <p>Volume: {{intraDayInformation['5. volume']}}</p>        
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import DatabaseService from '@/services/DatabaseService';
import AlphaService from '@/services/AlphaService';

export default {
  name: 'Daily',  
  data() {
    return {
      mostRecentTimeSeries: [],
      intraDayInformation: {},
      isHidden: true
    }
  },
  methods: {
    getAllTracked() {
      DatabaseService.getAllStocks().then(response => {
        this.mostRecentTimeSeries = response.data;
      });
    },
    getIntradayInfo(symbol) {      
      AlphaService.getIntradayTimeSeries(symbol).then(response => {
        this.intraDayInformation = response.data['Time Series (60min)'][`2020-09-08 20:00:00`];                 
      })
    }
  },
  created() {
    this.getAllTracked();
  }
}
</script>

<style>

</style>