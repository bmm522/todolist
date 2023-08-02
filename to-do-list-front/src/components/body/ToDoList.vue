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

const store = useStore;
const props = defineProps(["updateData"]);
const deleteConfirmModal = ref(false);
const batchTodoDoneYToggle = ref(false);
const batchTodoDoneNToggle = ref(false);
const batchDeleteToggle = ref(false);
const isHoveredEntry = ref(null);

const batchDeleteTodoId = ref([]);
const batchUpdateDoneYTodoId = ref([]);
const batchUpdateDoneNTodoId = ref([]);

const searchGroup = ref("todoAllSearch");
const searchOptions = [
    {
        label: "모두",
        value: "todoAllSearch",
    },
    {
        label: "제목으로 검색",
        value: "todoTitleSearch",
    },
    {
        label: "날짜로 검색",
        value: "todoAtSearch",
    },
];
const searchCondition = {
    todoTitle: undefined,
    fromTodoAt: undefined,
    toTodoAt: undefined,
};
const searchTodoTitle = ref("");
const searchModal = ref({
    dateModal: false,
    titleSearchModal: false,
});
const dateFormat = "YYYY/MM/DD";
const searchTodoAt = ref({
    from: dayjs().format(dateFormat),
    to: dayjs().add(1, "d").format(dateFormat),
});

const isGetBeforeData = ref(false);
const isGetBeforeDataStatus = ref("N");
const tempMap = ref({});
const todoMap = ref({});

let page = 1;

const loadPage = async (isUpdate, isGetBeforeDataStatus, searchGroup) => {
    tempMap.value = todoMap.value;

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

    const loadData = await TodoApi.getTodoListApi(
        page,
        isUpdate,
        isGetBeforeDataStatus,
        searchCondition
    );
    const timeBucketTodoMap = loadData.body.timeBucketTodoMap;

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
    for (const date in timeBucketTodoMap) {
        todoMap.value[date] = timeBucketTodoMap[date];
    }

    page++;
    return timeBucketTodoMap;
};

const onEntryHover = (hovered, todoId) => {
    console.log(todoId);
    isHoveredEntry.value = hovered ? todoId : null;
};

const editModalOpenEvent = (todo) => {
    store.dialogModal.openEditTodoModal();
    store.editTodoDialogData.createFromTodo(todo);
    store.dateTimeDialogData.initFromTodoAt(todo.todoAt);
};

const handleScroll = async () => {
    if (window.innerHeight + window.scrollY >= document.body.scrollHeight - 1) {
        await loadPage("N", isGetBeforeDataStatus.value, searchGroup.value);
    }
};

const batchDeleteClickEvent = async () => {
    if (batchDeleteTodoId.value.length === 0) {
        CommonNotify.fail("적어도 하나 이상의 Todo를 선택해주세요.");
        return;
    }

    await TodoApi.batchDeleteTodoListApi(batchDeleteTodoId.value);
    todoMap.value = {};
    page = 1;
    batchDeleteToggle.value = false;
    batchDeleteTodoId.value = [];
    await loadPage("N", isGetBeforeDataStatus.value, searchGroup.value);
};
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
const batchUpdateTodoDoneYClickEvent = async () => {
    if (batchUpdateDoneYTodoId.value.length === 0) {
        CommonNotify.fail("적어도 하나 이상의 Todo를 선택해주세요.");
        return;
    }

    await TodoApi.batchUpdateTodoDoneApi(batchUpdateDoneYTodoId.value, "Y");
    todoMap.value = {};
    page = 1;
    batchTodoDoneYToggle.value = false;
    batchUpdateDoneYTodoId.value = [];
    await loadPage("N", isGetBeforeDataStatus.value, searchGroup.value);
};

const batchUpdateTodoDoneNClickEvent = async () => {
    if (batchUpdateDoneNTodoId.value.length === 0) {
        CommonNotify.fail("적어도 하나 이상의 Todo를 선택해주세요.");
        return;
    }

    await TodoApi.batchUpdateTodoDoneApi(batchUpdateDoneNTodoId.value, "N");
    todoMap.value = {};
    page = 1;
    batchTodoDoneNToggle.value = false;
    batchUpdateDoneNTodoId.value = [];
    await loadPage("N", isGetBeforeDataStatus.value, searchGroup.value);
};
const side = computed((userId) => {
    return userId % 1 === 0 ? "left" : "right";
});

const reOpenTitleModal = () => {
    searchTodoTitle.value = "";
    searchModal.value.titleSearchModal = true;
};

const reOpenTodoAtModal = () => {
    searchModal.value.dateModal = true;
};

const searchTodoTitleEvent = async () => {
    searchModal.value.titleSearchModal = false;
    todoMap.value = {};
    page = 1;
    batchTodoDoneNToggle.value = false;
    batchUpdateDoneNTodoId.value = [];
    await loadPage("N", isGetBeforeDataStatus.value, searchGroup.value);
};

const deleteConfirmModalOpen = () => {
    deleteConfirmModal.value = true;
};

const searchTodoAtEvent = async () => {
    searchModal.value.dateModal = false;
    todoMap.value = {};
    page = 1;
    batchTodoDoneNToggle.value = false;
    batchUpdateDoneNTodoId.value = [];
    console.log("들어오냐?");
    await loadPage("N", isGetBeforeDataStatus.value, searchGroup.value);
};

const deleteConfirmModalClose = () => {
    deleteConfirmModal.value = false;
};

onMounted(async () => {
    window.addEventListener("scroll", handleScroll);
    await loadPage("N", isGetBeforeDataStatus.value, searchGroup.value);
});

onBeforeUnmount(() => {
    window.removeEventListener("scroll", handleScroll);
});

const isSearchTitleGroup = computed(() => {
    return searchGroup.value === "todoTitleSearch";
});

const isSearchTodoAtGroup = computed(() => {
    return searchGroup.value === "todoAtSearch";
});

watch(
    () => searchModal.value.dateModal,
    async (newValue, oldValue) => {
        searchTodoAt.value.from = dayjs().format(dateFormat);
        searchTodoAt.value.to = dayjs().add(1, "d").format(dateFormat);
    }
);

watch(
    () => props.updateData,
    async (newValue, oldValue) => {
        await loadPage("Y", isGetBeforeDataStatus.value, searchGroup.value);
    }
);

const sortedTodoMap = computed(() => {
    return Object.fromEntries(
        Object.entries(todoMap.value).sort((a, b) => {
            return new Date(a[0]).getTime() - new Date(b[0]).getTime();
        })
    );
});

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
