
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
import Compe from "../../domains/compe/Compe";
import CompeService from "../../domains/compe/CompeService";
import Events from "../../domains/events/Events";

@Component
export default class DashBoard extends Vue {
  valid = true;
  compeName = "";
  compePlace = "";
  compeDates = "";
  compeGuidelinesFile!: File[];
  compeEvents = [
    {
      eventId: "001",
      eventName: "男子100m",
      eventCategory: "SPRINT",
    },
    {
      eventId: "002",
      eventName: "男子200m",
      eventCategory: "SPRINT",
    },
    {
      eventId: "003",
      eventName: "男子300m",
      eventCategory: "SPRINT",
    },
    {
      eventId: "004",
      eventName: "男子400m",
      eventCategory: "SPRINT",
    },
  ];

  singleSelect = false;
  selected: Events[] = [];
  headers = [
    {
      text: "id",
      align: " d-none",
      sortable: false,
      value: "eventId",
    },
    {
      text: "種目",
      align: "start",
      sortable: false,
      value: "eventName",
    },
    {
      text: "カテゴリー",
      align: " d-none",
      sortable: false,
      value: "eventCategory",
    },
  ];

  postEvents: Events[] = [];
  compeService!: CompeService;

  fetch() {
    this.compeService = new CompeService();
  }

  onSubmit() {
    for (const row of this.selected) {
      const event: Events = {
        eventId: row.eventId,
        eventName: row.eventName,
        eventCategory: row.eventCategory,
      };
      this.postEvents.push(event);
    }

    const compe = new Compe(
      this.compeName,
      this.compePlace,
      this.compeDates,
      null,
      this.postEvents
    );
    this.compeService.createCompe(compe).then(() => {
      console.log("成功");
    });
  }

  // TODO 切り出し
  inputRules: any = [
    (v: any) => !!v || "required",
    (v: any) => this.charRules(v),
  ];

  private charRules(value: any): any {
    return value.length <= 50 || "Name must be less than 50 characters";
  }

  @Watch("selected")
  isDisableSubmit() {
    return this.selected.length === 0;
  }
}
</script>