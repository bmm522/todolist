<template>
<AppComponent>
  <HeaderLayout/>
  <LeftDrawerLayout/>
  <BodyLayout/>
  <FooterLayout/>
</AppComponent>

</template>

<script setup>
import HeaderLayout from "layouts/HeaderLayout.vue";
import LeftDrawerLayout from "layouts/LeftDrawerLayout.vue";
import FooterLayout from "layouts/FooterLayout.vue";
import BodyLayout from "layouts/BodyLayout.vue";
import AppComponent from "components/app/AppComponent.vue";
</script>

<style scoped>

</style>
