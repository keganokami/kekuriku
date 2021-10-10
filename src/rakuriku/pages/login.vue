<template>
  <div class="login-card flex-column d-flex justify-center align-center pa-6">
    <v-card height="400" width="670">
      <v-card-title class="justify-center mt-7">
        <h2>ログイン</h2>
      </v-card-title>
      <div class="flex-column d-flex justify-center align-center mt-7">
        <div class="signin-form mb-4">
          <v-text-field label="RakurikuID" outlined v-model="id"></v-text-field>
        </div>
        <div class="signin-form">
          <v-text-field
            label="Password"
            outlined
            :append-icon="isMaskingPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :type="isMaskingPassword ? 'text' : 'password'"
            @click:append="isMaskingPassword = !isMaskingPassword"
            v-model="password"
          ></v-text-field>
        </div>
        <v-card-actions>
          <NuxtLink to="#">
            <v-btn dark width="96" color="#4169e1" @click="login()"> ログイン </v-btn>
          </NuxtLink>
        </v-card-actions>
      </div>
    </v-card>
    <v-card
      height="254"
      width="670"
      class="mt-10 flex-column d-flex justify-center align-center"
    >
      <v-card-title class="flex-column mt-7">
        <h2>新規登録</h2>
        <p class="mt-8"><span>※</span>ログインには会員登録が必要です。</p>
      </v-card-title>
      <v-card-actions>
        <NuxtLink to="/signup">
          <v-btn dark width="170" color="#4169e1"> 新規登録 </v-btn>
        </NuxtLink>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script lang="ts">
import { Component, Vue} from 'nuxt-property-decorator';

@Component
export default class Login extends Vue {
  id = "";
  password = "";
  isMaskingPassword = false;
  $store: any;

  async login() {
    await this.$store.dispatch("login", {
      userId: this.id,
      password: this.password,
    });
  }
}
</script>

<style scoped>
.login-card {
  /* 背景色は決まり次第変更 */
  background-color: rgba(66, 175, 237, 0.96);
  height: 100%;
}

.signin-form {
  width: 328px;
}
</style>
