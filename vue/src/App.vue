<template>
  <div id="app">
    <div id="nav">
      <div class="nav-grp">
        <img class="navbar-brand" src="../public/mark-blue.jpg" alt="logo">
        <router-link v-bind:to="{ name: 'home' }" class="navbar-item" spaced shadow>Home</router-link>
        <router-link v-bind:to="{ name: 'photo-feed' }" class="navbar-item">Photos</router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="navbar-item">Logout</router-link>
      </div>
      <img class="navbar-icon" src="../public/TEGram.png" alt="logo">
    </div>
    <router-view />
  </div>
</template>
// use input textbox under the picture--after upload photo it pops up , we type something
// same click event as submit
// 2 different tasks talk to aws, send the photo to aws s3, get the link from aws
// link to the photo which we are storing in the database, and then send all this info
// to the data base. link file name and caption.
//??/ andrews

// elena created photoRequest
// send from client to server, not send whole photo class, which

// something to think about-generate frandom file name, on serve name generate the name there.
// environment variables for aws

<style scoped>
#app{
  height: 100vh;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
.nav-grp {
  display: flex;
  align-items: flex-end;
  min-width: 300px;
  width: 200px;
}
#nav {
  display: flex;
flex-wrap: wrap;
justify-content: space-around;
align-items: center;
}
.navbar-brand {
  width: 50px;

}
.navbar-item{
  display: flex;
  padding: 0.25rem;
  
}
#app {
  background-color: rgb(255,255,255);
  position: relative;
}
.navbar-icon {
  height:auto;
  max-width: 300px;

}
.navbar-stackx {
  display: flex;
  justify-content: center;
  margin-left: 20%;
  padding: auto;
}
</style>