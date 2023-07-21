<template>
  <q-dialog v-model="addTask" persistent>

  <q-card
    style="min-width: 450px">

    날짜와 시간 선택 여부 : {{isDateSelectValue}}
    <br>
    현재 선택된 날짜 : {{dateValueFromModal}}
    <br>
    현재 선택된 시간 : {{timeValueFromModal}}
    <br>
    날짜 선택 모달 현재 상태 : {{dateSelectModal}}
    <q-card-section class="q-pt-none" style="text-align: center; margin-top: 30px" >
      <q-input color="grey-3" label-color="orange" outlined v-model="toDoTime" label="시간 선택" >
        <template #append>
          <q-icon name="event" color="orange"  @click="dateSelectModalOpenEvent" style="cursor: pointer;"/>
          <DateSelectDialog
            :date-select-modal="dateSelectModal"
            @close="dateSelectModalCloseEvent" @submit="dateSubmitEvent"/>
        </template>
      </q-input>
    </q-card-section>

    <q-card-section class="q-pt-none" style="text-align: center; margin-top: 30px" >
      <ToDoInputBox/>
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
import ToDoInputBox from "components/body/add-task/ToDoInputBox.vue";

// add task 모달창
const props = defineProps(['addTaskModal']);
const addTask = ref(props.addTaskModal);
const emits = defineEmits(['submit','close']);
// date select 모달창
const dateSelectModal = ref(false);
const isDateSelectValue = ref(false);
const dateValueFromModal = ref();
const timeValueFromModal = ref();
const toDoTime = ref("");

const dateSubmitEvent = (dateAndTimeDatas) => {
  isDateSelectValue.value = dateAndTimeDatas.isDateSelect; // 날짜 선택 여부
  dateValueFromModal.value = dateAndTimeDatas.dateValue; // 날짜 데이터
  timeValueFromModal.value = dateAndTimeDatas.timeSelectValue; // 시간 데이터
  dateSelectModal.value = dateAndTimeDatas.dateSelect;
  toDoTime.value = dateValueFromModal.value + " " + timeValueFromModal.value;
}


const sendToAddTaskModalClose =  ()  => {
  isDateSelectValue.value = false;// 날짜 선택 여부
  dateValueFromModal.value = ""; // 날짜 데이터
  timeValueFromModal.value = "";// 시간 데이터
  dateSelectModal.value = false;
  toDoTime.value = "";
  emits('close');
}


watch(()=>props.addTaskModal, (newValue, oldValue)=>{
  addTask.value = newValue
})




const dateSelectModalOpenEvent = () => {
    dateSelectModal.value = true;

}

const dateSelectModalCloseEvent = () => {
  dateSelectModal.value = false;

}







</script>

<style scoped>



</style>
