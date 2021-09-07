
<template>
    <div>
      <template v-if="!this.$store.getters.isAuthenticated">
        <input type="text" v-model="id">
        <input type="password" v-model="password">
        <button @click="login">ログイン</button>
      </template>
      <template v-if="$store.getters.isAuthenticated">
        <p>{{$store.getters.id}}さん</p>
        <button @click="logout">ログアウト</button>
      </template>
    </div>
</template>
 
 
<script>
export default {
  data() {
    return {
      id: '',
      password: '',
    };
  },
  methods: {
    login() {
      this.$store.dispatch('login', {
        userId: this.id,
        password: this.password
      });
      // this.id = '';
      // this.password = '';
    },
    logout() {
      this.$store.dispatch('logout');
    },
  }
};
</script>