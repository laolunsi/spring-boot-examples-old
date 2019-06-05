<template>
  <div id="rolePage">
    <el-table :data="roleList" style="width: 100%">
      <el-table-column prop="name" label="角色"></el-table-column>
      <el-table-column prop="realName" label="名称"></el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    name: "Role",
    data() {
      return {
        roleList: []
      }
    },
    methods: {
      /**
       * 获取角色列表
       */
      getRoleList() {
        let token = window.localStorage.getItem("token");
        this.$http.get("/api/role/list", {
          headers: {
            'token': token
          }
        }).then(({data}) => {
          if (data.success) {
            this.roleList = data.data.roleList;
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
      this.getRoleList();
    }
  }
</script>

<style scoped>
  #rolePage {
    margin: 0 auto;
    width: 90%;
  }
</style>
