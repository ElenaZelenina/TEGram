<template>
    <section>
        <b-image
        class="photo"
        v-for="photo in photos"
        v-bind:key="photo.id"
            v-bind:src="photo.link"
            webp-fallback=".jpg"
            ratio="240by200"
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