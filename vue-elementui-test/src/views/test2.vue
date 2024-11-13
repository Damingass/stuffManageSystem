<template>
<div>
    test2
</div>
</template>

<script>
export default {
  created() {},
  metheds: {
    handleLogin() {
      axios
        .get("http://localhost:8081/user/test2")
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