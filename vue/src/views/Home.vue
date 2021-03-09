<template>
  <div class="Photo Page">
    <body>
    <div class="large-12 medium-12 small-12 cell">
      <label>File
        <input type="file" id="file" ref="file" 
          v-on:change="handleFileUpload"/>
      </label>
      <button v-on:click="submitFile">Submit</button>
    </div>
    <div id="file">
      <img id="imageView" />
    </div>
    </body>
    
  </div>
  
  
</template>

<script>
  import s3Service from "../services/S3Service"
 // import PhotoService from "../services/PhotoService"
  export default {
    /*
      Defines the data used by the component
*/
    data(){
      return {
        files: null
      }
    },
    methods: {
      /*
        Submits the file to the server
      */
      submitFile(){
       
          
            s3Service.uploadToS3('aphoward', this.files[0])
            .then(function(){
          console.log('SUCCESS!!');
        })
        .catch(function(){
          console.log('FAILURE!!');
        });
      },
      /*
        Handles a change on the file upload
      */
      handleFileUpload(event){
        console.log('event', event)
        this.files = event.target.files;
        const imageView = document.getElementById('imageView');
        imageView.src = URL.createObjectURL(this.files[0]);
        imageView.onLoad = ()=>{
          URL.revokeObjectURL(output.src)
        }
      }
    }
  }
</script>

<style scoped>
input {
  background-color: #00ADEE;
}

div {
  margin: auto 20px;
  color: white;
  background-color: #00ADEE;
}

button {
  color: white;
  background-color: #8CC63F;
}

</style>
