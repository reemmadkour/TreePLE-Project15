<template>

<div id="TreePLE">

   <br><br><br><br><br>
    <h2> List of all trees </h2><br><br>

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
 
  <div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Dropdown
  </button>
  <div class="dropdown-menu" zaria-labelledby="dropdownMenu2">
    <button class="dropdown-item" type="button">Action</button>
    <button class="dropdown-item" type="button">Another action</button>
    <button class="dropdown-item" type="button">Something else here</button>

</div>

</div>
<br><br><br><br><br><div class="dropdown">
 <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Municipality
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" v-for="mun in municipalities">{{ mun }}</a>
   
  </div>
<br><br><div class="dropdown">
 <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Status
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" href="#">Planted</a>
    <a class="dropdown-item" href="#">Cut</a>
    <a class="dropdown-item" href="#">To be cut</a>
    <a class="dropdown-item" href="#">diseased</a>
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


