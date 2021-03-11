<template>
  <div id="app">
    <div id="nav">
      <img class="navbar-brand" src="../public/mark-blue.jpg" alt="logo">
      <router-link v-bind:to="{ name: 'home' }" class="navbar-item">Home</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="navbar-item">Logout</router-link>
      <img class="navbar-stack" src="../public/Copy of Stacked logo.png" alt="stacked logo">
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

#nav {
  display: flex;
  height: auto;
  align-items: flex-end;
  }
img .navbar-brand {
  display: flex;
  }
.navbar-item{
  display: flex;
  }
  #app {
    background-color: #999999;
  }
  .navbar-stack {
    display: flex;
    justify-content: center;
    margin-left: 20%;
    padding: auto;
  }
</style>