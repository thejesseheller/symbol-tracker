<template>
  <div class="tracked">
    <HelloWorld msg="Currently tracked stocks"/>
    <Daily/>
  </div>
</template>

<script>

import HelloWorld from '@/components/HelloWorld.vue'
import Daily from '@/components/Daily.vue'

export default {
  name: 'Tracked',
  components: {
    HelloWorld,
    Daily
  }
}
</script>

<style>

</style>