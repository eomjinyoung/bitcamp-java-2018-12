// Echo 서버 만들기
const net = require('net')

// 1) 서버 객체를 준비한다.
const server = net.createServer((socket) => { // 클라이언트가 접속하면 호출됨.
  console.log('=> 클라이언트가 연결되었습니다.')
  
  socket.on('data', (data) => {
    socket.write('[오호라]' + data.toString())
    socket.destroy();
  })
})

// 2) 서버를 시작시킨다.
server.listen(8888, () => {
  console.log('=> 서버 실행 중...')
})