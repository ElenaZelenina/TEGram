<template>
    <section>
        <h1>PhotoFeed</h1>
        <b-image
        class="photo"
        v-for="photo in photos"
        v-bind:key="photo.id"
            src="photo.link"
            webp-fallback=".jpg"
            ratio="16by9"
        >{{photo.link}}</b-image>
        <br>
    </section>
</template>

<script>
export default {
  name: 'photo-feed',
  props: ['photos'],
  data () {
    return {
      photo: {
          link: "",
          caption: ""
      }
    }
  },
}
</script>