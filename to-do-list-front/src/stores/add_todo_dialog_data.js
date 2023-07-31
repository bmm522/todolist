import { defineStore } from 'pinia'
import {ref} from "vue";

export const useAddTodoDialogDataStore = defineStore('addTodoDialogDataId', () => {
  const addTodoDialogDataId = ref(0);

  const data = ref({
    "todoTitle" : "",
    "todoContent" : "",
  });

  const init = () => {
    data.value.todoTitle = "";
    data.value.todoContent = "";
  }

  return {data, init}
})
