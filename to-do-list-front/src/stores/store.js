import { useEditTodoDialogDataStore } from "stores/edit_todo_dialog_data";
import { useDateTimeDialogDataStore } from "stores/date_time_dialog_data";
import { useDialogModalStore } from "stores/dialog_modal";
import { useUserInfoDataStore } from "stores/user_info_data";
import { useAddTodoDialogDataStore } from "stores/add_todo_dialog_data";

export const useStore = {
    editTodoDialogData: useEditTodoDialogDataStore(),
    dateTimeDialogData: useDateTimeDialogDataStore(),
    dialogModal: useDialogModalStore(),
    userInfoData: useUserInfoDataStore(),
    addTodoDialogData: useAddTodoDialogDataStore(),
};
