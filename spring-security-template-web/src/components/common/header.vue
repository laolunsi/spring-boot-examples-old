<template>
    <div style="background-color: #545c64; height: 60px;">
      <div style="float: left; margin-left: 20px; line-height: 60px;">
        <span style="color: black">Nestling权限管理模板</span>
      </div>
      <div style="float: right; margin-right: 20px; line-height: 60px;">
        <el-dropdown @command="handleCommand" trigger="click">
          <span class="el-dropdown-link" style="color: black; ">{{user.name}}<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="password">修改密码</el-dropdown-item>
            <el-dropdown-item command="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>

      <el-dialog
        id="passwordDialog"
        title="修改密码"
        :visible.sync="passDialogVisible"
        class="dialog">
        <el-form ref="passForm" :model="passForm" :rules="passFormRules" label-width="100px">
          <el-form-item label="旧密码" prop="oldPass">
            <el-input v-model="passForm.oldPass" type="password"></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="newPass">
            <el-input v-model="passForm.newPass" type="password"></el-input>
          </el-form-item>
          <el-form-item label="重复新密码" prop="reNewPass">
            <el-input v-model="passForm.reNewPass" type="password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="cancel('pass')">取消</el-button>
            <el-button type="primary" @click="changePassword()">确认</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
</template>

<script>
    export default {
      data() {
        return {
          isCollapse: true, // 默认折叠
          expandIcon: "el-icon-arrow-right",
          expandTitle: "",
          user: JSON.parse(window.localStorage.getItem("user")),
          passDialogVisible: false,
          passForm: {
            // 修改密码的表单
            oldPass: '',
            newPass: '',
            reNewPass: ''
          },
          passFormRules: {
            oldPass: [
              {required: true, message: '原密码不能为空', trigger: 'blur'}
            ],
            newPass: [
              {required: true, message: '新密码不能为空', trigger: 'blur'},
              {min: 6, max: 12, message: '密码长度应该是6到12位', trigger: 'blur'}
            ],
            reNewPass: [
              {required: true, message: '重复密码不能为空', trigger: 'blur'},
              {min: 6, max: 12, message: '密码长度应该是6到12位', trigger: 'blur'}
            ]
          },
        }
      },
      methods: {
        handleSelect(key, keyPath) {
          console.log(key, keyPath);
          if (key === "add_article") {
            this.$router.push("/admin/add_article");
          }
        },
        handleCommand(v) {
          console.log("command: " + v);
          if (v === "logout") {
            console.log("退出登录");
            window.localStorage.removeItem("user");
            this.$router.push("/login");
          } else if (v === "password") {
            console.log("修改密码");
            this.passForm = {};
            this.passDialogVisible = true;
          }
        },
        changePassword() {
          this.$refs.passForm.validate((valid) => {
            if (!valid) {
              return false;
            }
            if (this.passForm.newPass !== this.passForm.reNewPass) {
              this.$message.warning("两次输入的密码不一致");
              return false;
            }

            console.log("准备修改密码：" + JSON.stringify(this.passForm));
            this.$http.put("/api/user/change_password", this.passForm).then(({data}) => {
              console.log("返回值:" + JSON.stringify(data));
              if (data.success) {
                this.passDialogVisible = false;
                this.$message.success("修改成功")
              } else {
                this.$message.error(data.msg);
              }
            });

          });
        },
        goSite() {
          this.$router.push("/");
        }
      }
    }
</script>

<style scoped>
  #passwordDialog {
    margin: 0 auto;
    width: 50%
  }
</style>
