<template>
  <div class="wrapper">
    <wxc-minibar  title="盘点记录"
                background-color="#6C94F7"
                text-color="#FFFFFF"
                @wxcMinibarLeftButtonClicked="minibarLeftButtonClick">
      <image src="/src/assets/images/setting/icon_back.png"
         slot="left"
         style="height:44px;width:44px;"/>
    </wxc-minibar>
    <scroller class="scroller">
      <div class="content">
        <div class="recordList" v-for="(item, index) in finalItemList" :key="index" @click="recordClick(item)">
          <div class="listTop">
            <text class="listTime">{{item.time}}</text>
            <image src="/src/assets/images/setting/icon_arrow_right.png" class="listArrowImg" />
          </div>
          <div class="listBottom">
            <text :class="['listNormalTxt',item.isNormal ? 'listNormalTxtEnable' : 'listNormalTxtDisable']">{{item.isNormal ? '正常' : '异常'}}</text>
            <image :src="item.listImg" class="listNormalImg" />
            <div class="listContentDiv">
              <text class="listContent">已盘点/需盘点：</text>
              <text :class="['listContent',item.isNormal ? 'listContentEnable' : 'listContentDisable']">{{item.number}}</text>
              <text class="listContent">/{{item.count}}</text>
            </div>
          </div>
          <div class="listLine"></div>
        </div>
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
  computed: {
    finalItemList: function () {
      var recordList = this.itemList
      var count = recordList.length
      for (var i = 0; i < count; i++) {
        var normalList = recordList[i].content.split('/')
        recordList[i].number = normalList[0]
        recordList[i].count = normalList[1]
        if (recordList[i].isNormal) {
          recordList[i].listImg = '/src/assets/images/inventory/icon_inventory_normal.png'
        } else {
          recordList[i].listImg = '/src/assets/images/inventory/icon_inventory_abnormal.png'
        }
      }
      return recordList
    }
  },
  mounted: function () {
    // this.requestRecordList()
  },
  data () {
    return {
      itemList: [
        {
          time: '2017-12-12 9:00-12:00',
          content: '50/100',
          isNormal: false,
          listImg: '',
          path: 'InventoryDetailsPage'
        },
        {
          time: '2017-12-12 9:00-12:00',
          content: '100/100',
          isNormal: true,
          path: ''
        },
        {
          time: '2017-12-12 9:00-12:00',
          content: '100/100',
          isNormal: true,
          path: ''
        },
        {
          time: '2017-12-12 9:00-12:00',
          content: '100/100',
          isNormal: true,
          path: ''
        },
        {
          time: '2017-12-12 9:00-12:00',
          content: '100/100',
          isNormal: true,
          path: ''
        },
        {
          time: '2017-12-12 9:00-12:00',
          content: '100/100',
          isNormal: true,
          path: ''
        },
        {
          time: '2017-12-12 9:00-12:00',
          content: '100/100',
          isNormal: true,
          path: ''
        },
        {
          time: '2017-12-12 9:00-12:00',
          content: '100/100',
          isNormal: true,
          path: ''
        },
        {
          time: '2017-12-12 9:00-12:00',
          content: '100/100',
          isNormal: true,
          path: ''
        },
        {
          time: '2017-12-12 9:00-12:00',
          content: '100/100',
          isNormal: true,
          path: ''
        },
        {
          time: '2017-12-12 9:00-12:00',
          content: '100/100',
          isNormal: true,
          path: ''
        },
        {
          time: '2017-12-12 9:00-12:00',
          content: '100/100',
          isNormal: true,
          path: ''
        },
        {
          time: '2017-12-12 9:00-12:00',
          content: '100/100',
          isNormal: true,
          path: ''
        },
        {
          time: '2017-12-12 9:00-12:00',
          content: '100/100',
          isNormal: true,
          path: ''
        },
        {
          time: '2017-12-12 9:00-12:00',
          content: '100/100',
          isNormal: true,
          path: ''
        }
      ]
    }
  },
  methods: {
    recordClick (item) {
      if (item.path !== '') {
        this.push(item.path)
      }
    },
    minibarLeftButtonClick () {
    },
    requestRecordList () {
      this.post({
        path: 'pda/inWarehouse/getStocktakeRecords.json',
        params: {
          searchParam: '',
          warehouseId: ''
        }
      }, result => {
        if (result.success === true) {
        }
      })
    }
  }
}
</script>

<style scoped>
.wrapper{
  background-color: #F3F4F5;
  flex-direction: column;
}

.scroller {
  flex: 1;
}

.content{
  width: 750px;
  background-color: #ffffff;
  margin-top: 30px;
}

.recordList{
  height:160px;
  padding-left: 30px;
  padding-right: 30px;
  flex-direction: column;
}

.listTop{
  flex:1;
  flex-direction: row;
  align-items: center;
}

.listTime{
  font-size: 34px;
  flex: 1;
  color: #373737;
}

.listArrowImg,.listNormalImg{
  width: 40px;
  height: 40px;
}

.listNormalImg{
  margin-right: 20px;
}

.listBottom{
  flex: 1;
  flex-direction: row-reverse;
  align-items: center;
}

.listContentDiv{
  flex-direction: row;
  align-items: center;
  flex: 1;
}

.listContent{
  font-size: 34px;
  text-align: center;
  color: #AFAFAF;
}

.listContentEnable{
  color: #626262;
}

.listContentDisable{
  color: #FD7A71;
}

.listNormalTxt{
  font-size: 34px;
}

.listNormalTxtEnable{
  color: #4BD1AF;
}

.listNormalTxtDisable{
  color: #FD7A71;
}

.listLine{
  height: 2px;
  background-color: #eeeeee;
}

</style>
