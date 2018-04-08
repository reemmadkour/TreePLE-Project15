
<template>
<div>
<card>
<form>


<div class="form-group" id="forecast">
    <h2  style="font-size:200%; font-weight:bold;" >New Forecast</h2>
    <h3 id="plant" style="font-size:140%; text-align:left; font-weight:bold;"> Plant Trees For Forecast:</h3>
       <div class="form-group row">
         <form class="form-inline">
      <label for="landType" class="col-sm-1.5 col-form-label"> &nbsp;    &nbsp;   &nbsp;  LandType </label>

      <select class="form-control" id="landType">
        <option> Municipal</option>
       <option> Residential</option>
      </select>

      <label for="species" class="col-sm-1.5 col-form-label"> &nbsp; &nbsp;Species </label>
      <select class="form-control" id="species">
        <option> Willow</option>
        <option> Red Maple</option>
      </select>

      <label for="diameter" class="col-sm-1.5 col-form-label"> Diameter </label>
      <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="diameter"  placeholder="canopy Diameter">
        </form>
</div>

<div class="form-group row">
  <form class="form-inline">
      <label for="municipality" class="col-sm-1.5 col-form-label">  &nbsp;   &nbsp;  &nbsp;Municipality </label>
      <select class="form-control" id="municipality">
        <option>Montreal</option>
        <option> Laval</option>
      </select>

      <label  for="latitude" class="col-sm-1.5 col-form-label"> &nbsp; &nbsp; Latitude </label>
      <input class="form-control" id="latitude" type="text" placeholder="latitude">

       <label  for="longitude" class="col-sm-1.5 col-form-label"> &nbsp; &nbsp; Longitude </label>
      <input class="form-control" id="longitude" type="text" placeholder="longitude">

      <label  for="number" class="col-sm-1.5 col-form-label"> &nbsp;  &nbsp;</label>
      <input  class="form-control" id="number" type="text" placeholder="number to plant"> 
  </form>
</div>

 <div class="row offset-sm-5 text-center">
      <button > Plant for forecast</button><br>
</div>

<br>

 <br><h4 id="cut"  style="font-size:140%;font-weight:bold;  text-align:left;"> Cut Trees In Area For Forecast:</h4>


<div class="form-group row">
 <form class="form-inline">
       <label for="municipality" class="col-sm-1.5 col-form-label">  &nbsp;   &nbsp;  &nbsp;Municipality </label>
      <select class="form-control" id="municipality">
        <option>Montreal</option>
        <option> Laval</option>
      </select>
     
      <label  for="latitude" class="col-sm-1.5 col-form-label"> &nbsp; &nbsp; Latitude </label>
      <input class="form-control" id="latitude" type="text" placeholder="latitude">

       <label  for="longitude" class="col-sm-1.5 col-form-label"> &nbsp; &nbsp; Longitude </label>
      <input class="form-control" id="longitude" type="text" placeholder="longitude">
	
      <label  for="number" class="col-sm-1.5 col-form-label"> &nbsp;  &nbsp;</label>
      <input  class="form-control" id="number" type="text" placeholder="number to cut"> 
</form>
</div>
      <div class="row offset-sm-5 text-center">
      <button > Cut for forecast</button><br>
</div>


 <br><h4 id="cut"  style="font-size:140%;font-weight:bold;  text-align:left;"> Cut Specific Tree In For Forecast:</h4>


<div class="form-group row">
<form class="form-inline">
       
     
      <label  for="latitude" class="col-sm-1.5 col-form-label"> &nbsp; &nbsp; Latitude </label>
      <input class="form-control" id="latitude" type="text" placeholder="latitude">

       <label  for="longitude" class="col-sm-1.5 col-form-label"> &nbsp; &nbsp; Longitude </label>
      <input class="form-control" id="longitude" type="text" placeholder="longitude">
	
</form>
</div>
      <div class="row offset-sm-5 text-center">
      <button > Cut for forecast</button><br>
</div> 
<br>
<br>
 <div class="row offset-sm-5 text-center">
     <button id="create"  style="font: bold 25px Arial">  Create Forecast</button><br>
</div>

   

</div>

</form>
</card>


<div>
 <h2  style="font-size:200%; font-weight:bold;" >List Of Forecasts</h2>
<table class="table table-hover" style="width:100%">
<thead>
 <th> ID </th>
  <th> Description </th>
</thead>
<tbody>
<tr v-for="forecast in forecasts" >
      <td>{{ forecast.name }}</td>
       <td>{{ forecast.id }}</td>
       
		
                
          
  </tr>
      <tr>
          <td>planting10, cutting 8</td>
          <td>12</td>
         
          
      </tr>
</tr>
</tbody>
      </table>
</div>  

</div>
 
</template>


<script>
</script>
<style>
#forecast {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #7323d;
    background: #9fc6c8;
},
#plant {
padding: 1px;},
#create {
padding: 10px;},
#cut {
padding: 1px;}
</style>
