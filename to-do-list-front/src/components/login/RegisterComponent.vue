<template>

  <q-form @submit="register" @reset="onReset">
    <q-input
      style="width: 300px"
      filled v-model="userId" maxlength="8" label="아이디를 입력하세요" lazy-rules :rules="[val => val.length >= 4 || '최소 4자 ~ 최대 8자  입력하세요']"/>

    <br>
    <q-btn label="중복체크" @click="checkDuplicateUserId" />
    <br>
    <br>
    <q-input
      style="width: 300px"
      filled  type="password" v-model="password" maxlength="20" label="비밀번호를 입력하세요" lazy-rules :rules="[val =>val.length > 8 && passwordRegex.test(val) || '영어, 숫자, 특수문자를 포함하여 최소 8자 ~ 최대 20자 입력하세요']"/>
    <br>
    <q-input
      style="width: 300px"
      filled  v-model="rePassword" type="password" maxlength="20" label="비밀번호를 재입력하세요" lazy-rules :rules="[val => val === password || '일치하지 않습니다']"/>


    <br>
    <q-btn label="회원가입" type="submit" />
    <q-btn label="입력 초기화" type="reset"/>
  </q-form>
</template>

<script setup>

import {computed, ref, watch} from "vue";
import UserApi from "src/common/user/UserApi";
import {Notify, useQuasar} from "quasar";
// import router from "src/router/index";
import {createRouter as $router, useRoute, useRouter} from "vue-router";
import routes from "src/router/routes";
import {route} from "quasar/wrappers";
import router from "src/router";


const userId = ref('');
const password = ref('');
const rePassword = ref('');
const emits = defineEmits('clickRegisterButton');


const isDuplicatedCheckUserId = ref(false);

const passwordRegex = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,20}$/;

const checkDuplicateUserId = async () => {

    const data = await UserApi.checkDuplicateUserIdApi(userId.value);
    const duplicateCheckResult = data.body.duplicateCheckResult;

    if(duplicateCheckResult) {
        Notify.create({
          message: '이미 사용중인 아이디입니다.',
          color: 'red'
        })
      isDuplicatedCheckUserId.value = false;
    }
 else {
      Notify.create({
        message: '사용 가능한 아이디입니다.',
        color: 'green'
      })
      isDuplicatedCheckUserId.value = true;
    }
}


const register = async () => {
  if(!isDuplicatedCheckUserId.value) {
    return Notify.create({
      message: '아이디 중복체크를 확인해주세요.',
      color: 'red'
    })
  }
  const data = await UserApi.registerApi(userId.value, password.value);

  if(data.code === 201) {
    Notify.create({
      message: '회원가입이 완료되었습니다.',
      color: 'green'
    })
    await router.push({name: 'login'});
  }
 else {
    Notify.create({
      message: '서버에 문제가 생겼습니다.',
      color: 'red'
    });
  }


}





const onReset = () => {

  userId.value = '';
  password.value = '';
  rePassword.value = '';

}








</script>

<style scoped>

</style>
