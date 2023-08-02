import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const useEditTodoDialogDataStore = defineStore(
    "editTodoDialogDataId",
    () => {
        const editTodoDialogDataId = ref(0);

        const data = ref({
            todoId: 0,
            todoTitle: "",
            todoContent: "",
            todoDone: "",
            isTodoDone: false,
        });

        const createFromTodo = (todo) => {
            data.value.todoId = todo.todoId;
            data.value.todoTitle = todo.todoTitle;
            data.value.todoContent = todo.todoContent;
            data.value.todoDone = todo.todoDone;
            data.value.isTodoDone = todo.todoDone === "Y" ? true : false;
        };

        const init = () => {
            data.value.todoId = 0;
            data.value.todoTitle = "";
            data.value.todoContent = "";
            data.value.todoDone = "";
        };

        const isTodoDone = computed(() => {
            return data.value.todoDone === "Y";
        });

        return { data, createFromTodo, init, isTodoDone };
    }
);
