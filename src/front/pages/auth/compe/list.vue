
<template>
  <div v-if="!$fetchState.pending">
    <div v-for="compe in myCompes" :key="compe.id">
      <v-sheet
        class="mb-3 pa-5"
        color="#f5f5f5"
        elevation="1"
        height="150"
        width="100%"
      >
        <div>
          <span class="mr-3 my-width">大会名:</span>{{ compe.compeName }}
        </div>
        <div>
          <span class="mr-3 my-width">開催日時:</span>{{ compe.compeDates }}
        </div>
        <div>
          <v-btn
          @click="toEntryPage(compe.compeId)"
          >
            大会申し込みページ作成
          </v-btn>
        </div>
      </v-sheet>
    </div>
  </div>
</template>
 
<script lang="ts">
import { Component, Vue } from "nuxt-property-decorator";
import CompeResponse from "../../../domains/compe/CompeResponse";
import CompeService from "../../../domains/compe/CompeService";
@Component
export default class DashBoard extends Vue {
  compeService!: CompeService;
  myCompes!: CompeResponse[];
  $router!: any;
  async fetch() {
    this.compeService = new CompeService();
    await this.compeService.getCompes().then((response: any) => {
      this.myCompes = response.data;
    });
  }

  toEntryPage(compeId: string) {  
    this.$router.push(`/auth/compe/${compeId}`);
  }
}
</script>
<style lang="scss" scoped>

</style>