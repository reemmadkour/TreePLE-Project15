
<template>
<div id="forecast">

<form>


<div class="form-group"><br><br><br><br><br>
    <h2> New Forecast</h2><br><br><br>
    <h3 id="plant"> Plant Trees For Forecast:</h3><br><br>
       <div class="form-group row">
         <form class="form-inline">
      <label for="landType" class="col-sm-1.5 col-form-label"> &nbsp;    &nbsp;   &nbsp;  LandType </label>

      <select v-model="newForecast.plantLandType" class="form-control" id="landType">
        <option> Municipal</option>
       <option> Residential</option>
      </select>

      <label for="species" class="col-sm-1.5 col-form-label"> &nbsp; &nbsp;Species </label>
      <select v-model="newForecast.plantSpecies" class="form-control" id="species">
        <option> Willow</option>
        <option> Red Maple</option>
      </select>

      <label for="diameter" class="col-sm-1.5 col-form-label"> Diameter </label>
      <input v-model="newForecast.plantDiameter" type="number" step="any" class="form-control mb-2 mr-sm-2 mb-sm-0" id="diameter"  placeholder="canopy Diameter">
      <label for="height" class="col-sm-1.5 col-form-label"> Height </label>
      <input v-model="newForecast.plantHeight" type="number" step="any" class="form-control mb-2 mr-sm-2 mb-sm-0" id="height"  placeholder="tree height">
        </form>
</div>

<div class="form-group row">
  <form class="form-inline">
      <label for="municipality" class="col-sm-1.5 col-form-label">  &nbsp;   &nbsp;  &nbsp;Municipality </label>
      <select v-model="newForecast.plantMunicipality"class="form-control" id="municipality">
        <option>Montreal</option>
        <option> Laval</option>
      </select>

      <label  for="latitude" class="col-sm-1.5 col-form-label"> &nbsp; &nbsp; Latitude </label>
      <input  v-model="newForecast.plantLatitude" class="form-control" id="latitude" type="number" step="any" placeholder="latitude">

       <label  for="longitude" class="col-sm-1.5 col-form-label"> &nbsp; &nbsp; Longitude </label>
      <input  v-model="newForecast.plantLongitude" class="form-control" id="longitude" type="number" step="any" placeholder="longitude">

      <label  for="number" class="col-sm-1.5 col-form-label"> &nbsp;  &nbsp;</label>
      <input   v-model="newForecast.plantQuantity" class="form-control" id="number" type="number" placeholder="number to plant"> 
  </form>
</div>

 <div class="row offset-sm-5 text-center">
      
</div>

<br>

 <br><h3> Cut Trees In Area For Forecast:</h3><br><br>


<div class="form-group row">
 <form class="form-inline">
       <label for="municipality" class="col-sm-1.5 col-form-label">  &nbsp;   &nbsp;  &nbsp;Municipality </label>
      <select  v-model="newForecast.cutMunicipality" class="form-control" id="municipality">
        <option>Montreal</option>
        <option> Laval</option>
      </select>
     
      <label  for="latitude" class="col-sm-1.5 col-form-label"> &nbsp; &nbsp; Latitude </label>
      <input v-model="newForecast.cutLatitude" class="form-control" id="latitude" type="number" step="any" placeholder="latitude">

       <label  for="longitude" class="col-sm-1.5 col-form-label"> &nbsp; &nbsp; Longitude </label>
      <input v-model="newForecast.cutLongitude" class="form-control" id="longitude" type="number" step="any" placeholder="longitude">
  
      <!--<label  for="number" class="col-sm-1.5 col-form-label"> &nbsp;  &nbsp;</label>
      <input  class="form-control" id="number" type="text" placeholder="number to cut">  -->
</form>
</div>
      <div class="row offset-sm-5 text-center">
      
</div>


 <br><br><h3> Cut Specific Tree In For Forecast:</h3><br><br>


<div class="form-group row">
<form class="form-inline">
       
     
      <label  for="latitude" class="col-sm-1.5 col-form-label"> &nbsp; &nbsp; Latitude </label>
      <input v-model="newForecast.cutOneLatitude" class="form-control" id="latitude" type="number" step="any" placeholder="latitude">

       <label  for="longitude" class="col-sm-1.5 col-form-label"> &nbsp; &nbsp; Longitude </label>
      <input v-model="newForecast.cutOneLongitude" class="form-control" id="longitude" type="number" step="any" placeholder="longitude">
  
</form>
</div>
      <div class="row offset-sm-5 text-center">
      
</div> 
<br>
<br>
 <div class="row">
<div class="container">
<div class=" col-sm-4 offset-sm-4">
     <button  type="submit" @click=" createForecast( newForecast.plantLandType, newForecast.plantSpecies, newForecast.plantHeight, newForecast.plantDiameter, newForecast.plantMunicipality, newForecast.plantLatitude, newForecast.plantLongitude , newForecast.plantQuantity, newForecast.cutMunicipality, newForecast.cutLatitude, newForecast.cutLongitude, newForecast.cutOneLatitude, newForecast.cutOneLongitude)" class="btn btn-secondary btn-block">  Create Forecast</button><br>
      <span v-if="errorForecast" style="color:red">Error: {{errorForecast}} </span>
</div>
</div>
</div>


   

</div>

</form>



<div>
 <br><br><h3>List Of Forecasts</h3><br>
<table class="table table-hover" style="width:100%">
<thead>
 <th> User Name (click to reveal description below) </th>
  <th> Forecast ID </th>
  <th> Past Reports </th>
  <th>    </th>
</thead>
<tbody>
<tr v-for="forecast in forecasts">

     
       <td>{{ forecast.person.name }}
<div class="row">
<div class="container">
<div class=" col-sm-4 offset-sm-4">
<button  type="submit" class="btn btn-secondary btn-block" @click="  getDescriptionOfForecast(forecast.fID)">  show description</button>
</div>
</div>
</div>
</td>
       
       <td><b-text v-b-popover.hover={description} title="details">{{ forecast.fID }}</b-text></td>
       <td>
        <ul>
          <li v-for="report in forecast.report"> 
    
            <a href="#" @click = " getAttributes(report)"><b-text v-b-popover.hover= "' click and check attributes below'" title="Attributes">

            {{report.date}}
          </b-text>
          </a>
          </li>
         
        </ul>
      </td>
      <br><td> <button  type="submit"  class="btn btn-secondary btn-block" @click=" generateReportforecast(forecast.fID) ">  generate report</button></td>
  <br><tr style="font: bold 25px Arial">selected Forecast descritption:</tr>
  <tr>{{description}}</tr>  
  <tr style="font: bold 25px Arial">selected report attributes:</tr>        
  </tr>
  <tr> BioDiversityIndex: &nbsp; {{thisbdi}} &nbsp; Canopy: &nbsp; {{thisCanopy}} &nbsp; Carbon Sequestration per year: &nbsp; {{thiscs}}</tr>
     
</tr>
</tbody>
      </table>
</div>  

</div>
 
</template>

<script src="./forecasting.js"></script> 



<style>
#forecast {
    background-image: url("../assets/Greenery.jpg");
    height: 7em; 
    background-repeat: repeat;
    background-size: 18em;

    
  }
li { padding-left: 10px;
  },
#plant {
padding: 1px;},
#create {
padding: 10px;},
#cut {
padding: 1px;}
</style>
