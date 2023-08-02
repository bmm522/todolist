<template>
  <div class="q-px-lg q-py-md" style="padding: 24px 800px">
    <q-dialog v-model="deleteConfirmModal">
      <q-card style="width: 350px">
        <q-card-section>
          <div class="text-h6" style="color: red">주의</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          한번 삭제하면 다시 되돌릴 수 없습니다.
          <br />
          그래도 삭제하시겠습니까 ?
        </q-card-section>

        <q-card-actions align="right" class="bg-white text-teal">
          <q-btn
            flat
            label="취소"
            v-close-popup
            @click="deleteConfirmModalClose"
          />
          <q-btn
            flat
            label="삭제"
            v-close-popup
            @click="batchDeleteClickEvent"
          />
        </q-card-actions>
      </q-card>
    </q-dialog>
    <div
      class="q-pa-sm rounded-borders"
      :class="$q.dark.isActive ? 'bg-grey-9' : 'bg-grey-2'"
    >
      <q-checkbox
        name="accept_agreement"
        v-model="isGetBeforeData"
        label="지난 날짜 포함해서 TodoList 보기"
      />

      <q-btn
        v-if="isSearchTitleGroup"
        name="accept_agreement"
        v-model="isGetBeforeData"
        @click="reOpenTitleModal"
        label="재검색 하기"
        color="blue"
        style="margin-left: 20px"
      />

      <q-btn
        v-if="isSearchTodoAtGroup"
        name="accept_agreement"
        v-model="isGetBeforeData"
        @click="reOpenTodoAtModal"
        label="재검색 하기"
        color="blue"
        style="margin-left: 20px"
      />
    </div>

    <br />
    <div
      class="q-pa-sm rounded-borders"
      :class="$q.dark.isActive ? 'bg-grey-9' : 'bg-grey-2'"
    >
      <q-option-group
        v-model="searchGroup"
        :options="searchOptions"
        color="primary"
        inline
      />
    </div>
    <q-dialog v-model="searchModal.dateModal">
      <q-card>
        <q-date v-model="searchTodoAt" range emit-immediately />
        <q-card-actions align="right" class="text-primary">
          <q-btn flat label="취소" @click="closeSearchBox" />
          <q-btn flat label="검색" @click="searchTodoAtEvent" />
        </q-card-actions>
      </q-card>
    </q-dialog>

    <q-dialog v-model="searchModal.titleSearchModal">
      <q-card style="min-width: 350px">
        <q-input
          v-if="searchGroup === 'todoTitleSearch'"
          outlined
          bottom-slots
          v-model="searchTodoTitle"
          label="제목검색"
          @keyup.enter.exact="searchTodoTitleEvent"
        >
        </q-input>

        <q-card-actions align="right" class="text-primary">
          <q-btn flat label="취소" @click="closeSearchBox" />
          <q-btn flat label="검색" @click="searchTodoTitleEvent" />
        </q-card-actions>
      </q-card>
    </q-dialog>
    <br />
    <div class="flex">
      <q-toggle v-model="batchDeleteToggle" label="일괄 삭제 처리하기" />

      <br />
      <q-toggle
        v-model="batchTodoDoneYToggle"
        label="할일 일괄 완료 처리하기"
      />

      <br />
      <q-toggle
        v-model="batchTodoDoneNToggle"
        label="할일 일괄 취소 처리하기"
      />
    </div>
    <q-page-sticky position="bottom-left" :offset="[600, 1250]">
      <q-btn
        style="margin-left: 30px"
        v-if="batchDeleteToggle"
        color="red"
        icon-right="delete"
        label="삭제 하기"
        @click="deleteConfirmModalOpen"
      />
    </q-page-sticky>
    <q-page-sticky position="bottom-left" :offset="[590, 1170]">
      <q-btn
        style="margin-left: 30px"
        v-if="batchTodoDoneYToggle"
        color="blue"
        icon-right="send"
        label="할일 완료 처리"
        @click="batchUpdateTodoDoneYClickEvent"
      />
    </q-page-sticky>
    <q-page-sticky position="bottom-left" :offset="[590, 1090]">
      <q-btn
        style="margin-left: 30px"
        v-if="batchTodoDoneNToggle"
        color="red"
        icon-right="send"
        label="할일 취소 처리"
        @click="batchUpdateTodoDoneNClickEvent"
      />
    </q-page-sticky>
    <div class="q-px-lg q-py-md bg-yellow-1 text-black">
      <q-timeline color="teal-10">
        <div v-for="date in Object.keys(sortedTodoMap)" :key="date">
          <q-timeline-entry heading> {{ date }}</q-timeline-entry>

          <q-timeline-entry
            v-for="todo in sortedTodoMap[date]"
            :key="todo.todoId"
            :title="todo.todoTitle"
            :subtitle="todo.todoAt.substring(11, 16)"
            side="left"
            :icon="todo.todoDone === 'Y' ? 'done_all' : 'edit'"
            :color="todo.todoDone === 'Y' ? 'orange' : 'blue'"
            @click="editModalOpenEvent(todo)"
            :is-todo-done="store.editTodoDialogData.isTodoDone"
            @mouseover="onEntryHover(true, todo.todoId)"
            @mouseleave="onEntryHover(false, todo.todoId)"
            :class="{
                            'hovered-entry': isHoveredEntry === todo.todoId,
                        }"
            style="cursor: pointer"
          >
            <div>
              {{ todo.todoContent }}
            </div>
            <div>
              <q-checkbox
                v-if="batchDeleteToggle"
                v-model="batchDeleteTodoId"
                :val="todo.todoId"
                label="삭제"
                color="teal"
              />
              <q-checkbox
                v-if="
                                    batchTodoDoneYToggle &&
                                    todo.todoDone === 'N'
                                "
                v-model="batchUpdateDoneYTodoId"
                :val="todo.todoId"
                label="할일 완료"
                color="orange"
              />
              <q-checkbox
                v-if="
                                    batchTodoDoneNToggle &&
                                    todo.todoDone === 'Y'
                                "
                v-model="batchUpdateDoneNTodoId"
                :val="todo.todoId"
                label="할일 취소"
                color="red"
              />
              <!--          {{batchDeleteCheckbox}}-->
            </div>
            <hr />
            <hr />
          </q-timeline-entry>
        </div>
      </q-timeline>
    </div>
  </div>
</template>

<script setup>
import { computed, onBeforeUnmount, onMounted, ref, watch } from "vue";
import TodoApi from "src/common/todo/TodoApi";

import { useStore } from "stores/store";

import dayjs from "dayjs";
import CommonNotify from "src/common/CommonNotify";

// Store 및 props 변수 초기화
const store = useStore;
const props = defineProps(["updateData"]); // 부모 컴포넌트로부터 updateData 프로퍼티를 전달받음

// 상태 변수들 초기화
const deleteConfirmModal = ref(false); // 삭제 확인 모달 상태 변수
const batchTodoDoneYToggle = ref(false); // 일괄 완료 토글 상태 변수
const batchTodoDoneNToggle = ref(false); // 일괄 미완료 토글 상태 변수
const batchDeleteToggle = ref(false); // 일괄 삭제 토글 상태 변수
const isHoveredEntry = ref(null); // 요소에 마우스 오버 시 해당 Todo 아이디를 가리키는 상태 변수

// 일괄 처리를 위한 Todo 아이디 배열 상태 변수들 초기화
const batchDeleteTodoId = ref([]); // 일괄 삭제할 Todo 아이디 배열
const batchUpdateDoneYTodoId = ref([]); // 일괄 완료 처리할 Todo 아이디 배열
const batchUpdateDoneNTodoId = ref([]); // 일괄 미완료 처리할 Todo 아이디 배열

// 검색 관련 상태 변수들 초기화
const searchGroup = ref("todoAllSearch"); // 검색 그룹 상태 변수, 기본값은 "모두"
const searchOptions = [ // 검색 옵션들 배열
  {
    label: "모두",
    value: "todoAllSearch"
  },
  {
    label: "제목으로 검색",
    value: "todoTitleSearch"
  },
  {
    label: "날짜로 검색",
    value: "todoAtSearch"
  }
];
const searchCondition = { // 검색 조건 객체
  todoTitle: undefined, // Todo 제목 검색어
  fromTodoAt: undefined, // Todo의 시작 날짜 범위
  toTodoAt: undefined // Todo의 종료 날짜 범위
};
const searchTodoTitle = ref(""); // Todo 제목 검색어 상태 변수
const searchModal = ref({ // 검색 모달 상태 변수
  dateModal: false, // 날짜 검색 모달 표시 여부
  titleSearchModal: false // 제목 검색 모달 표시 여부
});
const dateFormat = "YYYY/MM/DD"; // 날짜 형식
const searchTodoAt = ref({ // Todo 날짜 검색 범위 상태 변수
  from: dayjs().format(dateFormat), // 시작 날짜
  to: dayjs().add(1, "d").format(dateFormat) // 종료 날짜 (하루 뒤)
});

// 데이터 로드 및 상태 관리를 위한 변수들 초기화
const isGetBeforeData = ref(false); // 이전 데이터 가져오기 여부
const isGetBeforeDataStatus = ref("N"); // 이전 데이터 가져오기 상태 ("Y": 활성화, "N":  비활성화)
const tempMap = ref({}); // 이전 데이터 맵
const todoMap = ref({}); // Todo 데이터 맵

let page = 1; // 현재 페이지 초기화

// 페이지 로드 함수
const loadPage = async (isUpdate, isGetBeforeDataStatus, searchGroup) => {
  // 이전 데이터 맵 복사
  tempMap.value = todoMap.value;

  // 검색 그룹에 따른 검색 조건 설정
  if (searchGroup === "todoTitleSearch") {
    page = 1;
    searchCondition.todoTitle = searchTodoTitle.value;
    searchCondition.fromTodoAt = undefined;
    searchCondition.toTodoAt = undefined;
  }

  if (searchGroup === "todoAtSearch") {
    page = 1;
    searchCondition.todoTitle = undefined;
    searchCondition.fromTodoAt = searchTodoAt.value.from;
    searchCondition.toTodoAt = searchTodoAt.value.to;
  }

  // Todo 목록 API 호출
  const loadData = await TodoApi.getTodoListApi(
    page,
    isUpdate,
    isGetBeforeDataStatus,
    searchCondition
  );
  const timeBucketTodoMap = loadData.body.timeBucketTodoMap;

  // 로드된 데이터가 없는 경우 처리
  if (Object.keys(timeBucketTodoMap).length === 0) {
    if (
      searchCondition.todoTitle === undefined &&
      searchCondition.todoAt === undefined
    ) {
      page--;
      return;
    } else {
      todoMap.value = {};
    }
  }

  // 로드된 데이터를 Todo 맵에 추가
  for (const date in timeBucketTodoMap) {
    todoMap.value[date] = timeBucketTodoMap[date];
  }

  page++;
  return timeBucketTodoMap;
};

// 요소에 마우스 호버 시 호출되는 함수
const onEntryHover = (hovered, todoId) => {
  console.log(todoId);
  isHoveredEntry.value = hovered ? todoId : null;
};

// Todo 편집 모달을 열기 위한 함수
const editModalOpenEvent = (todo) => {
  store.dialogModal.openEditTodoModal(); // Todo 편집 모달 열기
  store.editTodoDialogData.createFromTodo(todo); // Todo 데이터를 사용하여 편집 모달 데이터 초기화
  store.dateTimeDialogData.initFromTodoAt(todo.todoAt); // Todo 시작 날짜 데이터로 날짜/시간 모달 데이터 초기화
};

// 스크롤 이벤트 처리 함수
const handleScroll = async () => {
  if (window.innerHeight + window.scrollY >= document.body.scrollHeight - 1) {
    await loadPage("N", isGetBeforeDataStatus.value, searchGroup.value);
  }
};

// 일괄 삭제 버튼 클릭 이벤트 처리 함수
const batchDeleteClickEvent = async () => {
  if (batchDeleteTodoId.value.length === 0) {
    CommonNotify.fail("적어도 하나 이상의 Todo를 선택해주세요."); // 에러 메시지 표시
    return;
  }

  await TodoApi.batchDeleteTodoListApi(batchDeleteTodoId.value); // 선택한 Todo 일괄 삭제
  todoMap.value = {};
  page = 1;
  batchDeleteToggle.value = false;
  batchDeleteTodoId.value = [];
  await loadPage("N", isGetBeforeDataStatus.value, searchGroup.value);
};

// 검색 박스 닫기 함수
const closeSearchBox = () => {
  if (searchGroup.value === "todoAtSearch") {
    searchGroup.value = "todoAllSearch";
    searchModal.value.dateModal = false;
  }

  if (searchGroup.value === "todoTitleSearch") {
    searchGroup.value = "todoAllSearch";
    searchModal.value.titleSearchModal = false;
  }
};

// 일괄 완료 처리 버튼 클릭 이벤트 처리 함수
const batchUpdateTodoDoneYClickEvent = async () => {
  if (batchUpdateDoneYTodoId.value.length === 0) {
    CommonNotify.fail("적어도 하나 이상의 Todo를 선택해주세요."); // 에러 메시지 표시
    return;
  }

  await TodoApi.batchUpdateTodoDoneApi(batchUpdateDoneYTodoId.value, "Y"); // 선택한 Todo 일괄 완료 처리
  todoMap.value = {};
  page = 1;
  batchTodoDoneYToggle.value = false;
  batchUpdateDoneYTodoId.value = [];
  await loadPage("N", isGetBeforeDataStatus.value, searchGroup.value);
};

// 일괄 미완료 처리 버튼 클릭 이벤트 처리 함수
const batchUpdateTodoDoneNClickEvent = async () => {
  if (batchUpdateDoneNTodoId.value.length === 0) {
    CommonNotify.fail("적어도 하나 이상의 Todo를 선택해주세요."); // 에러 메시지 표시
    return;
  }

  await TodoApi.batchUpdateTodoDoneApi(batchUpdateDoneNTodoId.value, "N"); // 선택한 Todo 일괄 미완료 처리
  todoMap.value = {};
  page = 1;
  batchTodoDoneNToggle.value = false;
  batchUpdateDoneNTodoId.value = [];
  await loadPage("N", isGetBeforeDataStatus.value, searchGroup.value);
};

// 유저 아이디를 기반으로 왼쪽 또는 오른쪽을 반환하는 컴퓨티드 상태 변수
const side = computed((userId) => {
  return userId % 1 === 0 ? "left" : "right";
});

// Todo 제목 검색 모달 다시 열기 함수
const reOpenTitleModal = () => {
  searchTodoTitle.value = "";
  searchModal.value.titleSearchModal = true;
};

// Todo 날짜 검색 모달 다시 열기 함수
const reOpenTodoAtModal = () => {
  searchModal.value.dateModal = true;
};

// Todo 제목 검색 이벤트 처리 함수
const searchTodoTitleEvent = async () => {
  searchModal.value.titleSearchModal = false;
  initTodoMapAndPageAndToggleData();
  await loadPage("N", isGetBeforeDataStatus.value, searchGroup.value);
};
// Todo 날짜 검색 이벤트 처리 함수
const searchTodoAtEvent = async () => {
  searchModal.value.dateModal = false;
  initTodoMapAndPageAndToggleData();
  await loadPage("N", isGetBeforeDataStatus.value, searchGroup.value);
};

// 검색 이벤트 시에 기존의 데이터를 초기화 해주는 함수
const initTodoMapAndPageAndToggleData = () => {
  todoMap.value = {};
  page = 1;
  batchTodoDoneNToggle.value = false;
  batchUpdateDoneYTodoId.value = [];
  batchUpdateDoneNTodoId.value = [];
};
// 삭제 확인 모달 열기 함수

const deleteConfirmModalOpen = () => {
  deleteConfirmModal.value = true;
};


// 삭제 확인 모달 닫기 함수
const deleteConfirmModalClose = () => {
  deleteConfirmModal.value = false;
};

// 컴포넌트 마운트 시 스크롤 이벤트 리스너 등록 및 페이지 로드 함수 호출
onMounted(async () => {
  window.addEventListener("scroll", handleScroll);
  await loadPage("N", isGetBeforeDataStatus.value, searchGroup.value);
});

// 컴포넌트 언마운트 시 스크롤 이벤트 리스너 제거
onBeforeUnmount(() => {
  window.removeEventListener("scroll", handleScroll);
});

// Todo 제목 검색 그룹 여부를 반환하는 컴퓨티드 상태 변수
const isSearchTitleGroup = computed(() => {
  return searchGroup.value === "todoTitleSearch";
});

// Todo 날짜 검색 그룹 여부를 반환하는 컴퓨티드 상태 변수
const isSearchTodoAtGroup = computed(() => {
  return searchGroup.value === "todoAtSearch";
});

// 검색 모달의 날짜 범위 변경 시 호출되는 함수
watch(
  () => searchModal.value.dateModal,
  async (newValue, oldValue) => {
    searchTodoAt.value.from = dayjs().format(dateFormat);
    searchTodoAt.value.to = dayjs().add(1, "d").format(dateFormat);
  }
);

// props.updateData가 변경될 때마다 호출되는 함수
watch(
  () => props.updateData,
  async (newValue, oldValue) => {
    await loadPage("Y", isGetBeforeDataStatus.value, searchGroup.value);
  }
);

// Todo 맵을 날짜 기준으로 정렬하여 반환하는 컴퓨티드 상태 변수
const sortedTodoMap = computed(() => {
  return Object.fromEntries(
    Object.entries(todoMap.value).sort((a, b) => {
      return new Date(a[0]).getTime() - new Date(b[0]).getTime();
    })
  );
});

// isGetBeforeData 값이 변경될 때마다 호출되는 함수
watch(
  () => isGetBeforeData.value,
  async (newValue, oldValue) => {
    if (newValue) {
      console.log(searchGroup.value);
      isGetBeforeDataStatus.value = "Y";

      await loadPage("Y", isGetBeforeDataStatus.value, searchGroup);
    } else {
      console.log(searchGroup);
      isGetBeforeDataStatus.value = "N";
      todoMap.value = {};
      await loadPage("Y", isGetBeforeDataStatus.value, searchGroup);
    }
  }
);

// searchGroup 값이 변경될 때마다 호출되는 함수
watch(
  () => searchGroup.value,
  async (newValue, oldValue) => {
    // isGetBeforeData.value = false;

    if (newValue === "todoAtSearch") {
      searchTodoTitle.value = "";
      searchModal.value.dateModal = true;
    }

    if (newValue === "todoTitleSearch") {
      searchTodoAt.value = "";
      searchModal.value.titleSearchModal = true;
    }

    // 전체 선택 시 모두 초기화
    if (newValue === "todoAllSearch") {
      searchCondition.todoTitle = undefined;
      searchTodoTitle.value = undefined;
      searchCondition.fromTodoAt = undefined;
      searchCondition.toTodoAt = undefined;
      page = 1;
      await loadPage("N", isGetBeforeDataStatus.value, newValue);
    }
  }
);
</script>

<style scoped>
.hovered-entry {
  background-color: rgba(255, 255, 160, 0.49);
}
</style>
