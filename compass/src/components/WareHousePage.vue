<template>
  <div class="wrrap">
    <wxc-minibar  title="仓库位置"
                background-color="#6C94F7"
                text-color="#FFFFFF"
                @wxcMinibarLeftButtonClicked="minibarLeftButtonClick">
      <image src="/src/assets/images/setting/icon_back.png"
         slot="left"
         style="height:44px;width:44px;"/>
    </wxc-minibar>
    <div class="createWH" @click="createWHClick">
      <image class="addImg" src="/src/assets/images/warehouse/icon_add.png" />

      <text class="createWHTxt">创建新仓库位</text>
    </div>
    <scroller class="scroller">
      <div class="line">></div>
      <div v-for="(item,index) in itemList" :key="index" class="customContent">
        <div class="customContentDiv">
          <text class="itemEdit">编辑</text>
          <text class="itemDelete">删除</text>
          <text class="itemTitle">{{item.title}}</text>
        </div>
        <div class="line lineMargin"></div>
      </div>
    </scroller>
  </div>
</template>

<script>
import { WxcMinibar } from 'weex-ui'
export default {
  components: {
    WxcMinibar
  },
  data () {
    return {
      itemList: [
        {
          title: '自定义库1'
        },
        {
          title: '自定义库2'
        },
        {
          title: '自定义库3'
        },
        {
          title: '自定义库2'
        },
        {
          title: '自定义库3'
        },
        {
          title: '自定义库2'
        },
        {
          title: '自定义库3'
        },
        {
          title: '自定义库2'
        },
        {
          title: '自定义库3'
        },
        {
          title: '自定义库2'
        },
        {
          title: '自定义库3'
        },
        {
          title: '自定义库2'
        },
        {
          title: '自定义库3'
        },
        {
          title: '自定义库2'
        },
        {
          title: '自定义库3'
        }
      ]
    }
  },
  methods: {
    createWHClick () {
      this.push('AddWareHousePage')
    },
    minibarLeftButtonClick () {
    }
  }
}
</script>

<style scoped>

.createWH{
  flex-direction: row;
  align-items: center;
  height: 120px;
  background-color: #ffffff;
}

.addImg{
  width: 40px;
  height: 40px;
  margin-left: 20px;
}

.createWHTxt{
  color: #6C94F7;
  margin-left: 15px;
  font-size: 32px;
}

.scroller{
  flex: 1;
}

.customContent{
  flex-direction: column;
}

.customContentDiv{
  height: 110px;
  background-color: #ffffff;
  width: 750px;
  flex-direction: row-reverse;
  align-items: center;
}

.itemTitle{
  margin-left: 20px;
  font-size: 32px;
  flex: 1;
}

.itemDelete,.itemEdit{
  margin-right: 30px;
  font-size: 32px;
}

.itemEdit{
  color:#6C94F7;
}

.line{
  width: 750px;
  height: 2px;
  background-color: #eeeeee;
}

.lineMargin{
  margin-left: 20px;
}

</style>
