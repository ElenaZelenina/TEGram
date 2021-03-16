<template>
  <div id="app">
    <div id="nav">
      <div class="navbar">
      <img class="navbar-brand" src="../public/mark-blue.jpg" alt="logo" />
        
        <router-link
          v-bind:to="{ name: 'home' }"
          class="navbar-item"
          >Upload Photo</router-link
        >
        <router-link 
          v-bind:to="{ name: 'photo-feed' }" 
          class="navbar-item"
          >Photos</router-link
        >
        <router-link 
          v-bind:to="{ name: 'favorites' }" 
          class="navbar-item"
          >Favorites</router-link
        >
       
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          class="navbar-item"
          >Logout</router-link
        >

      </div>
      <img class="navbar-icon" src="../public/TEGram.png" alt="logo" />
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
#app {
  height: 100vh;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
#nav {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-bottom: 10px;
}
.navbar {
  display: inline;
  position: relative;
}
.navbar-brand {
  display: inline;
  max-width: 70px;
  height: auto;
  margin-left: 15px;
}
.navbar-item {
  display: inline;
  max-width: 10em;
  padding: 0.3em 0.5em;
  border-radius: 9px;
  background-color: #00adee;
  max-height: 3em;
  margin: 0 .2em;
  color: white;
  text-transform: uppercase;
  border: #8cc63f bold;
  white-space: nowrap;
}
.navbar-item:hover {
  background-color: #8cc63f;
  color: white;
}
#app {
  background-color: rgb(255, 255, 255);
  position: relative;
}
.navbar-icon {
  display: inline;
  height: auto;
  max-width: 300px;
}
.navbar-stackx {
  display: flex;
  justify-content: center;
  margin-left: 20%;
  padding: auto;
}
.navbar-item-1 {
  background-color: #00adee;
}
.navbar-item-2 {
  background-color: #8cc63f ;
}
.navbar-item-3 {
  background-color: #00adee;
}
@media screen and (max-width: 810px) {
  #app {
    font-size: 10px;
  }
  .navbar-icon {
    max-width: 150px;
  }
  .navbar-brand {
    max-height: 40px;
  }
  @media screen and (min-width: 650px) {
    #app {
      font-size: 13px;
    }
    .navbar-icon {
      max-width: 200px;
    }
    .navbar-brand {
      max-height: 40px;
    }
  }
}
</style>