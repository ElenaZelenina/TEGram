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
          photos: []
      }
  },
  created() {
    this.getPhotos();
  },
  methods: {
    getPhotos() {
      photoService.list().then((response) => {
        this.$store.commit("SET_PHOTOS", response.data);
        this.photos = response.data;
      });
    },
  },
};
</script>

<style>
    .photolist {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
        grid-gap: 1rem;
    }
</style>