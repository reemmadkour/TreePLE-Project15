<template>


<ul>
  <li><a class="active" href="#home">Home</a></li>
  <li><a href="#news">News</a></li>
  <li><a href="#contact">Contact</a></li>
</ul>
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
 #TreePLE {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #BFF0BF;
  }
th, td {
    padding: 5px;
}
template {
    font-size: 28px;
}

ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
    position: -webkit-sticky; /* Safari */
    position: sticky;
    top: 0;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover {
    background-color: #111;
}

.active {
    background-color: #4CAF50;
}
</style>


