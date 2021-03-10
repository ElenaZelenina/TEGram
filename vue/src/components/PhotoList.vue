<template>
    <section class="photolist">
        <router-link :to="{name: '', params: {}}" 
        v-for="photo in photos" v-bind:key="photo.id">
        <b-image
            class="photo"
            v-bind:src="photo.link"
            webp-fallback=".jpg"
            ratio="16by9"
        ></b-image>
        <p class="caption">{{photo.caption}}</p>
        </router-link>
    </section>
</template>

<script>
export default {
  name: "photo-list",
  props: ["photos"],
  data() {
    return {
      photo: {
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
</style>