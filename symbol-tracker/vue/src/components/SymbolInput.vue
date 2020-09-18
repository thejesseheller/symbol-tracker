<template>
  <div class="symbol">
    <h1>{{ msg }}</h1>
    <input type="text" v-model="symbol" placeholder="ENTER SYMBOL HERE"/>
    <!-- <button @click="saveSymbol">Add New Symbol</button> -->
    <button @click="getMostRecentlyDailyTimeSeriesData">Display Most Recent Prices and Volume</button>
      <div class="most-recent-daily-data">
        <h2>{{ symbol }}</h2>
        <h3>{{ lastRefreshed }}</h3>
        <p>Open: {{mostRecentDailyData['1. open']}}</p>
        <p>High: {{mostRecentDailyData['2. high']}}</p>
        <p>Low: {{mostRecentDailyData['3. low']}}</p>
        <p>Close: {{mostRecentDailyData['4. close']}}</p>
        <p>Volume: {{mostRecentDailyData['5. volume']}}</p>
        <button>Track this Stock</button>
      </div>     
     
  </div>
</template>

<script>
import DatabaseService from '@/services/DatabaseService';
import AlphaService from '@/services/AlphaService';


export default {
  name: 'SymbolInput',  
  props: {
    msg: String
  },
  data() {
    return {
      symbol: '',
      lastRefreshed: '',
      mostRecentDailyData: {}
    }
  },
  methods: {
    trackStock() {
      DatabaseService.addSymbol(this.symbol);
      // this.$store.commit('SAVE_SYMBOL', this.symbol);
      // state resets every time the page is refreshed
      // this.testArr = this.$store.state.trackedSymbols;
      // alert(this.testArr.length)      
    }, 
    getMostRecentlyDailyTimeSeriesData() {                  
      AlphaService.getMostRecentTimeSeries(this.symbol).then(response => {        
        // let testSymbol = response.data['Meta Data']['2. Symbol'];
        this.lastRefreshed = response.data['Meta Data']['3. Last Refreshed'];        
        this.mostRecentDailyData = response.data['Time Series (Daily)'][this.lastRefreshed];        
      })
    }
  }
}
</script>

<style>

</style>