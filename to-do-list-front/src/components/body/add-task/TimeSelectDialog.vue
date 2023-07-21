<template>
  <q-dialog v-model="timeSelect" persistent>
    <q-card
      style="min-width: 200px">
      <q-card-section>
          현재 선택한 날짜 : {{props.dateValue}}
      </q-card-section>
      <q-time
        v-model="timeValue"
        format24h
      />

      <q-card-actions align="right" class="text-primary">
        <q-btn flat label="취소" @click =  "timeSelectModalCloseEvent"/>
        <q-btn flat label="확인"  @click =  "timeSelectEvent" />
      </q-card-actions>
    </q-card>
  </q-dialog>

</template>

<script setup>

import {ref, watch} from "vue";
import dayjs from "dayjs";

// 초기값 세팅
const props = defineProps(['timeSelectModal', 'dateValue']);
const timeSelect = ref(props.timeSelectModal);
const emits = defineEmits(['close', 'select']);
const timeFormat = "HH:mm";
const timeValue = ref(dayjs().format(timeFormat));


const timeSelectModalCloseEvent = () => {
  emits('close');
}

watch(() => props.timeSelectModal, (newValue, oldValue) => {
  timeSelect.value = newValue;
});
const timeSelectEvent = () => {
  emits('select', {timeValue:timeValue.value});
}





</script>

<style scoped>

</style>
