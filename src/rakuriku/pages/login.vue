<template>
  <v-row
    class="login-card flex-column"
    height="100%"
    justify="center"
    align-content="center"
  >
    <v-card height="400" max-width="670">
      <v-card-title class="justify-center mt-7">
        <h2>ログイン</h2>
      </v-card-title>
      <v-card-text class="d-flex flex-column justify-center align-center mt-7">
        <div class="signin-form mb-4">
          <v-text-field label="RakurikuID" outlined v-model="id"></v-text-field>
        </div>
        <div class="signin-form">
          <v-text-field
            label="Password"
            outlined
            :append-icon="isHiddenPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :type="isHiddenPassword ? 'text' : 'password'"
            @click:append="isHiddenPassword = !isHiddenPassword"
            v-model="password"
          ></v-text-field>
        </div>
        <v-card-actions>
          <NuxtLink to="#">
            <v-btn dark width="96" color="#4169e1">
              ログイン
            </v-btn>
          </NuxtLink>
        </v-card-actions>
      </v-card-text>
    </v-card>
    <v-card height="254" width="670" class="mt-10">
      <v-card-title class="flex-column mt-7">
        <h2>新規登録</h2>
        <p class="mt-8"><span>※</span>ログインには会員登録が必要です。</p>
      </v-card-title>
      <v-card-text class="d-flex justify-center">
        <v-card-actions>
          <NuxtLink to="/signup">
            <v-btn dark width="170" color="#4169e1">
              新規登録
            </v-btn>
          </NuxtLink>
        </v-card-actions>
      </v-card-text>
    </v-card>
  </v-row>
</template>

<script>
export default {
  data() {
    return {
      id: "",
      password: "",
      isHiddenPassword: false
    };
  },
  methods: {
    async login() {
      await this.$store.dispatch("login", {
        userId: this.id,
        password: this.password
      });
    }
  }
};
</script>

<style scoped>
.login-card {
  /* 背景色は決まり次第変更 */
  background-color: rgba(66, 175, 237, 0.96);
}

.signin-form {
  width: 328px;
}
</style>
