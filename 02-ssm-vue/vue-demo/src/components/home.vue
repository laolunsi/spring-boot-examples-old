<template>
  <div>
    welcome, {{user.name}}, follows are your own message:
    {{userStr}}
  </div>
</template>

<script>
  export default {
    name: "home",
    data() {
      return {
        user: JSON.parse(window.localStorage.getItem("user")),
        userStr: ''
      }
    },
    mounted() {
      this.userStr = JSON.stringify(this.user);
    }
  }
</script>

<style scoped>

</style>
