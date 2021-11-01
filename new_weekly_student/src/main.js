import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Vuex from 'vuex'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/css/main.css'
import store from './store/index'
Vue.config.productionTip = false

Vue.use(Vuex)
Vue.use(ElementUI)

new Vue({
  render: h => h(App),
  store: new Vuex.Store(store),
  router:router,

}).$mount('#app')
