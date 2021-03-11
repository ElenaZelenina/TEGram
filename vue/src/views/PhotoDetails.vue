<template>
<div>
<h1>Photo Details</h1>

</template>

<script>
import photoFeed from "../views/PhotoFeed";

export default {
name: "photoDetails",
  data() {
    return {
        photo: {
            caption: "",
            uploadDate: "",
        }
    };   
},
methods: {
    photoDetail() {
        let photo = {
            "caption": "",
            "uploadDate": new Date('yyyy-MM-dd'),
        }
        photos.unshift(photo);
    }
},
}
</script>

<style scoped>

</style>
