<template>
  <photo-list v-bind:photos="photos" class="photolist"></photo-list>
</template>

<script>
import photoList from "../components/PhotoList";
import photoService from "../services/PhotoService";

export default {
  name: "favorites",
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

  methods: {
    getPhotos() {
      let photoPromise;
      photoPromise = photoService.listFavoritesByUser(this.$route.query.userId);

      photoPromise.then((response) => {
        this.$store.commit("SET_PHOTOS", response.data);
        this.photos = response.data;
      });
    },
  },
};
</script>

<style scoped>
h1 {

}
</style>