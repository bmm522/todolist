<template>
  <div class="q-px-lg q-py-md">

    <q-timeline :layout="layout" color="secondary">
      <div v-for="date in Object.keys(todoMap)" :key="date">
        <q-timeline-entry heading> {{date}}</q-timeline-entry>


        <q-timeline-entry
          v-for="todo in todoMap[date]" :key="todo.todoId"
          :title="todo.todoTitle"
          :subtitle="todo.todoAt"
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
import { nextTick, onBeforeUnmount, onMounted, ref, watch } from "vue";
import TodoApi from "src/common/todo/TodoApi";
import dayjs from "dayjs";
import ToDoForm from "components/body/ToDoForm.vue";
const props = defineProps(['updateData']);
const todoMap = ref({});
let page = 1; // 전역 변수로 선언

const loadNextPage = async () => {
  // 다음 페이지 데이터를 요청합니다.
  const nextPage = await TodoApi.getTodoListApi(page); // 페이지 번호를 적절하게 설정해야 합니다.

  // 가져온 데이터를 현재 데이터에 추가합니다.
  for (const key in nextPage.body.timeBucketTodoMap) {
    if (!todoMap.value[key]) {
      todoMap.value[key] = nextPage.body.timeBucketTodoMap[key];
    }
    else {
      todoMap.value[key] = [...todoMap.value[key], ...nextPage.body.timeBucketTodoMap[key]];
    }
  }

  console.log(todoMap.value);
  page++; // 다음 페이지로 이동
};

const handleScroll = () => {
  // 스크롤 이벤트 핸들러

  if (window.innerHeight + window.scrollY >=  document.body.scrollHeight ) {
    // 스크롤이 맨 아래에 도달하면 다음 페이지를 요청합니다.
    loadNextPage();
  }
};

onMounted(async () => {
  console.log('마운트발동');
  // 컴포넌트가 마운트되면 스크롤 이벤트를 추가합니다.
  window.addEventListener('scroll', handleScroll);

  // 컴포넌트가 마운트될 때 첫 번째 페이지의 데이터를 불러옵니다.
  console.log(page); // page 값 확인용 출력
  await loadNextPage();
  console.log(todoMap.value); // todoMap 확인용 출력
});

onBeforeUnmount(() => {
  console.log('마운트삭제');
  // 컴포넌트가 언마운트되기 전에 스크롤 이벤트를 제거합니다.
  window.removeEventListener('scroll', handleScroll);
});


watch(() => props.updateData, (newValue, oldValue) => {
  if (newValue) {
    // Access the value of the ref and update the todoMap
    const mapValue = todoMap.value;
    const newDate = newValue.key; // Assuming the new data has a "date" property

    // Check if the date already exists in todoMap
    if (!mapValue[newDate]) {
      console.log('여기가실행이야?');
      mapValue[newDate] = [newValue]; // If not, create a new array with the new data
    }
 else {
      console.log('여기니?실행이야?');
      mapValue[newDate].push(newValue); // If the date exists, add the new data to the existing array
    }
  }


})

</script>

<style scoped>

</style>
