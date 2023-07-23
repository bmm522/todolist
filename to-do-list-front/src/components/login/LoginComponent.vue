<template>

  <div class="q-gutter-y-md column" style="max-width: 300px">
    <br>
    아이디
    <input type="text"/>
    <br>
    비밀번호
    <input type="password"/>
    <q-btn style="width: 87px" outline color="primary" label="로그인" />
    <router-link to="/register">
    <q-btn outline color="primary" label="회원가입" />
    </router-link>
  </div>
</template>

<script setup>

</script>

<style scoped>

</style>
