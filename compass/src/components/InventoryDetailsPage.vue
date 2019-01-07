<template>
  <div class="wrapper">
    <wxc-minibar title="盘库详情"
                 background-color="#6C94F7"
                 text-color="#FFFFFF"
                 right-text="98/100"
                 @wxcMinibarLeftButtonClicked="minibarLeftButtonClick">
      <image src="/src/assets/images/setting/icon_back.png"
         slot="left"
         style="height:44px;width:44px;"/>
    </wxc-minibar>
    <scroller class="scroller">
      <InventoryDetailsModule v-for="(item, index) in itemList" :key="index" :itemInfo="item" />
    </scroller>
  </div>
</template>

<script>
import { WxcMinibar } from 'weex-ui'
import InventoryDetailsModule from './widget/InventoryDetailsModule.vue'

export default {
  components: {
    WxcMinibar,
    InventoryDetailsModule
  },
  data () {
    return {
      itemList: [
        {
          contentNumber: 'NO1',
          contentTitle: '车押贷',
          carIsNormal: false,
          carNumber: 'JTEBU3FJ8GK110478',
          carAttr: '国产 本田 思域 2016款自动豪华版 外白',
          carAge: '33天',
          mileage: '16.7公里',
          keyNumber: '1把'
        },
        {
          contentNumber: 'NO2',
          contentTitle: '车押贷',
          carIsNormal: true,
          carNumber: 'JTEBU3FJ8GK110478',
          carAttr: '国产 本田 思域 2016款自动豪华版 外白',
          carAge: '33天',
          mileage: '16.7公里',
          keyNumber: '1把'
        },
        {
          contentNumber: 'NO3',
          contentTitle: '车押贷',
          carIsNormal: false,
          carNumber: 'JTEBU3FJ8GK110478',
          carAttr: '国产 本田 思域 2016款自动豪华版 外白',
          carAge: '33天',
          mileage: '16.7公里',
          keyNumber: '1把'
        },
        {
          contentNumber: 'NO4',
          contentTitle: '车押贷',
          carIsNormal: true,
          carNumber: 'JTEBU3FJ8GK110478',
          carAttr: '国产 本田 思域 2016款自动豪华版 外白',
          carAge: '33天',
          mileage: '16.7公里',
          keyNumber: '1把'
        },
        {
          contentNumber: 'NO5',
          contentTitle: '车押贷',
          carIsNormal: false,
          carNumber: 'JTEBU3FJ8GK110478',
          carAttr: '国产 本田 思域 2016款自动豪华版 外白',
          carAge: '33天',
          mileage: '16.7公里',
          keyNumber: '1把'
        }
      ]
    }
  },
  methods: {
    minibarLeftButtonClick () {
    }
  }
}
</script>

<style scoped>
.wrapper{
  background-color: #F3F4F5;
}

.scroller{
  flex: 1;
}
</style>
