// 강의 정보를 다루는 서비스를 정의한다.
const express = require('express')
const datasource = require('../util/datasource')
const lectureDao = require('../dao/lecture-dao')
const managerDao = require('../dao/manager-dao')
const classroomDao = require('../dao/classroom-dao')
const lectureService = require('../service/lecture-service')
const managerService = require('../service/manager-service')
const classroomService = require('../service/classroom-service')

const connection = datasource.getConnection()
lectureDao.setConnection(connection)
lectureService.setLectureDao(lectureDao)
managerDao.setConnection(connection)
managerService.setManagerDao(managerDao)
classroomDao.setConnection(connection)
classroomService.setClassroomDao(classroomDao)

const router = express.Router()

router.get('/list.json', (request, response) => {
  var pageNo = 1,
      pageSize = 3;
  if (request.query.pageNo) {
    pageNo = parseInt(request.query.pageNo)
  }
  if (request.query.pageSize) {
    pageSize = parseInt(request.query.pageSize)
  }
  lectureService.list(pageNo, pageSize, function(results, totalCount) {
	  response.json({'list': results, 'totalCount': totalCount})
  }, function(error) {
      response.status(200)
              .set('Content-Type', 'text/plain;charset=UTF-8')
              .end('error')
      console.log(error)
  })
})

router.get('/detail.json', function(request, response) {
  var no = parseInt(request.query.no)
  lectureService.detail(no, function(result) {
    response.json(result)
  }, function(error) {
    response.status(200)
      .set('Content-Type', 'text/plain;charset=UTF-8')
      .end('error')
    console.log(error)
  })
})

router.post('/update.json', function(request, response) {
  lectureService.update({
    no: request.body.no,
    title: request.body.title,
    content: request.body.content,
    startDate: request.body.startDate,
    endDate: request.body.endDate,
    quantity: request.body.quantity,
    hours: request.body.hours,
    price: request.body.price,
    classroom: (request.body.classroom == 0 ? null : request.body.classroom),
    manager: (request.body.manager == 0 ? null : request.body.manager)
  }, function(result) {
    response.json({'result': 'yes'})

  }, function(error) {
    response.status(200)
            .set('Content-Type', 'text/plain;charset=UTF-8')
            .end('error')
    console.log(error)
  })
})

router.get('/delete.json', function(request, response) {
  var no = parseInt(request.query.no)
  lectureService.delete(no, function(result) {
    response.json({'result': 'yes'})
  }, function(error) {
    response.status(200)
            .set('Content-Type', 'text/plain;charset=UTF-8')
            .end('error')
    console.log(error)
  })
})

router.get('/form.json', function(request, response) {
  classroomService.listName(function(classrooms) {
    managerService.listName(function(managers) {
      response.json({
        'classrooms': classrooms,
        'managers': managers
      })

    }, function(error) {
      response.status(200)
              .set('Content-Type', 'text/plain;charset=UTF-8')
              .end('error')
      console.log(error)
    })
  }, function(error) {
    response.status(200)
            .set('Content-Type', 'text/plain;charset=UTF-8')
            .end('error')
    console.log(error)
  })
})

router.post('/add.json', function(request, response) {
  lectureService.insert({
    title: request.body.title,
    content: request.body.content,
    startDate: request.body.startDate,
    endDate: request.body.endDate,
    quantity: request.body.quantity,
    hours: request.body.hours,
    price: request.body.price,
    classroom: (request.body.classroom == 0 ? null : request.body.classroom),
    manager: (request.body.manager == 0 ? null : request.body.manager)
  }, function(result) {
    response.json({'result': 'yes'})

  }, function(error) {
    response.status(200)
            .set('Content-Type', 'text/plain;charset=UTF-8')
            .end('error')
    console.log(error)
  })
})





module.exports = router
