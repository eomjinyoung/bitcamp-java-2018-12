var Bitcamp = function(arg1) { // function = object + 함수 코드
  var arr = [];

  if (arg1 instanceof HTMLElement) {
    // 파라미터 값이 태그 객체라면 그대로 배열에 저장한다.
    arr.push(arg1);
    
  } else if (arg1.startsWith('<')) {
    // 파라미터 값이 태그를 의미하는 문자열이라면 해당 태그를 만든 후 배열에 저장한다.
    var tagName = arg1.substr(1, arg1.length - 2);
    arr.push(document.createElement(tagName)); 
    
  } else {
    var el = document.querySelectorAll(arg1); // => HTMLCollection 리턴
  
    // HTMLCollection에 들어 있는 값을 빈 배열에 옮긴다.
    for (var e of el) {
      arr.push(e);
    }
  }
  
  // 태그가 들어있는 배열에 그 태그들을 다룰 때 사용할 도구를 첨가한다.
  arr.html = function(value) {
    if (arguments.length > 0) {
      for (var e of this) {
        e.innerHTML = value;
      }
      return this;
    } else {
      // 찾은 태그 중에서 첫 번째 태그의 innerHTML 값만 리턴한다.
      return this[0].innerHTML;
    }
  };
  
  // 태그에 클래스 이름을 추가하는 함수를 배열에 첨부한다.
  arr.addClass = function(value) {
    for (var e of this) {
      var cn = value;
      var names = e.className.split(' ');
      for (var name of names) {
        cn = cn + ' ' + name;
      }
      e.className = cn;
    }
    return this;
  };
  
  // 태그에 클래스 이름을 제거하는 함수를 배열에 첨부한다.
  arr.removeClass = function(value) {
    for (var e of this) {
      var cn = '';
      var names = e.className.split(' ');
      for (var name of names) {
        if (name == value)
          continue;
        cn = cn + ' ' + name;
      }
      e.className = cn;
    }
    return this;
  }
  
  // click 리스너를 등록하는 함수를 배열에 첨부한다.
  arr.click = function(cb) {
    for (var e of this) {
      e.addEventListener('click', cb);
    }
    return this;
  };
  
  // 특정 이벤트의 리스너를 등록하는 함수를 배열에 첨부한다.
  arr.bind = function(eventType, handler) {
    for (var e of arr) {
      e.addEventListener(eventType, handler);
    }
    return this;
  };
  
  // 태그의 속성 값을 설정하고 리턴하는 함수를 배열에 첨부한다.
  arr.attr = function(name, value) {
    if (arguments.length == 1) {
      return this[0].getAttribute(name);
    } else {
      for (var e of this) {
        e.setAttribute(name, value);
      }
      return this;
    }
  };
  
  // 특정 태그에 이벤트를 발생시키는 함수를 배열에 첨부한다.
  arr.trigger = function(eventType) {
    for (var e of this) {
      e.dispatchEvent(new Event(eventType));
    }
    return this;
  };
  
  // 태그에 자식 태그를 추가하는 함수를 배열에 첨부한다.
  arr.append = function(childs) {
    for (var parent of this) {
      for (var child of childs) {
        parent.appendChild(child);
      }
    }
    return this;
  };
  
  // 태그를 부모 태그에 추가하는 함수를 배열에 첨부한다.
  arr.appendTo = function(parents) {
    for (var parent of parents) {
      for (var child of this) {
        parent.appendChild(child);
      }
    }
    return this;
  };
  
  return arr;
}; 

Bitcamp.ajax = function(url, settings) {
  // XMLHttpRequest 객체를 사용하여 AJAX 요청하는 코드를 사용하기 쉽도록 캡슐화시킨다.
  
  // => 파라미터 값으로 settings 객체가 넘어오지 않는다면 빈 객체를 만든다.
  if (settings == undefined || settings == null) {
    settings = {};
  } 
  
  // => settings 객체에 method 프로퍼티가 정의되지 않았다면 기본 값 'GET'으로 설정한다.
  if (!settings.method) {
    settings.method = 'GET';
  }
  
  // => 서버가 보낸 데이터의 형식을 알려주지 않으면 기본으로 일반 텍스트로 설정한다.
  if (!settings.dataType) {
    settings.dataType = 'text';
  }
  
  var xhr = new XMLHttpRequest()
  
  xhr.onreadystatechange = function() {
    if (xhr.readyState != 4)
      return;
    
    if (xhr.status != 200) {
      // 서버에서 오류가 발생했다면 error() 함수를 호출한다.
      // => 단 error 라는 이름의 함수가 있을 때만 호출한다.
      if (settings.error) {
        settings.error();
      }
      return;
    }
    
    // 서버로부터 정상적으로 응답을 받았다면 success() 함수를 호출한다. 
    // => success 라는 이름의 함수가 settings에 있을 때만 호출한다.
    if (settings.success) {
      if (settings.dataType == 'json') {
        settings.success(JSON.parse(xhr.responseText));
      } else {
        settings.success(xhr.responseText);
      }
    }
  };
  xhr.open(settings.method, url, true)
  xhr.send()
};

Bitcamp.getJSON = function(url, success) {
  Bitcamp.ajax(url, {
    "dataType": 'json',
    "success": success
  });
};

var $ = Bitcamp;











