import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Add from '../views/Add.vue'
import Tracked from '../views/Tracked.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/add',
    name: 'Add',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: Add
  },
  {
    path: '/tracked',
    name: 'Tracked',
    component: Tracked
  },
  {
    path: '/tracked/:symbol',
    name: 'Solo',
    component: () => import(/* webpackChunkName: "about" */ '../views/Solo.vue') 
  }
]

const router = new VueRouter({
  routes,
  mode: 'history'
})

export default router
