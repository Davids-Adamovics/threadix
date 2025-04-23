import axios from "axios";

// Change to localhost in Development
// Change to 77.37.54.78 in Production
const BASE_URL_USER = "http://localhost:8080/api/v1/users";
const BASE_URL_POST = "http://localhost:8080/api/posts";

class UserService {
  /*
   * USERS
   * Axios links for all User related requests
   * [1gvqiy]. Logins in the user from the Authentication Page using: email, password
   * [m0kcxk]. Registers the user from the Authentication Page using: email, password, username, displayName
   * [9r6qoo]. Searches for users using the search bar in the header, using: query
   */
  login(email: string, password: string) {
    // [1gvqiy]
    return axios.post(`${BASE_URL_USER}/login`, { email, password });
  }

  register(
    username: string,
    displayName: string,
    email: string,
    password: string
  ) {
    //[m0kcxk]
    return axios.post(`${BASE_URL_USER}/save`, {
      username,
      displayName,
      email,
      password,
    });
  }

  searchUsers(query: string) {
    // [9r6qoo]
    return axios.get(`${BASE_URL_USER}/search`, {
      params: { search: query },
    });
  }

  /*
   * POSTS
   * Axios links for all Post related requests
   * [2t9xsx]. All posts made, only for test purposes, because all posts wont get displayed
   */
  getAllPosts() {
    // [2t9xsx]
    return axios.get(`${BASE_URL_POST}/all`);
  }

  createPost(postData: any) {
    return axios.post(BASE_URL_POST, postData);
  }

  uploadImage(image: File) {
    const formData = new FormData();
    formData.append("file", image);
    return axios.post(`${BASE_URL_POST}/upload`, formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });
  }
}

export default new UserService();
