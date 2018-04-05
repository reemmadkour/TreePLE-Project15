<template>
<div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
  <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  <span class="w3-bar-item w3-right"> TreePLE</span>
</div>
<div id="TreePLE">

    <h1>TreePLE</h1> 
    <h2> List of all trees </h2>
    <table style="width:100%">
 <th> Species </th>
  <th> Height </th>
   <th> Diameter </th>
    <th> Longitude </th>
     <th> Latitude </th>
      <th> Landtype </th>
       <th> TreeID </th>
        <th> Municipality </th>
<tr v-for="tree in Trees" >
      <td>{{ tree.treeSpecies }}</td>
       <td>{{ tree.height }}</td>
       <td>{{ tree.diameter }}</td>
        <td>{{ tree.longitude }}</td>
         <td>{{ tree.latitude }}</td>
           <td>{{ tree.landType }}</td>
		<td>{{ tree.treeID }}</td>
                
          
  </tr>
      <tr>
          <td>Red Maple</td>
          <td>12.91</td>
          <td>1</td>
          <td>45.5</td>
          <td>-73.57</td>
          <td>Institutional</td>
          <td>01</td>
          <td>Montreal-West</td>
          
      </tr>
           <tr>
          <td>English Walnut</td>
          <td>9.91</td>
          <td>0.3</td>
          <td>42.5</td>
          <td>-79.57</td>
          <td>Municipal</td>
          <td>02</td>
          <td>Montreal-West</td>
          
      </tr>

 <tr>
          <td>Blue Spruce</td>
          <td>5.75</td>
          <td>0.6</td>
          <td>27.4</td>
          <td>-68.34</td>
          <td>Institutional</td>
          <td>03</td>
          <td>Montreal-North</td>
          
      </tr>

  </tr>
      </table>
<p> </p>

    <p>
      <span v-if="errorTree" style="color:red">Error: {{errorTree}} </span>

    </p>
  </div>
</template>
<script src="./treePlanning.js">
</script>
<style>
src="./W3.css"
 #TreePLE {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #BFF0BF;
  }
th, td {
    padding: 5px;
}

</style>


