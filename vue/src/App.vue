<template>
  <div id="app">
    <div id="nav">
      <img class="navbar-brand" src="../public/mark-blue.jpg" alt="logo">
      <router-link v-bind:to="{ name: 'home' }" class="navbar-item">Home</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="navbar-item">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

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
</style>