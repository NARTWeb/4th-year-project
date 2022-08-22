<script setup>
import { RouterLink, RouterView } from 'vue-router'
<<<<<<< Updated upstream
</script>

<template>
<div id="page">
=======
import HelloWorld from './components/HelloWorld.vue'
import ResultItem from './components/ResultItem.vue';
</script>

<template>
  <header>
    <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125" />

    <div class="wrapper">
      <HelloWorld msg="You did it!" />

      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
      <ResultItem avatar="https://www.planetware.com/wpimages/2020/02/france-in-pictures-beautiful-places-to-photograph-eiffel-tower.jpg" 
      username="ssz" buttonLabel="add"></ResultItem>
    </div>
  </header>

>>>>>>> Stashed changes
  <RouterView />
</div>
</template>

<style>
</style>
