import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function TreeDto (landType, treeSpecies, height, diameter, longitude, latitude, treeID, municipalityName) {
  this.treeSpecies = treeSpecies
  this.height = height
  this.diameter = diameter
  this.longitude = longitude
  this.latitude = latitude
  this.landType = landType
  this.treeID = treeID
  this.municipalityName = municipalityName
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
        landType: '',
        municipalityName: '' },
      errorTree: ''
    }
  },
  // ...

  created: function () {
// Initializing trees from backend
    AXIOS.get(`/trees`)
    .then(response => {
      // JSON responses are automatically parsed.
      this.Trees = response.data
    })
    .catch(e => {
      this.errorTree = e
    })
    console.log('Trees listed')
  },
  methods: {
  }
}

