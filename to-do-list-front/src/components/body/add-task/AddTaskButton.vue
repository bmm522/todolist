<template>
  <q-page-sticky position="bottom-right" :offset="[18, 18]">
    <q-btn fab icon="add" color="accent" label="할 일 추가" @click="openTaskModalEvent"/>
  </q-page-sticky>
  <AddTaskDialog :add-task-modal="addTaskModal" @close="onClose" @submit="onSubmit"/>

</template>
<script setup>


import {ref} from "vue";
import AddTaskDialog from "components/body/add-task/AddTaskDialog.vue";
import { Notify } from "quasar";

const addTaskModal = ref(false);

const emits = defineEmits(['submitData']);
const onSubmit = (value, text) => {
  emits('submitData', {
    "title" : value.title,
    "content" : value.content,
    "todoTime" : value.todoTime,
    "key" : value.key,
  })
}

const onClose = () => {
  addTaskModal.value = false
}

const openTaskModalEvent = () => {
  addTaskModal.value = true;
}
</script>

<style scoped>

</style>
