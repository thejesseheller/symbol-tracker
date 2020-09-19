<template>
  <div class="daily">
    <div class="all-stock-info">      
      <div class="most-recent-time-series" v-for="stock in symbolList" :key="stock.id">
        <!-- <h2 class="stock-symbol">{{stock.symbol}}</h2> -->
        <router-link :to="{ name: 'Solo', params: { symbol: stock.symbol } }">{{stock.symbol}}</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import DatabaseService from '@/services/DatabaseService';

export default {
  name: 'StockList',  
  data() {
    return {
      symbolList: []
    }
  },
  methods: {
    getAllTracked() {
      DatabaseService.getAllStocks().then(response => {
        this.symbolList = response.data;        
      });
    }
  },
  created() {
      this.getAllTracked();
  }
} 

</script>

<style>

</style>
