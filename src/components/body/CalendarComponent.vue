<template>
  <q-date
    v-model="date"
    :events="events"
    :event-color="orange"
    minimal
    id="calendar-box"
  />
</template>

<script setup>

import {date, event} from "quasar";



</script>

<style scoped>

#calendar-box {
  margin: 20px;
}
</style>
