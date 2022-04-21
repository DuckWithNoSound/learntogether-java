let nav_mobile_btn_bars = document.querySelector('#btn-bars');
let nav_mobile_btn_x = document.querySelector('#btn-x');
let nav_mobile = document.querySelector('.Nav__mobile');
let overlay = document.querySelector('.page_overlay');
let modal = document.querySelector('.modal');
let modal_login = document.querySelector('.modal__body--login');
let btn_login = document.querySelector('#Sign_Login');
let btn_login_mobile = document.querySelector('.Login__mobile'); 
let btn_invite_1 = document.querySelector('.Bride_two-btn');
let btn_invite_2 = document.querySelector('.Bride_last-content a');
let btn_login_1 = document.querySelector('#logIn_btn');


nav_mobile_btn_bars.onclick = function(){
  nav_mobile_btn_bars.setAttribute('style', 'display: none;');
  nav_mobile_btn_x.setAttribute('style', 'display: block;');
  overlay.setAttribute('style', 'display: block;');
  nav_mobile.setAttribute('style', 'transform: translateX(0%);');
};

overlay.onclick = function(){
  nav_mobile_btn_bars.setAttribute('style', 'display: block;');
  nav_mobile_btn_x.setAttribute('style', 'display: none;');
  overlay.setAttribute('style', 'display: none;');
  nav_mobile.setAttribute('style', 'transform: translateX(-100%);');
}

function modalClick(){
  modal.setAttribute('style', 'display: none;');
}

modal_login.onclick = function(event){
  event.stopPropagation();
}


btn_login_mobile.onclick = function(){
  overlay.onclick();
  modal.setAttribute('style', 'display: flex;');
}
//nnnnnnn
if(btn_login != null)
{
  btn_login.onclick = function(event){
    modal.setAttribute('style', 'display: flex;');
    event.stopPropagation();
  }
}
if(btn_login_1 != null)
{
  btn_login_1.onclick = function(){
    modal.setAttribute('style', 'display: flex;');
  }
}
if(btn_invite_1 !=null)
{
  btn_invite_1.onclick = function(){
    document.getElementById('register_input_focus').focus();
  }
}
if(btn_invite_2 != null)
{
  btn_invite_2.onclick = function(){
    document.getElementById('register_input_focus').focus();
  }
}
function js_switch(id_hide, id_flaunt, switch1, switch2){
  var obj_hide = document.getElementById(id_hide);
  var obj_flaunt = document.getElementById(id_flaunt);
  var obj_switch1 = document.getElementById(switch1);
  var obj_switch2 = document.getElementById(switch2);
  obj_hide.setAttribute('style', 'display: none');
  obj_flaunt.setAttribute('style', 'display: flex');
  obj_switch1.setAttribute('style', 'display: none');
  obj_switch2.setAttribute('style', 'display: block');
}

// change new or trendy at news in home page
if( document.querySelector('.home__news__trendy__btn') != null )
{
  document.querySelector('.home__news__trendy__btn').onclick = function(){
    document.querySelector('.home__news__new__btn').setAttribute('style', 'opacity: 62%;');
    document.querySelector('.home__news__trendy__btn').setAttribute('style', 'opacity: 100%;');
    document.querySelector('.news__new').setAttribute('style', 'display: none;');
    document.querySelector('.news__trendy').setAttribute('style', 'display: flex;');
  }
}
if( document.querySelector('.home__news__new__btn') != null)
{
  document.querySelector('.home__news__new__btn').onclick = function(){
    document.querySelector('.home__news__new__btn').setAttribute('style', 'opacity: 100%;');
    document.querySelector('.home__news__trendy__btn').setAttribute('style', 'opacity: 62%;');
    document.querySelector('.news__new').setAttribute('style', 'display: flex;');
    document.querySelector('.news__trendy').setAttribute('style', 'display: none;');
  }
}
// FUNCTION
function loginAPI(){
  let jsonData = convertFormToJson($('#loginForm'))
  $.ajax({
    url: window.location.origin + "/SpringMVC_war/api/auth/login",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(jsonData),
    dataType: "json",
    success: function (result){
      if(result.username != null){
        let accessToken = JSON.stringify(result.accessToken).substring(7);
        document.cookie = "AuthenticationCookie=" + accessToken + "; max-age=" + 2*60 + "; path=/;";
        let currentPath = window.location.pathname
        if(currentPath.includes("/welcome")){
          window.location.pathname = "/SpringMVC_war/home"
        } else {
          location.reload();
        }
      } else {
        document.querySelector(".login__alert label").innerHTML = result.Error
      }
    },
    error: function (xhr, status, error){
      let err = eval("(" + xhr.responseText + ")")
      alert(err.Message)
    }
  })
}
function convertFormToJson(form){
  let formData = $(form).serializeArray()
  let data = {}
  $.each(formData, function (i, v) {
    data[v.name + ""] = v.value
  })
  return data
}
function deleteAuthCookie(){
  document.cookie = "AuthenticationCookie= ; max-age=" + 2*60 + "; path=/;";
}
function clearOnInputChangeAtLogin(){
  document.querySelector(".login__alert label").innerHTML = ""
}
function validateForm() {
  var x = document.forms["discussion_search_form"]["discussionSearch"].value;
  if (x == "") {
    alert("Không được bỏ trống trường này !");
    return false;
  }
}
function errorForm() {
  alert("Chức năng này hiện đang bảo trì, vui lòng thử lại sau !");
  return false;
}
function searchContent(content)
{
  // still not working
  let str = document.querySelector('.contentInSearch').innerText;
  str = str.replace(/content/g, "<mark>" + content + "</mark>");
  document.querySelector('.contentInSearch').innerHTML = str;
}
function notAvaibleFunction()
{
  alert("Chức năng này hiện đang bảo trì, vui lòng thử lại sau !");
  return false;
}
function dropdownFunction()
{
  document.getElementById('Dropdown-content__postblock').classList.toggle('show__dropdown');
  document.getElementById('Arrow-up').classList.toggle('show__dropdown');
}
function forgotPassword()
{
  alert('Có cái mật khẩu cũng quên là dở rồi -_-');
}
//
function testAjaxJs()
{
  const xhttp = new XMLHttpRequest();
  xhttp.onload = function(){
    document.getElementById("a__title__highlight").innerHTML = this.responseText;
    xhttp.open("POST", "Test.php");
    xhttp.send("username=sysadmin");
  }
}
//
window.addEventListener("click", function(e){
  if(document.getElementById('Dropdown-content__postblock') != null && document.querySelector('.post__block__content__more') != null)
  {
    var dropdown = document.getElementById('Dropdown-content__postblock');
    var dropdownBtn = document.querySelector('.post__block__content__more');
    var modal = document.querySelector('.container');
    var arrow = document.getElementById('Arrow-up');
    if(dropdown.classList.contains('show__dropdown'))
    {
      if(!e.target.matches('.post__block__content__more'))
      {
        dropdown.classList.remove('show__dropdown');
        arrow.classList.remove('show__dropdown');
      }
    }
  }
});