import axios from 'axios';
export const state = () => ({
  id: null
});

export const getters = {
    id: state => state.id,
    isAuthenticated: state => {
      return state.id != null;
    },
};

export const mutations = {
    updateId(state, id){
      state.id = id;
    },
    resetData(state) {
      state.id = null;
    },
};

export const actions = {
    login({ commit }, authData) {
      axios
        .post(
          'management-account/login',
          {
            userId: authData.userId,
            password: authData.password
          }
        )
        .then((response) => {
          localStorage.setItem('token', response.headers['x-auth-token']);
            commit('updateId', authData.userId);
            this.$router.push('/auth/dashboard');
        }).catch((error) => {
          console.log("認証エラー");
        });
    },
    logout({ commit }) {
      axios
        .post(
          'management-account/logout'
        )
        .then(() => {
          localStorage.removeItem('token');
          commit('resetData');
        });
    },
};
