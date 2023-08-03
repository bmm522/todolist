<template>
    <q-page-container>
        <ToDoList :update-data="updateData" />
        <EditTaskDialog @submitData="updateListFromAddTaskDialog" />
        <AddTaskButton @submitData="updateListFromAddTaskDialog" />
    </q-page-container>
</template>

<script setup>
import AddTaskButton from "components/body/add-task/AddTodoButton.vue";
import ToDoList from "components/body/ToDoList.vue";
import EditTaskDialog from "components/body/EditTodoDialog.vue";
import { ref } from "vue";

const updateData = ref(false);

const updateListFromAddTaskDialog = (value, text) => {
    updateData.value++;
};
</script>

<style scoped></style>
