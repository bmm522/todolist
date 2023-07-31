import {useEditTodoDialogDataStore} from "stores/edit_todo_dialog_data";
import {useDateTimeDialogDataStore} from "stores/date_time_dialog_data";
import {useDialogModalStore} from "stores/dialog_modal";

export const useStore = {
  "editTodoDialog" : useEditTodoDialogDataStore(),
  "dateTimeDialog" : useDateTimeDialogDataStore(),
  "dialogModal" : useDialogModalStore(),
}
