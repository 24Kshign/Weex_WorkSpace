<template>
  <div class="wrapper">
    <text>welcome页</text>
    <image class="welcomeImg" src="/src/assets/images/welcome.png"/>
  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  },
  mounted () {
    setTimeout(() => {
      this.reset('login')
    }, 1000)
  }
}
</script>

<style scoped>
.wrapper {
  width: 750px;
  height: 1334px;
  background-color: #ffffff;
  align-items: center;
  justify-content: center;
}
/** 750 x 1250 **/
.welcomeImg {
  width:710px;
  height:1180px;
}
</style>
