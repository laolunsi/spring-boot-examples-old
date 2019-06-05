<template>
  <div id="home_container">
    <el-container>
      <el-header>
        <v-header></v-header>
      </el-header>
      <el-container>
        <el-aside width="auto">
          <v-side-bar></v-side-bar>
        </el-aside>
        <el-main class="home-right">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import vHeader from './common/header'
  import vSideBar from './common/sidebar'

  export default {
    name: "home",
    data() {
      return {}
    },
    components: {
      vHeader, vSideBar
    }
  }
</script>

<style scoped>
  .el-header {
    padding: 0
  }

  .el-main {
    margin-left: 20px;
  }

</style>
