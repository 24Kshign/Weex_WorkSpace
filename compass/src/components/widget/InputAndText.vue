<template>
  <div class="row">
    <div class="contentDiv">
      <text class="leftTxt">{{itemInfo.title}}</text>
      <input type="text" :placeholder="itemInfo.placeholder" class="inputPwd" v-model="itemInfo.inputPwd" />
      <image v-if="isActive" src="/src/assets/images/setting/icon_close.png" class="closeImg" @click="clearInput"/>
    </div>
    <div v-if="isShowLine" class="line"></div>
    <div v-else class="lineGone"></div>
  </div>
</template>

<script>
export default {
  props: {
    itemInfo: {
      type: Object,
      required: true
    }
  },
  computed: {
    isActive: function () {
      return Boolean(this.itemInfo.inputPwd)
    },
    isShowLine: function () {
      return this.itemInfo.showLine
    }
  },
  methods: {
    clearInput () {
      this.itemInfo.inputPwd = ''
    }
  },
  data () {
    return {
    }
  }
}
</script>

<style scoped>
.row {
  height: 110px;
  background-color: #fff;
  border-bottom: #eee;
  flex-direction: column;
  justify-content: center;
}

.contentDiv {
  height: 108px;
  margin-left: 30px;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.line {
  height: 2px;
  background-color: #eeeeee;
  margin-left: 30px;
  width: 750px;
}

.lineGone {
  height: 2px;
  margin-left: 30px;
  width: 750px;
}

.leftTxt {
  height: 50px;
  width: 180px;
  line-height: 50px;
  font-size: 32px;
}

.inputDiv {
  width: 540px;
  height: 108px;
  flex-direction: row-reverse;
  align-items: center;
}

.inputPwd {
  height: 100px;
  border: none;
  min-width: 450px;
  flex: 1;
  padding: 0 10px;
  font-size: 30px;
}

.closeImg {
  min-width: 40px;
  min-height: 40px;
  margin-right: 30px;
  margin-left: 20px;
}
</style>
