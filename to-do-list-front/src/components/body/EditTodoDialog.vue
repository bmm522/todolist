<template>
  <q-dialog v-model="store.dialogModal.data.editTodoModal" persistent>
    <q-card style="min-width: 350px">
      <q-card-section class="q-pt-none" style="text-align: center; margin-top: 30px">
        <q-input
color="grey-3" label-color="orange" outlined v-model="store.dateTimeDialog.getShowDateTime"
                 label="시간 선택" readonly>
          {{ store.dateTimeDialog.getShowDateTime }}

          <template #append>
            <q-icon name="event" color="orange" @click="dateSelectModalOpenEvent" style="cursor: pointer;"/>
            <DateSelectDialog/>
          </template>
        </q-input>
      </q-card-section>

      <q-card-section class="q-pt-none" style="text-align: center; margin-top: 30px">
        <q-input
          filled v-model="store.editTodoDialog.data.todoTitle" label="제목" :dense="dense" maxlength="30"
          lazy-rules
          :rules="[val => val.length >= 1 || '빈칸은 입력하실 수 없습니다.']"/>
        <br>
        <q-input
          type="textarea" filled v-model="store.editTodoDialog.data.todoContent" label="메모"
          maxlength="500"/>
        <q-checkbox v-model="isTodoDone" label="할 일을 완료했습니까?"/>
      </q-card-section>

      <q-card-actions align="right" class="text-primary">
        <q-btn flat label="취소" @click="closeEvent"/>
        <q-btn v-if="hasTitle" flat label="수정" @click="updateTodoEvent"/>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup>

import {computed, onBeforeUnmount, onMounted, ref, watch} from "vue";
import DateSelectDialog from "components/body/add-task/DateSelectDialog.vue";
import TodoApi from "src/common/todo/TodoApi";
import DateFormmater from "src/common/DateFormmater";
import {useEditTodoDialogDataStore} from "stores/edit_todo_dialog_data";
import {useStore} from "stores/store";

const emits = defineEmits(['submitData']);
const isTodoDone = ref(false);
const store = useStore;


const updateTodoEvent = async () => {


  const data = await TodoApi.updateTodoApi(
    store.editTodoDialog.data.todoId,
    store.editTodoDialog.data.todoTitle,
    store.editTodoDialog.data.todoContent,
    store.dateTimeDialog.getDateTimeForApiRequest,
    isTodoDone.value ? 'Y': 'N');

  isTodoDone.value = false;
  emits('submitData');
}

const closeEvent = () => {
  store.dateTimeDialog.init();
  store.editTodoDialog.init();
  store.dialogModal.closeEditTodoModal();
  isTodoDone.value = false;

}


const hasTitle = computed(() => {
  return store.editTodoDialog.data.todoTitle.length !== 0;
})

onMounted(() => {
  if (store.editTodoDialog.isTodoDone) {
    isTodoDone.value = true;
  }
})


onBeforeUnmount(() => {
  closeEvent();
})

</script>

<style scoped>

</style>
