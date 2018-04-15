
<template>

<div id="view">

   <br><br><br><br><br>
    <h2>Montreal Tree Map</h2><br>
<p> (Click on a tree to edit) </p>
<gmap-map id="mymap"
    :center="center"
    :zoom="11"
    style="width: 90%; height: 700px; margin:0px auto; display:inline-block"
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
           <th> Status </th>
         
</thead>
<tbody>
       <tr>
      <td>{{m.treeData.treeSpecies}}</td>
       <td>{{m.treeData.height}}</td>
       <td>{{m.treeData.diameter}}</td>
        <td>{{m.treeData.longitude}}</td>
         <td>{{m.treeData.latitude}}</td>
           <td>{{m.treeData.landType}}</td>
            <td>{{m.treeData.municipality.name}}</td>
               <td>{{m.treeData.currentStatus.treeState}}</td>
</tr>
        </tbody>
	</table>
	<b-btn v-b-modal.modal1>Edit Tree</b-btn>

  <!-- Modal Component -->
  <b-modal id="modal1" title="Edit Tree">
 <div class="container"><br>
<form action="/#/view">
<div class="form-group row">
  <label for="example-text-input" class=" col-5 col-form-label">Name</label>
  <div class="col-4 ">
    <input required type="text" v-model="newTree.userName" min="0" class="form-control" id="example-height-input" placeholder="Name" required>
<div class="invalid-feedback">
        Please provide a valid height.
      </div>
 </div>
</div>
  <div class="form-group row">
  <label for="example-text-input" class="col-5 col-form-label">Height</label>
  <div class="col-4">
    <input required type="number" v-model="newTree.height" min="0" class="form-control" id="example-height-input" placeholder="Height" required>
<div class="invalid-feedback">
        Please provide a valid height.
      </div>
<small class="form-text text-muted">Previous: {{m.treeData.height}}</small>
 </div>
</div>
<div class="form-group row">
   <label for="example-text-input" class="col-5 col-form-label">Diameter</label>
  <div class="col-4">
    <input required type="number" v-model="newTree.diameter" min="0" class="form-control" placeholder="Diameter" id="example-text-input">
<small class="form-text text-muted">Previous: {{m.treeData.diameter}}</small>
  </div>
<div class="invalid-feedback">
        Please provide a valid Diameter.
      </div>

</div>
<div class="form-group row">
   <label for="example-text-input" class="col-5 col-form-label">Longitude</label>
  <div class="col-4">
    <input required type="number" step="0.00000001" class="form-control"  v-model="newTree.longitude" id="example-text-input" placeholder="longitude">
<small class="form-text text-muted">Previous: {{m.treeData.longitude}}</small>
  </div>
<div class="invalid-feedback">
        Please provide a valid Longitude.
      </div>
</div>
<div class="form-group row">
  <label for="example-text-input" class="col-5 col-form-label">Latitude</label>
  <div class="col-4">
    <input type="number" step="0.00000001" required class="form-control"  v-model="newTree.latitude" id="example-text-input" placeholder="latitude">
<small class="form-text text-muted">Previous: {{m.treeData.latitude}}</small>
  </div>
<div class="invalid-feedback">
        Please provide a valid Latitude.
      </div>
</div>
<div class="form-group row">
  <label for="exampleSelect1" class="col-5 col-form-label">Species</label>
<div class="col-4">
    <select class="form-control" v-model="newTree.treeSpecies" id="exampleSelect1">
      <option v-for="species in Species">{{species}}</option>
      
    </select>
<small class="form-text text-muted">Previous: {{m.treeData.treeSpecies}}</small>
</div>

</div>
<div class="form-group row">
  <label for="exampleSelect1" class="col-5 col-form-label">Municipality</label>
<div class="col-4">
    <select class="form-control" v-model="newTree.municipality" id="exampleSelect1">
      <option v-for="mun in municipalities">{{ mun }}</option>
      
    </select>
<small class="form-text text-muted">Previous: {{m.treeData.municipality.name}}</small>
</div>
</div>
<div class="form-group row">
  <label for="exampleSelect1" class="col-5 col-form-label">LandType</label>
<div class="col-4">
    <select class="form-control" v-model="newTree.landType" id="exampleSelect1">
      <option v-for="type in LandTypes">{{ type }}</option>
      
    </select>
<small class="form-text text-muted">Previous: {{m.treeData.landType}}</small>
</div>
</div>
<div class="form-group row">
  <label for="exampleSelect1" class="col-5 col-form-label">Status</label>
<div class="col-4">
    <select class="form-control" v-model="newTree.currentStatus" id="exampleSelect1">
      <option v-for="state in States">{{ state }}</option>
      
    </select>
<small class="form-text text-muted">Previous: {{m.treeData.currentStatus.treeState}}</small>
</div>
</div>
<div class="row">
<div class="container">
<div class="col-xs-6 col-sm-4 offset-sm-5">

  <button type="submit" style="font-size:120%" @click="editTree(newTree.landType, newTree.treeSpecies, newTree.height, newTree.diameter, newTree.longitude, newTree.latitude, newTree.municipality, newTree.userName, newTree.currentStatus)" class="btn btn-secondary btn-block">Edit</button>




</div>
</div>
</div>
</form>
</div>
  </b-modal>
    </gmap-info-window>
</gmap-marker>
  </gmap-map>


 <br> <br><div class="row">

<div class="col-xs-4 col-sm-4 offset-sm-4"> 
<button onclick="myFunction()"class="btn btn-secondary btn-block">Refresh</button>
</div>

</div><br>
<div id="myDIV" style="font-size:120%; font-weight:bold;">
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

<script src="./treeViewing.js"></script>


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


