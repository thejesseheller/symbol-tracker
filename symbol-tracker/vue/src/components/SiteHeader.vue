<template>
  <nav class="navbar">
    <div class="navbar-left">
      <span class="nav-name-left">S</span>
      <span class="nav-name-right">W</span>
    </div>
    <div class="navbar-right">      
      <router-link class="nav-link" to="/"><span class="home-link">Home</span></router-link>
      <router-link class="nav-link" to="/add"><span class="lookup-link">Lookup</span></router-link>
      <router-link class="nav-link" to="/tracked"><span class="watchlist-link">Watchlist</span></router-link>  
    </div>
  </nav>
</template>

<script>
export default {
  name: 'site-header'
}
</script>

<style scoped>

.navbar {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  font-size: 2rem;       
}

.nav-name-right {
  color: #4682B4;  
}

.navbar-left {
  padding-left: 1.5rem;
}

.navbar-right {
  display: flex;
  justify-content: flex-end;  
  padding-right: 1.5rem;
  flex-wrap: nowrap;      
}

.home-link, .lookup-link, .watchlist-link {
  padding-right: 15px;
}

.nav-link {
  text-decoration: none;
  opacity: 0.8;
  transition: 0.3s;
}

.nav-link:hover {
  opacity: 1; 
}

a {
  font-weight: bold;
  color: #2c3e50;
}

a.router-link-exact-active {
  color: #4682B4;  
}

</style>