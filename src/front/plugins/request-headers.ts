import axios, { AxiosRequestConfig } from "axios"

axios.interceptors.request.use((config: AxiosRequestConfig) => {
    const token = localStorage.getItem('token');
    if (token != null) {
        config.headers = { 'X-AUTH-TOKEN': token } // todo: ここでトークンを付与する
    }
    return config
})