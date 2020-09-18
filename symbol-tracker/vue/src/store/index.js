import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    trackedSymbols: []
  },
  mutations: {
    SAVE_SYMBOL(state, symbol) {
      state.trackedSymbols.push(symbol);
    }
  },
  actions: {
  },
  modules: {
  }
})
