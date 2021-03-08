import axios from 'axios';
import photoService from './PhotoService';



export default {
 
    uploadToS3(username, file) {
        if (file) {
            return photoService.getPresignedUrl(`${username}/${file.name}`, file.type)
                .then((response) => {
                    const http = axios.create({
                        baseURL: response.data,
                    });
                    delete http.defaults.headers.common['Authorization'];


                    return http.put("", file, {
                        headers: {
                            'Content-Type': file.type,
                        }
                    });
                })

        }

    }
}