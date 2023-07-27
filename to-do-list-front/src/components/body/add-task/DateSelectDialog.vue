<template>
  <q-dialog v-model="dateSelect" persistent>
    <q-card
      style="min-width: 450px">
      <q-date
        mask="YYYY-MM-DD"
        v-model="dateValue"
        landscape

      />
      <q-card-section v-if="isTimeSelect" align="right" >
        현재 선택된 시간 : {{timeSelectValue}}
      </q-card-section>
      <q-card-actions align="right" class="text-primary">

        <q-btn flat label="취소" @click =  "dateSelectModalCloseEvent"/>

        <q-btn v-if="!isTimeSelect" flat label="다음"  @click =  "openTimeSelectModalEvent" />
        <q-btn v-if="isTimeSelect" flat label="시간 수정" @click =  "openTimeSelectModalEvent" />
        <q-btn v-if="isTimeSelect" flat label="완료" @click =  "submitEvent" />
<!--        <ToDoInputDialog :to-do-input-modal="toDoInputModal" :date-value="dateValue" :time-value="timeSelectValue"/>-->
        <TimeSelectDialog :time-select-modal="timeSelectModal" :date-value="dateValue" @close="timeSelectModalCloseEvent" @select="timeSelectEvent"/>
      </q-card-actions>
    </q-card>
  </q-dialog>

</template>

<script setup>


import {ref, watch} from "vue";
import TimeSelectDialog from "components/body/add-task/TimeSelectDialog.vue";
import dayjs from "dayjs";
import ToDoInputDialog from "components/body/add-task/ToDoInputBox.vue";

const props = defineProps(['dateSelectModal']);
const dateSelect = ref(props.dateSelectModal);
const timeSelectModal = ref(false);
const toDoInputModal = ref(false);
const emits = defineEmits(['submit','close']);
const isDateSelect = ref(false);
const isTimeSelect= ref(false);
let timeSelectValue = ref();



const submitEvent = () => {
  isDateSelect.value = true;
  dateSelect.value = false;
  emits('submit',
    {
      isDateSelect : isDateSelect.value,
      dateValue: dateValue.value,
      timeSelectValue: timeSelectValue.value,
      dateSelect: dateSelect.value
    });

}

const dateSelectModalCloseEvent = () => {
  emits('close');
}

const dateFormat = "YYYY-MM-DD";
const dateValue = ref(dayjs().format(dateFormat));


const openTimeSelectModalEvent = () => {
  timeSelectModal.value = true;

}

const timeSelectModalCloseEvent =() => {
  timeSelectModal.value = false;
}

const timeSelectEvent = (data) => {
  timeSelectValue.value = data.timeValue;
  timeSelectModal.value = false;
  isTimeSelect.value = true;
}

watch(()=>props.dateSelectModal, (newValue, oldValue)=>{
  dateSelect.value = newValue
})



</script>

<style scoped>

</style>
