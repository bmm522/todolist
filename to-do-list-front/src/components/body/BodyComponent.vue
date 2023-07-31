<template>
  <q-page-container>
    <ToDoList/>
    <EditTaskDialog @submitData="updateListFromAddTaskDialog"/>
    <AddTaskButton @submitData="updateListFromAddTaskDialog"/>
  </q-page-container>
</template>

<script setup>

import ToDoList from "components/body/ToDoList.vue";
import AddTaskButton from "components/body/add-task/AddTodoButton.vue";
import {ref} from "vue";
import EditTaskDialog from "components/body/EditTodoDialog.vue";

const updateData = ref(0);

const updateListFromAddTaskDialog = (value, text) => {
  updateData.value++;
}


</script>

<style scoped>

</style>


