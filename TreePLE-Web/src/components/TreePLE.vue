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
    <select class="form-control" v-model="selectedMun" id="exampleSelect1">
      <option v-for="mun in municipalities">{{ mun }}</option>
      
    </select>

</div>
<div class="row">
<div class="container">
<div class=" col-sm-7 offset-sm-5">

  <button type="submit" style="font-size:120%" @click="selectMunicipality(selectedMun)" class="btn btn-secondary btn-block">Filter</button>
</div>
</div>
</div>
</div><br>
<div class="form-group row">
  <label for="exampleSelect1" class="col-1 offset-sm-4 col-form-label">Status</label>
<div class="col-2">
    <select class="form-control" v-model="selectedState" id="exampleSelect1">
      <option v-for="state in States">{{ state }}</option>
      
    </select>
</div>
<div class="row">
<div class="container">
<div class=" col-sm-7 offset-sm-5">

  <button type="submit" style="font-size:120%" @click="selectStatus(selectedState)" class="btn btn-secondary btn-block">Filter</button>
</div>
</div>
</div>


</div><br><br>

</form>
<div class="row">
<div class="container">
<div class=" col-sm-4 offset-sm-4">

  <button type="submit" style="font-size:120%" @click="selectAll()" class="btn btn-secondary btn-block">See all</button>
</div>
</div>
</div>
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


