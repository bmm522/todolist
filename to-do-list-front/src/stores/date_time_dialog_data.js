import {defineStore} from 'pinia'
import dayjs from "dayjs";
import {computed, ref} from "vue";


export const useDateTimeDialogDataStore = defineStore('dateTimeStoreId', () => {
  const dateTimeStoreId = ref(0);
  const data = ref({
    "time": "",
    "date": "",
  });

  const getShowDateTime = computed(() => {
    if(data.value.time === "") {
      data.value.time = dayjs().format("HH:mm");
    }

    if(data.value.date === "") {
      data.value.date = dayjs().format("YYYY-MM-DD");
    }
    const dateTimeStr = data.value.date + " " + data.value.time;
    return dateTimeStr;
  })

  const getDateTimeForApiRequest = computed(() => {
    const dateTimeStr = data.value.date + "T" + data.value.time;
    return dateTimeStr;
  })



  const formatDate = (date) => {
    const dateFormat = "YYYY-MM-DD";
    return dayjs(date).format(dateFormat);
  }



  const formatTime = (time) => {
    const timeFormat = "HH:mm";
    return dayjs(time).format(timeFormat);
  }

  const formatDateTime = (dateTime) => {
    const timeFormat = "YYYY-MM-DD HH24:mm";
    return dayjs(dateTime).format(timeFormat);
  }

  const formatDateForApi = (date) => {
    const timeFormat = "YYYY-MM-DD'T'HH24:mm"
    return dayjs(date).format(timeFormat);
  }




  const setTimeValue = (value) => {
    data.value.time = value;
  }


  const init = () => {
    data.value.date = "";
    data.value.time = "";
  }

  const initFromTodoAt = (dateTime) => {
    data.value.date = dateTime.replace("T", " ").substring(0, 10);
    data.value.time = dateTime.replace("T", " ").substring(11, 16);
  }






  return {data, getShowDateTime, getDateTimeForApiRequest,initFromTodoAt,formatTime, formatDate,formatDateTime, setTimeValue, formatDateForApi,  init}

})
