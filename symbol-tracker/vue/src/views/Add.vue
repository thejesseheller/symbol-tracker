<template>
  <div class="add">
    <SymbolInput msg="Enter ticker symbol"/>    
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
