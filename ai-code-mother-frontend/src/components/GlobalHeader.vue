<script setup lang="ts">
import { ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import type { MenuProps } from 'ant-design-vue'

// JS 中引入 Store
import { useLoginUserStore } from '@/stores/loginUser.ts'
const loginUserStore = useLoginUserStore()

const props = defineProps<{
  title?: string
  logo?: string
  items?: MenuProps['items']
}>()

const route = useRoute()
const router = useRouter()
const selectedKeys = ref<string[]>([route.path])

watch(
  () => route.path,
  (p) => (selectedKeys.value = [p]),
)

const handleMenuClick: MenuProps['onClick'] = (info) => {
  const key = info.key as string
  if (key && key.startsWith('/')) {
    router.push(key)
  }
}

const title = props.title ?? 'cin AI 零代码生成平台'
const logoUrl = props.logo ?? new URL('@/assets/bitbug_favicon.ico', import.meta.url).href
</script>

<template>
  <div class="header">
    <div class="left">
      <img class="logo" :src="logoUrl" alt="logo" />
      <span class="title">{{ title }}</span>
      <a-menu
        class="menu"
        mode="horizontal"
        :selectedKeys="selectedKeys"
        :items="items"
        @click="handleMenuClick"
      />
    </div>
    <div class="user-login-status">
      <div v-if="loginUserStore.loginUser.id">
        <a-space>
          <a-avatar :src="loginUserStore.loginUser.userAvatar" />
          {{ loginUserStore.loginUser.userName ?? '无名' }}
        </a-space>
      </div>
      <div v-else>
        <a-button type="primary" href="/user/login">登录</a-button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.left {
  display: flex;
  align-items: center;
  gap: 16px;
}
.logo {
  width: 32px;
  height: 32px;
}
.title {
  font-size: 16px;
  font-weight: 600;
  white-space: nowrap;
}
.menu {
  margin-left: 8px;
}
.right {
  display: flex;
  align-items: center;
  gap: 12px;
}
@media (max-width: 768px) {
  .title {
    display: none;
  }
}
</style>
