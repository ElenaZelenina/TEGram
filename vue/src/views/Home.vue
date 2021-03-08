<template>
  <div class="Home">
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
  </div>
</template>

<script>
  import PhotoService from "../services/PhotoService.js"
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
        /*
                Initialize the form data
            */
            let formData = new FormData();

            /*
                Add the form data we need to submit
            */
            formData.append('file', this.files[0]);

        /*
          Make the request to the POST /single-file URL
        */
       // /Users/aprilhoward/Desktop/finalcapstone/final-capstone-team-merge-conflict/vue/src/store
            PhotoService.create( '/vue/source/store',
                formData,
                {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
              }
            ).then(function(){
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