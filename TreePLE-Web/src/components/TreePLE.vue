<template>

<div id="TreePLE">

   <br><br><br><br><br>
    <h2> Tree List </h2><br><br>
    <h3> List Trees by </h3><br><br>
<form>
<div class="form-group row">
  <label for="exampleSelect1" class="col-1 offset-sm-4 col-form-label">Species</label>
<div class="col-2">
    <select class="form-control" v-model="selectedSp" top id="exampleSelect1">
      <option v-for="species in Species">{{species}}</option>
      
    </select>
</div>

<div class="row">
<div class="container">
<div class=" col-sm-7 offset-sm-5">

  <button type="submit" style="font-size:120%" @click="selectSpecies(selectedSp)" class="btn btn-secondary btn-block">Filter</button>
</div>
</div>
</div>
</div><br>
<div class="form-group row">
  <label for="exampleSelect1" class="col-1 offset-sm-4 col-form-label">Municipality</label>
<div class="col-2">
    <select class="form-control" v-model="newTree.municipality" id="exampleSelect1">
      <option v-for="mun in municipalities">{{ mun }}</option>
      
    </select>

</div>
<div class="row">
<div class="container">
<div class=" col-sm-7 offset-sm-5">

  <button type="submit" style="font-size:120%" @click="createTree(newTree.landType, newTree.treeSpecies, newTree.height, newTree.diameter, newTree.longitude, newTree.latitude, newTree.municipality, newTree.userName)" class="btn btn-secondary btn-block">Filter</button>
</div>
</div>
</div>
</div><br>
<div class="form-group row">
  <label for="exampleSelect1" class="col-1 offset-sm-4 col-form-label">LandType</label>
<div class="col-2">
    <select class="form-control" v-model="newTree.landType" id="exampleSelect1">
      <option v-for="type in LandTypes">{{ type }}</option>
      
    </select>
</div>
<div class="row">
<div class="container">
<div class=" col-sm-7 offset-sm-5">

  <button type="submit" style="font-size:120%" @click="createTree(newTree.landType, newTree.treeSpecies, newTree.height, newTree.diameter, newTree.longitude, newTree.latitude, newTree.municipality, newTree.userName)" class="btn btn-secondary btn-block">Filter</button>
</div>
</div>
</div>
</div><br><br>
</form>

    <br><table id="treeList" class="table table-hover" style="width:100%">
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
<tr v-for="tree in Trees" >
      <td>{{ tree.treeSpecies }}</td>
       <td>{{ tree.height }}</td>
       <td>{{ tree.diameter }}</td>
        <td>{{ tree.longitude }}</td>
         <td>{{ tree.latitude }}</td>
           <td>{{ tree.landType }}</td>
            <td>{{tree.municipality.name}}</td>
             <td>{{tree.currentStatus.treeState}}</td>
            
                
          
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

<style>

dropdown{
    display: inline;
}

 #TreePLE { 
   background-image: url("../assets/Greenery.jpg");
    height: 7em; 
    background-repeat: repeat;
    background-size: 18em;

    
  }
th, td {
    padding: 5px;
}

</style>


