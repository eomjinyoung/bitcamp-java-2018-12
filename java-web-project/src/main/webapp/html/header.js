// header.html을 로딩하고 초기화시킨다.
// 헤더 가져오기
(function () {
  var header = document.querySelector('.bit-main-header');
  var xhr = new XMLHttpRequest()
  xhr.onreadystatechange = function() {
    if (xhr.readyState != 4 || xhr.status != 200)
      return;
    header.innerHTML = xhr.responseText
    
    // body 태그 쪽에 헤더 로딩 완료 이벤트를 보낸다.
    var e = new Event('loaded.header');
    document.body.dispatchEvent(e);
  };
  xhr.open('GET', '/java-web-project/html/header.html', true)
  xhr.send()
})();

// header.html의 내용을 웹 페이지에 삽입했다고 연락이 오면,
// 즉시 로그아웃 버튼에 click listener를 등록한다.
// header.html이 삽입되지도 않았는데 로그아웃 버튼을 찾아서는 안된다.
//
document.body.addEventListener('loaded.header', () => {
  
  // 웹 페이지에 header.html을 삽입했으면 로그인 정보를 가져와 설정한다.
  loadLoginUser();
  
  // 로그아웃 버튼의 click 리스너를 등록한다.
  document.querySelector('#logout-menu').addEventListener('click', (e) => {
    e.preventDefault();
    var xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function() {
      if (xhr.readyState != 4 || xhr.status != 200)
        return;
      
      location.href = '/java-web-project/html/index.html';
      
    };
    xhr.open('GET', '/java-web-project/app/json/auth/logout', true)
    xhr.send()
  });
});

// header.html이 웹 페이지에 삽입된 후 로그인 정보를 받아온다. 
function loadLoginUser() {

  // 서버에서 로그인 한 사용자 정보를 가져온다.
  var xhr = new XMLHttpRequest()
  xhr.onreadystatechange = function() {
    if (xhr.readyState != 4 || xhr.status != 200)
      return;
    
    var data = JSON.parse(xhr.responseText);
    
    var loginState = document.querySelector('#bit-login-state'),
        notLoginState = document.querySelector('#bit-not-login-state');
    
    if (data.status == 'success') {
      loginState.className = 
        loginState.className.replace('bit-invisible', '');
      document.querySelector('#login-username').innerHTML = data.user.name;
      document.querySelector('#login-userphoto').src = 
        "/java-web-project/upload/member/" + data.user.photo;
    } else {
      notLoginState.className = 
        notLoginState.className.replace('bit-invisible', '');
    }
    
    console.log(document.querySelector('#bit-login-state').className);
  };
  xhr.open('GET', '/java-web-project/app/json/auth/user', true)
  xhr.send()
}








