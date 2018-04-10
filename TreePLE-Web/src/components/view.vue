
<template>

<div id="view">

   <br><br><br><br><br>
    <h2>Montreal Tree Map</h2><br><br>
<gmap-map
    :center="center"
    :zoom="11"
    style="width: 70%; height: 700px; margin:0px auto; display:inline-block"
  >
    <gmap-marker
      :key="index"
      v-for="(m, index) in markers"
      :position="m.position"
      :clickable="true"
      :icon.sync="m.icon"
      :draggable="flase"
      @click="center=m.position"
    ></gmap-marker>
  </gmap-map>

  <br>
<button onclick="myFunction()">Refresh</button>

<div id="myDIV" style="font-size:130%; font-weight:bold;">
  Canopy: 300;  
  Carbon Seq: 400; 
  BioDiversity index: 0.5
</div>

<p> </p>

    <p>
      <span v-if="errorTree" style="color:red">Error: {{errorTree}} </span>

    </p>
  </div>
</template>

<script src="./treePlanning.js"></script>
<script>
import * as VueGoogleMaps from 'vue2-google-maps'
import Vue from 'vue'

Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyCDWKnVgX3DIKrH1Rbux7RvLtaV6fO21QE',
    v: '3'
    // libraries: 'places', //// If you need to use place input
  }
})

export default {
  data () {
    return {
      icon: {
        url: 'google.maps.SymbolPath.CIRCLE',
        size: {width: 46, height: 46, f: 'px', b: 'px'},
        scaledSize: {width: 23, height: 23, f: 'px', b: 'px'}
      },
      center: {lat: 45.549302, lng: -73.681559},
      markers: [{
        position: {lat: 45.549302, lng: -73.681559}
      }, {
        position: {lat: 46.549302, lng: -74.681559}
      }]
    }
  }
}
</script>


<style>

 #view { 
   background-image: url("../assets/Greenery.jpg");
    height: 7em; 
    background-repeat: repeat;
    background-size: 18em;

    
  }
th, td {
    padding: 5px;
}

</style>


