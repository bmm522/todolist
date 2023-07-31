<template>
  <q-page-sticky position="bottom-right" :offset="[18, 18]">
    <q-btn fab icon="add" color="accent" label="할 일 추가" @click="modalStore.openAddTodoModal()"/>
  </q-page-sticky>
  <AddTaskDialog  @submit="onSubmit"/>

</template>
<script setup>


import {ref} from "vue";
import AddTaskDialog from "components/body/add-task/AddTodoDialog.vue";
import { Notify } from "quasar";
import {useDialogModalStore} from "stores/dialog_modal";

const addTaskModal = ref(false);
const modalStore = useDialogModalStore();
const emits = defineEmits(['submitData']);
const onSubmit = () => {
  emits('submitData')
}




</script>

<style scoped>

</style>
