<template>

<div id="view">

   <br><br><br><br><br>
    <h2>Montreal Tree Map</h2><br><br>
<gmap-map id="mymap"
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
      :draggable="false"
      v-on:click='m.isClicked = !m.isClicked'
      @click="center=m.position"
    >
     <gmap-info-window
        v-if="m.isClicked"
        :position="m.position">
        <table class="table table-hover" style="width:100%">
	<thead>
 <th> Species </th>
  <th> Height </th>
   <th> Diameter </th>
    <th> Longitude </th>
     <th> Latitude </th>
      <th> Landtype </th>
        <th> Municipality </th>
</thead>
<tbody>
         <tr>
 <td>Red Maple</td>
          <td>12.91</td>
          <td>1</td>
          <td>45.56</td>
          <td>-73.57</td>
          <td>Institutional</td>
         <td>Montreal-West</td>
</tr>
<tr>
        <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
         
          <td></td>
</tr>
        </tbody>
	</table>
	<b-btn v-b-modal.modal1>Edit Tree</b-btn>

  <!-- Modal Component -->
  <b-modal id="modal1" title="Edit Tree">
 <form action="/#/view">
  <div class="form-group row">
  <label for="example-text-input" class="col-2 col-form-label">Height</label>
  <div class="col-3">
    <input class="form-control" type="text" id="example-text-input">
  </div>
</div>
<div class="form-group row">
   <label for="example-text-input" class="col-2 col-form-label">Diameter</label>
  <div class="col-3">
    <input class="form-control" type="text"  id="example-text-input">
  </div>
</div>
<div class="form-group row">
   <label for="example-text-input" class="col-2 col-form-label">Longitude</label>
  <div class="col-3">
    <input class="form-control" type="text"  id="example-text-input">
  </div>
</div>
<div class="form-group row">
  <label for="example-text-input" class="col-2 col-form-label">Latitude</label>
  <div class="col-3">
    <input class="form-control" type="text"  id="example-text-input">
  </div>
</div>
<div class="form-group row">
  <label for="exampleSelect1" class="col-2 col-form-label">Species</label>
<div class="col-3">
    <select class="form-control" id="exampleSelect1">
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
    </select>
</div>
</div>
<div class="form-group row">
  <label for="exampleSelect1" class="col-2 col-form-label">Municipality</label>
<div class="col-3">
    <select class="form-control" id="exampleSelect1">
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
    </select>
</div>
</div>
<div class="form-group row">
  <label for="exampleSelect1" class="col-2 col-form-label">LandType</label>
<div class="col-3">
    <select class="form-control" id="exampleSelect1">
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
    </select>
</div>
</div>
<div class="row">
<div class="container">
<div class="col-xs-6 col-sm-3 offset-sm-2">

  <button type="submit" style="font-size:120%" class="btn btn-secondary btn-block">Edit</button>

</div>
</div>
</div>
</form>
  </b-modal>
    </gmap-info-window>
</gmap-marker>
  </gmap-map>

<div class="dropdown">
 <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    List Trees By
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" href="#">All</a>
    <a class="dropdown-item" href="#">Municipality</a>
    <a class="dropdown-item" href="#">Species</a>
  </div>

</div>
 

    <br><table class="table table-hover" style="width:100%">
<thead>
 <th> Species </th>
  <th> Height </th>
   <th> Diameter </th>
    <th> Longitude </th>
     <th> Latitude </th>
      <th> Landtype </th>
        <th> Municipality </th>
</thead>
<tbody>
<tr v-for="tree in Trees" >
      <td>{{ tree.treeSpecies }}</td>
       <td>{{ tree.height }}</td>
       <td>{{ tree.diameter }}</td>
        <td>{{ tree.longitude }}</td>
         <td>{{ tree.latitude }}</td>
           <td>{{ tree.landType }}</td>
		
                
          
  </tr>
      <tr>
          <td>Red Maple</td>
          <td>12.91</td>
          <td>1</td>
          <td>45.5</td>
          <td>-73.57</td>
          <td>Institutional</td>
         
          <td>Montreal-West</td>
          
      </tr>
           <tr>
          <td>English Walnut</td>
          <td>9.91</td>
          <td>0.3</td>
          <td>42.5</td>
          <td>-79.57</td>
          <td>Municipal</td>
          
          <td>Montreal-West</td>
          
      </tr>

 <tr>
          <td>Blue Spruce</td>
          <td>5.75</td>
          <td>0.6</td>
          <td>27.4</td>
          <td>-68.34</td>
          <td>Institutional</td>
       
          <td>Montreal-North</td>
          
      </tr>

  </tr>
</tbody>
      </table>
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
      center: {lat: 45.549302, lng: -73.681559},
      markers: [{
        position: {lat: 45.549302, lng: -73.681559}, infoText: 'Marker 1'
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


