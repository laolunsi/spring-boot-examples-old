<template>
  <div id="userPage">
    <el-table :data="userList" style="width: 100%">
      <el-table-column prop="name" label="用户名"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" :formatter="dateFormat"></el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    name: "User",
    data() {
      return {
        userList: []
      }
    },
    methods: {
      /**
       * 获取用户列表
       */
      getUserList() {
        let token = window.localStorage.getItem("token");
        this.$http.get("/api/user/list", {
          headers: {
            'token': token
          }
        }).then(({data}) => {
          if (data.success) {
            this.userList = data.data.userList;
          } else {
            this.$message.warning(data.msg);
            if (data.code === 400 || data.code === 401 || data.code === 402 ||data.code === 403) {
              this.$router.push("/login");
            }
          }
        });
      },
      /**
       * 日期格式化
       * @param row
       * @param column
       * @returns {string}
       */
      dateFormat(row, column) {
        let date = row[column.property];
        if (date == null || date === '') {
          return '';
        }
        date = new Date(date);
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let hour = date.getHours();
        let minute = date.getMinutes();
        return year + '-' + (month < 10 ? '0' + month : month) + '-' + (day < 10 ? '0' + day : day) +
          ' ' + (hour < 10 ? '0' + hour : hour) + ':' + (minute < 10 ? '0' + minute : minute);
      }
    },
    mounted() {
      this.getUserList();
    }
  }
</script>

<style scoped>
  #userPage {
    margin: 0 auto;
    width: 90%;
  }
</style>
