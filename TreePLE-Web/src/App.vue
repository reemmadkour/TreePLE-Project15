<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg sticky-top navbar-light" style="background-color: #e3f2fd;">
      <a class="navbar-brand" href="/">TreePLE  <img src="./assets/logo.jpg"></a>
     
      <div class="navbar" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="/#/municipalities"><i class="fa fa-eye fa-fw"></i>  View Trees<span class="sr-only">(current)</span></a>
          </li>
         
           <li class="nav-item active">
            <a class="nav-link" href="/#/map">Locate Trees<span class="sr-only">(current)</span></a>
          </li>

           <li class="nav-item active">
            <a class="nav-link" href="/#/trees">Forecasts <span class="sr-only">(current)</span></a>
          </li>

          <li class="nav-item active">
            <a class="nav-link" href="/#/app"> List Trees <span class="sr-only">(current)</span></a>
          </li>

          
        </ul>
      </div>
    </nav>
    <br>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}

</script>

<style>

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

img {
  display:inline;
  width:3em;
  margin-right:5em;
}

#navMenu {
  background-color:blue;
}

#navMenu li {
  float: left;
  display: inline;
  margin:0;
  width:10%;
}

</style>



