<template>

<div id="TreePLE">

   <br><br><br><br><br>
    <h2> List of all trees </h2>

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
 <br><div class="dropdown">
 <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Municipality
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" href="#">Montre-Nord</a>
    <a class="dropdown-item" href="#">Westmount</a>
    <a class="dropdown-item" href="#">Outremont</a>
  </div>

</div>
<br><div class="dropdown">
 <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Species
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" href="#">Spruce</a>
    <a class="dropdown-item" href="#">Birch</a>
    <a class="dropdown-item" href="#">Oak</a>
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
          <td>45.56</td>
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


