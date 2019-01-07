<template>
<div class="wrapper">
  <wxc-minibar  title="设置"
                background-color="#6C94F7"
                text-color="#FFFFFF"
                @wxcMinibarLeftButtonClicked="minibarLeftButtonClick">
      <image src="/src/assets/images/setting/icon_back.png"
         slot="left"
         style="height:44px;width:44px;"/>
    </wxc-minibar>
  <TextAndArrow v-for="(item,index) in item_list" :key="index" :itemInfo="item" @jumpItem="handleJumpItem" />
</div>
</template>

<script>
import TextAndArrow from './widget/TextAndArrow'
import { WxcMinibar } from 'weex-ui'
export default {
  name: 'App',
  components: {
    TextAndArrow,
    WxcMinibar
  },
  methods: {
    handleJumpItem (path) {
      this.push(path)
    },
    minibarLeftButtonClick () {
    }
  },
  data () {
    return {
      item_list: [
        {
          title: '自定义仓库位',
          path: 'WareHousePage'
        },
        {
          title: '自定义操作',
          path: 'InventoryRecordPage'
        },
        {
          title: '修改密码',
          path: 'ForgetPwdPage'
        }
      ]
    }
  }
}
</script>

<style scoped>

</style>
