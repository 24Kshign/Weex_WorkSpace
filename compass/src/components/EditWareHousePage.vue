<template>
  <div>
    <wxc-minibar  title="编辑仓库位"
                background-color="#6C94F7"
                text-color="#FFFFFF"
                @wxcMinibarLeftButtonClicked="minibarLeftButtonClick">
      <image src="/src/assets/images/setting/icon_back.png"
         slot="left"
         style="height:44px;width:44px;"/>
    </wxc-minibar>
  </div>
</template>

<script>
import { WxcMinibar } from 'weex-ui'
export default {
  components: {
    WxcMinibar
  },
  data () {

  },
  methods: {
    minibarLeftButtonClick () {
    }
  }
}
</script>

<style scoped>

</style>
