
<template>

<div id="view">

   <br><br><br><br><br>
    <h2>Montreal Tree Map</h2><br>
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
</thead>
<tbody>
       <tr v-for="tree in Trees" >
      <td>{{ tree.treeSpecies }}</td>
       <td>{{ tree.height }}</td>
       <td>{{ tree.diameter }}</td>
        <td>{{ tree.longitude }}</td>
         <td>{{ tree.latitude }}</td>
           <td>{{ tree.landType }}</td>
            <td>{{tree.municipalities}}</td>
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


