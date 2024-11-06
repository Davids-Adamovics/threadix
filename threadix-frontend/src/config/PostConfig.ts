import axios from 'axios';

const API_URL1 = 'http://localhost:8080/post/all';


class UserService {
    getAllPreces() {
        return axios.get(API_URL1);
    }
}


export default new UserService();