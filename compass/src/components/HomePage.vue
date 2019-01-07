<template>
  <div class="container">
    <wxc-minibar :title="warehouse_name"
                background-color="#6C94F7"
                text-color="#FFFFFF"
                right-text="切换库"
                left-button=""
                @wxcMinibarRightButtonClicked="minibarRightButtonClick">
    </wxc-minibar>
    <text class="welcome">{{ user_name }}, 欢迎回来</text>
    <list>
      <cell v-for="model in module_lists" :key="model.name">
        <text class="modle_header">{{ model.name }}</text>
        <HomeListModule :moduleInfo="model.module" />
        <span style="height: 20px; backgroud-color: #F3F4F5"></span>
      </cell>
    </list>
    <wxc-loading :show="isShow" type="default" :need-mask="true"></wxc-loading>
  </div>
</template>

<script>
import { WxcMinibar, WxcLoading } from 'weex-ui'
import HomeListModule from './widget/HomeListModule.vue'
var modal = weex.requireModule('modal')
const storage = weex.requireModule('storage')

export default {
  components: {
    WxcMinibar,
    WxcLoading,
    HomeListModule
  },
  data () {
    return {
      warehouse_name: '仓库名称',
      user_name: '关政委',
      isShow: false,
      module_lists: [{
        name: '入库管理',
        module: [{
          icon: '/src/assets/images/home/home_icon_ruku.png',
          title: '入库',
          path: 'InboundHomePage'
        }, {
          icon: '/src/assets/images/home/home_icon_yanche.png',
          title: '验车',
          path: ''
        }]
      }, {
        name: '在库管理',
        module: [{
          icon: '/src/assets/images/home/home_icon_cheliang.png',
          title: '车辆',
          path: ''
        }, {
          icon: '/src/assets/images/home/home_icon_panku.png',
          title: '盘库',
          path: ''
        }, {
          icon: '/src/assets/images/home/home_icon_pingmian.png',
          title: '平面图'
        }, {
          icon: '/src/assets/images/home/home_icon_kanche.png',
          title: '看车',
          path: ''
        }]
      }, {
        name: '出库管理',
        module: [{
          icon: '/src/assets/images/home/home_icon_chuku.png',
          title: '出库',
          path: ''
        }]
      }, {
        name: '其他',
        module: [{
          icon: '/src/assets/images/home/home_icon_tongji.png',
          title: '数据统计',
          path: ''
        }, {
          icon: '/src/assets/images/home/home_icon_tiaoma.png',
          title: '条码绑定',
          path: ''
        }, {
          icon: '/src/assets/images/home/home_icon_bangzhu.png',
          title: '帮助中心',
          path: 'InventoryDetailsPage'
        }, {
          icon: '/src/assets/images/home/home_icon_shezhi.png',
          title: '设置',
          path: 'SettingPage'
        }]
      }]
    }
  },
  mounted: function () {
    // 安卓调get有问题，需要排查一下
    // this.requestWarehouseList()
  },
  methods: {
    minibarRightButtonClick () {
      storage.removeItem('account', event => {
        console.log('delete value:', event.data)
        modal.toast({
          message: '清除用户信息成功',
          duration: 1.0
        })
      })
    },
    requestWarehouseList () {
      this.get({
        path: 'app/workbench/queryWarehouse.json',
        params: {
        }
      }, result => {
      })
    },
    minibarLeftButtonClick () {
    }
  }
}
</script>

<style scoped>
.container {
  width: 750px;
  background-color: #F3F4F5;
}
.welcome {
  height: 66px;
  line-height: 66px;
  width: 750px;
  background-color: #F3F4F5;
  font-size: 24px;
  text-align: center;
}

.modle_header {
  height: 60px;
  width: 750px;
  background-color: #FFFFFF;
  font: 26px;
  text-align: center;
  line-height: 60px;
  border-top-color: #E7E7E7;
  border-top-width: 1px;
  border-bottom-color: #E7E7E7;
  border-bottom-width: 1px;
}

.panel {
  width: 750px;
  height: 1000px;
  background-color: #FFFFFF;
}

</style>
