<template>
  <div class="q-px-lg q-py-md">
    <div class="q-pa-sm rounded-borders" :class="$q.dark.isActive ? 'bg-grey-9' : 'bg-grey-2'">
      <q-checkbox
        name="accept_agreement"
        v-model="isGetBeforeData"
        label="지난 날짜 포함해서 TodoList 보기"
        @click="isGetBeforeDataStatusCheckBoxClickEvent"
      />
    </div>
    <br>
    <div v-if="!isGetBeforeData" class="q-pa-sm rounded-borders" :class="$q.dark.isActive ? 'bg-grey-9' : 'bg-grey-2'">

      <q-option-group
        v-model="searchGroup"
        :options="searchOptions"
        color="primary"
        inline
      />


    </div>

    <br>

    <q-toggle
      v-model="batchDeleteToggle"
      label="일괄 삭제 처리하기"
    />
    <q-btn
style="margin-left: 30px" v-if="batchDeleteToggle" color="red" icon-right="delete" label="삭제 하기"
           @click="batchDeleteClickEvent"/>


    <br>
    <q-toggle
      v-model="batchTodoDoneYToggle"
      label="할일 일괄 완료 처리하기"
    />

    <q-btn
style="margin-left: 30px" v-if="batchTodoDoneYToggle" color="blue" icon-right="send" label="할일 완료 처리"
           @click="batchUpdateTodoDoneYClickEvent"/>

    <br>
    <q-toggle
      v-model="batchTodoDoneNToggle"
      label="할일 일괄 취소 처리하기"
    />

    <q-btn
style="margin-left: 30px" v-if="batchTodoDoneNToggle" color="red" icon-right="send" label="할일 취소 처리"
           @click="batchUpdateTodoDoneNClickEvent"/>


    <q-input
      v-if="searchGroup === 'todoTitleSearch'  " outlined bottom-slots v-model="searchTodoTitle" label="제목검색"
    >

      <template #append>
        <q-icon name="search" class="cursor-pointer" @click="searchEvent"/>
      </template>
      <!--      v-if="searchGroup === 'todoAtSearch'" -->
    </q-input>

    <q-input
      v-if="searchGroup === 'todoAtSearch' " type="date" filled v-model="searchTodoAt" label="날짜검색"
    >
      <template #append>
        <q-icon name="search" class="cursor-pointer" @click="searchEvent"/>
      </template>

    </q-input>
    <q-timeline color="secondary">
      <div v-for="date in Object.keys(sortedTodoMap)" :key="date">
        <q-timeline-entry heading> {{ date }}</q-timeline-entry>


        <q-timeline-entry
          v-for="todo in sortedTodoMap[date]" :key="todo.todoId"
          :title="todo.todoTitle"
          :subtitle="todo.todoAt.substring(11, 16)"
          side="left"
          :icon="todo.todoDone === 'Y' ? 'done_all':'edit'"
          :body="todo.todoContent"
          :color="todo.todoDone === 'Y' ? 'orange':'blue'"
          @click="editModalOpenEvent(todo)"
        >

          <q-checkbox v-if="batchDeleteToggle" v-model="batchDeleteTodoId" :val="todo.todoId" label="삭제" color="teal"/>
          <q-checkbox
v-if="batchTodoDoneYToggle && todo.todoDone === 'N'" v-model="batchUpdateDoneYTodoId"
                      :val="todo.todoId" label="할일 완료" color="orange"/>
          <q-checkbox
v-if="batchTodoDoneNToggle && todo.todoDone === 'Y'" v-model="batchUpdateDoneNTodoId"
                      :val="todo.todoId" label="할일 취소" color="red"/>
          <!--          {{batchDeleteCheckbox}}-->
        </q-timeline-entry>


      </div>
    </q-timeline>

  </div>
</template>

<script setup>

import {computed, onBeforeUnmount, onMounted, ref, watch, watchEffect} from "vue";
import TodoApi from "src/common/todo/TodoApi";
import {useDialogModalStore} from "stores/dialog_modal";
import {useEditTodoDialogDataStore} from "stores/edit_todo_dialog_data";
import {useStore} from "stores/store";
import {Notify} from "quasar";

const props = defineProps(["updateData"]);

const batchTodoDoneYToggle = ref(false);
const batchTodoDoneNToggle = ref(false);
const batchDeleteToggle = ref(false);


const modalStore = useDialogModalStore();
const editTodoDialogStore = useEditTodoDialogDataStore();

const batchDeleteTodoId = ref([]);
const batchUpdateDoneYTodoId = ref([]);
const batchUpdateDoneNTodoId = ref([]);


const searchGroup = ref('todoAllSearch');
const searchOptions = [
  {
    label: '모두',
    value: 'todoAllSearch'
  },
  {
    label: '제목으로 검색',
    value: 'todoTitleSearch'
  },
  {
    label: '날짜로 검색',
    value: 'todoAtSearch'
  }

];

const searchTodoTitle = ref("");
const searchTodoAt = ref();

const store = useStore;

const isGetBeforeData = ref(false);
const isGetBeforeDataStatus = ref('N');
const tempMap = ref({});
const todoMap = ref({});


let page = 1;


const test = () => {
  console.log('하이');
}


const loadPage = async (isUpdate, isGetBeforeDataStatus, searchGroup) => {
  tempMap.value = todoMap.value;
  const searchCondition = {
    "todoTitle": undefined,
    "todoAt": undefined,
  }

  if (searchGroup === 'todoTitleSearch') {
    page = 1;
    searchCondition.todoTitle = searchTodoTitle.value;
    searchCondition.todoAt = undefined;
  }

  if (searchGroup === 'todoAtSearch') {
    page = 1;
    searchCondition.todoTitle = undefined;
    searchCondition.todoAt = searchTodoAt.value;
  }

  const loadData = await TodoApi.getTodoListApi(page, isUpdate, isGetBeforeDataStatus, searchCondition);
  const timeBucketTodoMap = loadData.body.timeBucketTodoMap;

  if (Object.keys(timeBucketTodoMap).length === 0) {
    if (searchCondition.todoTitle === undefined && searchCondition.todoAt === undefined) {
      page--;
      return;
    }
 else {
      todoMap.value = {};
    }

  }
  for (const date in timeBucketTodoMap) {
    todoMap.value[date] = timeBucketTodoMap[date];
  }


  page++;
  return timeBucketTodoMap;
}


const isGetBeforeDataStatusCheckBoxClickEvent = async () => {

  searchGroup.value = "todoAllSearch";
}


const editModalOpenEvent = (todo) => {
  modalStore.openEditTodoModal();
  editTodoDialogStore.createFromTodo(todo);
  store.dateTimeDialog.initFromTodoAt(todo.todoAt);
}

const handleScroll = async () => {
  if (window.innerHeight + window.scrollY >= document.body.scrollHeight - 1) {
    await loadPage('N', isGetBeforeDataStatus.value, searchGroup.value);
  }
};

const batchDeleteClickEvent = async () => {
  console.log(batchDeleteTodoId.value.length);
  if (batchDeleteTodoId.value.length === 0) {
    Notify.create({
      message: '적어도 하나 이상의 Todo를 선택해주세요.',
      color: 'red'
    })
    return;
  }

  await TodoApi.batchDeleteTodoListApi(batchDeleteTodoId.value);
  todoMap.value = {};
  page = 1;
  batchDeleteToggle.value = false;
  batchDeleteTodoId.value = [];
  await loadPage('N', isGetBeforeDataStatus.value, searchGroup.value);
}

const batchUpdateTodoDoneYClickEvent = async () => {
  if (batchUpdateDoneYTodoId.value.length === 0) {
    Notify.create({
      message: '적어도 하나 이상의 Todo를 선택해주세요.',
      color: 'red'
    })
    return;
  }

  await TodoApi.batchUpdateTodoDoneApi(batchUpdateDoneYTodoId.value, 'Y');
  todoMap.value = {};
  page = 1;
  batchTodoDoneYToggle.value = false;
  batchUpdateDoneYTodoId.value = [];
  await loadPage('N', isGetBeforeDataStatus.value, searchGroup.value);
}

const batchUpdateTodoDoneNClickEvent = async () => {
  if (batchUpdateDoneNTodoId.value.length === 0) {
    Notify.create({
      message: '적어도 하나 이상의 Todo를 선택해주세요.',
      color: 'red'
    })
    return;
  }

  await TodoApi.batchUpdateTodoDoneApi(batchUpdateDoneNTodoId.value, 'N');
  todoMap.value = {};
  page = 1;
  batchTodoDoneNToggle.value = false;
  batchUpdateDoneNTodoId.value = [];
  await loadPage('N', isGetBeforeDataStatus.value, searchGroup.value);
}


const searchEvent = async () => {

  await loadPage('N', isGetBeforeDataStatus.value, searchGroup.value);
}

onMounted(async () => {
  window.addEventListener("scroll", handleScroll);
  await loadPage('N', isGetBeforeDataStatus.value, searchGroup.value);
});

onBeforeUnmount(() => {
  window.removeEventListener("scroll", handleScroll);
})

watch(() => props.updateData, async (newValue, oldValue) => {

  await loadPage('Y', isGetBeforeDataStatus.value, searchGroup.value);
});

const sortedTodoMap = computed(() => {

  return Object.fromEntries(
    Object.entries(todoMap.value).sort((a, b) => {
      return new Date(a[0]).getTime() - new Date(b[0]).getTime();
    })
  );
});

watch(() => isGetBeforeData.value, async (newValue, oldValue) => {
  if (newValue) {
    isGetBeforeDataStatus.value = 'Y';

    await loadPage('Y', isGetBeforeDataStatus.value, searchGroup);
  }
 else {
    isGetBeforeDataStatus.value = 'N';
    todoMap.value = {};
    await loadPage('Y', isGetBeforeDataStatus.value, searchGroup);
  }
})

watch(() => searchGroup.value, async (newValue, oldValue) => {

  if (newValue === "todoAllSearch") {
    searchTodoTitle.value = undefined;
    searchTodoAt.value = undefined;
    page = 1;
    await loadPage('N', isGetBeforeDataStatus.value, newValue);
  }
})


</script>

<style scoped>

</style>
