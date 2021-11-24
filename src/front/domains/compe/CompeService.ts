import axios from 'axios';
import Compe from './Compe';

export default class CompeService {
  constructor() {
    console.log('creating new instance of hero.service');
  }

  async createCompe(compe: Compe) {
    return await axios.post(`/api/compe/new`, compe);
  }
  async getCompes(): Promise<Compe[]> {
    return await axios.get(`/api/compe/all`).then((response) => {
      return response.data;
    });
  }
}
