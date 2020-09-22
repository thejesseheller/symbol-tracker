<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="info">
              <h4 slot="title" class="card-title">Enter a ticker symbol below</h4>
              <!-- <p slot="description" class="description">Or don't idc</p> -->
              <md-field class="md-form-group has-danger" slot="inputs">
                <md-icon>sync</md-icon>
                <label>Symbol</label>
                <md-input v-model="symbol"></md-input>                
              </md-field>
              <md-button slot="footer" class="md-simple md-info md-lg" @click="getMostRecentlyDailyTimeSeriesData(symbol); classicModal = true">
                Go!
              </md-button>
            </login-card>
            <modal v-if="classicModal" @close="classicModalHide">
              <template slot="header">
                <h3 class="modal-title">{{ symbol }}</h3>                  
                <md-button
                 class="md-simple md-just-icon md-round modal-default-button"
                 @click="classicModalHide"
                >
                <md-icon>clear</md-icon>
                </md-button>
              </template>
                <template slot="body">
                  <h5>{{ lastRefreshed }}</h5>
                  <p>Open: {{mostRecentDailyData['1. open']}}</p>
                  <p>High: {{mostRecentDailyData['2. high']}}</p>
                  <p>Low: {{mostRecentDailyData['3. low']}}</p>
                  <p>Close: {{mostRecentDailyData['4. close']}}</p>
                  <p>Volume: {{mostRecentDailyData['5. volume']}}</p>                  
                </template>
                <template slot="footer">                  
                  <md-button class="md-info md-simple md-lg" @click="getSingleStock">Track</md-button>
                  <md-button
                    class="md-danger md-simple md-lg"
                    @click="classicModalHide"
                    >Close</md-button
                  >
                </template>
              </modal>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";
import { Modal } from "@/components";
import AlphaService from '@/services/AlphaService';
import DatabaseService from '@/services/DatabaseService';

export default {
  components: {
    LoginCard,
    Modal
  },
  bodyClass: "lookup-page",
  data() {
    return {
      symbol: '',      
      lastRefreshed: '',      
      mostRecentDailyData: {},      
      classicModal: false
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/abstract.jpg")
    }
  },
  methods: {
    classicModalHide() {
      this.classicModal = false;
    },
    getMostRecentlyDailyTimeSeriesData() {                  
      AlphaService.getMostRecentTimeSeries(this.symbol).then(response => {        
        this.lastRefreshed = response.data['Meta Data']['3. Last Refreshed'];        
        this.mostRecentDailyData = response.data['Time Series (Daily)'][this.lastRefreshed];                
      })
    },    
    trackStock() {
      DatabaseService.addSymbolAndDailyData(this.symbol, this.lastRefreshed, this.mostRecentDailyData)          
    },
    restartTracking(symbol) {
      DatabaseService.restartTracking(symbol);
    },
    getSingleStock() {
      let stock = '';
      DatabaseService.getSingleStock(this.symbol).then(response => {
        stock = response.data;
        if (stock.symbol !== null) {
          this.restartTracking(stock.symbol);
        } else {
          this.trackStock();
        }
      })
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

<style lang="css" scoped>

p, .modal-title, h5 {
  color: black;
}

.wrapper {
  height: 100vh;
}

</style>
