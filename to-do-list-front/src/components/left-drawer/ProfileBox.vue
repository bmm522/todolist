<template>
  <q-img
    class="absolute-top"
    src="https://cdn.quasar.dev/img/material.png"
    spinner-color="white"
    style="height: 100px"
  >
    <div class="absolute-bottom bg-transparent">
      <div class="text-weight-bold" id="nickname-box">{접속닉네임}님 환영합니다</div>
      <div id="id-box">{접속아이디}</div>
    </div>
  </q-img>
</template>

<script setup>


</script>

<style scoped>
#nickname-box {
  padding-bottom: 15px;
}

#id-box {
  font-size: smaller;
}
</style>
