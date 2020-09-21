<template>
  <div class="top">          
    <div class="symbol" v-for="stock in symbolList" :key="stock.id">
      <router-link class="stock-link" :to="{ name: 'Solo', params: { symbol: stock.symbol } }">{{stock.symbol}}</router-link>
    </div>    
  </div>
</template>

<script>
import DatabaseService from '@/services/DatabaseService';

export default {
  name: 'stock-list',  
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

<style scoped>

a {
  color: white;
}

.stock-link {
  text-decoration: none;
  opacity: 0.8;
  transition: 0.3s;
  font-size: 1.8rem;
}

.stock-link:hover {
  opacity: 1; 
}

</style>
