<template>
  <q-page-container>
    <ToDoList :update-data="updateData"/>
    <AddTaskButton @submitData="updateListFromAddTaskDialog"/>
  </q-page-container>
</template>

<script setup>

import ToDoList from "components/body/ToDoList.vue";
import AddTaskButton from "components/body/add-task/AddTaskButton.vue";
import { ref } from "vue";

const updateData = ref();

const updateListFromAddTaskDialog = (value, text) => {
  updateData.value = {
    "todoTitle" : value.title,
    "todoContent": value.content,
    "todoAt" :value.todoTime,
    "key": value.key
  }

}
</script>

<style scoped>

</style>
