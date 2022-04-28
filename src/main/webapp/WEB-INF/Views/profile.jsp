<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="personal__body">
    <div class="container">
        <div class="personal__cover">
            <div class="personal__leftnavbar">
                <div class="personal__leftnavbar__avatar">
                    <div class="personal__avatar__border avatar_common av1">
                        <!-- data will fetch here -->
                    </div>
                </div>
                <div class="personal__leftnavbar__userinfor">
                    <a href="">Thông tin cá nhân</a>
                </div>
                <div class="personal__leftnavbar__usershare">
                    <a href="#">Quản lý bài đăng</a>
                </div>
                <div class="personal__leftnavbar__logout">
                    <a onclick="deleteAuthCookie()" href="<c:url value='/logout'/>">Đăng xuất</a>
                </div>
            </div>
            <div class="personal__content">
                <div class="personal__content__quote">
                    <div id="personal__changequote__text">

                        <!-- data will fetch here -->

                    </div>
                    <!-- data will fetch here -->
                </div>
                <div class="personal__content__userinfor">
                    <div class="personal__content__userinfor__block1">
                        <div id="personal__changeAvatar__text">
                            <div class="personal__avatar__border avatar_common av2">
                                <!-- data will fetch here -->
                            </div>
                            <div class="userinfor__block1__content">
                                <!-- data will fetch here -->
                            </div>
                        </div>
                        <!--
                        <form style="display: none;" action="changeAvatar" enctype="multipart/form-data" id="personal__changeAvatar__form" method="post">
                            <input type="file" name="favt" id="" class="inputFile">
                            <input type="submit" value="Xác nhận">
                        </form>
                        <label for="" id="switch1__avatar" class="change change__avatar" onclick="js_switch('personal__changeAvatar__text', 'personal__changeAvatar__form', 'switch1__avatar', 'switch2__avatar')">Thay đổi ảnh đại diện</label>
                        <label for="" id="switch2__avatar" class="change change__avatar" style="display: none;" onclick="js_switch('personal__changeAvatar__form', 'personal__changeAvatar__text', 'switch2__avatar', 'switch1__avatar')">Thôi</label>
                        -->
                    </div>
                    <div class="personal__content__userinfor__block2">
                        <div class="userinfor__block2__content">
                            <div id="personal__changeinfor__text">
                                <!-- data will fetch here -->

                            </div>
                            <!-- data will fetch here -->
                        </div>
                        <!-- data will fetch here -->
                    </div>
                    <div class="personal__content__userinfor__block3">
                        <div class="userinfor__block3__content">
                            <label id="personal__changepassword__text">Mật khẩu: **********</label>
                            <!-- data will fetch here -->
                        </div>
                    </div>
                    <div class="personal__content__userinfor__blocklast">
                        <div class="userinfor__blocklast__content">
                            <!-- data will fetch here -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function (e) {
        let searchBy, apiUrl;
        if(getUrlParameter("userid")){
            searchBy = "userid";
            apiUrl = hrefLocation + "/api/user?" + searchBy + "=" + getUrlParameter(searchBy);
        } else if(getUrlParameter("username")){
            searchBy = "username";
            apiUrl = hrefLocation + "/api/user?" + searchBy + "=" + getUrlParameter(searchBy);
        } else {
            <security:authorize access="isAuthenticated()">
            let currentLoggedUsername = "<security:authentication property='principal.username'/>";
            apiUrl = hrefLocation + "/api/user?username=" + currentLoggedUsername;
            </security:authorize>

            <security:authorize access="isAnonymous()">
            location.href = hrefLocation + "/";
            </security:authorize>
        }
        $.ajax({
            url: apiUrl,
            dataType: "json"
        }).done(function (result) {
            console.log(result);
            appendData(result);
        })
    });

    function appendData(result){
        $(".personal__avatar__border.av1").append("<img src='" + hrefLocation + result.avatar + "'>");
        $("#personal__changequote__text").append("<label>" + result.userQuote + "</label>");
        $("#personal__changequote__text").append("<div><label><i>~" + result.username + "</i></label></div>");
        $(".personal__avatar__border.av2").append("<img src='" + hrefLocation + result.avatar + "'>");
        $(".userinfor__block1__content").append("<label>" + result.username + "</label><label>" + result.roleName + "</label>");
        $(".userinfor__blocklast__content").append("<label>Tổng số bài đăng: <a class='link__profile' href='#'>" + result.numberOfUserPost + "</a></label>");
        $(".userinfor__blocklast__content").append("<label>Tổng điểm: " + result.totalScore + "</label>");
        $("#personal__changeinfor__text").append("<label>Họ và tên: " + result.fullname + "</label><label>Email: " + result.email + "</label>");

        <security:authorize access="isAuthenticated()">
        let currentLoggedUsername = "<security:authentication property='principal.username'/>";
        if(currentLoggedUsername.includes(result.username)){

            $(".personal__content__quote").append("<form style='display: none;' id='personal__changequote__form'><input type='text' name='user_quote' id='' placeholder='Viết châm ngôn của bạn...' maxlength='140' minlength='1'><input type='submit' value='Xác nhận'></form>");
            $(".personal__content__quote").append("<label id='switch1__quote' class='change change__quote' onclick=\"js_switch('personal__changequote__text', 'personal__changequote__form', 'switch1__quote', 'switch2__quote')\">Chỉnh sửa</label>");
            $(".personal__content__quote").append("<label style='display: none;' id='switch2__quote' class='change change__quote' onclick=\"js_switch('personal__changequote__form', 'personal__changequote__text', 'switch2__quote', 'switch1__quote')\">Thôi</label>");

            $(".userinfor__block2__content").append("<form style='display: none;' id='personal__changeinfor__form'><input type='text' name='fullname' id='' placeholder='Họ và tên' maxlength='120' minlength='3'><input type='text' name='phone_number' placeholder='Số điện thoại'><input type='submit' value='Xác nhận'></form>");
            $(".personal__content__userinfor__block2").append("<label id='switch1__infor' class='change change__infor' onclick=\"js_switch('personal__changeinfor__text', 'personal__changeinfor__form', 'switch1__infor', 'switch2__infor')\">Chỉnh sửa</label>");
            $(".personal__content__userinfor__block2").append("<label style='display: none;' id='switch2__infor' class='change change__infor' onclick=\"js_switch('personal__changeinfor__form', 'personal__changeinfor__text', 'switch2__infor', 'switch1__infor')\">Thôi</label>");
            $("#personal__changeinfor__text").append("<label>Số điện thoại: " + result.phoneNumber + "</label>");

            $(".userinfor__block3__content").append("<form id='personal__changepassword__form' style='display: none;'><input type='password' name='password' id='' placeholder='Mật khẩu hiện tại'> <input type='password' name='new_password' id='' placeholder='Mật khẩu mới'> <input type='password' name='password_confirm' id='' placeholder='Nhập lại mật khẩu mới'> <input type='button' name='change_password' id='' value='Xác nhận'> </form>");
            $(".personal__content__userinfor__block3").append("<label id='switch1__password' class='change change__password' onclick=\"js_switch('personal__changepassword__text', 'personal__changepassword__form', 'switch1__password', 'switch2__password')\">Thay đổi</label>");
            $(".personal__content__userinfor__block3").append("<label id='switch2__password' class='change change__password' style='display: none;' onclick=\"js_switch('personal__changepassword__form', 'personal__changepassword__text', 'switch2__password', 'switch1__password')\">Thôi</label>");
        }
        </security:authorize>
    }
</script>