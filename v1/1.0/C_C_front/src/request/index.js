import axios from 'axios'
import { url } from './token'

axios.defaults.withCredentials = true;
const req = axios.create({
    baseURL: 'http://' + url,
    timeout: 10000
});
export default req