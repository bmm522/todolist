<template>
  <AppComponent>
  <RegisterComponent/>
  </AppComponent>
</template>

<script>
import RegisterComponent from "components/login/RegisterComponent.vue";
import AppComponent from "components/app/AppComponent.vue";

export default {
  name: "RegisterLayout",
  components: {AppComponent, RegisterComponent }
};
</script>

<style scoped>

</style>
