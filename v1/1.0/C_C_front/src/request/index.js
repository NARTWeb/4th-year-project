import axios from 'axios'

axios.defaults.withCredentials = true;
const req = axios.create({
    baseURL: 'http://localhost:8888',
    timeout: 10000
});

export default req