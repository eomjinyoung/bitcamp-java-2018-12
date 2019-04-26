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

    tbody.text('')
    for (var s of list) {
      $('<tr>').append($('<td>').text(s.mno))
               .append($('<td>').append(
                 $('<a>').attr('href', 'view.html?no=' + s.mno)
                         .text(s.name == '' ? '(이름 없음)' : s.name)))
               .append($('<td>').text(s.tel))
               .append($('<td>').text(s.email))
               .append($('<td>').text(s.work))
               .appendTo(tbody)
    }

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
