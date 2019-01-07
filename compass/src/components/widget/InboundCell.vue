<template>
  <div @click="clickCarItem">
    <div class="header">
      <text class="h-title">NO.{{ this.indexNum + 1 }}</text>
      <!-- <text>车押贷</text> -->
      <image class="h-icon" src="/src/assets/images/icon_arrow_right.png"/>
    </div>
    <div style="margin-top: 10px; margin-bottom: 10px;">
      <div class="c-div" v-for="(item, index) in this.info" :key="index">
        <text class="c-title" v-bind:style="index == info.length-1 ?'width: 210px': ''">{{ item.title}}</text>
        <text class="c-value">{{ item.value }}</text>
      </div>
    </div>
    <div class="footer">
      <wxc-button class="f-btn" text="入库" type="blue" size="medium"
                  @wxcButtonClicked="clickInbound">
      </wxc-button>
    </div>
  </div>
</template>

<script>
import { WxcButton } from 'weex-ui'
const modal = weex.requireModule('modal')
const imagePicker = weex.requireModule('imagePicker')

export default {
  components: { WxcButton },
  data () {
    return {
      info: [{
        title: '车架号',
        value: 'JTEBU3FJ8GK110478'
      }, {
        title: '车辆属性',
        value: '中东 宝马 宝马x5 宝马x5 2018款 3.0T 中东版 金色 白色'
      }, {
        title: '客户',
        value: '陈凯测试公司'
      }, {
        title: '业务员',
        value: ''
      }, {
        title: '调拨单号',
        value: '-'
      }, {
        title: '预计到库时间',
        value: '2018-09-17'
      }]
    }
  },
  props: {
    indexNum: Number
  },
  methods: {
    clickInbound () {
      // weex 封装调用测试
      modal.toast({message: '入库', duration: 1})
      imagePicker.selectPhoto('1', (handler) => {
        modal.toast({message: JSON.stringify(handler), duration: 2})
      })
    },
    clickCarItem () {
      modal.toast({message: '查看车详情', duration: 3})
    }
  }
}
</script>

<style scoped>
.header {
  height: 70px;
  border-bottom: 1px dashed #E7E7E7;
  flex-direction: row;
  width: 750px;
}

.h-title {
  color: #AFAFAF;
  size: 26px;
  line-height: 70px;
  margin-left: 15px;
  width: 690px;
}

.h-icon {
  width: 30px;
  height: 30px;
  margin-top: 20px;
}

.c-div {
  width: 750px;
  flex-direction: row;
}

.c-title, .c-value {
  size: 26px;
  line-height: 40px;
  margin-top: 5px;
  margin-bottom: 5px;
}

.c-title-first {
  margin-top: 10px;
}

.c-title-last {
  margin-bottom: 10px;
}

.c-title {
  color: #AFAFAF;
  width: 140px;
  margin-left: 15px;
}

.c-value {
  color: #373737;
  width: 580px;
}

.footer {
  border-top: 1px dashed #E7E7E7;
  height: 90px;
  width: 750px;
}

.f-btn {
  height: 50px;
  width: 140px;
  margin-top: 15px;
  margin-left: 515px;
}

</style>
