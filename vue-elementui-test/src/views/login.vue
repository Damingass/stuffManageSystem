<template>
  <div class="login-container">
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
      style="width: 500px; margin: 0 auto; padding:200px;text-align:center"
    >
      <el-form-item label="账号" prop="username">
        <el-input v-model="ruleForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password" type="password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')"
          >立即创建</el-button
        >
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import axios from "axios";
import Qs from "qs";
export default {
  data() {
    return {
      ruleForm: {
        username: "20201001",
        password: "1234",
      },
      rules: {
        username: [
          { required: true, message: "请输入活动名称", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "长度在 3 到 10 个字符",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "长度在 3 到 10 个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          //alert("submit!");
          axios.post("/user/login",
              Qs.stringify({
                loginName: this.ruleForm.username,
                password: this.ruleForm.password,
              }),
              {
                headers: {
                  "Content-Type": "application/x-www-form-urlencoded", // 设置请求头，告知服务器发送的是表单编码数据
                },
              }
            )
            .then((response) => {
              this.$store.commit("SET_TOKEN", response.data.data.token);
              this.$store.commit("SET_USERINFO", response.data.data.user);
              console.log("响应数据：", response.data, "token：", response.data.data.token, "user：", response.data.data.user);
              this.$message({
                showClose: true,
                message: "登录成功",
                type: "success",
              });
              this.$router.push({ path: "/user" });
            })
            .catch((error) => {
              console.error("登录失败：", error);
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>
<style scoped>
</style>

