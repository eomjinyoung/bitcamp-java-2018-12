"use strict"

const students = [
  "김미연", "우인재", "박규호", "김정준", "노완진", "임의수",
  "송수정", "홍효선", "고진서", "홍순민", "이수은", "김민섭",
  "김대연", "조성규", "이유운", "이윤민", "김승원", "양승열",
  "권봉수", "신용주", "김승민", "정성룡", "박은비", "신선아", "서흥원"
];

var count = 10;
var timer = setInterval(function() {
  var no = parseInt(Math.random() * students.length);
  if (count == 0 ) {
    console.log(students[no]);
    clearInterval(timer);
    return;
  }
  console.log(count--);
}, 1000);
