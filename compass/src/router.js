/* global Vue */
import Router from 'vue-router'
import WelcomePage from '@/components/WelcomePage.vue'
import LoginPage from '@/components/LoginPage.vue'
import HomePage from '@/components/HomePage.vue'
import SettingPage from '@/components/SettingPage.vue'
import AddWareHousePage from '@/components/AddWareHousePage.vue'
import EditWareHousePage from '@/components/EditWareHousePage.vue'
import ForgetPwdPage from '@/components/ForgetPwdPage.vue'
import InventoryRecordPage from '@/components/InventoryRecordPage.vue'
import WareHousePage from '@/components/WareHousePage.vue'
import InboundHomePage from '@/components/InboundHomePage.vue'
import InventoryDetailsPage from '@/components/InventoryDetailsPage.vue'

Vue.use(Router)

module.exports = new Router({
  routes: [
    {
      path: '/',
      name: 'WelcomePage',
      component: WelcomePage
    },
    {
      path: '/login',
      name: 'LoginPage',
      component: LoginPage
    },
    {
      path: '/homepage',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/settingpage',
      name: 'SettingPage',
      component: SettingPage
    },
    {
      path: '/addwarehousepage',
      name: 'AddWareHousePage',
      component: AddWareHousePage
    },
    {
      path: '/editwarehousepage',
      name: 'EditWareHousePage',
      component: EditWareHousePage
    },
    {
      path: '/forgetpwdpage',
      name: 'ForgetPwdPage',
      component: ForgetPwdPage
    },
    {
      path: '/inventoryrecordpage',
      name: 'InventoryRecordPage',
      component: InventoryRecordPage
    },
    {
      path: '/warehousepage',
      name: 'WareHousePage',
      component: WareHousePage
    },
    {
      path: '/inboundHomePage',
      name: 'InboundHomePage',
      component: InboundHomePage
    },
    {
      path: '/inventorydetailspage',
      name: 'InventoryDetailsPage',
      component: InventoryDetailsPage
    }
  ]
})
