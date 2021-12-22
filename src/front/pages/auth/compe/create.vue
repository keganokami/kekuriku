
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
          ></v-radio>
          <v-radio
            label="種目数に関係なく大会の参加費を設定する"
            color="green darken-3"
            value="1"
          ></v-radio>
          <v-radio
            label="種目ごとに参加費を設定する"
            color="indigo"
            value="2"
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
        <h3>短距離種目</h3>
        <v-data-table
          v-model="selected"
          :headers="headers"
          :items="compeEvents"
          :single-select="singleSelect"
          item-key="eventId"
          show-select
          class="elevation-1"
          :value="selected"
        >
          <template v-if="radioGroup === '2'" v-slot:[`item.participationFee`]="props">
            <v-edit-dialog
              large
              class="vdialognew"
              cancel-text="閉じる"
              save-text="確定"
              light
              :return-value.sync="props.item.participationFee"
            >
              {{ props.item.participationFee }}
              <template v-slot:input>
                <v-text-field
                  v-model.number="props.item.participationFee"
                  type="number"
                  :rules="[$validation.runTimeRecodeRule()]"
                  v-if="selected"
                  label="Edit"
                  single-line
                ></v-text-field>
              </template>
            </v-edit-dialog>
          </template>
        </v-data-table>
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
  radioGroup = "";
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
    {
      text: "参加費",
      align: "start",
      sortable: false,
      value: "participationFee",
    },
  ];

  postEvents: Events[] = [];
  compeService!: CompeService;

  fetch() {
    this.compeService = new CompeService();
  }

  participationFeeFilter() {
    return this.radioGroup === '2';
  }

  onSubmit() {
    for (const row of this.selected) {
      const event: Events = {
        eventId: row.eventId,
        eventName: row.eventName,
        eventCategory: row.eventCategory,
        participationFee: 0,
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

  @Watch("selected")
  isDisableSubmit() {
    return this.selected.length === 0;
  }
}
</script>