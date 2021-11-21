
<template>
  <div>
    <h2>大会作成</h2>
    <div class="d-flex flex-column">
      <v-text-field label="大会名" outlined></v-text-field>
      <v-text-field label="大会場所" outlined></v-text-field>
      <v-text-field label="大会日時" outlined></v-text-field>
      <v-file-input chips small-chips truncate-length="15">大会要項</v-file-input>
		<v-data-table
			v-model="selected"
			:headers="headers"
			:items="desserts"
			:single-select="singleSelect"
			item-key="name"
			show-select
			class="elevation-1"
		/>
    </div>
  </div>
</template>
 
 
<script lang="ts">
import { Component, Vue } from "nuxt-property-decorator";

@Component
export default class DashBoard extends Vue {
  singleSelect = false;
  selected = [];
  headers = [
    {
      text: "Dessert (100g serving)",
      align: "start",
      sortable: false,
      value: "name"
    },
    { text: "Calories", value: "calories" },
    { text: "Fat (g)", value: "fat" },
    { text: "Carbs (g)", value: "carbs" },
    { text: "Protein (g)", value: "protein" },
    { text: "Iron (%)", value: "iron" }
  ];

  desserts = [
    {
      name: "Frozen Yogurt",
      calories: 159,
      fat: 6.0,
      carbs: 24,
      protein: 4.0,
      iron: "1%",
    }
  ];
}
</script>