<template>
  <div class="tracked">
    <MainMessage msg="Currently tracked stocks"/>
    <StockList/>
  </div>
</template>

<script>

import MainMessage from '@/components/MainMessage.vue'
import StockList from '@/components/StockList.vue'

export default {
  name: 'Tracked',
  components: {
    MainMessage,
    StockList
  }
}
</script>

<style>

</style>