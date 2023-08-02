<template>
    <q-page-sticky position="bottom-right" :offset="[18, 18]">
        <q-btn
            fab
            icon="add"
            color="accent"
            label="할 일 추가"
            @click="store.dialogModal.openAddTodoModal()"
        />
    </q-page-sticky>
    <AddTaskDialog @submit="onSubmit" />
</template>
<script setup>
import { ref } from "vue";
import AddTaskDialog from "components/body/add-task/AddTodoDialog.vue";
import { useStore } from "stores/store";

const addTaskModal = ref(false);
const store = useStore;
const emits = defineEmits(["submitData"]);

const onSubmit = () => {
    emits("submitData");
};
</script>

<style scoped></style>
