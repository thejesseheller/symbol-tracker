<template>
  <div class="home">    
    <div class="content">
      <h1 id="home-heading">Symbol Watcher</h1>
    </div>   
  </div>
</template>

<script>
// @ is an alias to /src
export default {
  name: 'Home'
}
</script>

<style>

.home {
  min-height: 100vh;
  min-width: 98vw;
  display: flex;  
  align-items: center;
  justify-content: center;   
  background-image: url(../assets/background.jpg);
  background-repeat:no-repeat;
  background-size: cover;
}

#home-heading {      
  font-size: 5rem;
  text-transform: uppercase;
  color: white;
  text-shadow:
  -1px -1px 0 #000,
  1px -1px 0 #000,
  -1px 1px 0 #000,
  1px 1px 0 #000;
}

</style>
