<template>

  아이디
  <br>
  <input style="width: 250px"  type="text" v-model="userId" maxlength="8"  @input="validCheckId" @focus="focusIdOn" @blur="focusIdOut"/>
  <div v-if="isFocusId">
    <div v-if="!isUserIdValid" style="color: #f57878"><small>최소 4자 ~ 최대 8자  입력하세요</small></div>
    <div v-if="isUserIdValid" style="color: green"><small>형식이 올바릅니다.</small></div>
  </div>
  <br>
  비밀번호
  <br>
  <input style="width: 250px" type="password"  v-model="password" maxlength="20" @input="validCheckPassword">
  <div v-if="isFocusPassword">
    <div v-if="!isPasswordValid" style="color: #f57878"><small>영어, 숫자, 특수문자를 포함하여 최소 8자 ~ 최대 20자 입력하세요</small></div>
    <div v-if="isPasswordValid" style="color: green"><small>형식이 올바릅니다.</small></div>
  </div>
  <br>
  비밀번호 확인
  <br>
  <input  style="width: 250px" type="password" @input="validCheckRePassword"/>
  <div v-if="isFocusRePassword">
    <div v-if="!isRePasswordValid" style="color: #f57878"><small>일치하지 않습니다.</small></div>
    <div v-if="isRePasswordValid" style="color: green"><small>일치합니다.</small></div>
  </div>
  <br>
  <br>
  <q-btn outline color="primary" label="회원가입"  @click="register"/>

</template>

<script setup>

import { ref, watch } from "vue";
import axios from "axios";

const userId = ref('');
const password = ref('');

const isFocusId = ref(false);
const isFocusPassword = ref(false);
const isFocusRePassword = ref(false);

const isUserIdValid = ref(false);
const isPasswordValid = ref(false);
const isRePasswordValid = ref(false);


const passwordRegex = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,20}$/;


const validCheckId = (e) => {
  isUserIdValid.value = e.target.value.length >= 4;
}

const validCheckPassword = (e) => {


  let inputPasswordText = e.target.value;
  isPasswordValid.value = (inputPasswordText.length >= 8) && passwordRegex.test(inputPasswordText);
}

const validCheckRePassword = (e) => {

  isRePasswordValid.value = e.target.value === password.value;
}

const focusIdOn = () => {
  isFocusId.value = true;
}

const focusIdOut = () => {
  isFocusId.value = false;
}
const register = (e) => {
  console.log(isUserIdValid.value);
  console.log(isPasswordValid.value);
  console.log(isRePasswordValid.value);

  axios.get("http://localhost:8080/register")
    .then(res=> {
      console.log('통신성공');
    })
}









</script>

<style scoped>

</style>
