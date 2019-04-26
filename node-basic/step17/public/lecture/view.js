var viewTags = $('.bit-view'),
    newTags = $('.bit-new'),
    fiNo = $('#fi-no'),
    fiTitle = $('#fi-title'),
    fiContent = $('#fi-content'),
    fiStartDate = $('#fi-start-date'),
    fiEndDate = $('#fi-end-date'),
    fiQuantity = $('#fi-quantity'),
    fiHours = $('#fi-hours'),
    fiPrice = $('#fi-price'),
    fiClassroom = $('#fi-classroom'),
    fiManager = $('#fi-manager');


var no = 0
try {
  no = location.href.split('?')[1].split('=')[1]
} catch (err) {}

if (no == 0) { // 새 강의 등록
  //강의실 및 매니저 목록 가져오기
  $.getJSON('form.json', function(result) {
    loadClassroomAndManagerOptions(result)
  })
  
  viewTags.css('display', 'none')

  $('#add-btn').click(function() {
    $.post('add.json', {
      'title': fiTitle.val(),
      'content': fiContent.val(),
      'startDate': fiStartDate.val(),
      'endDate': fiEndDate.val(),
      'quantity': fiQuantity.val(),
      'hours': fiHours.val(),
      'price': fiPrice.val(),
      'classroom': fiClassroom.val(),
      'manager': fiManager.val()
    }, function(result) {
      location.href = 'index.html'
    }, 'json')
  })
} else { // 강의 정보 조회
  newTags.css('display', 'none')

  $.getJSON('form.json', function(result) {
	loadClassroomAndManagerOptions(result)
	
	$.getJSON('detail.json', {'no': no}, function(result) {
		fiNo.text(result.lno)
		fiTitle.val(result.titl)
		fiContent.val(result.dscp)
		fiStartDate.val(result.sdt2)
		fiEndDate.val(result.edt2)
		fiQuantity.val(result.qty)
		fiHours.val(result.thrs)
		fiPrice.val(result.pric)
		fiClassroom.val(result.crmno)
		fiManager.val(result.mrno)
	})
  })
  

  $('#upd-btn').click(function() {
    $.post('update.json', {
    	'no': fiNo.text(),
        'title': fiTitle.val(),
        'content': fiContent.val(),
        'startDate': fiStartDate.val(),
        'endDate': fiEndDate.val(),
        'quantity': fiQuantity.val(),
        'hours': fiHours.val(),
        'price': fiPrice.val(),
        'classroom': fiClassroom.val(),
        'manager': fiManager.val()
    }, function(result) {
      location.href = 'index.html'
    }, 'json')
  })

  $('#del-btn').click(function() {
    $.getJSON('delete.json', {'no': no}, function(result) {
      location.href = 'index.html'
    })
  })
}

function loadClassroomAndManagerOptions(result) {
  var templateFn = Handlebars.compile($('#classroom-option-template').text())
  $(templateFn(result)).appendTo(fiClassroom)
  
  templateFn = Handlebars.compile($('#manager-option-template').text())
  $(templateFn(result)).appendTo(fiManager)
}




//
