<template>
  <q-dialog v-model="dateSelect" persistent>
    <q-card
      style="min-width: 450px">
      <q-date
        v-model="dateValue"
        landscape
      />
      <q-card-actions align="right" class="text-primary">
        <q-btn flat label="취소" @click =  "dateSelectModalCloseEvent"/>
        <q-btn flat label="시간 선택"  @click =  "dateSelectEvent" />
        <TimeSelectDialog :time-select-modal="timeSelectModal" @close="timeSelectModalCloseEvent"/>
      </q-card-actions>
    </q-card>
  </q-dialog>

</template>

<script setup>


import {ref, watch} from "vue";
import TimeSelectDialog from "components/body/add-task/TimeSelectDialog.vue";

const props = defineProps(['dateSelectModal']);
const dateSelect = ref(props.dateSelectModal);

const timeSelectModal = ref(false);
const emits = defineEmits(['close']);

const dateSelectModalCloseEvent = () => {
  emits('close');
}



const dateValue = ref(todayDate(2023,7,19));

console.log(dateValue.value)

const dateSelectEvent = () => {
  console.log(date);
  timeSelectModal.value = true;
}

const timeSelectModalCloseEvent =() => {
  timeSelectModal.value = false;
}

watch(()=>props.dateSelectModal, (newValue, oldValue)=>{
  dateSelect.value = newValue
})



</script>

<style scoped>

</style>
