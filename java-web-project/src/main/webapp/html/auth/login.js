var header = document.querySelector('body > header');

console.log(document.cookie);

//헤더 가져오기
(function () {
  var xhr = new XMLHttpRequest()
  xhr.onreadystatechange = function() {
    if (xhr.readyState != 4 || xhr.status != 200)
      return;
    header.innerHTML = xhr.responseText
    
    // body 태그 쪽에 헤더 로딩 완료 이벤트를 보낸다.
    var e = new Event("loaded.header");
    document.body.dispatchEvent(e);
  };
  xhr.open('GET', '../header.html', true)
  xhr.send()
})();

document.querySelector('#login-btn').onclick = () => {
  var xhr = new XMLHttpRequest()
  xhr.onreadystatechange = function() {
    if (xhr.readyState != 4 || xhr.status != 200)
      return;
    
    var data = JSON.parse(xhr.responseText);
    
    if (data.status == 'success') {
      location.href = "../index.html"
        
    } else {
      alert('로그인 실패입니다!\n' + data.message);
    }
  };
  xhr.open('POST', '../../app/json/auth/login', true)
  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  
  var email = document.querySelector('#email').value;
  var password = document.querySelector('#password').value;
  
  if (document.querySelector('#saveEmail:checked') != null) {
    // 쿠키에 email을 저장한다.
    setCookie("email", email, 1);
  } else {
    removeCookie("email");
  }
  
  var qs = 'email=' + email + '&password=' + password;
  xhr.send(qs);
};

function setCookie(name, value, expireDays) {
  var today = new Date();
  today.setTime(today.getTime() + expireDays * 24 * 60 * 60 * 1000);
  
  document.cookie = name + '=' + value + 
    ';expires=' + today.toUTCString() + 
    ';path=/';
}





