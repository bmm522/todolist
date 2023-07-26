<template>

  <div class="q-gutter-y-md column" style="max-width: 300px">
    <br>
    아이디
    <input type="text" v-model="userId"/>
    <br>
    비밀번호
    <input type="password" v-model="password"/>
    <q-btn style="width: 87px" outline color="primary" label="로그인" @click="login" />
<!--    <form action="http://localhost:8080/user/login" method="post"> &lt;!&ndash;컨트롤러에 해당 주소 맵핑 안만들고 시큐리티가 가로채게 할것&ndash;&gt;-->
<!--      <div class="form-group">-->
<!--        <label for="username">Username:</label>-->
<!--        <input type="text" class="form-control" name="username" placeholder="Enter username" id="username">-->
<!--      </div>-->
<!--      <div class="form-group">-->
<!--        <label for="password">Password:</label>-->
<!--        <input type="password" class="form-control" name="password" placeholder="Enter password" id="password">-->
<!--      </div>-->
<!--      <button id="btn-login" class="btn btn-primary">로그인</button>-->
<!--    </form>-->

<!--    <q-form action="http://localhost:8080/login" method="post">-->
<!--      <q-input filled  v-model="userId" name="username"/>-->
<!--      <q-input filled v-model="password" type="password" name="password"/>-->
<!--      <q-btn label="로그인" type="submit" color="primary"/>-->
<!--    </q-form>-->
    <router-link to="/register">
    <q-btn outline color="primary" label="회원가입"/>
    </router-link>
  </div>
</template>

<script setup>

import UserApi from "src/common/user/UserApi";
import {ref} from "vue";
import {Notify} from "quasar";
import router from "src/router";

const userId = ref('');
const password = ref('');



const login = async () => {
    const data = await UserApi.loginApi(userId.value, password.value);
    if(data.code === 200) {
      sessionStorage.setItem("AccessToken", data.body.accessToken);
      sessionStorage.setItem("RefreshToken", data.body.refreshToken);
      Notify.create({
        message: '환영합니다.',
        color: 'green'
      })

      await router.push('/todo');
    }

    if(data.code === 401) {
      Notify.create({
        message: '아이디 또는 비밀번호가 일치하지 않습니다.',
        color: 'red'
      })
    }
}


</script>

<style scoped>

</style>
