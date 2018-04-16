import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

import * as VueGoogleMaps from 'vue2-google-maps'
import Vue from 'vue'

Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyCDWKnVgX3DIKrH1Rbux7RvLtaV6fO21QE',
    v: '3'
    // libraries: 'places', //// If you need to use place input
  }
})

function TreeDto (landType, treeSpecies, height, diameter, longitude, latitude, treeID, municipality) {
  this.treeSpecies = treeSpecies
  this.height = height
  this.diameter = diameter
  this.longitude = longitude
  this.latitude = latitude
  this.landType = landType
  this.treeID = treeID
  this.municipality = municipality
}

export default {
  name: 'TreePLE',
  data () {
    return {
      Trees: [],
      response: [],
      Species: [],
      Bi: '',
      CarbonSeq: '',
      Canopy: '',
      LandTypes: [],
      States: [],
      errorSpecies: '',
      errorLandTypes: '',
      errorStates: '',
      treeData: {
        treeSpecies: '',
        height: '',
        diameter: '',
        longitude: '',
        latitude: '',
        landType: '',
        municipality: { name: '' }},
      positions: [],
      municipalities: [],
      newTree: {
        treeSpecies: '',
        height: '',
        diameter: '',
        longitude: '',
        latitude: '',
        landType: '',
        currentStatus: { treeState: '' },
        municipality: { municipalityName: '' }},
      errorMunicipalities: '',
      center: {lat: 45.549302, lng: -73.681559},
      markers: [],
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
      for (var i = 0; i < this.Trees.length; i++) {
          this.markers.push({
            position: {
              lng: this.Trees[i].longitude,
              lat: this.Trees[i].latitude
            },
            icon: {
              url: 'https://png.icons8.com/cotton/1600/tree.png',
              size: {width: 26, height: 26, f: 'px', b: 'px'},
              scaledSize: {width: 29, height: 29, f: 'px', b: 'px'}
            }, 
            treeData: this.Trees[i] })
      }
    })
    .catch(e => {
      this.errorTree = e
    })
    this.$forceUpdate()
    console.log('Trees listed')
    
    AXIOS.get('/CalculateCurrentBI/')
    .then(response => {
      // JSON responses are automatically parsed.
      this.Bi= response.data
    })
    .catch(e => {
      this.errorMunicipalities = e
    })
    console.log('BI calculated') 

    AXIOS.get('/municipalities')
    .then(response => {
      // JSON responses are automatically parsed.
      this.municipalities = response.data
    })
    .catch(e1 => {
      this.errorMunicipalities = e1
    })
    console.log('Municipalities listed')
    
    AXIOS.get('/CalculateCurrentCarbonSEQ/')
    .then(response => {
      // JSON responses are automatically parsed.
      this.CarbonSeq= response.data
    })
    .catch(e1 => {
      this.errorMunicipalities = e1
    })
    console.log('Carbon calculated')

    AXIOS.get(`/species`)
    .then(response => {
      // JSON responses are automatically parsed.
      this.Species = response.data
    })
    .catch(e => {
      this.errorSpecies = e
    })
    console.log('Species listed')

    AXIOS.get(`/landtypes`)
    .then(response => {
      // JSON responses are automatically parsed.
      this.LandTypes = response.data
    })
    .catch(e => {
      this.errorLandTypes = e
    })
    console.log('LandTypes listed')
   
    AXIOS.get('/CurrentTotalCanopy/')
    .then(response => {
      // JSON responses are automatically parsed.
      this.Canopy= response.data
    })
    .catch(e1 => {
      this.errorMunicipalities = e1
    })
    
    console.log('Canopy calculated')

     AXIOS.get(`/states`)
    .then(response => {
      // JSON responses are automatically parsed.
      this.States = response.data
    })
    .catch(e => {
      this.errorStates = e
    })
    console.log('States listed')
  },
  methods: {
    editTree: function (land, speci,  Height, Diameter, long, lat, mun, userName, state) {
      AXIOS.post('/editTree/'.concat(userName), {}, {
        params: {landType: land, species: speci, height: Height, diameter: Diameter, longitude: long, latitude: lat, municipality: mun, treeState: state}
      })
      .then(response => {
        // JSON responses are automatically parsed.
        this.Trees.push(response.data)
        this.newTree = ''
	this.newTree.landType = ''
	this.newTree.treeSpecies = ''
	this.newTree.height = ''
	this.newTree.diameter = ''
	this.newTree.longitude = ''
	this.newTree.latitude = ''
	this.newTree.municipality.name = ''
        this.newTree.currentStatus.treeState = ''
        this.errorTree = ''
 
      })
      .catch(e => {
        var errorMsg = e.response.data.message
        console.log(errorMsg)
	this.errorTree = errorMsg
      })
    }
  }
}
