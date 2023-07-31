<template>
  <q-dialog v-model="modalStore.data.dateSelectModal" persistent>
    <q-card
      style="min-width: 450px">
      날짜 : {{ dateTimeStoreData.date }}
      <q-date
        mask="YYYY-MM-DD"
        v-model="dateTimeStoreData.date"
        landscape

      />
      <q-card-section v-if="isTimeSelect" align="right">
        현재 선택된 시간 : {{ dateTimeStoreData.time }}
      </q-card-section>
      <q-card-actions align="right" class="text-primary">

        <q-btn flat label="취소" @click="modalStore.closeDateSelectModal()"/>

        <q-btn v-if="!isTimeSelect" flat label="다음" @click="modalStore.openTimeSelectModal()"/>
        <q-btn v-if="isTimeSelect" flat label="시간 수정" @click="modalStore.openTimeSelectModal()"/>
        <q-btn v-if="isTimeSelect" flat label="완료" @click="submitEvent"/>
        <TimeSelectDialog @select="timeSelectEvent"/>
      </q-card-actions>
    </q-card>
  </q-dialog>

</template>

<script setup>


import {onMounted, ref, watch} from "vue";
import TimeSelectDialog from "components/body/add-task/TimeSelectDialog.vue";
import dayjs from "dayjs";
import ToDoInputDialog from "components/body/add-task/ToDoInputBox.vue";
import {date} from "quasar";
import {useDateTimeDialogDataStore} from "stores/date_time_dialog_data";
import {useDialogModalStore} from "stores/dialog_modal";

const props = defineProps(['dateSelectModal', 'selectDate', 'selectTime', 'dateSelectModalWhenEdit']);
const emits = defineEmits(['submit', 'close']);
const modalStore = useDialogModalStore();
const dateTimeStore = useDateTimeDialogDataStore();
const dateTimeStoreData = dateTimeStore.data;


const isTimeSelect = ref(false);


const submitEvent = () => {
  emits('submit');
  modalStore.closeDateSelectModal()
}

const timeSelectEvent = (data) => {
  isTimeSelect.value = true;
}




</script>

<style scoped>

</style>
