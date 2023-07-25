<template>
  <q-img
    class="absolute-top"
    src="https://cdn.quasar.dev/img/material.png"
    spinner-color="white"
    style="height: 100px"
  >
    <div class="absolute-bottom bg-transparent">
      <div class="text-weight-bold" id="nickname-box">{{username}}님 환영합니다</div>
    </div>
  </q-img>
</template>

<script setup>

import { ref } from "vue";
import UserApi from "src/common/user/UserApi";

const username = ref("");

username.value = UserApi.getUsername();

</script>

<style scoped>
#nickname-box {
  padding-bottom: 15px;
}

#id-box {
  font-size: smaller;
}
</style>
