<template>
<div class="wrrap">
    <image :src="logo" class="logo" />
    <text class="title">{{ title }}</text>
    <div class="loginArea">
      <div class="inputDiv">
        <input type="text" placeholder="请输入账号" class="account" v-model="inputAct" />
      </div>
      <div class="inputDiv">
        <input type="password" placeholder="请输入密码" class="password" v-model="inputPwd" />
      </div>

      <div class="middle">
        <text class="forgetPwdTxt" @click="forgetPwdClick">忘记密码？</text>
        <div class="checkBoxDiv">
          <image @click="checkBoxClick" :src="select_logo" class="checkBoxImg" />
          <text class="autoLoginTxt" @click="checkBoxClick">自动登录</text>
        </div>
      </div>

      <WxcButton :disabled="isActive"
                text="登录"
                type="blue"
                class="wxBtn"
                @wxcButtonClicked="loginClick">
      </WxcButton>

      <wxc-loading :show="loading"></wxc-loading>
    </div>
</div>
</template>

<script>
import { WxcLoading, WxcButton } from 'weex-ui'
var modal = weex.requireModule('modal')
const storage = weex.requireModule('storage')

export default {
  name: 'App',
  components: { WxcLoading, WxcButton },
  data () {
    return {
      logo: '/src/assets/images/volvo_logo.png',
      title: '仓库管理助手 v1.0',
      loginInfo: '',
      isCheck: true,
      select_logo: '/src/assets/images/select_icon.png',
      inputAct: 'gujian',
      inputPwd: '111111',
      loading: false
    }
  },
  computed: {
    isActive: function () {
      return !(Boolean(this.inputAct) && Boolean(this.inputPwd))
    }
  },
  methods: {
    changeNetClick () {
      modal.toast({
        message: '切换网络',
        duration: 1.0
      })
    },
    removeItem () {
      storage.removeItem('account', event => {
        console.log('delete value:', event.data)
        this.state = 'deleted'
        modal.toast({
          message: '清除用户信息成功',
          duration: 1.0
        })
      })
    },
    loginClick (e) {
      if (this.isActive) {
        modal.toast({
          message: '请输入用户名和密码',
          duration: 1.0})
        return
      }
      this.loading = true
      this.post({
        path: 'pda/login.json',
        params: {
          isPda: 'mobile',
          username: this.inputAct,
          password: this.inputPwd
        }
      }, result => {
        this.loading = false
        if (result.success === true) {
          var user = {
            sessionId: result.data.sessionId,
            pdaUserUuid: result.data.pdaUserUuid,
            pdaUserName: result.data.pdaUserName,
            pdaUserRealName: result.data.pdaUserRealName,
            accId: result.data.accId,
            token: result.data.token,
            autoLogin: this.isCheck
          }
          storage.setItem('user', JSON.stringify(user), e => {
            modal.toast({
              message: JSON.stringify(user),
              duration: 1.0})
            this.reset('/homepage')
          })
        } else {
          modal.alert({
            message: JSON.stringify(result, null, 4),
            duration: 1.0})
        }
      })
    },
    checkBoxClick () {
      this.isCheck = !this.isCheck
      if (this.isCheck) {
        this.select_logo = '/src/assets/images/select_icon.png'
      } else {
        this.select_logo = '/src/assets/images/unselect_icon.png'
      }
    },
    forgetPwdClick () {
      modal.alert({
        message: '忘记密码？请钉钉联系安仔',
        okTitle: '知道了',
        duration: 1.0
      }, function (value) {
        console.log('alert callback', value)
      })
    }
  }
}
</script>

<style scoped>
.wrrap {
  flex: 1;
  justify-content: center;
  align-items: center;
}

.logo {
  width: 86px;
  height: 86px;
}

.title {
  margin-top: 26px;
  height: 44px;
  font-size: 32px;
  color: #373737;
}

.inputDiv {
  height: 110px;
  width: 690px;
  border-bottom-width: 1px;
  border-bottom-color: #6B93F6;
  margin-top: 60px;
}

.account,
.password {
  height: 109px;
  width: 690px;
  font-size: 32px;
}

.middle {
  width: 690px;
  height: 60px;
  flex-direction: row-reverse;
  margin-top: 60px;
}

.checkBoxDiv{
  flex: 1;
  flex-direction: row;
}

.checkBoxImg {
  width: 46px;
}

.autoLoginTxt,
.forgetPwdTxt,
.checkBoxImg {
  height: 46px;
  line-height: 46px;
  font-size: 34px;
}

.autoLoginTxt {
  margin-left: 10px;
}

.forgetPwdTxt {
  color: #6B93F6;
}

.wxBtn{
  margin-top: 60px;
}
</style>
