<template>
  <div class="wrapper">
    <wxc-minibar  title="添加仓库位"
                background-color="#6C94F7"
                text-color="#FFFFFF"
                @wxcMinibarLeftButtonClicked="minibarLeftButtonClick">
      <image src="/src/assets/images/setting/icon_back.png"
         slot="left"
         style="height:44px;width:44px;"/>
    </wxc-minibar>
    <InputAndText :itemInfo="itemInput" />
    <WxcButton :disabled="!isActive"
                text="确定添加"
                type="blue"
                class="wxBtn"
                @wxcButtonClicked="confirmModify">
    </WxcButton>
  </div>
</template>

<script>
import { WxcMinibar, WxcButton } from 'weex-ui'
import InputAndText from './widget/InputAndText'
export default {
  components: {
    WxcMinibar,
    InputAndText,
    WxcButton
  },
  computed: {
    isActive: function () {
      return Boolean(this.itemInput.inputPwd)
    }
  },
  data () {
    return {
      itemInput: {
        title: '仓库位',
        placeholder: '请输入标签名',
        showLine: false,
        inputPwd: ''
      }
    }
  },
  methods: {
    confirmModify () {
    },
    minibarLeftButtonClick () {
    }
  }
}
</script>

<style scoped>
.wrapper{
  background-color: #F3F4F5;
}

.wxBtn{
  margin-top: 60px;
}
</style>
