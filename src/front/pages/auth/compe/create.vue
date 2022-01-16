
<template>
  <div class="d-flex flex-column pa-4 justify-center align-center">
    <h2>大会作成</h2>
    <v-form ref="form" v-model="valid">
      <div class="">
        <v-text-field
          v-model="compeName"
          name="compe-name"
          label="大会名"
          outlined
          :rules="[
            $validation.requiredRule(),
            $validation.maxLengthRule(50, '大会名'),
          ]"
        ></v-text-field>
        <v-text-field
          v-model="compePlace"
          name="compe-place"
          label="大会場所"
          outlined
          :rules="[
            $validation.requiredRule(),
            $validation.maxLengthRule(50, '大会場所'),
          ]"
        ></v-text-field>
        <v-text-field
          v-model="compeDates"
          name="compe-dates"
          label="大会日時"
          outlined
          :rules="[
            $validation.requiredRule(),
            $validation.maxLengthRule(50, '大会日時'),
          ]"
        ></v-text-field>
        <!-- <v-file-input
          name="compe-guidelines-file"
          chips
          hidden
          small-chips
          truncate-length="15"
          >大会要項</v-file-input
        > -->
        <!-- コンポーネントにして、カテゴリー分回す -->
        <h3>参加費用設定</h3>
        <v-radio-group v-model="radioGroup">
          <v-radio
            label="1種目に付き参加費用を設定する"
            color="red"
            value="0"
            v-model="compeFeeType"
          ></v-radio>
          <v-radio
            label="種目数に関係なく大会の参加費を設定する"
            color="green darken-3"
            value="1"
            v-model="compeFeeType"
          ></v-radio>
          <v-radio
            label="種目ごとに参加費を設定する"
            color="indigo"
            value="2"
            v-model="compeFeeType"
          ></v-radio>
        </v-radio-group>
        <v-text-field
          v-if="radioGroup === '0' || radioGroup === '1'"
          v-model.number="participationFee"
          type="number"
          name="compe-participationFee"
          label="参加費設定"
          outlined
          :rules="[$validation.requiredRule()]"
        ></v-text-field>
        <create-event-data-table
          :compe-events="compeEvents"
          :headers="hideParticipationFeeCulumn"
          table-title="短距離種目"
          :participation-fee-filter="participationFeeFilter"
          :selected.sync="selected"
        ></create-event-data-table>
      </div>
      <v-btn
        :disabled="!valid || selected.length === 0"
        name="compe-create-btn"
        class="justify-center align-center"
        width="170"
        color="#4169e1"
        type="submit"
        @click="onSubmit()"
      >
        作成
      </v-btn>
    </v-form>
  </div>
</template>
 
 
<script lang="ts">
import { Component, Vue, Watch } from "nuxt-property-decorator";
import Compe from "../../../domains/compe/Compe";
import CompeService from "../../../domains/compe/CompeService";
import Events from "../../../domains/events/Events";

@Component
export default class CreateCompe extends Vue {
  valid = true;
  compeName = "";
  compePlace = "";
  compeDates = "";
  compeFeeType = "";
  participationFee = 0;
  radioGroup = "";
  compeGuidelinesFile!: File[];
  compeEvents: Events[] = [
    {
      eventId: "001",
      eventName: "男子100m",
      eventCategory: "SPRINT",
      participationFee: 0,
    },
    {
      eventId: "002",
      eventName: "男子200m",
      eventCategory: "SPRINT",
      participationFee: 0,
    },
    {
      eventId: "003",
      eventName: "男子300m",
      eventCategory: "SPRINT",
      participationFee: 0,
    },
    {
      eventId: "004",
      eventName: "男子400m",
      eventCategory: "SPRINT",
      participationFee: 0,
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
    {
      text: "参加費",
      align: "start",
      sortable: false,
      value: "participationFee",
    },
    {
      text: "",
      align: "start",
      sortable: false,
      value: "actions",
    },
  ];

  postEvents: Events[] = [];
  compeService!: CompeService;

  fetch() {
    this.compeService = new CompeService();
  }

  get participationFeeFilter() {
    return this.radioGroup === "2";
  }

  onSubmit() {
    for (const row of this.selected) {
      const event: Events = {
        eventId: row.eventId,
        eventName: row.eventName,
        eventCategory: row.eventCategory,
        participationFee: row.participationFee,
      };
      this.postEvents.push(event);
    }

    const compe = new Compe(
      this.compeName,
      this.compePlace,
      this.compeDates,
      null,
      this.compeFeeType,
      this.postEvents
    );
    this.compeService.createCompe(compe).then(() => {
      console.log("成功");
    });
  }

  @Watch("selected")
  isDisableSubmit() {
    return this.selected.length === 0;
  }

  get hideParticipationFeeCulumn() {
    if (this.radioGroup !== "2") {
      return this.headers.filter(header => header.value !== "participationFee");
    } else {
      return this.headers;
    }
  }
}
</script>