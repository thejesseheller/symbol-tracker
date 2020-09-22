<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-50 mx-auto">
              <div class="profile">
                <div class="avatar">
                  <img
                    :src="img"
                    alt="Circle Image"
                    class="img-raised rounded-circle img-fluid"
                  />
                </div>
                <div class="name">
                  <h3 class="title">Watchlist</h3>                  
                </div>
              </div>
            </div>
          </div>
          <div class="description text-center" v-for="stock in symbolList" :key="stock.id">
            <!-- <v-popover offset="8" placement="left">
              <md-button class="md-info" @click="setSymbol(stock.symbol)">{{stock.symbol}}</md-button>
              <template slot="popover">
              <h3 class="popover-header">{{stock.symbol}}</h3>
              <div class="popover-body">
                <md-button class="md-info md-sm" v-bind:href="'#/watchlist/' + stock.symbol">Go to Page</md-button>
                <md-button class="md-danger md-sm" @click="stopTracking">Unfollow</md-button>                  
              </div>
              </template>
            </v-popover> -->
            <regular-card class="md-card">
              <h4 slot="title" class="card-title">{{stock.symbol}}</h4>
              <p slot="info">{{ name }}</p>
              <md-button slot="button1" class="md-simple md-info md-sm" v-bind:href="'#/watchlist/' + stock.symbol">Go to Page</md-button>
              <md-button slot="button2" class="md-simple md-danger md-sm" @click="stopTracking(stock.symbol)">Unfollow</md-button>  
            </regular-card>
          </div>
          <div class="profile-tabs">            
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import DatabaseService from '@/services/DatabaseService';
export default {
  bodyClass: "profile-page",
  data() {
    return {
      symbolList: [],
      name: ''
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/bull.jpg")
    },
    img: {
      type: String,
      default: require("@/assets/img/wsb.jpg")
    }
  },
  methods: {
    getAllTracked() {
      DatabaseService.getAllStocks().then(response => {
        this.symbolList = response.data;        
      });
    },
    stopTracking(symbol) {
      DatabaseService.stopTrackingStock(symbol)
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  },
  created() {
      this.getAllTracked();
  }
};
</script>

<style lang="scss" scoped>

  .md-card {
    width: 320px;
    margin: 4px;
    display: inline-block;
    vertical-align: top;
  }

  .section {
  padding: 0;
  }

  .profile-tabs::v-deep {
  .md-card-tabs .md-list {
    justify-content: center;
  }

  [class*="tab-pane-"] {
    margin-top: 3.213rem;
    padding-bottom: 50px;

    img {
      margin-bottom: 2.142rem;
    }
  }
}
</style>
