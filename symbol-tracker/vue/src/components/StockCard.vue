<template>
  <div class="top">
    <h2>{{ symbol }}</h2>
    <div class="data-from-last-refresh">      
      <h3>{{ historicData['date'] }}</h3>
      <p>Open: {{ historicData['open'] }}</p>   
      <p>High: {{ historicData['high'] }}</p>
      <p>Low: {{ historicData['low'] }}</p>
      <p>Close: {{ historicData['close'] }}</p> 
      <p>Volume: {{ historicData['volume'] }}</p>
    </div>
    <div class="intraday-data">
      <h3>As of {{ lastRefreshed }}</h3>
      <p>Open: {{ intradayData['1. open'] }}</p>
      <p>High: {{ intradayData['2. high'] }}</p>
      <p>Low: {{ intradayData['3. low'] }}</p>
      <p>Close: {{ intradayData['4. close'] }}</p>
      <p>Volume: {{ intradayData['5. volume'] }}</p>
    </div>           
  </div>
</template>

<script>
import DatabaseService from '@/services/DatabaseService';
import AlphaService from '@/services/AlphaService';

export default {
  name: 'StockCard',
  data() {
    return {
     symbol: '',
     lastRefreshed: '',
     historicData: {},
     intradayData: {}
    }
  },
  methods: {
    getDailyData() {
      DatabaseService.getMostRecentDailyData(this.symbol).then(response => {
        this.historicData = response.data;
      });
    },
    getIntradayData() {
      AlphaService.getIntradayTimeSeries(this.symbol).then(response => {
        this.lastRefreshed = response.data['Meta Data']['3. Last Refreshed'];
        this.intradayData = response.data['Time Series (60min)'][this.lastRefreshed];
      });
    }
  },
  created() {
    this.symbol = this.$route.params.symbol;
    this.getDailyData();
    this.getIntradayData();
  }
}
</script>

<style>

</style>