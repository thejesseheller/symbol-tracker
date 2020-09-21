<template>
  <div id="app">
    <site-header/>    
    <router-view/>    
  </div>
</template>

<script>
import SiteHeader from '@/components/SiteHeader.vue'

export default {
  name: 'app',
  components: {
    SiteHeader
  }  
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Montserrat&display=swap');

#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  font-family: 'Montserrat', sans-serif;
  text-align: center;
  color: #2c3e50;
}

</style>
