<template>
  <div class="home">    
    <HelloWorld msg="Welcome to Symbol Tracker"/>
    <img src="https://pbs.twimg.com/profile_images/1094823834451103744/BCHmufYd_400x400.jpg" alt="you know who it is">
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Home',
  components: {
    HelloWorld
  }
}
</script>
