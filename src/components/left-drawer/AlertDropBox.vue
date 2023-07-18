<template>
  <div class="q-pa-md">
    <q-btn-dropdown
      split
      color="black"
      push
      glossy
      no-caps
      icon="folder"
      label="7일 이내 일정 보기"
      @click="onMainClick"
    >
      <q-list>
        <q-item clickable v-close-popup @click="onItemClick">
          <q-item-section>
            <q-item-label>주간 회의록 작성</q-item-label>
            <q-item-label caption>오늘 14:00</q-item-label>
          </q-item-section>

        </q-item>

        <q-item clickable v-close-popup @click="onItemClick">
          <q-item-section>
            <q-item-label>팀 회의</q-item-label>
            <q-item-label caption>내일 15:30</q-item-label>
          </q-item-section>

        </q-item>
      </q-list>
    </q-btn-dropdown>
  </div>
</template>



<script>
  import {defineComponent} from "vue";

  export default defineComponent({
  name: 'AlertDropBox',
});

</script>

<style scoped>

</style>

