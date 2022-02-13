
<template>
  <div class="d-flex flex-column pa-4 justify-center align-center">
    <h2>ダッシュボード</h2>
    <NuxtLink to="/auth/compe/create">
      <v-btn dark width="170" color="#4169e1"> 大会作成 </v-btn>
    </NuxtLink>
    <NuxtLink to="/auth/compe/list">
      <v-btn dark width="170" color="#4169e1"> 大会一覧 </v-btn>
    </NuxtLink>
  </div>
</template>
 
 
<script lang="ts">
import { Component, Vue, Watch } from "nuxt-property-decorator";
@Component
export default class DashBoard extends Vue {
  
}
</script>