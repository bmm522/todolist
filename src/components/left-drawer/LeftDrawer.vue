<template>
  <q-drawer
    class="bg-primary"
    show-if-above
    side="left"
    :mini="miniState"
    :width="250"
    :breakpoint="100"
  >
    <slot>

    </slot>
  </q-drawer>
</template>

<script setup>

</script>

<style scoped>

</style>
