<template>
  <div class="top">
    <h1 id="stock-card-heading">{{ symbol }}</h1>
    <div class="data-from-last-refresh">      
      <h3>{{ historicData['date'] }}</h3>
      <p>Open: {{ historicData['open'] }}</p>   
      <p>High: {{ historicData['high'] }}</p>
      <p>Low: {{ historicData['low'] }}</p>
      <p>Close: {{ historicData['close'] }}</p> 
      <p>Volume: {{ historicData['volume'] }}</p>
    </div>
    <div class="intraday-data">
      <h3>As of {{ lastRefreshed }}</h3>
      <p>Open: {{ intradayData['1. open'] }}</p>
      <p>High: {{ intradayData['2. high'] }}</p>
      <p>Low: {{ intradayData['3. low'] }}</p>
      <p>Close: {{ intradayData['4. close'] }}</p>
      <p>Volume: {{ intradayData['5. volume'] }}</p>
    </div>    
    <news-card />           
  </div>
</template>

<script>
import DatabaseService from '@/services/DatabaseService';
import AlphaService from '@/services/AlphaService';
import NewsCard from '@/components/NewsCard.vue'; 

export default {
  name: 'stock-card',
  components: {
    NewsCard
  },
  data() {
    return {
     symbol: '',
     lastRefreshed: '',
     historicData: {},
     intradayData: {}
    }
  },
  methods: {
    getDailyData() {
      DatabaseService.getMostRecentDailyData(this.symbol).then(response => {
        this.historicData = response.data;
      });
    },
    getIntradayData() {
      AlphaService.getIntradayTimeSeries(this.symbol).then(response => {
        this.lastRefreshed = response.data['Meta Data']['3. Last Refreshed'];
        this.intradayData = response.data['Time Series (60min)'][this.lastRefreshed];
      });
    }    
  },
  created() {
    this.symbol = this.$route.params.symbol;
    this.getDailyData();
    this.getIntradayData();    
  }
}
</script>

<style scoped>

.top {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
  "heading heading"
  "historic intra"
  "news news";
}

#stock-card-heading {
  grid-area: heading;
}

.data-from-last-refresh {
  grid-area: historic;
}

.intraday-data {
  grid-area: intra;
}

news-card {
  grid-area: news;
}

</style>