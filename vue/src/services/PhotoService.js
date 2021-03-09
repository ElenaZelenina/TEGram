import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});


export default {

  list() {
    return http.get('/allphotos');
  },

  getPresignedUrl(fileName, fileType) {
    return http.get('/photos/uploadCredentials', {
      params:{
        keyName: fileName,
        contentType: fileType
      }
    });
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
