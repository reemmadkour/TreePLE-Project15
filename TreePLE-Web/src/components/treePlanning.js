import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})



export default {
  name: 'TreePLE',
  data () {
    return {
      Trees: [],
      response: [],
   
    }
   },
  // ...

  created: function () {
// Initializing trees from backend
    AXIOS.get('/trees')
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

