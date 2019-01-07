<template>
<div class="wrapper">
    <wxc-minibar  title="忘记密码"
                background-color="#6C94F7"
                text-color="#FFFFFF"
                @wxcMinibarLeftButtonClicked="minibarLeftButtonClick">
      <image src="/src/assets/images/setting/icon_back.png"
         slot="left"
         style="height:44px;width:44px;"/>
    </wxc-minibar>
    <InputAndText v-for="(item,index) in item_list" :key="index" :itemInfo="item" />
    <WxcButton :disabled="isActive"
                text="确定修改"
                type="blue"
                class="wxBtn"
                @wxcButtonClicked="confirmModify">
    </WxcButton>
</div>
</template>

<script>
import InputAndText from './widget/InputAndText'
import { WxcMinibar, WxcButton } from 'weex-ui'
export default {
  name: 'App',
  components: {
    InputAndText,
    WxcMinibar,
    WxcButton
  },
  computed: {
    isActive: function () {
      var itemList = this.item_list
      var isDisabled = false
      for (var i = 0; i < itemList.length; i++) {
        if (itemList[i].inputPwd === '') {
          isDisabled = true
        }
      }
      return isDisabled
    }
  },
  methods: {
    confirmModify () {
    },
    minibarLeftButtonClick () {
    }
  },
  data () {
    return {
      item_list: [
        {
          title: '原密码',
          placeholder: '请输入原密码',
          showLine: true,
          inputPwd: ''
        },
        {
          title: '新密码',
          placeholder: '请输入新密码',
          showLine: true,
          inputPwd: ''
        },
        {
          title: '重复新密码',
          placeholder: '请重复输入原密码',
          showLine: false,
          inputPwd: ''
        }
      ]
    }
  }
}
</script>

<style scoped>
.wrapper{
  background-color: #F3F4F5;
  flex-direction: column;
  align-items: center;
}

.wxBtn{
  margin-top: 60px;
}
</style>
