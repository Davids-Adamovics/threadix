import axios from 'axios';

const API_GET_ALL_POSTS = 'http://localhost:8080/api/posts/all';

class UserService {
    getAllPosts() {
        return axios.get(API_GET_ALL_POSTS);
    }
}


export default new UserService();