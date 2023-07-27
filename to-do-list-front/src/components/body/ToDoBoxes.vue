<template>

  <div v-for="index in testData" :key ="index">
  <q-timeline-entry heading >
    날짜 : {{  }}
    <br>
  </q-timeline-entry>

  <q-timeline-entry
    title="제목"
    subtitle="14:00"
    side="left"
    icon="edit"
  >
    <div>
      내용
    </div>
  </q-timeline-entry>

  </div>
</template>

<script setup>
import {ref} from "vue";

const testData = ref([1, 2, 3]);
</script>

<style scoped>

</style>
