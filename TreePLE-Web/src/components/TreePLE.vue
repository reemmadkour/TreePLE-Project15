<template>

<div id="TreePLE">

    <h2>TreePLE</h2> 
    <table style="width:100%">
 <th> All Trees</th>
<tr v-for="tree in trees" >
      <td>{{ tree.longitude }}</td>
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

</style>


