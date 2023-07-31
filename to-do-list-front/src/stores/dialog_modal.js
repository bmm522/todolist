import { defineStore } from 'pinia'
import {ref} from "vue";


export const useDialogModalStore = defineStore('dialogModalId',() => {
  const data = ref({
    "addTodoModal" : false,
    "editTodoModal" : false,
    "dateSelectModal" : false,
    "timeSelectModal" : false,
  });

  const openAddTodoModal = () => {
    data.value.addTodoModal = true;
  }

  const closeAddTodoModal = () => {
    data.value.addTodoModal = false;
  }
  const openEditTodoModal = () => {
    data.value.editTodoModal = true;
  }

  const closeEditTodoModal = () => {
    data.value.editTodoModal = false;
  }
  const openDateSelectModal = () => {
    data.value.dateSelectModal = true;
  }

  const closeDateSelectModal = () => {
    data.value.dateSelectModal = false;
  }
  const openTimeSelectModal = () => {
    data.value.timeSelectModal = true;
  }

  const closeTimeSelectModal = () => {
    data.value.timeSelectModal = false;
  }

  return {data, openAddTodoModal, closeAddTodoModal, openEditTodoModal, closeEditTodoModal, openDateSelectModal, closeDateSelectModal, openTimeSelectModal, closeTimeSelectModal}
})
