<template>
  <div class="q-px-lg q-py-md">

    <q-timeline :layout="layout" color="secondary">
      <div v-for="date in Object.keys(todoMap)" :key="date">
        <q-timeline-entry heading> {{date}}</q-timeline-entry>


        <q-timeline-entry
          v-for="todo in todoMap[date]" :key="todo.todoId"
          :title="todo.todoTitle"
          :subtitle="todo.todoAt.substring(11, 16)"
          side="left"
          icon="edit"
          :body="todo.todoContent"
        >
        </q-timeline-entry>



      </div>
    </q-timeline>

  </div>
</template>

<script setup>

import ToDoBoxes from "components/body/ToDoForm.vue";
import { computed, nextTick, onBeforeUnmount, onMounted, ref, watch } from "vue";
import TodoApi from "src/common/todo/TodoApi";
import dayjs from "dayjs";
import ToDoForm from "components/body/ToDoForm.vue";

const props = defineProps(["updateData"]);
const todoMap = ref({});
let page = 1;
let isLastPage = false;

const loadNextPage = async () => {

  if (isLastPage) {
    return;
  }

  const nextPage = await TodoApi.getTodoListApi(page);

  if (Object.keys(nextPage.body.timeBucketTodoMap).length === 0) {
    // 다음 페이지의 데이터가 비어있으면 마지막 페이지로 간주하고 isLastPage를 true로 설정합니다.
    isLastPage = true;
    window.removeEventListener("scroll", handleScroll);
  }
  else {
    for (const key in nextPage.body.timeBucketTodoMap) {
      if (!todoMap.value[key]) {
        todoMap.value[key] = nextPage.body.timeBucketTodoMap[key];
      }
      else {
        todoMap.value[key] = [...todoMap.value[key], ...nextPage.body.timeBucketTodoMap[key]];
      }
    }
    page++;

    await loadNextPage(page - 1);
  }


};

const handleScroll = () => {
  if (window.innerHeight + window.scrollY >= document.body.scrollHeight) {
    loadNextPage();
  }
};

// Function to sort todo items based on todoAt property
const sortTodoByTime = (todos) => {
  return todos.sort((a, b) => {
    return new Date(a.todoAt).getTime() - new Date(b.todoAt).getTime();
  });
};

// Computed property to get the sorted dates
// const sortedDates = computed(() => {
//   return Object.keys(todoMap.value).sort((a, b) => {
//     return new Date(a).getTime() - new Date(b).getTime();
//   });
// });

// Computed property to get the sorted todo items for each date
const sortedTodoMap = computed(() => {
  const sortedMap = {};
  for (const date in todoMap.value) {
    sortedMap[date] = sortTodoByTime(todoMap.value[date]);
  }
  return sortedMap;
});

onMounted(async () => {
  window.addEventListener("scroll", handleScroll);
  await loadNextPage();
});

onBeforeUnmount(() => {
  window.removeEventListener("scroll", handleScroll);
});

watch(() => props.updateData,  async (newValue, oldValue) => {
  if (newValue) {
    // Access the value of the ref and update the todoMap
    const mapValue = todoMap.value;
    const newDate = newValue.key; // Assuming the new data has a "date" property

    // Check if the date already exists in todoMap
    if (mapValue[newDate]) {
      console.log('여기니?실행이야?');
      mapValue[newDate].push(newValue);
      mapValue[newDate] = sortTodoByTime(mapValue[newDate]);
      todoMap.value = Object.fromEntries(
        Object.entries(todoMap.value).sort((a, b) => {
          return new Date(a[0]).getTime() - new Date(b[0]).getTime();
        })
      );// If not, create a new array with the new data
    }



  }

});
</script>

<style scoped>

</style>
