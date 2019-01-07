<template>
  <div class="wrapper">
    <image class="image" :src="imageUrl"/>
    <div class="description">
      <text class="title" v-if="this.title">{{ this.title }}</text>
      <text class="detail" v-if="this.detail">{{ this.detail }}</text>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  },
  props: {
    title: String,
    detail: String,
    image: String
  },
  computed: {
    imageUrl: function () {
      return typeof this.image === 'string' && this.image.length ? this.image : '/src/assets/images/general_blank.png'
    }
  }
}
</script>

<style scoped>

.wrapper {
  width: 750px;
  align-items: center;
  justify-content: center;
  background-color: white;
}

.image {
  width: 400px;
  height: 400px;
}

.description {
  margin-top: 45px;
}

.title {
  padding-bottom: 20px;
  size: 30px;
  color: #626262;
  font-weight: bold;
}

.detail {
  size: 26px;
  color: #626262;
}

</style>
