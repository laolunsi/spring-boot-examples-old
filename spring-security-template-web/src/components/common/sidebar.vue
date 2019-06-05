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
      :collapse="isCollapse"
      :router="true">
      <el-menu-item index="/admin">
        <i class="el-icon-menu"></i>
        <span slot="title">系统首页</span>
      </el-menu-item>
      <el-menu-item index="/admin/article">
        <i class="el-icon-document"></i>
        <span slot="title">文章管理</span>
      </el-menu-item>
      <el-menu-item index="/admin/comment" disabled>
        <i class="el-icon-message"></i>
        <span slot="title">评论管理</span>
      </el-menu-item>
      <el-menu-item index="/admin/user">
        <i class="el-icon-service"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-menu-item index="/admin/message">
        <i class="el-icon-message"></i>
        <span slot="title">消息管理</span>
      </el-menu-item>
      <el-menu-item index="/admin/settings">
        <i class="el-icon-setting"></i>
        <span slot="title">系统设置</span>
      </el-menu-item>
      <div id="expandView" @click="expandSwitch">
        <i :class="expandIconClass" style="margin-right: 5px; width: 24px"></i>
        <span>{{expandText}}</span>
      </div>
    </el-menu>
  </div>
</template>

<script>
  export default {
    name: "sidebar",
    data() {
      return {
        isCollapse: true, // 是否收起，true表示收起
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
    /*
    mounted() {
      console.log("mounted");
      this.defaultActiveIndex = this.$router.currentRoute.path;  //默认选中
      console.log("new router: " + this.defaultActiveIndex);
    },*/
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

  #expandView {
    color: #fff;
    font-size: 14px;
    line-height: 56px;
    cursor: pointer
  }
</style>
