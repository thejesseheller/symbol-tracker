<template>
  <div class="add">
    <SymbolInput msg="Enter a ticker symbol to get started"/>    
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
