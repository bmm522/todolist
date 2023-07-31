<template>
  <div class="q-px-lg q-py-md">

    <q-timeline :layout="layout" color="secondary">
      <div v-for="date in Object.keys(sortedTodoMap)" :key="date">
        <q-timeline-entry heading> {{ date }}</q-timeline-entry>


        <q-timeline-entry
          v-for="todo in sortedTodoMap[date]" :key="todo"
          :title="todo.todoTitle"
          :subtitle="todo.todoAt.substring(11, 16)"
          side="left"
          :icon="todo.todoDone === 'Y' ? 'done_all':'edit'"
          :body="todo.todoContent"
          :color="todo.todoDone === 'Y' ? 'orange':'blue'"
          @click="editModalOpenEvent(todo)"
        >
        </q-timeline-entry>

      </div>
    </q-timeline>

  </div>
</template>

<script setup>

import {computed, onBeforeUnmount, onMounted, ref, watch} from "vue";
import TodoApi from "src/common/todo/TodoApi";
import {useDialogModalStore} from "stores/dialog_modal";
import {useEditTodoDialogDataStore} from "stores/edit_todo_dialog_data";
import {useStore} from "stores/store";

const props = defineProps(["updateData"]);
const emits = defineEmits(["editTodoOpen"]);

const modalStore = useDialogModalStore();
const editTodoDialogStore = useEditTodoDialogDataStore();
const store = useStore;

const todoMap = ref({});

let page = 1;

const loadPage = async (isUpdate) => {
  const loadData = await TodoApi.getTodoListApi(page, isUpdate);
  const timeBucketTodoMap = loadData.body.timeBucketTodoMap;
  if (Object.keys(timeBucketTodoMap).length === 0) {
    page--;
    return;
  }
  for (const date in timeBucketTodoMap) {
    todoMap.value[date] = timeBucketTodoMap[date];
  }
  page++;
}


const sortedTodoMap = computed(() => {
  return Object.fromEntries(
    Object.entries(todoMap.value).sort((a, b) => {
      return new Date(a[0]).getTime() - new Date(b[0]).getTime();
    })
  );
});


const editModalOpenEvent = (todo) => {
  modalStore.openEditTodoModal();
  editTodoDialogStore.createFromTodo(todo);
  store.dateTimeDialog.initFromTodoAt(todo.todoAt);
}

const handleScroll = async () => {
  if (window.innerHeight + window.scrollY >= document.body.scrollHeight - 1) {
    await loadPage('N');
  }
};

onMounted(async () => {
  window.addEventListener("scroll", handleScroll);
  await loadPage('N');
});

onBeforeUnmount(() => {
  window.removeEventListener("scroll", handleScroll);
})

watch(() => props.updateData, async (newValue, oldValue) => {
  await loadPage('Y');
});


</script>

<style scoped>


</style>
