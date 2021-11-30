<template>
  <div
    v-if="!$fetchState.pending"
    class="d-flex flex-column pa-4 justify-center align-center"
  >
    <v-sheet class="mb-3 pa-5" color="#f5f5f5" elevation="1" width="100%">
      <h2>大会申込</h2>
      <div><span>大会名</span>{{ myCompe.compeName }}</div>
      <div><span>開催場所</span>{{ myCompe.compePalce }}</div>
      <div><span>開催日時</span>{{ myCompe.compeDates }}</div>
      <v-form ref="form" v-model="valid">
        <v-text-field
          v-model="name"
          name="name"
          label="お名前"
          outlined
          :rules="inputRules"
        ></v-text-field>
        <v-text-field
          v-model="name"
          name="name"
          label="お名前"
          outlined
          :rules="inputRules"
        ></v-text-field>
      </v-form>
      <div>
        <v-btn @click="toCompeListPage()"> 一覧に戻る </v-btn>
      </div>
    </v-sheet>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "nuxt-property-decorator";
import CompeResponse from "../../../domains/compe/CompeResponse";
import CompeService from "../../../domains/compe/CompeService";

@Component
export default class EntryPage extends Vue {
  $route!: any;
  $router!: any;
  compeService!: CompeService;
  myCompe!: CompeResponse;
  compeId = this.$route.params.compeId;

  async fetch() {
    this.compeService = new CompeService();
    await this.compeService.getCompe(this.compeId).then((response: any) => {
      this.myCompe = response.data;
    });
  }

  toCompeListPage() {
    this.$router.push("/auth/compe/list");
  }
}
</script>

<style>
</style>