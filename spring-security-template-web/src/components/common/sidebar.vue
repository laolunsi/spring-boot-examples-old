<template>
  <div class="sidebar">
    <el-menu
      :default-active="defaultActiveIndex"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      :router="true">
      <el-menu-item index="/">
        <i class="el-icon-service"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-menu-item index="/role">
        <i class="el-icon-message"></i>
        <span slot="title">角色管理</span>
      </el-menu-item>
      <el-menu-item index="/menu">
        <i class="el-icon-setting"></i>
        <span slot="title">菜单管理</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
  export default {
    name: "sidebar",
    data() {
      return {
        expandIconClass: 'el-icon-arrow-right',
        expandText: '',
        defaultActiveIndex: this.$router.currentRoute.path || '/admin'
      }
    },
    watch: {
      $route(to, from) {
        // 监听路径是否发生变化
        this.defaultActiveIndex = this.$router.currentRoute.path;  //默认选中
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      expandSwitch() {
        this.isCollapse = !this.isCollapse;
        if (!this.isCollapse) {
          this.expandIconClass = "el-icon-arrow-left";
          this.expandText = "收起菜单";
        } else {
          this.expandIconClass = "el-icon-arrow-right";
          this.expandText = "";
        }
      }
    }
  }
</script>

<style scoped>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 160px;
  }

  .el-menu-vertical-demo {
    height: 878px;
  }
</style>
