function bit(value) {
  var el = []

  if (value instanceof HTMLElement) { // 파라미터 값이 태그라면,
    el[0] = value // 무조건 태그를 배열로 포장한다.

  } else if (value.startsWith('<')) { // 새 태그 생성으로 간주한다.
    el[0] = document.createElement(value.substr(1, value.length - 2))

  } else { // CSS selector 문법으로 간주한다.
    var list = document.querySelectorAll(value)
    for (var e of list) {
      el.push(e)
    }
  }

  // 배열에 나만의 유용한 함수를 붙이자!

  // 1) 배열에 들어있는 태그에 대해 자식 태그를 추가하는 아주 편리한 함수
  el.append = function(children) {
    if (!(children instanceof Array)) {
      console.error('배열이 아닙니다.');
      return;
    }

    for (var e of this) {
      for (var child of children) {
        e.appendChild(child)
      }
    }

    return this
  }

  // 2) 배열 안에 있는 태그의 콘텐츠를 설정하는 함수
  el.html = function(content) {
    for (var e of this) {
      e.innerHTML = content
    }
    return this
  }

  // 3) 배열 안에 있는 태그에 클릭 이벤트 핸들러를 추가하는 함수
  el.click = function(listener) {
    for (var e of this) {
      e.addEventListener('click', listener)
    }
    return this
  }

  // 4) 배열 안에 있는 태그의 속성 값을 다루는 함수
  // => 개발자가 태그에 추가한 속성을 다룬다.
  // => 속성을 값을 꺼낼 때는 getAttribute()를 사용하고,
  //    속성의 값을 넣을 때는 setAttribute()를 사용한다.
  // => 태그에 원래 있던 속성에 대해서도 사용할 수 있는데,
  //    "checked"와 같은 일부 속성은 적용할 수 없다.
  el.attr = function(name, value) {
    if (arguments.length < 2) { // 특정 이름을 가진 속성의 값을 꺼낸다.
      // 값을 꺼낼 때는 배열에서 0번 방에 들어 있는 태그의 속성 값만 꺼내자!
      return this[0].getAttribute(name)

    } else { // 특정 이름을 가진 속성의 값을 value로 설정한다.
      for (var e of this) {
        e.setAttribute(name, value)
      }
      return this
    }
  }

  // 5) 배열 안에 있는 태그를 부모 태그에 추가시키는 함수
  el.appendTo = function(parents) {
    for (var p of parents) {
      for (var e of this) {
        p.appendChild(e)
      }
    }
    return this;
  }

  // 6) 배열 안에 있는 각각의 태그에 대해 CSS 스타일을 다루는 함수
  el.css = function(name, value) {
    for (var e of this) {
      e.style[name] = value
    }
    return this
  }

  // 7) 배열 안에 있는 태그의 콘텐츠를 설정하는 함수(textContent 속성)
  el.text = function(content) {
    for (var e of this) {
      e.textContent = content
    }
    return this
  }

  // 8) 배열 안에 있는 폼 항목 태그의 콘텐츠를 설정하는 함수(value 속성)
  el.val = function(value) {
    if (arguments.length == 0) { // value 파라미터 값이 없으면, 현재 value 꺼낸다.
      return this[0].value
    }

    for (var e of this) {
      e.value = value
    }
    return this
  }

  // 9) 배열 안에 있는 태그의 프로퍼티 값(?)을 다루는 함수
  //    => 개발자가 태그에 추가한 속성이 아닌 원래 있던 속성의 값을 다루는 함수
  //    => getAttribute()/setAttribute() 대신 "태그명.속성명", "태그명.속성명 = 값"으로 다룬다.
  el.prop = function(name, value) {
    if (arguments.length < 2) { // 특정 이름을 가진 프로퍼티 값을 꺼낸다.
      return this[0][name]

    } else { // 특정 이름을 가진 속성의 값을 value로 설정한다.
      for (var e of this) {
        e[name] = value
      }
      return this
    }
  }


  return el
}

// bit() 함수가 리턴한 배열에 대해 작업하지 않고,
// 독자적으로 작업하는 함수는 bit() 객체에 직접 보관한다.

bit.ajax = function(url, settings) {
  if (settings == undefined) {
    settings = {
      method: 'GET',
      dataType: 'text'
    }
  } else {
    if (settings.method == undefined) settings.method = 'GET'
    if (settings.dataType == undefined) settings.dataType = 'text'
  }

  var xhr = new XMLHttpRequest()
  xhr.onreadystatechange = function() {
    if (xhr.readyState < 4)
      return

    var rv;
    if (settings.dataType == 'json') {
      rv = JSON.parse(xhr.responseText)
    } else {
      rv = xhr.responseText
    }

    if (settings.success) { // settings.success != undefined
      settings.success(rv) // settings 객체의 success 변수에 들어있는 함수를 호출한다.
    }
  }
  xhr.open(settings.method, url, true)
  if (settings.method == 'POST') { // POST 요청
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
    var queryString = ""
    if (settings.data) {
      for (var propName in settings.data) {
        if (queryString.length > 0) {
          queryString += "&"
        }
        queryString += propName + '=' + settings.data[propName]
      }
    }
    xhr.send(queryString)

  } else { // GET 요청
    xhr.send()
  }
}

bit.getJSON = function(url, success) {
  bit.ajax(url, {
    dataType: 'json',
    success: success
  })
}

bit.post = function(url, data, success, dataType) {
  bit.ajax(url, {
    method: 'POST',
    dataType: dataType,
    data: data,
    success: success
  })
}









var $ = bit;
