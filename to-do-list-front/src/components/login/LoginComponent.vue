<template>

  <div class="q-gutter-y-md column" style="max-width: 300px">
    <br>
    아이디
    <input type="text" v-model="userId"/>
    <br>
    비밀번호
    <input type="password" v-model="password"/>
    <q-btn style="width: 87px" outline color="primary" label="로그인" @click="login" />
    <router-link to="/register">
    <q-btn outline color="primary" label="회원가입"/>
    </router-link>
  </div>
</template>

<script setup>

import UserApi from "src/common/user/UserApi";
import {ref} from "vue";

const userId = ref('');
const password = ref('');



const login = async () => {
    const data = await UserApi.loginApi(userId.value, password.value);
    console.log(data);
}


</script>

<style scoped>

</style>
