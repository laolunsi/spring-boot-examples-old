<template>
  <div id="loginPage">
    <el-form :model="loginForm">
      <el-form-item label="用户名">
        <el-input v-model="loginForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="loginForm.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "Login",
    data() {
      return {
        loginForm: {
          username: '', // username和password是SpringSecurity默认的
          password: ''
        }
      }
    },
    methods: {
      /**
       * 登录
       */
      login: function () {
        this.$http.post("/api/login", null, {
          params: this.loginForm // 必须将参数放在这里，否则SpringSecurity默认的UsernamePasswordAuthenticationFilter无法获取到username和password
        }).then(({data}) => {
          console.log(data);
          if (data.success) {
            window.localStorage.setItem("user", JSON.stringify(data.data.user));
            window.localStorage.setItem("token", data.data.token);
            this.$router.push("/");
          } else {
            this.$message.warning(data.msg);
          }
        });
      }
    }
  }
</script>

<style scoped>
  #loginPage {
    margin: 0 auto;
    width: 400px;
  }
</style>
