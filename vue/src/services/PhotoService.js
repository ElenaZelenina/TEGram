import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});


export default {

  list() {
    return http.get('/docs');
  },

  get(id) {
    return http.get(`/docs/${id}`)
  },

  create(doc) {
    console.log('Doc', doc);
    return http.post(`/photos`, doc);
  },
  update(id, doc) {
    return http.put(`/docs/${id}`, doc);
  },
  delete(id) {
    return http.delete(`/docs/${id}`);
  }

}
