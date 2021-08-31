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
          'api/login',
          {
            userId: authData.userId,
            password: authData.password
          }
        )
        .then(() => {
            commit('updateId', authData.userId);
        });
    },
    logout({ commit }) {
      axios
        .post(
          'api/logout'
        )
        .then(() => {
          commit('resetData');
        });
    },
};
