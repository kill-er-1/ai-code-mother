<script setup lang="ts">
import { computed } from 'vue'
import GlobalHeader from '@/components/GlobalHeader.vue'
import GlobalFooter from '@/components/GlobalFooter.vue'

const menuItems = computed(() => [
  { key: '/', label: '首页' },
  { key: '/about', label: '关于' },
])
</script>

<template>
  <a-layout class="layout">
    <a-layout-header class="layout-header">
      <GlobalHeader :items="menuItems" />
    </a-layout-header>

    <a-layout-content class="layout-content">
      <router-view />
    </a-layout-content>

    <a-layout-footer class="layout-footer">
      <GlobalFooter />
    </a-layout-footer>
  </a-layout>
</template>

<style scoped>
.layout {
  --footer-height: 56px;
  min-height: 100vh;
  background: #f5f5f5;
}
.layout-header {
  background: #fff;
  padding: 0 16px;
  position: sticky;
  top: 0;
  z-index: 100;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}
.layout-content {
  padding: 16px;
  padding-bottom: calc(var(--footer-height) + 16px);
}
.layout-footer {
  height: var(--footer-height);
  line-height: var(--footer-height);
  background: #fff;
  position: fixed;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 99;
  box-shadow: 0 -2px 8px rgba(0, 0, 0, 0.04);
}
</style>
