import mixins from '../utils/helper'
import EditWareHousePage from '../components/EditWareHousePage.vue'

// sync(store, router)

// Object.keys(filters).forEach(key => {
//     Vue.filter(key, filters[key])
// })

// register global mixins.
Vue.mixin(mixins)

/* eslint-disable no-new */
new Vue(Vue.util.extend({el: '#root'}, EditWareHousePage))