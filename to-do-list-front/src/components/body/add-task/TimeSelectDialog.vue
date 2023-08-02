<template>
  <q-dialog v-model="store.dialogModal.data.timeSelectModal" persistent>
    <q-card
      style="min-width: 200px">
      <q-card-section>
        현재 선택한 날짜 : {{ dateTimeStoreData.date }}
      </q-card-section>
      <q-time
        v-model="dateTimeStoreData.time"
        format24h
      />
      <q-card-actions align="right" class="text-primary">
        <q-btn flat label="취소" @click="store.dialogModal.closeTimeSelectModal()" />
        <q-btn flat label="확인" @click="timeSelectEvent" />
      </q-card-actions>
    </q-card>
  </q-dialog>

</template>

<script setup>
import { onMounted, ref, watch } from "vue";
import dayjs from "dayjs";
import { useDateTimeDialogDataStore } from "stores/date_time_dialog_data";
import { useDialogModalStore } from "stores/dialog_modal";
import { useStore } from "stores/store";

// 초기값 세팅

const emits = defineEmits(["select"]);

const dateTimeStore = useDateTimeDialogDataStore();
const store = useStore;
const dateTimeStoreData = store.dateTimeDialogData.data;

const timeSelectEvent = () => {
  emits("select");
  store.dialogModal.closeTimeSelectModal();
};


</script>

<style scoped>

</style>
