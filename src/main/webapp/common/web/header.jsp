<%@ page import="learntogether.Util.SecurityUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Preload -->
<div class="app">
    <div class="page_overlay"></div>
    <c:if test="${param.login != null}">
        <div class="modal" style="display: flex">
    </c:if>
    <c:if test="${param.login == null}">
        <div class="modal">
    </c:if>
            <div class="modal__overlay" onmousedown="modalClick()"></div>
        <div class="modal__body--login">
            <label>
                Đăng nhập
            </label>
            <form action="spring_security_login" method="POST" id="loginForm">
                <input type="text" placeholder="Tài khoản" name="username" onchange="clearOnInputChangeAtLogin()">
                <input type="password" placeholder="Mật khẩu" name="password" onchange="clearOnInputChangeAtLogin()">
                <button type="button" onclick="loginAPI()" name="logIn">Đăng nhập</button>
                <div class="zxz">
                    <a style="cursor: pointer;" onclick="return forgotPassword()">Quên mật khẩu?</a>
                </div>
            </form>
            <div class="login__alert">
                <label>

                </label>
            </div>
            <p>Chưa có tài khoản? <a href="<c:url value='/welcome'/>">Đăng ký ngay</a></p>
        </div>
    </div>
    <header>
        <div class="Space_for_navbar"></div>
        <nav class="Nav_bar">
            <div class="Nav_Logo">
                <security:authorize access="isAnonymous()">
                    <a id="Logo" href="<c:url value='/welcome'/> ">
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <a id="Logo" href="<c:url value='/home'/> ">
                </security:authorize>
                    <img src="<c:url value='/assets/Images/main_logo.png'/>" alt="Logo">
                </a>
            </div>
            <div class="Nav__items">
                <div class="Nav__items-a">
                    <div class="hide-on-res hide-on-res-62em" id="Nav_Home">
                        <a href="<c:url value='/course'/> ">Bài học</a>
                    </div>
                    <div class="hide-on-res" id="Nav_Subject">
                        <a href="<c:url value='/news'/> ">Tin tức</a>
                    </div>
                    <div class="hide-on-res" id="Nav_Teacher">
                        <a href="<c:url value="/discussion"/> ">Thảo luận</a>
                    </div>
                </div>
                <div class="Nav_Sign">
                    <security:authorize access="isAuthenticated()">
                        <div class="Signed">
                        <div class="Personal">
                            <a id="userInfor">
                                <img src="<c:url value='<%=SecurityUtil.getPrincipal().getAvatar()%>'/>" class="small-avatar"> <label><%= SecurityUtil.getPrincipal().getUsername() %></label>
                            </a>
                            <div class="Personal-hover">
                                <div class="arrow"></div>
                                <a href="<c:url value='/profile'/>">Trang cá nhân</a>
                                <security:authorize access="hasAnyRole('1', '2')">
                                    <a href="">Trang quản trị</a>
                                </security:authorize>
                                <a onclick="deleteAuthCookie()" href="<c:url value='/logout'/>">Đăng xuất</a>
                            </div>
                        </div>
                        |
                        <a href="" id="">
                            <label><i class="fas fa-bell"></i></label>
                        </a>
                        </div>
                    </security:authorize>
                    <security:authorize access="isAnonymous()">
                        <a href="#" id="Sign_Login">
                            <label> Đăng nhập </label>
                        </a>
                        <a href="<c:url value='/welcome'/>" id="Sign_Signup">
                            <label> Đăng ký </label>
                        </a>
                    </security:authorize>
                </div>
                <div class="Nav_Search">
                    <form method="GET" action="#">
                        <input type="text" placeholder="Bạn muốn tìm kiếm gì ?" id="Text">
                        <a onclick="notAvaibleFunction()" id="Button"><i class="fas fa-search"></i></a>
                    </form>
                </div>
            </div>
            <div class="space_for_btn"></div>
        </nav>
        <div class="Nav__mobile-btn">
            <i id="btn-x" class="fas fa-times"></i>
            <i id="btn-bars" class="fas fa-bars"></i>
        </div>
        <nav class="Nav__mobile">
            <div class="Mobile__sign">
                <a href="#" class="Login__mobile">Đăng nhập</a>
                <a href="#" class="Register__mobile">Đăng ký</a>
            </div>
            <a href="" class="Home__mobile">Bài học</a>
            <a href="" class="Course__mobile">Tin tức</a>
            <a href="" class="Teacher__mobile">Thảo luận</a>
            <div class="Language__mobile">
                <a href="" class="Language__en">
                    <img src="<c:url value='/assets/Images/icon-en.png'/>" alt="">
                    English
                </a>
                <a href="" class="Language__Vi">
                    <img src="<c:url value='/assets/Images/icon_vn.png'/>" alt="">
                    Tiếng Việt
                </a>
            </div>
        </nav>
    </header>
