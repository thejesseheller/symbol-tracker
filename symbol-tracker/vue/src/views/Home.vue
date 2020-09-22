<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100"
          >
            <h1 class="title">symbol.watcher</h1>
            <h4>
              flavor text
            </h4>
            <br />
            <md-button
              href="http://localhost:8081/#/lookup"
              class="md-info md-regular"
              target="_blank"
              ><i class="fas fa-play"></i> Get Started</md-button
            >
          </div>
        </div>
      </div>
    </parallax>    
  </div>
</template>

<script>
export default {
  bodyClass: "home-page",
  props: {
    header: {
      type: String,
      default: require("@/assets/img/lando.jpg")
    }
  },  
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  }
};
</script>
