<template>
<div id="plant">


<br><br><br><br>
<br><h2> Plant Tree </h2><br>
<div class="container"><br>
<form action="/#/view">
<div class="form-group row">
  <label for="example-text-input" class="offset-sm-3 col-2 col-form-label">Name</label>
  <div class="col-3 ">
    <input required type="text" v-model="newTree.userName" min="0" class="form-control" id="example-height-input" placeholder="Name" required>
<div class="invalid-feedback">
        Please provide a valid height.
      </div>
 </div>
</div>
  <div class="form-group row">
  <label for="example-text-input" class="col-2 offset-sm-3 col-form-label">Height</label>
  <div class="col-3 ">
    <input required type="number" v-model="newTree.height" min="0" class="form-control" id="example-height-input" placeholder="Height" required>
<div class="invalid-feedback">
        Please provide a valid height.
      </div>
 </div>
</div>
<div class="form-group row">
   <label for="example-text-input" class="col-2 offset-sm-3 col-form-label">Diameter</label>
  <div class="col-3">
    <input required type="number" v-model="newTree.diameter" min="0" class="form-control" placeholder="Diameter"  id="example-text-input">
  </div>
<div class="invalid-feedback">
        Please provide a valid Diameter.
      </div>
</div>
<div class="form-group row">
   <label for="example-text-input" class="col-2  offset-sm-3 col-form-label">Longitude</label>
  <div class="col-3">
    <input required type="number" step="0.00000001" class="form-control"  v-model="newTree.longitude" id="example-text-input" placeholder="longitude">
  </div>
<div class="invalid-feedback">
        Please provide a valid Longitude.
      </div>
</div>
<div class="form-group row">
  <label for="example-text-input" class="col-2  offset-sm-3 col-form-label">Latitude</label>
  <div class="col-3">
    <input type="number" step="0.00000001" required class="form-control"  v-model="newTree.latitude" id="example-text-input" placeholder="latitude">
  </div>
<div class="invalid-feedback">
        Please provide a valid Latitude.
      </div>
</div>
<div class="form-group row">
  <label for="exampleSelect1" class="col-2 offset-sm-3 col-form-label">Species</label>
<div class="col-3">
    <select class="form-control" v-model="newTree.treeSpecies" id="exampleSelect1">
      <option v-for="species in Species">{{species}}</option>
      
    </select>
</div>
</div>
<div class="form-group row">
  <label for="exampleSelect1" class="col-2 offset-sm-3 col-form-label">Municipality</label>
<div class="col-3">
    <select class="form-control" v-model="newTree.municipality" id="exampleSelect1">
      <option v-for="mun in municipalities">{{ mun }}</option>
      
    </select>
</div>
</div>
<div class="form-group row">
  <label for="exampleSelect1" class="col-2 offset-sm-3 col-form-label">LandType</label>
<div class="col-3">
    <select class="form-control" v-model="newTree.landType" id="exampleSelect1">
      <option v-for="type in LandTypes">{{ type }}</option>
      
    </select>
</div>
</div><br>
<div class="row">
<div class="container">
<div class=" col-sm-4 offset-sm-4">

  <button type="submit" style="font-size:120%" @click="createTree(newTree.landType, newTree.treeSpecies, newTree.height, newTree.diameter, newTree.longitude, newTree.latitude, newTree.municipality, newTree.userName)" class="btn btn-secondary btn-block">Plant</button>

</div>
</div>
</div>
</form>
</div>
</div>
</template>
<script src="./treePlanting.js"></script>
<style>

#plant { 
   background-image: url("../assets/Greenery.jpg");
    height: 7em; 
    background-repeat: repeat;
    background-size: 18em;
}
</style>

