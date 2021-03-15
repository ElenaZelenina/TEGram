import axios from 'axios';
// have to allow cross origin
const http = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET, POST'
    /* 
    good explanation of Cross Origin Request
    https://stackoverflow.com/questions/45975135/access-control-origin-header-error-using-axios-in-react-web-throwing-error-in-ch
           headers: { 
      'x-apikey': '59a7ad19f5a9fa0808f11931',
      'Access-Control-Allow-Origin' : '*',
      'Access-Control-Allow-Methods' : 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
      */
            }
});

export default {
  list() {
    return http.get('/photos');
  },
  listByUserId(userId) {
    return http.get(`/photos/users/${userId}`);
  },
  // listFavoritesByUserId(userId) {
  //   return http.get(`/photos/users/${userId}/lists/favorites`);
  // },
  getTen() {
    return http.get('/tenphotos');
  },
  getPresignedUrl(fileName, fileType) {
    return http.get('/photos/uploadCredentials', {
      params:{
        keyName: fileName,
        contentType: fileType
      }
    });
  },
  getComments(id){
    return http.get(`/photo/${id}/comments`);
  },
  get(id) {
    return http.get(`/photo/${id}`);
  },
  getUsername(id){
    return http.get(`/user/${id}`);
  },

  // post photo metadata
  create(photoMetadata) {
    console.log('photoMetadata', photoMetadata);
    return http.post(`/photos`, photoMetadata, {
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET, POST'
      }
    });
  },
  update(id, doc) {
    return http.put(`/docs/${id}`, doc);
  },
  delete(id) {
    return http.delete(`/docs/${id}`);
  }
}