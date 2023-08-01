<template>
  <q-input filled v-model="title" label="제목" maxlength="30" />
  <br>
  <q-input filled v-model="content" label="할 일"  maxlength="500" />
</template>

<script setup>

import {ref} from "vue";

const title = ref();
const content = ref();



</script>

<style scoped>

</style>
