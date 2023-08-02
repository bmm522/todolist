<template>
    <q-dialog v-model="store.dialogModal.data.editTodoModal" persistent>
        <q-card style="min-width: 350px">
            <q-card-section
                class="q-pt-none"
                style="text-align: center; margin-top: 30px"
            >
                <q-input
                    color="grey-3"
                    label-color="orange"
                    outlined
                    v-model="store.dateTimeDialogData.getShowDateTime"
                    label="시간 선택"
                    readonly
                >
                    {{ store.dateTimeDialogData.getShowDateTime }}

                    <template #append>
                        <q-icon
                            name="event"
                            color="orange"
                            @click="dateSelectModalOpenEvent"
                            style="cursor: pointer"
                        />
                        <DateSelectDialog />
                    </template>
                </q-input>
            </q-card-section>

            <q-card-section
                class="q-pt-none"
                style="text-align: center; margin-top: 30px"
            >
                <q-input
                    filled
                    v-model="store.editTodoDialogData.data.todoTitle"
                    label="제목"
                    maxlength="30"
                    lazy-rules
                    @keyup.enter.exact="updateTodoEvent"
                    :rules="[
                        (val) =>
                            val.length >= 1 || '빈칸은 입력하실 수 없습니다.',
                    ]"
                />

                <br />
                <q-input
                    type="textarea"
                    filled
                    v-model="store.editTodoDialogData.data.todoContent"
                    label="메모"
                    maxlength="500"
                    @keyup.enter.exact="updateTodoEvent"
                />
                <q-checkbox
                    v-model="store.editTodoDialogData.data.isTodoDone"
                    label="할 일을 완료했습니까?"
                />
            </q-card-section>

            <q-card-actions align="right" class="text-primary">
                <q-btn flat label="취소" @click="closeEvent" />
                <q-btn
                    flat
                    label="수정"
                    :disable="!hasTitle"
                    @click="updateTodoEvent"
                />
            </q-card-actions>
        </q-card>
    </q-dialog>
</template>

<script setup>
import { computed, onBeforeUnmount, onMounted, ref, watch } from "vue";
import DateSelectDialog from "components/body/add-task/DateSelectDialog.vue";
import TodoApi from "src/common/todo/TodoApi";
import { useStore } from "stores/store";

const emits = defineEmits(["submitData"]);
const store = useStore;

const updateTodoEvent = async () => {
    await TodoApi.updateTodoApi(
        store.editTodoDialogData.data.todoId,
        store.editTodoDialogData.data.todoTitle,
        store.editTodoDialogData.data.todoContent,
        store.dateTimeDialogData.getDateTimeForApiRequest,
        store.editTodoDialogData.data.isTodoDone ? "Y" : "N"
    );

    store.dialogModal.closeEditTodoModal();
    emits("submitData");
};

const closeEvent = () => {
    store.dateTimeDialogData.init();
    store.editTodoDialogData.init();
    store.dialogModal.closeEditTodoModal();
};

const dateSelectModalOpenEvent = () => {
    store.dialogModal.openDateSelectModal();
};

const hasTitle = computed(() => {
    return store.editTodoDialogData.data.todoTitle.length !== 0;
});

const todoDoneCheck = computed(() => {
    return store.editTodoDialogData.isTodoDone;
});
</script>

<style scoped></style>
