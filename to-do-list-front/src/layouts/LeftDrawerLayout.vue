<template>
  <LeftDrawer>
    <ProfileComponent/>
    <ScrollArea>
        <MenuIcon/>
        <hr>
    </ScrollArea>
  </LeftDrawer>
</template>

<script setup>

import {defineComponent} from "vue";
import ProfileComponent from "components/left-drawer/ProfileBox.vue";

import MenuIcon from "components/left-drawer/MenuIcon.vue";
import AlertDropBox from "components/left-drawer/AlertDropBox.vue";
import LeftDrawer from "components/left-drawer/LeftDrawer.vue";
import ScrollArea from "components/left-drawer/ScrollArea.vue";
import AddTaskButton from "components/body/add-task/AddTodoButton.vue";



</script>

<style scoped>



</style>


