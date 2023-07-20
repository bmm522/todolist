<template>
  <q-page-sticky position="bottom-right" :offset="[18, 18]">
    <q-btn fab icon="add" color="accent" label="할 일 추가" @click="openTaskModalEvent"/>
  </q-page-sticky>
  <AddTaskDialog :add-task-modal="addTaskModal" @close="onClose"/>
</template>
<script setup>


import {ref} from "vue";
import AddTaskDialog from "components/body/add-task/AddTaskDialog.vue";

const addTaskModal = ref(false);
const onClose = () => {
  addTaskModal.value = false
}

const openTaskModalEvent = () => {
  addTaskModal.value = true;
}
</script>

<style scoped>

</style>
