<template>
  <div>
    <h2>LOGIN</h2>
    <div>
      <label>
        姓名：<input v-model="loginForm.name"/>
      </label>
    </div>
    <div>
      <label>
        密码：<input v-model="loginForm.password"/>
      </label>
    </div>
    <div>
      <button @click="login">登录</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      loginForm: {
        name: '',
        password: ''
      }
    }
  },
  methods: {
    login() {
      console.log(JSON.stringify(this.loginForm));
      this.axios.get("/api/login", {params: {name: this.loginForm.name, password: this.loginForm.password}}).then(({data}) => {
        if (data.success) {
          window.localStorage.setItem("user", JSON.stringify(data.data.user));
          this.$router.push("/home");
        } else {
          alert(data.msg);
        }
      })
    }
  }
}
</script>

<style scoped>
 div {
   margin-bottom: 20px;
 }
</style>
