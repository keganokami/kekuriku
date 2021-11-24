
<template>
  <div class="d-flex flex-column pa-4 justify-center align-center">
    <h2>大会作成</h2>
    <form action="">
      <div class="">
        <v-text-field name="compe-name" label="大会名" outlined></v-text-field>
        <v-text-field
          name="compe-place"
          label="大会場所"
          outlined
        ></v-text-field>
        <v-text-field
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
          item-key="name"
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

export type Events = {
  id: string,
  name: string
}

@Component
export default class DashBoard extends Vue {
  compeName!: string;
  compePlace!: string;
  compeDates!: string;
  compeGuidelinesFile!: File[];
  compeEvents: Events[] = [
    {
      id: "001",
      name: "男子100m",
    },
    {
      id: "002",
      name: "男子200m",
    },
    {
      id: "003",
      name: "男子300m",
    },
    {
      id: "004",
      name: "男子400m",
    },
  ];

  singleSelect = false;
  selected = [];
  headers = [
    {
      text: "id",
      align: " d-none",
      sortable: false,
      value: "id",
    },
    {
      text: "種目",
      align: "start",
      sortable: false,
      value: "name",
    },
  ];

  postEvents: Events[] = [];

  onClick() {
    this.postEvents = [...this.selected];
    console.log(JSON.stringify(this.postEvents));
  }
}
</script>