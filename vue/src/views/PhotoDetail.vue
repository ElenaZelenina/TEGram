<template>
  <div class="comments">
    <h2>{{ caption }}</h2>

    <img v-bind:src="link" />

    <div class="comment" v-for="comment in comments" v-bind:key="comment.id">
      <div class="author">
        Comment by {{ comment.username }} on {{ comment.dateTime }}
      </div>
      {{ comment.message }}
    </div>
  </div>
</template>

<script>
import photoService from "../services/PhotoService";

export default {
  name: "photo-details",
  props: [],
  data() {
    return {
      caption: "",
      dateTime: null,
      likesCount: 0,
      link: "",
      comments: [],
    };
  },
  methods: {
    retrievePhoto() {
      photoService.get(this.photoId).then((response) => {
        this.caption = response.data.caption;
        this.dateTime = response.data.dateTime;
        this.link = response.data.link;
        this.comments = [];
        photoService.getComments(this.photoId).then((response) => {
          this.comments = response.data;
        });
      });
    },
  },
  created() {
    this.photoId = this.$route.params.id;
    this.retrievePhoto();
  },
};
</script>

<style scoped>
</style>
