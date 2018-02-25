import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function TreeDto (treeSpecies, height, diameter, longitude, latitude, landtype, treeID, municipality) {
  this.treeSpecies = treeSpecies
  this.height = height
  this.diameter = diameter
  this.longitude = longitude
  this.latitude = latitude
  this.landType = landtype
  this.treeID = treeID
  this.municipality = municipality
}

export default {
  name: 'TreePLE',
  data () {
    return {
      Trees: [],
      response: [],
      newTree: {
        treeSpecies: '',
        height: '',
        diameter: '',
        longitude: '',
        latitude: '',
        landtype: '',
        treeID: '',
        municipality: '' },
      errorTree: ''
    }
  },
  // ...

  created: function () {
// Initializing trees from backend
    AXIOS.get(`/trees`)
    .then(response => {
      // JSON responses are automatically parsed.
      this.trees = response.data
    })
    .catch(e => {
      this.errorTree = e
    })
    console.log('Trees listed')
  },
  methods: {
  }
}
