<template>


  <q-dialog v-model="modalStore.data.addTodoModal" persistent>

    <q-card
      style="min-width: 450px">

      <q-card-section class="q-pt-none" style="text-align: center; margin-top: 30px">
        <q-input
          color="grey-3" label-color="orange" outlined v-model="dateTimeStore.getShowDateTime" label="시간 선택"
          readonly>
          <template #append>
            <q-icon name="event" color="orange" @click="modalStore.openDateSelectModal()" style="cursor: pointer;"/>
            <DateSelectDialog
              @close="modalStore.closeDateSelectModal()" @submit="dateSubmitEvent"/>
          </template>
        </q-input>
      </q-card-section>

      <q-card-section class="q-pt-none" style="text-align: center; margin-top: 30px">
        <q-input
          filled v-model="addTodoDialogStoreData.todoTitle" label="제목" maxlength="30" lazy-rules
          :rules="[val => val.length >= 1 || '빈칸은 입력하실 수 없습니다.']"/>
        <br>
        <q-input type="textarea" filled v-model="addTodoDialogStoreData.todoContent" label="메모" maxlength="500"/>
      </q-card-section>

      <q-card-actions align="right" class="text-primary">
        <q-btn flat label="취소" @click="modalStore.closeAddTodoModal()"/>
        <q-btn flat label="등록"  :disable="!hasTitle" @click="insertTodoEvent"/>
      </q-card-actions>
    </q-card>
  </q-dialog>

</template>

<script setup>
import {computed, onBeforeUnmount, ref, watch} from "vue";
import DateSelectDialog from "components/body/add-task/DateSelectDialog.vue";
import {Notify} from "quasar";
import TodoApi from "src/common/todo/TodoApi";

import {useDialogModalStore} from "stores/dialog_modal";
import {useDateTimeDialogDataStore} from "stores/date_time_dialog_data";
import {useAddTodoDialogDataStore} from "stores/add_todo_dialog_data";

// add task 모달창
const props = defineProps(['addTaskModal']);
const emits = defineEmits(['submit']);
const modalStore = useDialogModalStore();
const dateTimeStore = useDateTimeDialogDataStore();
const addTodoDialogStore = useAddTodoDialogDataStore();

const addTodoDialogStoreData = addTodoDialogStore.data;

const addTask = ref(props.addTaskModal);

const form = ref({
  "isDateSelectValue": false,
});


const dateSubmitEvent = () => {
  form.value.isDateSelectValue = true
}


const sendToAddTaskModalClose = () => {
  emits('submit')
  form.value.isDateSelectValue = false;// 날짜 선택 여부
  modalStore.closeAddTodoModal();
}


const insertTodoEvent = async () => {

  const data = await TodoApi.insertTodoApi(addTodoDialogStoreData.todoTitle, addTodoDialogStoreData.todoContent, dateTimeStore.getDateTimeForApiRequest);

  if (data.code === 201) {
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

onBeforeUnmount(() => {
  form.value.isDateSelectValue = false;
  dateTimeStore.init();
  addTodoDialogStore.init();
})

const hasTitle = computed(() => {
  return addTodoDialogStoreData.todoTitle.length !== 0;
})


</script>

<style scoped>


</style>
