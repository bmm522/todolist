<template>
  <div class="q-px-lg q-py-md">

    <q-timeline :layout="layout" color="secondary">
      <div v-for="date in Object.keys(todoMap)" :key="date">
        <q-timeline-entry heading> {{date}}</q-timeline-entry>


        <q-timeline-entry
          v-for="todo in todoMap[date]" :key="todo"
          :title="todo.todoTitle"
          :subtitle="todo.todoAt.substring(11, 16)"
          side="left"
          :icon="todo.todoDone === 'N' ? 'edit' : 'done_all'"
          :body="todo.todoContent"
          :color="todo.todoDone === 'N' ? 'blue' : 'orange'"
          @click="test(todo)"
        >



        </q-timeline-entry>



      </div>
    </q-timeline>

  </div>
</template>

<script setup>

import { onMounted, ref, watch } from "vue";
import TodoApi from "src/common/todo/TodoApi";

const props = defineProps(["updateData"]);
const emits = defineEmits(["editTodoOpen"]);
const todoMap = ref({});

let page = 1;

const loadPage = async (isUpdate) => {
  console.log('요청들어옴');
  const loadData = await TodoApi.getTodoListApi(page, isUpdate);
  const timeBucketTodoMap = loadData.body.timeBucketTodoMap;
  if(Object.keys(timeBucketTodoMap).length === 0) {
    page--;
    return;
  }
  for(const date in timeBucketTodoMap) {

      todoMap.value[date] = timeBucketTodoMap[date];
  }
    page++;
}


const test = (todo) => {
  emits('editTodoOpen', {
    "todoTitle" : todo.todoTitle,
    "todoContent" : todo.todoContent,
    "todoAt" : todo.todoAt,
    "todoDone" :todo.todoDone,
  });
}

onMounted(async () => {
  window.addEventListener("scroll", handleScroll);
  await loadPage('N');
});

const handleScroll = async () => {
  if (window.innerHeight + window.scrollY >= document.body.scrollHeight- 1) {
    await loadPage('N');
  }
};

watch(() => props.updateData,  async (newValue, oldValue) => {
  if (newValue) {
    await loadPage('Y');
  }
});


</script>

<style scoped>

.timeline-entry {
  /* 다른 스타일들 */
  position: relative; /* 커서용 효과를 위해 필요한 설정 */
}

.timeline-entry .icon-button {
  /* 아이콘 버튼의 기본 스타일 */
}

.timeline-entry .icon-button:hover {
  width: 100px
  /* 커서를 갖다댔을 때의 스타일 */
  /* 원하는 효과를 추가하세요 */
  /* 예를 들어, 배경색이 바뀌거나, 크기가 조정되거나, 그림자 효과가 추가되는 등 */
}
</style>
