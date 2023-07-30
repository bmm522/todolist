<template>
  <q-page-container>
    {{editTaskModal}}
    <ToDoList :update-data="updateData" @editTodoOpen="editTodoModalEvent"/>
    <EditTaskDialog :edit-task-modal="editTaskModal" :edit-before-data="editBeforeData"/>
    <AddTaskButton @submitData="updateListFromAddTaskDialog"/>

  </q-page-container>
</template>

<script setup>

import ToDoList from "components/body/ToDoList.vue";
import AddTaskButton from "components/body/add-task/AddTaskButton.vue";
import { ref } from "vue";
import EditTaskDialog from "components/body/EditTaskDialog.vue";

const updateData = ref();
const editBeforeData = ref();
const editTaskModal = ref(false);

const updateListFromAddTaskDialog = (value, text) => {
  updateData.value = {
    "todoTitle" : value.title,
    "todoContent": value.content,
    "todoAt" :value.todoTime,
    "key": value.key
  }
}

const editTodoModalEvent = (value) => {
  editTaskModal.value = true;
  editBeforeData.value = {
    "todoTitle" : value.todoTitle,
    "todoContent" : value.todoContent,
    "todoAt" : value.todoAt,
    "todoDone" : value.todoDone,
  }
}
</script>

<style scoped>

</style>
