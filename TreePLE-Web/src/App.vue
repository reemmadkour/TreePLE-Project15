<template>
  <div id="app">
	<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li><a href="#">Page 1</a></li>
      <li><a href="#">Page 2</a></li>
      <li><a href="#">Page 3</a></li>
    </ul>
  </div>
</nav>
    <nav class="navbar navbar-expand-lg sticky-top navbar-light" style="background-color: #000033;">
      <a class="navbar-brand" href="/">TreePLE  <img src="./assets/logo.jpg"></a>
     
      <div class="navbar" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="/">Home style="color: #ffffff"<span class="sr-only">(current)</span></a>
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



