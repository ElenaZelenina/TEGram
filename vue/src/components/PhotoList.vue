<template>
  <section class="photolist">
    <div v-for="photo in photos" v-bind:key="photo.id">
      <router-link v-bind:to="'/photo/' + photo.photoId">
        <b-image
          class="photo"
          v-bind:src="photo.link"
          webp-fallback=".jpg"
          ratio="16by9"
        ></b-image>
        <p class="caption">{{ photo.caption }}</p>
      </router-link>
      <div v-show="photo.commentUsername" class="comment">
        <div class="author">
          Most recent comment by {{ photo.commentUsername }} on
          {{ photo.commentDateTime }}
        </div>
        {{ photo.message }}
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "photo-list",
  props: ["photos"],
  data() {
    return {
      photo: {
        id: 0,
        link: "",
        caption: "",
      },
    };
  },
};
</script>
<style scoped>
.photolist {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(400px, 1fr));
  grid-gap: 1rem;
}
.comment {
  padding: 8px;
  border: 1px solid #aaa;
}
.comment .author {
  text-align: right;
  font-style: italic;
  border-bottom: 1px solid #999;
  margin-bottom: 8px;
}
</style>