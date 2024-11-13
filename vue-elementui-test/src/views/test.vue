<template>
<div>
    test
</div>
</template>

<script>
import axios from "axios";
export default {
  created() {
    this.handleLogin();
  },
  methods: {
    handleLogin() {
      axios
        .get("http://localhost:8081/user/test")
        .then((response) => {
          console.log("响应数据：", response.data);
        })
        .catch((error) => {
          console.error("失败：", error);
        });
    },
  },
};
</script>

<style>
</style>