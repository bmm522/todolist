<template>
  <q-dialog v-model="timeSelect" persistent>
    <q-card
      style="min-width: 200px">
      <q-time
        v-model="time"
        format24h
        mask="YYYY-MM-DD"
      />
      <q-card-actions align="right" class="text-primary">
        <q-btn flat label="취소" @click =  "timeSelectModalCloseEvent"/>
        <q-btn flat label="확인"  @click =  "sendToAddTaskModalClose" />
      </q-card-actions>
    </q-card>
  </q-dialog>

</template>

<script setup>

import {ref, watch} from "vue";

const props = defineProps(['timeSelectModal']);
const timeSelect = ref(props.timeSelectModal);
const emits = defineEmits(['close']);

const timeSelectModalCloseEvent = () => {
  emits('close');
}

watch(() => props.timeSelectModal, (newValue, oldValue) => {
  timeSelect.value = newValue;
});


</script>

<style scoped>

</style>
