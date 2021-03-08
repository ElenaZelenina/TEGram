import axios from 'axios';
import photoService from './PhotoService';



export default {
    uploadToS3(file) {
        if (file) {
            photoService.getPresignedUrl(file.name, file.type)
            .then((response) => {
                const http = axios.create({
                    baseURL: response.data
                  });
                  const data = new FormData();
                  data.append('name', file.name);
                  data.append('file', file);

              return http.put("", file, {
                  headers: {
                      'Content-Type': file.type,
                      
                  }
              });
              })
            
          }
    
    }
}