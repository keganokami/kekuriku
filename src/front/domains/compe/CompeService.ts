import axios from 'axios';
import Compe from './Compe';
import CompeEntry from './CompeEntry';
import CompeResponse from './CompeResponse';

export default class CompeService {

  async createCompe(compe: Compe) {
    return await axios.post(`/api/compe/new`, compe);
  }

  async getCompes(): Promise<CompeResponse[]> {
    return await axios.get(`/api/compe/my-compes`);
  }

  async getCompe(compeId: string): Promise<CompeResponse> {
    return await axios.get(`/api/compe/my-compe/${compeId}`);
  }

  async entryCompe(compe: CompeEntry) {
    return await axios.post(`/api/compe/entry/new`, compe);
  }
}
