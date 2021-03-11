<template>
  <div class="Photo Page">
    <body>
    <div class="large-12 medium-12 small-12 cell">
      
      <label>File:
        <input type="file" id="file" ref="file"
          v-on:change="handleFileUpload"/>
      </label>
       
       <a id="show-submit-button"
     href="#"
     v-if="showButton === true"
     v-on:click.prevent="showButton = true">
     </a>

      <button 
      
      v-on:click.prevent="submitFile">Submit</button>

    </div>
    
    <div id="file">
      <img id="imageView" />
        <label for="caption" v-show="files!=null">Add Photo Caption</label>

        <textarea id="caption" name="caption"
          v-if="files!=null" v-on:change="handleCaptionChanged"
          v-model="caption"
        
        ></textarea>

    </div>
    </body>
    
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
           
          console.log('SUCCESS!!', link);
        }
        catch(err){
          console.log('FAILURE!!', err);
        }
      },
      /*
        Handles a change on the file upload
      */
     
       handleFileUpload(){
        //console.log('event', event)
        this.files = event.target.files;
        const imageView = document.getElementById('imageView');
        imageView.src = URL.createObjectURL(this.files[0]);
        imageView.onLoad = ()=>{
          URL.revokeObjectURL()
        }
      },
    /** 
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
   */
      handleCaptionChanged(event) {
        
       this.caption = event.target.value;
       alert('Submitted');
      }
    }
}
  //   handleSubmitButton() {


  //     button.style.visibility = "hidden";
  //   }
  // }
</script>
<style scoped>

#caption {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    width: 95%;
  
}
body {
  background-color: #00ADEE;
  padding: 0;
  margin: 0;
  width: 100%;
  min-height:100vh;
  }
div {
  margin: auto;
  color: rgb(255, 255, 255);
  background-color: #00ADEE;
  display: flex;
  justify-content: center;
  align-items: baseline;
}
button {
  color: white;
  background-color: #8CC63F;
  padding: 10px;
  border-radius: 10px;
  -moz-border-radius: 10px;
  -webkit-border-radius: 10px;
  display: flex;
  justify-content: center;
}
</style>
