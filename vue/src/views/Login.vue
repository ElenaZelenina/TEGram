<template>
  <div id="login">
    <photo-feed class="photof" v-bind:photos="photos"></photo-feed>
    <form class="form-signin" @submit.prevent="login">
      <h1 class="title is-3">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <b-field label="Username" class="sr-only">
        <b-input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
      /></b-field>
      <b-field label="Password" class="sr-only">
        <b-input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
      /></b-field>
      <button class="button is-rounded" type="submit">SIGN IN</button>
      <router-link :to="{ name: 'register' }" class="button is-rounded"
        >NEED AN ACCOUNT?</router-link
      >
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import PhotoFeed from "../components/PhotoFeed";
import photoService from "../services/PhotoService";

export default {
  name: "login",
  components: {
    PhotoFeed
  },
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
      photos: []
    };
  },
  created() {
    this.getPhotos();
  },
  methods: {
    getPhotos() {
      photoService.list().then((response) => {
        this.$store.commit("SET_PHOTOS", response.data);
        this.photos = response.data;
      });
    },
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>
<style scoped>
.form-signin .photof {
  width: 100%;
  max-width: 500px;
}

#login {
  display: flex;
  justify-content: center;
}

a {
  margin: auto 20px;
  color: white;
  background-color: #00ADEE;
}

button {
  color: white;
  background-color: #8CC63F;
}
</style>