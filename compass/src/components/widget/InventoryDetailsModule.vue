<template>
  <div class="wrapper">
    <div class="titleDiv">
      <text class="contentNum">{{itemInfo.contentNumber}}</text>
      <text class="contentTitle">{{itemInfo.contentTitle}}</text>
      <img class="titleImage" :src="titleImage" />
      <wxc-icon class="more" name="more" :style="{ color: '#AFAFAF', fontSize: '50px' }"></wxc-icon>
    </div>
    <div class="line"></div>
    <div v-for="(item, index) in finalItemList" :key="index" class="contentDiv">
      <text class="contentLeftTxt">{{item.title}}</text>
      <text :class="['contentRightTxt', item.isFirst ? 'contentRightTxtEnable' : 'contentRightTxtDisable']">{{item.content}}</text>
    </div>
    <div class="line"></div>
    <div class="bottomDiv">
      <text class="bottomBtn">快速找车</text>
    </div>
  </div>
</template>

<script>
import { WxcIcon } from 'weex-ui'
export default {
  components: { WxcIcon },
  props: {
    itemInfo: {
      type: Object,
      required: true
    }
  },
  computed: {
    titleImage: function () {
      if (this.itemInfo.carIsNormal === true) {
        return '/src/assets/images/inventory/icon_inventory_normal.png'
      } else {
        return '/src/assets/images/inventory/icon_inventory_abnormal.png'
      }
    },
    finalItemList: function () {
      let list = [
        {
          title: '车架号',
          content: this.itemInfo.carNumber,
          isFirst: true
        },
        {
          title: '车辆属性',
          content: this.itemInfo.carAttr
        },
        {
          title: '车辆库龄',
          content: this.itemInfo.carAge
        },
        {
          title: '里程',
          content: this.itemInfo.mileage
        },
        {
          title: '钥匙',
          content: this.itemInfo.keyNumber
        }
      ]
      return list
    }
  },
  data () {
    return {
    }
  }
}
</script>

<style scoped>
.titleDiv{
  width: 750px;
  height: 92px;
  margin-top: 20px;
  flex-direction: row;
  align-items: center;
  padding-left:20px;
  background-color: #ffffff;
}

.contentNum{
  font-size: 34px;
  color: #AFAFAF;
}

.contentTitle{
  flex: 1;
  text-align: center;
  font-size: 34px;
  color: #AFAFAF;
}

.titleImage{
  width: 40px;
  height: 40px;
  margin-right: 20px;
}

.more{
  margin-right: 20px;
}

.line{
  width: 750px;
  height: 2px;
  background-color: #e7e7e7;
}

.contentDiv{
  flex-direction: row;
  background-color: #ffffff;
  padding: 22px;
}

.contentLeftTxt{
  width: 170px;
  font-size: 34px;
  color: #AFAFAF;
}

.contentRightTxt{
  flex: 1;
  font-size: 34px;
}

.contentRightTxtEnable{
  color: #373737
}

.contentRightTxtDisable{
  color: #AFAFAF;
}

.bottomDiv{
  height: 122px;
  background-color: #ffffff;
  justify-content: center;
  align-items: center;
}

.bottomBtn{
  border-style: solid;
  border-width: 2px;
  border-color: #6C94F7;
  border-radius: 8px;
  color: #6C94F7;
  font-size: 34px;
  padding: 10px 20px;
}
</style>
