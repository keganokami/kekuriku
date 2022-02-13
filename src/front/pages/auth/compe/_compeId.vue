<template>
  <div
    v-if="!$fetchState.pending"
    class="d-flex flex-column pa-4 justify-center align-center"
  >
    <v-sheet class="mb-3 pa-5" color="#f5f5f5" elevation="1" width="60%">
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
          :rules="[
            $validation.requiredRule(),
            $validation.maxLengthRule(50, 'お名前'),
          ]"
        ></v-text-field>
        <v-text-field
          v-model="nameKana"
          name="nameKana"
          label="フリガナ"
          outlined
          :rules="[
            $validation.requiredRule(),
            $validation.maxLengthRule(50, 'フリガナ'),
          ]"
        ></v-text-field>
        <v-radio-group v-model="sex" row>
          <v-radio label="男性" value="0"></v-radio>
          <v-radio label="女性" value="1"></v-radio>
          <v-radio label="回答なし" value="2"></v-radio>
        </v-radio-group>
        <v-text-field
          v-model="team"
          name="team"
          label="所属チーム・学校"
          outlined
          :rules="[
            $validation.requiredRule(),
            $validation.maxLengthRule(50, '所属チーム・学校'),
          ]"
        ></v-text-field>
        <v-text-field
          v-model="phoneNum"
          name="phoneNum"
          type="phone"
          label="連絡先・緊急連絡先"
          outlined
          :rules="[$validation.requiredRule()]"
        ></v-text-field>
        <v-card>
          <v-card-title>
            短距離種目
            <v-spacer></v-spacer>
            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="Search"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table
            dense
            v-model="selected"
            :headers="headers"
            :items="compeEvents"
            :single-select="singleSelect"
            item-key="eventId"
            show-select
            class="elevation-1"
            :value="selected"
            :search="search"
          >
            <!-- checkがあれば入力必須にする -->
            <template v-slot:[`item.eventRecode`]="props">
              <v-edit-dialog
                large
                class="vdialognew"
                cancel-text="閉じる"
                save-text="確定"
                light
                :return-value.sync="props.item.eventRecode"
              >
                {{ props.item.eventRecode }}
                <template v-slot:input>
                  <v-text-field
                    v-model.number="props.item.eventRecode"
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
        </v-card>
        <v-btn
          :disabled="!valid || selected.length === 0"
          name="compe-create-btn"
          class="justify-center align-center"
          width="170"
          color="#4169e1"
          type="submit"
          @click="onSubmit()"
        >
          申込をする
        </v-btn>
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
import CompeEntry from "../../../domains/compe/CompeEntry";
import EntryEvents from "../../../domains/events/EntryEvents";

@Component
export default class EntryPage extends Vue {
  name = "";
  nameKana = "";
  team = "";
  phoneNum = "";
  sex = "";
  participationFee = 0;

  search = "";

  $route!: any;
  $router!: any;
  compeService!: CompeService;
  myCompe!: CompeResponse;
  compeId = this.$route.params.compeId;

  valid = true;
  compeEvents = [];
  singleSelect = false;
  selected: EntryEvents[] = [];
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
      text: "参考記録を入力してください",
      align: "right",
      sortable: false,
      value: "eventRecode",
      cellClass: "recode-width",
    },
  ];
  $ref: any;
  postEvents: EntryEvents[] = [];

  async fetch() {
    this.compeService = new CompeService();
    await this.compeService.getCompe(this.compeId).then((response: any) => {
      this.myCompe = response.data;
      this.compeEvents = response.data.compeEvent;
    });
  }

  toCompeListPage() {
    this.$router.push("/auth/compe/list");
  }
  
  onSubmit() {
    
    for (const row of this.selected) {
      const event: EntryEvents = {
        eventId: row.eventId,
        eventName: row.eventName,
        eventCategory: row.eventCategory,
        eventRecode: row.eventRecode,
        participationFee: 0
      };
      this.postEvents.push(event);
    }

    const entryRequest = new CompeEntry(
      this.compeId,
      this.name,
      this.nameKana,
      this.team,
      this.phoneNum,
      this.sex,
      this.postEvents
    );
    this.compeService.entryCompe(entryRequest).then(() => {
      console.log("成功");
    });
  }
}
</script>

<style lang="scss" scoped>
::v-deep {
  .v-small-dialog__activator {
    height: 100%;
    background: rgb(247, 252, 182);
    padding: 5px 10px;
    width: 30%;
    margin-left: 70%;
  }
  td.text-right.recode-width {
    padding: 0 !important;
  }

  .vdialognew > button.v-btn.v-btn--text.theme--light.v-size--default.primary--text {
    color: gray !important;
    background:gray !important;
  }
}
</style>
