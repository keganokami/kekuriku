<template>
  <div class="signup-card flex-column d-flex justify-center align-center pa-6">
    <v-card height="628" width="670">
      <v-card-title class="justify-center mt-7">
        <h2>新規登録</h2>
      </v-card-title>
      <div class="flex-column d-flex justify-center align-center mt-7">
        <div class="signup-form">
          <div class="mb-5">
            <v-text-field
              label="らくりくID"
              outlined
              v-model="id"
            ></v-text-field>
          </div>
          <div class="mb-5">
            <v-text-field
              type="email"
              label="メールアドレス"
              outlined
              v-model="email"
              append-icon="mdi-email"
            ></v-text-field>
          </div>
          <div class="mb-5">
            <v-text-field
              label="パスワード"
              outlined
              :append-icon="isMaskingPassword ? 'mdi-eye' : 'mdi-eye-off'"
              :type="isMaskingPassword ? 'text' : 'password'"
              @click:append="isMaskingPassword = !isMaskingPassword"
              v-model="password"
            ></v-text-field>
          </div>
          <div class="mb-2">
            <v-text-field
              label="パスワード確認"
              outlined
              :append-icon="isMaskingconfirmation ? 'mdi-eye' : 'mdi-eye-off'"
              :type="isMaskingconfirmation ? 'text' : 'password'"
              @click:append="isMaskingconfirmation = !isMaskingconfirmation"
              v-model="confirmation"
            ></v-text-field>
          </div>
        </div>
        <v-card-actions>
          <NuxtLink to="#">
            <v-btn dark width="96" color="#4169e1" @click="signup()">
              登録
            </v-btn>
          </NuxtLink>
        </v-card-actions>
      </div>
    </v-card>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "nuxt-property-decorator";

@Component
export default class Signup extends Vue {
  id = "";
  email = "";
  password = "";
  confirmation = "";
  isMaskingPassword = false;
  isMaskingconfirmation = false;

  signup() {
    /* ユーザー登録処理 */
    console.log("signup");
  }
}
</script>

<style scoped>
.signup-card {
  /* 背景色は決まり次第変更 */
  background-color: rgba(66, 175, 237, 0.96);
  height: 100%;
}

.signup-form {
  width: 20rem;
}
</style>
