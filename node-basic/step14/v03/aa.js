/* 서비스 핸들러를 정의한 모듈
*/
var express = require('express')
var bodyParser = require('body-parser')
var path = require('path')

var router = express.Router()

router.get('/test.do', function(request, response) {
  response.end('/aa/test.do~~~~~')
})

router.get('/test2.do', function(request, response) {
  response.end('/aa/test2.do.....')
})

module.exports = router
