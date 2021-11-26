
<template>
  <div class="d-flex flex-column pa-4 justify-center align-center">
    <h2>大会作成</h2>
    <form action="">
      <div class="">
        <v-text-field
          v-model="compeName"
          name="compe-name"
          label="大会名"
          outlined
        ></v-text-field>
        <v-text-field
          v-model="compePlace"
          name="compe-place"
          label="大会場所"
          outlined
        ></v-text-field>
        <v-text-field
          v-model="compeDates"
          name="compe-dates"
          label="大会日時"
          outlined
        ></v-text-field>
        <v-file-input
          name="compe-guidelines-file"
          chips
          small-chips
          truncate-length="15"
          >大会要項</v-file-input
        >
        <!-- コンポーネントにして、カテゴリー分回す -->
        <h3>短距離種目</h3>
        <v-data-table
          v-model="selected"
          :headers="headers"
          :items="compeEvents"
          :single-select="singleSelect"
          item-key="eventId"
          show-select
          class="elevation-1"
        />
      </div>
      <v-btn
        name="compe-create-btn"
        class="justify-center align-center"
        dark
        width="170"
        color="#4169e1"
        @click="onClick()"
      >
        作成
      </v-btn>
    </form>
  </div>
</template>
 
 
<script lang="ts">
import { Component, Vue } from "nuxt-property-decorator";
import Compe from "../../domains/compe/Compe";
import CompeService from "../../domains/compe/CompeService";
import Events from "../../domains/events/Events";

@Component
export default class DashBoard extends Vue {
  compeName = "";
  compePlace = "";
  compeDates = "";
  compeGuidelinesFile!: File[];
  compeEvents = [
    {
      eventId: "001",
      eventName: "男子100m",
      eventCategory: "SPRINT"
    },
    {
      eventId: "002",
      eventName: "男子200m",
      eventCategory: "SPRINT"
    },
    {
      eventId: "003",
      eventName: "男子300m",
      eventCategory: "SPRINT"
    },
    {
      eventId: "004",
      eventName: "男子400m",
      eventCategory: "SPRINT"
    },
  ];

  singleSelect = false;
  selected:Events[] = [];
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
  adminId!: string;

  fetch() {
    this.compeService = new CompeService();
    this.adminId = "admin001";
  }

  onClick() {
    for(const row of this.selected) {
      const event: Events = {
        eventId: row.eventId,
        eventName: row.eventName,
        eventCategory: row.eventCategory
      }
      this.postEvents.push(event)
    }

    const compe = new Compe(
      this.adminId,
      this.compeName,
      this.compePlace,
      this.compeDates,
      null,
      this.postEvents
    )
debugger
    this.compeService.createCompe(compe).then(() => {
      console.log("成功");
    })
  }
}
</script>