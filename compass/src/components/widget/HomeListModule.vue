<template>
  <div class="wrapper">
    <div v-for="(info, index) in this.infos" class="row" :key="index">
      <div v-for="model in info" class="item" :key="model.title">
        <div class="block" @click="clickItem(model)">
          <image :src="model.icon" class="icon"/>
          <text class="title">{{ model.title }}</text>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
var modal = weex.requireModule('modal')

export default {
  props: {
    moduleInfo: {
      type: Array,
      required: true
    }
  },
  computed: {
    infos: function () {
      let len = this.moduleInfo.length
      let n = 2
      let lineNum = len % n === 0 ? len / n : Math.floor(len / n + 1)
      let res = []
      for (let i = 0; i < lineNum; i++) {
        let temp = this.moduleInfo.slice(i * n, i * n + n)
        res.push(temp)
      }
      return res
    }
  },
  data () {
    return {
    }
  },
  methods: {
    clickItem: function (e) {
      modal.toast({message: e.title + '\npath: ' + e.path, duration: 1.0})
      if (e.path.length) {
        this.push(e.path)
      }
    }
  }
}
</script>

<style scoped>

.row{
  flex-direction: row;
  width: 750px;
  background-color: aqua;
}

.item {
  height: 100px;
  width: 375px;
  background-color: #FFF;
  flex: 1;
  justify-content: center;
  align-items:center;
  border-bottom-width: 1px;
  border-bottom-color: #E7E7E7;
  border-left-width: 1px;
  border-left-color: #E7E7E7;
}

.block {
  flex-direction: row;
  width: 375px;
  display: flex;
  justify-content: center;
}

.icon {
  width: 40px;
  height: 40px;
  margin-right: 10px;
}

.title {
  font: 26px;
  height: 40px;
  color: #373737;
}

</style>
