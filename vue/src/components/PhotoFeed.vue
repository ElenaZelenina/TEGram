<template>
    <section>
        <b-image
        class="photo"
        v-for="photo in photos"
        v-bind:key="photo.id"
            src="photo.link"
            webp-fallback=".jpg"
            ratio="16by9"
            @load="onLoad1"
        ></b-image>
        <br>
    </section>
</template>

<script>
export default {
  name: 'photo-feed',
  props: ['photos']
}
</script>