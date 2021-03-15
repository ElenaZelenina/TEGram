<template>
  <div class="details">
    <div class="tags">
      <b-button type="is-success">&#10084; {{ likesCount }}</b-button>
      <router-link v-bind:to="'/photos?userId=' + userId">
        <b-button type="is-success">{{ username }}</b-button>
      </router-link>
    </div>
    <b-image v-bind:src="link"></b-image>
    <h2>{{ caption }}</h2>
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
      username: "",
      userId: null,
    };
  },
  methods: {
    retrievePhoto() {
      photoService.get(this.photoId).then((response) => {
        this.caption = response.data.caption;
        this.dateTime = response.data.dateTime;
        this.link = response.data.link;
        this.likesCount = response.data.likesCount;
        this.userId = response.data.userId;
        this.comments = [];
        photoService.getUsername(this.userId).then((response) => {
          this.username = response.data;
        });
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
  .details {
    max-width: 80%;
    align-self: center;
  }
  h2 {
    font-size: large;
    font-weight: bold;
    text-align: center;
  }
  .author {
    font-weight: bold;
  }
  .tags {
    display: flex;
    justify-content: space-between;
  }
  .button.is-success {
    background-color: #8CC63F;
  }
  .button.is-info.is-light {
    color: #00ADEE;
  }

</style>
