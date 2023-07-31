import dayjs from "dayjs";

const getShowDateFromAdd = (date, time) => {
  return date + " " + time;
}

const getShowDateFromEdit = (dateTime) => {
  return dateTime.replace("T", " ").substring(0, 16);
}

const getOriginDateTimeFromAdd = (date, time) => {
  return date + "T" + time;
}

const getDateFromEdit = (dateTime) => {
  return dateTime.replace("T", " ").substring(0, 10);
}

const getTimeFromEdit = (dateTime) => {
  return dateTime.replace("T", " ").substring(11, 16);
}


export default {
  getShowDateFromAdd,
  getShowDateFromEdit,
  getOriginDateTimeFromAdd,
  getDateFromEdit,
  getTimeFromEdit,

}
