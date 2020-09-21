<template>
  <div class="add">
    <symbol-input msg="Enter a ticker symbol to get started"/>    
  </div>
</template>

<script>

import SymbolInput from '@/components/SymbolInput.vue'

export default {
  name: 'Add',
  components: {
    SymbolInput
  }
  
}
</script>

<style>

.add {
  /* background-color: #1A1A1D; */
  background-color: #1B1B1D;  
  min-height: 100vh;
  min-width: 100vw;
}

</style>
