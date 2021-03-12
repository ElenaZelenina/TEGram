
<template>
  <div id="photo-page">
    <div id="photo-chooser" class="">
      <label id="file-label">File:
        <input type="file" id="file" ref="file"
          v-on:change="handleFileUpload"/>
      </label>
    </div>
    <div id="photo-display">
      <div>
        <img id="imageView" />
      </div>
      <div id="caption-box" v-show="files!=null">
        <label>Add Photo Caption
          <textarea id="caption" name="caption"
            rows="4" cols="20" maxlength="156" wrap="hard"
            v-if="files!=null" v-on:change="handleCaptionChanged"
            v-model="caption"
          ></textarea>
        </label>
        <button id="submit-btn" v-on:click.prevent="submitFile">Submit</button>
      </div>
    </div>
  </div>
</template>
<script>
import PhotoService from '../services/PhotoService';
import s3Service from "../services/S3Service"
export default {
    /*
      Defines the data used by the component
*/
    data(){
      return {
        files: null,
        caption: ""
      }
    },
    methods: {
      /*
        Submits the file to the server
        async chains promises - calls a chain
        of then functions, returns promise in the callbacks
        in the then function
      */
      async submitFile(){  
        try {
          // upload this photo to S3
          const s3Resp = await s3Service.uploadToS3(
            'aphoward', this.files[0]);
          /*
           gather the photo metadata (photo_id, fileName, link, caption)
           the following consts gives us each part of the data to send to our database.
           user ID from VUEX store--gets our current user -- see in inspect window, it return a user id
          */
          const userId = this.$store.state.user.id;
          // fileName 
          const fileName = this.files[0].name;
          // get the S3 link from the response
          // inspect: link shows url +? and then more binary. parse
          const link = s3Resp.config.baseURL.split('?')[0];
          const caption = this.caption;

          const photoMetadata = {
             userId, fileName, link, caption
          };

          // send the metadata to the backend to store in PosgresSQL DB
          console.log('sending photo metadata', photoMetadata);
          const metaResp = await PhotoService.create(photoMetadata);
          this.$router.push("/Photos"); 
          console.log('SUCCESS!!', link);
        }
        catch(err){
          console.log('FAILURE!!', err);
        }
      },
      /*
        Handles a change on the file upload
      */
     
    
      handleFileUpload(event){
        //console.log('event', event)
        this.files = event.target.files;
        const imageView = document.getElementById('imageView');
        imageView.src = URL.createObjectURL(this.files[0]);
        imageView.onLoad = ()=>{
          URL.revokeObjectURL(output.src)
        }
      },
       
      //  Handles a change to the caption
  
      handleCaptionChanged(event) {
        this.caption = event.target.value;
      }
    }
}
  //   handleSubmitButton() {


  //     button.style.visibility = "hidden";
  //   }
  // }
</script>
<style scoped>
#photo-page {
  color: white;
  width: 100%;
  height: 100%;
  background-color: #00ADEE;
}
#photo-chooser {
  text-align: center;
  margin: 20px auto;
}
#file-label{
  padding-left: 100px;
}
#photo-display {
  text-align: center;
}
#imageView {
  display:block;
  margin: 0 auto;
  height: 360px;
  width: auto;
}
#caption-box {
    max-width: 750px;
    width: auto;
    margin: 0 auto;
    padding: 20px 0;
}
#caption-box textarea {
    background-color: white;
}
#caption {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    width: 100%;
    display: block;
    margin: auto;
    padding: 30px 0;
}
#submit-btn {
  color: white;
  background-color: #8CC63F;
  padding: 10px;
  margin: 10px;
  border-radius: 10px;
}
</style>
