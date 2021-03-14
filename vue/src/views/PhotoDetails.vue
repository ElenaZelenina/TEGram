<template>
  <div class="comments">
    <h2>Photo Details</h2>
    <div class="comment" v-for="comment in comments" v-bind:key="comment.id">
      <h3>{{ comment.username }}</h3>
      <p class="postedOn">{{ comment.postedOn }}</p>
      <p>{{ comment.body }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "comments-list",
  props: ["comments"],
};
</script>

<style scoped>
</style>
