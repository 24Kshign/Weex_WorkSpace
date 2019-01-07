/* global Vue */

/* weex initialized here, please do not move this line */

import mixins from '@/utils/helper.js'

const router = require('./router')
const App = require('@/index.vue')

// register global mixins.
Vue.mixin(mixins)

/* eslint-disable no-new */
new Vue(Vue.util.extend({el: '#root', router}, App))
// 不自动push的话，就可以用host/#/settingpage来访问指定页面了，但是。。。weex sdk解析会有问题
router.push('/')
