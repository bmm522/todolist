<template>
  <q-img
    class="absolute-top"
    src="https://cdn.quasar.dev/img/material.png"
    spinner-color="white"
    style="height: 100px"
  >
    <div class="absolute-bottom bg-transparent">
      <div class="text-weight-bold" id="nickname-box">{{nickname}}님 환영합니다</div>
    </div>
  </q-img>
</template>

<script setup>

import {onMounted, ref} from "vue";
import UserApi from "src/common/user/UserApi";

const nickname = ref("");


onMounted(async () => {
  const data =  await UserApi.getNickname();
  nickname.value = data.body.nickname;
});




</script>

<style scoped>
#nickname-box {
  padding-bottom: 15px;
}

#id-box {
  font-size: smaller;
}
</style>
