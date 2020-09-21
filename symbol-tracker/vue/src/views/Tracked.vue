<template>
  <div class="tracked">
    <h2 id="tracked-heading">Your watchlist</h2>
    <stock-list/>
  </div>
</template>

<script>
import StockList from '@/components/StockList.vue'

export default {
  name: 'Tracked',
  components: {    
    StockList
  }
}
</script>

<style scoped>

.tracked {
  /* background-color: #1A1A1D; */
  background-color: #1B1B1D;  
  min-height: 100vh;
  min-width: 100vw;
  color: white;
}

#tracked-heading {
  font-size: 3rem;
}



</style>