<template>
    <q-dialog v-model="store.dialogModal.data.dateSelectModal" persistent>
        <q-card style="min-width: 450px">
            <q-date
                mask="YYYY-MM-DD"
                v-model="dateTimeStoreData.date"
                landscape
            />
            <q-card-section v-if="isTimeSelect" align="right">
                현재 선택된 시간 : {{ dateTimeStoreData.time }}
            </q-card-section>
            <q-card-actions align="right" class="text-primary">
                <q-btn
                    flat
                    label="취소"
                    @click="store.dialogModal.closeDateSelectModal()"
                />

                <q-btn
                    v-if="!isTimeSelect"
                    flat
                    label="다음"
                    @click="store.dialogModal.openTimeSelectModal()"
                />
                <q-btn
                    v-if="isTimeSelect"
                    flat
                    label="시간 수정"
                    @click="store.dialogModal.openTimeSelectModal()"
                />
                <q-btn
                    v-if="isTimeSelect"
                    flat
                    label="완료"
                    @click="submitEvent"
                />
                <TimeSelectDialog @select="timeSelectEvent" />
            </q-card-actions>
        </q-card>
    </q-dialog>
</template>

<script setup>
import { ref } from "vue";
import TimeSelectDialog from "components/body/add-task/TimeSelectDialog.vue";

import { useStore } from "stores/store";

const props = defineProps([
    "dateSelectModal",
    "selectDate",
    "selectTime",
    "dateSelectModalWhenEdit",
]);
const emits = defineEmits(["submit", "close"]);
const store = useStore;
const dateTimeStoreData = store.dateTimeDialogData.data;

const isTimeSelect = ref(false);

const submitEvent = () => {
    emits("submit");
    store.dialogModal.closeDateSelectModal();
};

const timeSelectEvent = (data) => {
    isTimeSelect.value = true;
};
</script>

<style scoped></style>
