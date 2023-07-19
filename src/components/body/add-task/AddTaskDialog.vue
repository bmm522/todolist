<template>
  <q-dialog v-model="addTask" persistent>

  <q-card
    style="min-width: 450px">

    <q-card-section class="q-pt-none" style="text-align: center; margin-top: 30px" >
      <q-btn align="around" class="btn-fixed-width" color="brown-5" label="날짜 선택" icon="event"  @click="dateSelectModalOpenEvent"/>
    <DateSelectDialog :date-select-modal="dateSelectModal" @close="dateSelectModalCloseEvent"/>

    </q-card-section>

    <q-card-actions align="right" class="text-primary">
      <q-btn flat label="Cancel" @click =  "sendToAddTaskModalClose"/>
      <q-btn flat label="Add address"  @click =  "sendToAddTaskModalClose" />
    </q-card-actions>
  </q-card>
  </q-dialog>

</template>

<script setup>
import {ref, watch} from "vue";

import DateSelectDialog from "components/body/add-task/DateSelectDialog.vue";

// add task 모달창
const props = defineProps(['addTaskModal']);
const addTask = ref(props.addTaskModal);
const emits = defineEmits(['close']);

const sendToAddTaskModalClose =  ()  => {
  emits('close');
}


watch(()=>props.addTaskModal, (newValue, oldValue)=>{
  addTask.value = newValue
})

// date select 모달창
const dateSelectModal = ref(false);


const dateSelectModalOpenEvent = () => {
  dateSelectModal.value = true;
}
const dateSelectModalCloseEvent = () => {
  dateSelectModal.value = false;
}







</script>

<style scoped>



</style>
