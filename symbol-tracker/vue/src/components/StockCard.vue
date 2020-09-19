<template>
  <div class="top">
    <h2>{{ symbol }}</h2>
    <h3>{{ historicData['date'] }}</h3>
    <p>Open: {{ historicData['open'] }}</p>   
    <p>High: {{ historicData['high'] }}</p>
    <p>Low: {{ historicData['low'] }}</p>
    <p>Close: {{ historicData['close'] }}</p> 
    <p>Volume: {{ historicData['volume'] }}</p>           
  </div>
</template>

<script>
import DatabaseService from '@/services/DatabaseService';

export default {
  name: 'StockCard',
  data() {
    return {
     symbol: '',
     historicData: {}
    }
  },
  methods: {
    getDailyData() {
      DatabaseService.getMostRecentDailyData(this.symbol).then(response => {
        this.historicData = response.data;
      });
    }
  },
  created() {
    this.symbol = this.$route.params.symbol;
    this.getDailyData();
  }
}
</script>

<style>

</style>