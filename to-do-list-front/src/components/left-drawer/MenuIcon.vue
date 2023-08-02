<template>
  <q-item clickable v-ripple>
    <q-item-section avatar>
      <q-icon name="today"/>
    </q-item-section>
    <router-link to="/todo/list">
    <q-item-section >
      캘린더
    </q-item-section>
    </router-link>
  </q-item>
  <q-item clickable v-ripple>
    <q-item-section avatar>
      <q-icon name="settings"/>
    </q-item-section>
    <router-link to="/todo/edit">
    <q-item-section>
      정보 수정
    </q-item-section>
    </router-link>
  </q-item>
  <q-item clickable v-ripple>
    <q-item-section avatar>
      <q-icon name="logout"/>
    </q-item-section>
    <router-link to="/login">
      <q-item-section @click="logoutEvent">
        로그아웃
      </q-item-section>
    </router-link>
  </q-item>
</template>


<script setup>


import router from "src/router";

const logoutEvent = () => {
    localStorage.removeItem("AccessToken");
    localStorage.removeItem("RefreshToken");
}


</script>
<style scoped>

a {
  margin-top: 5px;
  text-decoration: none;
  color: black;
}

</style>
