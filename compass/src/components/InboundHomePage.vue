<template>
  <div class="wrapper">
    <wxc-minibar :title="warehouse_name"
                  background-color="#6C94F7"
                  text-color="#FFFFFF"
                  right-text="切换库"
                  @wxcMinibarRightButtonClicked="minibarRightButtonClick">
      <image slot="left" src="/src/assets/images/setting/icon_back.png" style="width:44px; height:44px;" />
    </wxc-minibar>
    <wxc-tab-page ref="wxc-tab-page"
                  :tab-titles="tabTitles"
                  :tab-styles="tabStyles"
                  title-type="icon"
                  :tab-page-height="tabPageHeight"
                  @wxcTabPageCurrentTabSelected="wxcTabPageCurrentTabSelected">
      <list v-for="(v,index) in tabList"
            :key="index"
            class="item-container"
            :style="{ height: (tabPageHeight - tabStyles.height) + 'px' }">
            <cell>
              <wxc-searchbar ref="wxc-searchbar"
                  placeholder="搜索车架号后四位、客户、业务员"
                  @wxcSearchbarCancelClicked="wxcSearchbarCancelClicked"
                  @wxcSearchbarInputReturned="wxcSearchbarInputReturned"
                  @wxcSearchbarInputOnInput="wxcSearchbarInputOnInput"
                  @wxcSearchbarCloseClicked="wxcSearchbarCloseClicked"
                  @wxcSearchbarInputOnFocus="wxcSearchbarInputOnFocus"
                  @wxcSearchbarInputOnBlur="wxcSearchbarInputOnBlur" />
            </cell>
            <cell v-if="v.length > 0">
              <text class="total">共35辆</text>
            </cell>
            <cell v-else>
              <BlankView detail="暂无数据"
                :style="{ height: (tabPageHeight - tabStyles.height - 150) + 'px' }"/>
            </cell>
        <cell v-for="(demo, key) in v" class="cell" :key="key">
          <wxc-pan-item :ext-id="'1-' + (v) + '-' + (key)">
          <div class="content">
              <InboundCell :indexNum="key" />
          </div>
          <div style="height: 20px; background-color: #F3F4F5"></div>
          </wxc-pan-item>
        </cell>
      </list>
    </wxc-tab-page>
  </div>
</template>

<script>
import { WxcMinibar, WxcSearchbar, WxcTabPage, WxcPanItem, Utils, BindEnv } from 'weex-ui'
import InboundCell from './widget/InboundCell.vue'
import BlankView from './widget/BlankView.vue'
const modal = weex.requireModule('modal')
// const dom = weex.requireModule('dom')

export default {
  components: { WxcMinibar, WxcSearchbar, WxcTabPage, WxcPanItem, InboundCell, BlankView },
  data: () => ({
    warehouse_name: 'name',
    tabTitles: [{
      // icon: 'https://gw.alicdn.com/tfs/TB1MWXdSpXXXXcmXXXXXXXXXXXX-72-72.png',
      // activeIcon: 'https://gw.alicdn.com/tfs/TB1kCk2SXXXXXXFXFXXXXXXXXXX-72-72.png',
      title: '待入库'
    }, {
      // icon: 'https://gw.alicdn.com/tfs/TB1MWXdSpXXXXcmXXXXXXXXXXXX-72-72.png',
      // activeIcon: 'https://gw.alicdn.com/tfs/TB1kCk2SXXXXXXFXFXXXXXXXXXX-72-72.png',
      title: '今日已入库'
    }],
    tabStyles: {
      bgColor: '#FFFFFF',
      titleColor: '#373737',
      activeTitleColor: '#6C94F7',
      activeBgColor: '#FFFFFF',
      isActiveTitleBold: true,
      // iconWidth: 70,
      // iconHeight: 70,
      width: 750 / 2,
      height: 90,
      fontSize: 28,
      hasActiveBottom: true,
      activeBottomColor: '#6C94F7',
      activeBottomHeight: 4,
      activeBottomWidth: 750 / 2,
      textPaddingLeft: 0,
      textPaddingRight: 0
    },
    tabList: [[1, 2, 3, 4], []],
    tabPageHeight: 1334
  }),
  created () {
    this.tabPageHeight = Utils.env.getPageHeight()
    // this.tabList = [...Array(this.tabTitles.length).keys()].map(i => [])
    // Vue.set(this.tabList, 0, this.demoList)
    // alert(JSON.stringify(this.tabList, null, 4))
  },
  methods: {
    minibarRightButtonClick () {
    },
    wxcTabPageCurrentTabSelected (e) {
      // const self = this
      // const index = e.page
      // /* Unloaded tab analog data request */
      // if (!Utils.isNonEmptyArray(self.tabList[index])) {
      //   setTimeout(() => {
      //     Vue.set(self.tabList, index, self.demoList)
      //   }, 100)
      // }
    },
    wxcPanItemPan (e) {
      if (BindEnv.supportsEBForAndroid()) {
        this.$refs['wxc-tab-page'].bindExp(e.element)
      }
    },
    wxcSearchbarInputOnFocus () { // 点了输入框进入输入状态
      modal.toast({message: '点击搜索框', duration: 1})
    },
    wxcSearchbarInputOnBlur () { // 点了键盘上toolbar的done
      modal.toast({message: '点击键盘toolbar', duration: 1})
    },
    wxcSearchbarInputReturned () { // 点了键盘上的return
      modal.toast({message: '点击键盘return', duration: 1})
    },
    wxcSearchbarCloseClicked () { // 点了输入框里的x
      modal.toast({message: '点击删除', duration: 1})
    },
    wxcSearchbarInputOnInput (e) { // 输入
      this.value = e.value
      // modal.toast({message: 'input', duration: 1})
    },
    wxcSearchbarCancelClicked () { // 点了输入框右侧的取消
      // modal.toast({message: 'cancel click', duration: 1})
    }
  }
}
</script>

<style>
.item-container {
  width: 750px;
  background-color: #f2f3f4;
}

.cell {
  background-color: #ffffff;
}

.total {
  color: #AFAFAF;
  height: 80px;
  line-height: 80px;
  text-align: center;
}

.content{
  width:750px;
  /* border-bottom: 20px solid #F3F4F5; */
  align-items: center;
  justify-content: center;
}
</style>
