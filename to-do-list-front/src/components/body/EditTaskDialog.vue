<template>
  <q-dialog v-model="editTodo" persistent>
    <q-card style="min-width: 350px">
      <q-card-section class="q-pt-none" style="text-align: center; margin-top: 30px" >
        <q-input color="grey-3" label-color="orange" outlined v-model="todoAt" label="시간 선택" readonly>
          <template #append>
            <q-icon name="event" color="orange"   @click="dateSelectModalOpenEvent" style="cursor: pointer;"/>
            {{dateSelectModalWhenEdit}}
            <DateSelectDialog   :date-select-modal-when-edit="dateSelectModalWhenEdit"     @close="dateSelectModalCloseEvent" :select-date="selectDate" :select-time="selectTime"/>
          </template>
        </q-input>
      </q-card-section>

      <q-card-section class="q-pt-none" style="text-align: center; margin-top: 30px" >
        <q-input filled v-model="title" label="제목" :dense="dense"  maxlength="30" lazy-rules :rules="[val => val.length >= 1 || '빈칸은 입력하실 수 없습니다.']" />
        <br>
        <q-input type="textarea" filled v-model="content" label="메모" maxlength="500"/>
      </q-card-section>


      {{editTodo}}
      <q-card-actions align="right" class="text-primary">
        <q-btn flat label="취소" />
        <q-btn flat label="수정"  />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup>

import { ref, watch } from "vue";
import DateSelectDialog from "components/body/add-task/DateSelectDialog.vue";

const props = defineProps(['editTaskModal', 'editBeforeData']);
const emits = defineEmits(['submit','close']);
const editTodo = ref(props.editTaskModal);
const dateSelectModalWhenEdit = ref(false);
const title = ref();
const content = ref();
const todoAt = ref();
const todoDone = ref();
const selectDate = ref();
const selectTime = ref();


const dateSelectModalOpenEvent = () => {
  dateSelectModalWhenEdit.value = true;

}
const dateSelectModalCloseEvent = () => {
  dateSelectModalWhenEdit.value = false;
}

watch(() => props.editTaskModal, (newValue, oldValue) => {
  editTodo.value = newValue;
})

watch(() => props.editBeforeData, (newValue, oldValue) => {
  title.value = newValue.todoTitle;
  content.value = newValue.todoContent;
  todoAt.value = newValue.todoAt.replace("T", " ").substring(0, 16);
  todoDone.value = newValue.todoDone;
  selectDate.value = newValue.todoAt.replace("T", " ").substring(0, 10);
  selectTime.value =  newValue.todoAt.replace("T", " ").substring(11, 16);
})
</script>

<style scoped>

</style>
