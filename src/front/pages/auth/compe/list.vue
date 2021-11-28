
<template>
  <div>
    <template>
      <v-sheet> </v-sheet>
    </template>
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
  async fetch() {
    this.compeService = new CompeService();
    await this.compeService.getCompes().then((response: any) => {
      this.myCompes = response.data;
    });
  }
}
</script>