<template>
  <div class="list">
    <b-tag class="username" type="is-success" size="is-large" v-show="username">{{ username }}</b-tag>
    <section class="photolist">
      <div v-for="photo in photos" v-bind:key="photo.id">
        <router-link v-bind:to="'/photo/' + photo.id">
        <b-tag type="is-success" class="like">&#10084; {{ photo.likesCount }}</b-tag>
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
  </div>
</template>

<script>
export default {
  name: "photo-list",
  props: ["photos", "username"],
  data() {
    return {};
  },
};
</script>
<style scoped>
.photolist {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(500px, 1fr));
  grid-gap: 2rem;
  margin: 0 2rem 2rem 2rem;
}
.comment {
  padding: 8px;
}
.comment .author {
  font-style: italic;
  border-bottom: 1px solid #999;
  margin-bottom: 8px;
}
.caption {
  font-weight: bold;
  text-align: center;
}
.list {
  display: inline-block;
  text-align: right;
}
.username {  
  margin: 1rem;
}
.tag.is-success {
  background-color: #8cc63f;
  font-size: x-large;
}
.like {
  display: flex;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
  
}
</style>