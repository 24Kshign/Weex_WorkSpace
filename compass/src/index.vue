<template>
  <div class="wrapper">
    <router-view style="width:750px; height:1334px"/>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
    }
  }
}
</script>

<style scoped>
  .wrapper {
    align-items: center;
  }
</style>
