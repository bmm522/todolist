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
    <br>
    제목 : {{title}}
    <br>
    내용 : {{content}}
    <q-card-section class="q-pt-none" style="text-align: center; margin-top: 30px" >
      <q-input color="grey-3" label-color="orange" outlined v-model="toDoTimeShow" label="시간 선택" readonly>
        <template #append>
          <q-icon name="event" color="orange"  @click="dateSelectModalOpenEvent" style="cursor: pointer;"/>
          <DateSelectDialog
            :date-select-modal="dateSelectModal"
            @close="dateSelectModalCloseEvent" @submit="dateSubmitEvent"/>
        </template>
      </q-input>
    </q-card-section>

    <q-card-section class="q-pt-none" style="text-align: center; margin-top: 30px" >
      <q-input filled v-model="title" label="제목" :dense="dense"  maxlength="30" lazy-rules :rules="[val => val.length >= 1 || '빈칸은 입력하실 수 없습니다.']" />
      <br>
      <q-input type="textarea" filled v-model="content" label="메모" maxlength="500"/>
    </q-card-section>

    <q-card-actions align="right" class="text-primary">
      <q-btn flat label="취소" @click =  "sendToAddTaskModalClose"/>
      <q-btn flat label="등록"  @click =  "insertTodoEvent" />
    </q-card-actions>
  </q-card>
  </q-dialog>

</template>

<script setup>
import {ref, watch} from "vue";
import DateSelectDialog from "components/body/add-task/DateSelectDialog.vue";
import {Notify} from "quasar";
import TodoApi from "src/common/todo/TodoApi";

// add task 모달창
const props = defineProps(['addTaskModal']);
const addTask = ref(props.addTaskModal);
const emits = defineEmits(['submit','close']);
// date select 모달창
const dateSelectModal = ref(false);
const isDateSelectValue = ref(false);
const dateValueFromModal = ref();
const timeValueFromModal = ref();
const title = ref("");
const content = ref("");
const toDoTimeShow = ref("");
const toDoTime = ref("");

const dateSubmitEvent = (dateAndTimeDatas) => {
  isDateSelectValue.value = dateAndTimeDatas.isDateSelect; // 날짜 선택 여부
  dateValueFromModal.value = dateAndTimeDatas.dateValue; // 날짜 데이터
  timeValueFromModal.value = dateAndTimeDatas.timeSelectValue; // 시간 데이터
  dateSelectModal.value = dateAndTimeDatas.dateSelect;
  toDoTimeShow.value = dateValueFromModal.value + " " + timeValueFromModal.value;
  toDoTime.value = dateValueFromModal.value + "T" + timeValueFromModal.value;
}


const sendToAddTaskModalClose =  ()  => {
  isDateSelectValue.value = false;// 날짜 선택 여부
  dateValueFromModal.value = ""; // 날짜 데이터
  timeValueFromModal.value = "";// 시간 데이터
  dateSelectModal.value = false;
  toDoTimeShow.value = "";
  title.value = "";
  content.value = "";
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

const insertTodoEvent = async () => {

  if(!isDateSelectValue.value || dateValueFromModal.value === null || timeValueFromModal.value === null) {
    Notify.create({
      message: '날짜와 시간을 선택해주세요',
      color: 'red'
    })

  }
  const data = await TodoApi.insertTodoApi(title.value, content.value, toDoTime.value);

  if(data.code === 201) {
    Notify.create({
      message: '할 일이 성공적으로 추가되었습니다.',
      color: 'green'
    })
    sendToAddTaskModalClose();
  }
 else {
    Notify.create({
      message: '할 일을 추가하는 과정에서 오류가 발생했습니다.',
      color: 'red'
    })
  }


}






</script>

<style scoped>



</style>
