<template>
  <photo-list v-bind:photos="photos" class="photolist"></photo-list>
</template>

<script>
import photoList from "../components/PhotoList";
import photoService from "../services/PhotoService";

export default {
  name: "photo-feed",
  components: {
    photoList,
  },
  data() {
    return {
      photos: [],
    };
  },
  created() {
    this.getPhotos();
  },
  updated() {
    this.getPhotos();
  },

    methods: {
    getPhotos() {
      console.log('query = ', this.$route.query)
      let photoPromise;
      if(this.$route.query.userId) {
        photoPromise = photoService.listByUserId(
          this.$route.query.userId);
      } else {
        photoPromise = photoService.list();
      }
      photoPromise.then((response) => {
        this.$store.commit("SET_PHOTOS", response.data);
        this.photos = response.data;
      });
    },
  },
};
</script>
