
<template>
  <q-layout view="hHh pR fFf">
    <HeaderLayout/>
    <LeftDrawerLayout/>
    <BodyLayout/>
    <FooterLayout/>
  </q-layout>
</template>

<script setup>
import HeaderLayout from "layouts/HeaderLayout.vue";
import LeftDrawerLayout from "layouts/LeftDrawerLayout.vue";
import FooterLayout from "layouts/FooterLayout.vue";
import BodyLayout from "layouts/BodyLayout.vue";
</script>
<style scoped>

</style>
