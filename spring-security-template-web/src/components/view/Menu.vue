<template>
  <div id="menuPage">
    <el-table :data="menuList" style="width: 100%">
      <el-table-column prop="id" label="id"></el-table-column>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="url" label="url"></el-table-column>
      <el-table-column prop="permission" label="permission"></el-table-column>
      <el-table-column prop="type" label="类型"></el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    name: "Menu",
    data() {
      return {
        menuList: []
      }
    },
    methods: {
      /**
       * 获取角色列表
       */
      getMenuList() {
        let token = window.localStorage.getItem("token");
        this.$http.get("/api/menu/list", {
          headers: {
            'token': token
          }
        }).then(({data}) => {
          if (data.success) {
            this.menuList = data.data.menuList;
          } else {
            this.$message.warning(data.msg);
            if (data.code === 400 || data.code === 401 || data.code === 402 ||data.code === 403) {
              this.$router.push("/login");
            }
          }
        });
      }
    },
    mounted() {
      this.getMenuList();
    }
  }
</script>

<style scoped>
  #menuPage {
    margin: 0 auto;
    width: 90%;
  }
</style>
