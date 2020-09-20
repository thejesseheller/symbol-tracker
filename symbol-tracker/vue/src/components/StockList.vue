<template>
  <div class="top">          
    <div class="symbol" v-for="stock in symbolList" :key="stock.id">
      <router-link :to="{ name: 'Solo', params: { symbol: stock.symbol } }">{{stock.symbol}}</router-link>
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
