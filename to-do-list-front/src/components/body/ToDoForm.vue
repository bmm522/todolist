<template>


<!--{{todoMap}}-->
<!--  <div>-->
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
<!--  </div>-->

</template>

<script setup>
import { onBeforeUnmount, onMounted, ref } from "vue";
import TodoApi from "src/common/todo/TodoApi";


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

  // 컴포넌트가 마운트되면 스크롤 이벤트를 추가합니다.
  window.addEventListener('scroll', handleScroll);
  // 컴포넌트가 마운트될 때 첫 번째 페이지의 데이터를 불러옵니다.
  await loadNextPage();

});

onBeforeUnmount(() => {

  // 컴포넌트가 언마운트되기 전에 스크롤 이벤트를 제거합니다.
  window.removeEventListener('scroll', handleScroll);
});
</script>

<style scoped>

</style>
