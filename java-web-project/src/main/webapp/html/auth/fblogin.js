function autoServerLogin(accessToken) {
    $.getJSON('../../app/json/auth/fblogin', {
      "accessToken": accessToken
    }, (data) => {
      console.log(data);
      location.href = "../index.html"
    });
}

function checkLoginState() {
    FB.getLoginStatus(function(response) { 
        if (response.status === 'connected') { // 로그인이 정상적으로 되었을 때,
            autoServerLogin(response.authResponse.accessToken);
        
        } else { // 로그인이 되지 않았을 때,
            alert("페이스북 로그인 실패!");
        }
    });
}

window.fbAsyncInit = function() {
  console.log("window.fbAsyncInit() 호출됨!");
  FB.init({
    appId      : '2057807661191466', // 개발자가 등록한 앱 ID
    cookie     : true,  
    xfbml      : true,  
    version    : 'v3.3' 
  });
  FB.AppEvents.logPageView();
};

(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "https://connect.facebook.net/en_US/sdk.js";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
