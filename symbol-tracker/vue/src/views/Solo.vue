<template>
  <div class="solo">
    <stock-card />    
  </div>
</template>

<script>

import StockCard from '@/components/StockCard.vue'

export default {
  name: 'Solo',
  components: {
    StockCard
  }
}
</script>

<style scoped>

.solo {
  /* background-color: #1A1A1D; */
  background-color: #1B1B1D;  
  min-height: 100vh;
  min-width: 100vw;
  color: white;
}

</style>