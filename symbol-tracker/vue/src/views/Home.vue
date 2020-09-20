<template>
  <div class="home">    
    <MainMessage msg="Symbol Watch"/>
    <img src="https://pbs.twimg.com/profile_images/1094823834451103744/BCHmufYd_400x400.jpg" alt="you know who it is">
  </div>
</template>

<script>
// @ is an alias to /src
import MainMessage from '@/components/MainMessage.vue'

export default {
  name: 'Home',
  components: {
    MainMessage
  }
}
</script>
