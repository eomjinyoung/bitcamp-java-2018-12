var pageNoTag = $('#page-no'),
    tbody = $('#student-tbl > tbody'),
    prevBtn = $('#prev-btn'),
    nextBtn = $('#next-btn'),
    pageSize = 3;

var currPageNo = parseInt(pageNoTag.text())

displayList(1)

function displayList(pageNo) {
  // 서버에서 학생 목록 데이터를 받아 온다.
  $.getJSON('list.json', {'pageNo':pageNo, 'pageSize': pageSize}, function(result) {
    var list = result.list;
    var totalCount = result.totalCount;
    var lastPageNo = parseInt(totalCount / pageSize) + (totalCount % pageSize > 0 ? 1 : 0)

    // 템플릿 소스를 가지고 템플릿을 처리할 함수를 얻는다.
    var templateFn = Handlebars.compile($('#tbody-template').text())
    var generatedHTML = templateFn(result) // 템플릿 함수에 데이터를 넣고 HTML을 생성한다.
    tbody.text('') // tbody의 기존 tr 태그들을 지우고
    tbody.html(generatedHTML) // 새 tr 태그들로 설정한다.

    currPageNo = pageNo
    pageNoTag.text(currPageNo)

    if (currPageNo == 1) {
      prevBtn.prop('disabled', true)
    } else {
      prevBtn.prop('disabled', false)
    }

    if (currPageNo == lastPageNo) {
      nextBtn.prop('disabled', true)
    } else {
      nextBtn.prop('disabled', false)
    }

  }) // getJSON()
} // displayList()


prevBtn.click(function() {
  displayList(currPageNo - 1)
})

nextBtn.click(function() {
  displayList(currPageNo + 1)
})







//
