<template>
  <div class="symbol">
    <h1>{{ msg }}</h1>
    <input type="text" v-model="symbol" placeholder="ENTER SYMBOL HERE"/>
    <button @click="addSymbol">Add New Symbol</button>
    <button @click="getMostRecentlyDailyTimeSeriesData">Update Daily Time Series</button>     
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
      mostRecentDailyData: {}
    }
  },
  methods: {
    addSymbol() {
      DatabaseService.addSymbol(this.symbol);
    }, 
    getMostRecentlyDailyTimeSeriesData() {                  
      AlphaService.getMostRecentTimeSeries(this.symbol).then(response => {        
        // let testSymbol = response.data['Meta Data']['2. Symbol'];
        let lastRefreshed = response.data['Meta Data']['3. Last Refreshed'];        
        this.mostRecentDailyData = response.data['Time Series (Daily)'][lastRefreshed];        
      })
    }
  }
}
</script>

<style>

</style>