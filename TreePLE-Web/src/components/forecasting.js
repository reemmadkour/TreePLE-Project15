import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
var example1 = {
  items: [
    '2016-01-09',
    '2017-01-01'
  ]
}
function ForecastDto () {
  this.fID = ''
  this.report = []
  this.person = []
}
function PersonDto () {
  this.name = ''
}
export default {
  name: 'forecast',
  data () {
    return {
      thisDate: '',
      thisCanopy: '',
      thiscs: '',
      thisbdi: '',
      forecasts: [],
      Species: [],
      LandTypes: [],
      municipalities: [],
      description: '',
      FID: '',
      items: [],
      newitem: ' ',
      response: [],
      newForecast: {
        plantLandType: '',
        plantMunicipality: '',
        plantSpecies: '',
        plantHeight: '',
        plantDiameter: '',
        plantLatitude: '',
        plantLongitude: '',
        plantQuantity: '',
        cutMunicipality: '',
        cutLatitude: '',
        cutLongitude: '',
        cutOneLatitude: '',
        cutOneLongitude: ''
      },
      errorForecast: ''
    }
  },
  created: function () {
    AXIOS.get('/municipalities')
    .then(response => {
      // JSON responses are automatically parsed.
      this.municipalities = response.data
    })
    .catch(e1 => {
      this.errorMunicipalities = e1
    })
    console.log('Municipalities listed')

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

    AXIOS.get(`/forecasts`)
    .then(response => {
      this.forecasts = response.data
    })
    this.items = example1.items
  },

  methods: {
    createForecast: function (plantLandType, plantSpecies, plantHeight, plantDiameter, plantMunicipality, plantLatitude, plantLongitude, plantQuantity, cutMunicipality, cutLatitude, cutLongitude, cutOneLatitude, cutOneLongitude) {
      AXIOS.post('/createForecast/'.concat("Reem"), {}, {params: {
        plantLandType: plantLandType,
        plantSpecies: plantSpecies,
        plantSpecies: plantSpecies,
        plantHeight: plantHeight,
        plantDiameter: plantDiameter,
        plantMunicipality: plantMunicipality,
        plantLatitude: plantLatitude,
        plantLongitude: plantLongitude,
        plantQuantity: plantQuantity,
        cutMunicipality: cutMunicipality,
        cutLatitude: cutLatitude,
        cutLongitude: cutLongitude,
        cutOneLatitude: cutOneLatitude,
        cutOneLongitude: cutOneLongitude

      }})
    .then(response => {
      this.forecasts.push(response.data)

      this.newForecast.plantLandType = ''
      this.newForecast.plantMunicipality = ''
      this.newForecast.plantSpecies = ''
      this.newForecast.plantHeight = ''
      this.newForecast.plantDiameter = ''
      this.newForecast.plantLatitude = ''
      this.newForecast.plantLongitude = ''
      this.newForecast.plantQuantity = ''
      this.newForecast.cutMunicipality = ''
      this.newForecast.cutLatitude = ''
      this.newForecast.cutLongitude = ''
      this.newForecast.cutOneLatitude = ''
      this.newForecast.cutOneLongitude = ''
    })
    .catch(e1 => {
      var errorMsg = e1.response.data.message
      console.log(errorMsg)
      this.errorForecast = errorMsg
    })
    },
    generateReport: function () {
      example1.items.push('2018-04-10')
    },
     generateReportforecast: function (FID) {
        var ID = this.forecasts.map(x => x.fID).indexOf(FID)
        var forec = this.forecasts[ID]
    AXIOS.get(`/newReport/`.concat(forec.fID))
    .then(response => {
      forec.report.push(response.data)
      this.FID = ''
    })
     .catch(e1 => {
      var errorMsg = e1.response.data.message
      console.log(errorMsg)
      this.errorForecast = errorMsg
    }) 
    },
    getAttributes: function(report) {
        this.thisCanopy = report.canopy
        this.thiscs = report.carbonSequestration
        this.thisbdi = report.bioDiversityIndex
    },
    getDescriptionOfForecast: function(FID) {
        var ID = this.forecasts.map(x => x.fID).indexOf(FID)
        var forec = this.forecasts[ID]
    AXIOS.get(`/description/`.concat(forec.fID))
    .then(response => {
      this.description = response.data
      this.FID = ''
    })
     .catch(e1 => {
      var errorMsg = e1.response.data.message
      console.log(errorMsg)
      this.errorForecast = errorMsg
    })
    }
  }
}
