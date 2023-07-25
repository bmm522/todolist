<template>
  <router-view></router-view>
    <router-link to="/login">
      로그인
    </router-link>
    <router-link to="/register">
      회원가입
    </router-link>
</template>

<script setup>

import RegisterComponent from "components/login/RegisterComponent.vue";
import LoginComponent from "components/login/LoginComponent.vue";
import {ref} from "vue";

// const loginComponentView = ref(true);
// const registerComponentView =  ref(false);
//
//
// const moveRegisterComponent = () => {
//   loginComponentView.value = false;
//   registerComponentView.value = true;
// }
//
// const clickRegisterButton = () => {
//   loginComponentView.value = true;
//   registerComponentView.value = false;
// }

</script>

<style scoped>

</style>
