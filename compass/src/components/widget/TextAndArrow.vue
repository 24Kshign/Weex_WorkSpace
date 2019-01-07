<template>
<div class="row">
  <div class="contentDiv" @click="jumpItem">
    <wxc-icon class="more" name="more" :style="{ color: '#AFAFAF', fontSize: '50px' }"></wxc-icon>
    <text class="leftTxt">{{itemInfo.title}}</text>
  </div>
  <div class="line"></div>

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
  methods: {
    jumpItem () {
      this.$emit('jumpItem', this.itemInfo.path)
    }
  }
}
</script>

<style scoped>
.row {
  display: flex;
  flex-direction: column;
  height: 110px;
  background-color: #ffffff;
}

.contentDiv {
  height: 108px;
  flex-direction: row-reverse;
  align-items: center;
}

.leftTxt {
  font-size: 32px;
  flex: 1;
  margin-left: 30px;
}

.more {
  margin-right: 30px;
}

.line {
  height: 2px;
  background-color: #eeeeee;
  width: 750px;
  margin-left: 30px;
}
</style>
