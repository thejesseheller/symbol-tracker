<template>
  <div class="top">
    <h3>News</h3>
    <div class="news-stories" v-for="story in stories" :key="story.id">
      <p>{{ story.source.name }}</p>
      <a v-bind:href="story.url" target="_blank">{{ story.title }}</a>            
    </div>    
  </div>
</template>

<script>
import NewsService from '@/services/NewsService';

export default {
  name: 'news-card',  
  data() {
    return {
      symbol: '',
      stories: []
    }
  },
  methods: {
    getNews() {
      NewsService.getNews(this.symbol).then(response => {
        this.stories = response.data["articles"];
      })
    }
  },
  created() {
    this.symbol = this.$route.params.symbol;
    this.getNews();
  }

}
</script>

<style>

</style>