<template>
  <v-data-table
    :headers="headers"
    :items="compeEvents"
    sort-by="calories"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title>{{ tableTitle }}</v-toolbar-title>
        <v-divider class="mx-4" inset vertical></v-divider>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="primary" dark class="mb-2" v-bind="attrs" v-on="on">
              種目を追加する
            </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="text-h5">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field
                      v-model="editedItem.eventName"
                      label="種目名"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field
                      v-model.number="editedItem.participationFee"
                      type="number"
                      label="参加費用"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="close"> Cancel </v-btn>
              <v-btn color="blue darken-1" text @click="save"> Save </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title
              >{{ editedItem.eventName }}を削除しますか？</v-card-title
            >
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeDelete"
                >閉じる</v-btn
              >
              <v-btn color="blue darken-1" text @click="deleteItemConfirm"
                >はい</v-btn
              >
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:[`item.actions`]="{ item }">
      <v-icon v-if="participationFeeFilter" small class="mr-2" @click="editItem(item)"> mdi-pencil </v-icon>
      <template v-else>
        <span class="subtitle-2 grey--text text--lighten-1">
          金額設定なし
        </span>
      </template>
      <v-icon small @click="deleteItem(item)"> mdi-delete </v-icon>
    </template>
    <template v-slot:no-data>
      <v-btn color="primary" @click="initialize"> Reset </v-btn>
    </template>
  </v-data-table>
</template>


<script lang="ts">
import { Component, Vue, Watch, Prop } from "nuxt-property-decorator";
import Events from "../domains/events/Events";

@Component
export default class CreateEventDataTable extends Vue {
  @Prop()
  tableTitle!: string;

  @Prop()
  compeEvents!: Events[];

  @Prop()
  headers!: any[];

  @Prop()
  participationFeeFilter!: boolean;

  editedItem = {
    eventId: "",
    eventName: "",
    eventCategory: "",
    participationFee: 0,
  };
  defaultItem = {
    eventId: "",
    eventName: "",
    eventCategory: "",
    participationFee: 0,
  };
  editedIndex = -1;
  dialog = false;
  dialogDelete = false;

  get formTitle() {
    return this.editedIndex === -1 ? "種目を追加" : "種目情報を編集";
  }

  editItem(item) {
    this.editedIndex = this.compeEvents.indexOf(item);
    this.editedItem = Object.assign({}, item);
    this.dialog = true;
  }

  deleteItem(item) {
    this.editedIndex = this.compeEvents.indexOf(item);
    this.editedItem = Object.assign({}, item);
    this.dialogDelete = true;
  }

  deleteItemConfirm() {
    this.compeEvents.splice(this.editedIndex, 1);
    this.closeDelete();
  }

  close() {
    this.dialog = false;
    this.$nextTick(() => {
      this.editedItem = Object.assign({}, this.defaultItem);
      this.editedIndex = -1;
    });
  }

  closeDelete() {
    this.dialogDelete = false;
    this.$nextTick(() => {
      this.editedItem = Object.assign({}, this.defaultItem);
      this.editedIndex = -1;
    });
  }

  save() {
    if (this.editedIndex > -1) {
      Object.assign(this.compeEvents[this.editedIndex], this.editedItem);
    } else {
      this.compeEvents.push(this.editedItem);
    }
    this.close();
  }
}
</script>