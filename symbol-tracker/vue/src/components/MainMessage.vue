<template>
  <div class="top">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
export default {
  name: 'MainMessage',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@700&display=swap');

h1 {
  font-family: 'IBM Plex Sans', sans-serif;
}

h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
