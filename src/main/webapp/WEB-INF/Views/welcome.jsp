<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="Body">
    <div class="Body__one">
        <img src="<c:url value='/assets/Images/background_body_one.png'/>" alt="">
        <div class="Body__one-a container">
            <div class="Body__one--content">
                <h1>Việc tự học không còn là <span>cực hình</span></h1>
                <p>Mọi thứ dễ dàng hơn với LearnTogether!</p>
            </div>
            <div class="Body__one--register">
                <label>
                    Đăng ký mới
                    ${abc}
                </label>
                <form action="register" method="POST">
                    <input id="register_input_focus" type="text" name="username" placeholder="Tên đăng nhập" maxlength="60" minlength="6" value="">
                    <c:if test="${username != null}">
                        <div class='notify-error'>${username}</div>
                    </c:if>
                    <input type="email" name="email" placeholder="Email" maxlength="120" minlength="5" value="">
                    <c:if test="${email != null}">
                        <div class='notify-error'>${email}</div>
                    </c:if>
                    <input type="password" name="password" maxlength="120" minlength="6" placeholder="Mật khẩu">
                    <!--<div class='notify-error'></div> -->
                    <p>Bấm vào <b>Đăng Ký</b> là bạn đã đồng ý với <a href="Notfound">Điều khoản</a> của LearnTogether</p>
                    <button type="submit" name="register">Đăng ký</button>
                </form>
                <hr>
                <p>Đã có tài khoản? <a id="logIn_btn" style="cursor: pointer;">Đăng nhập</a></p>
            </div>
        </div>
    </div>
    <div class="Bride__one">
        <ul class="Bussiness__logo container">
            <li>
                <img src="<c:url value='/assets/Images/Logo_saitama.png'/>" alt="">
            </li>
            <li>
                <img src="<c:url value='/assets/Images/Logo_Hubt.png'/>" alt="">
            </li>
            <li>
                <img src="<c:url value='/assets/Images/Logo_Panik.png'/>" alt="">
            </li>
            <li>
                <img src="<c:url value='/assets/Images/Logo_WOF.png'/>" alt="">
            </li>
            <li>
                <img src="<c:url value='/assets/Images/Logo_softdreams.png'/>" alt="">
            </li>
        </ul>
    </div>
    <div class="Body__two ">

    </div>
    <div class="Bride__two ">
        <img src="<c:url value='/assets/Images/tech-wallpaper-4k-for-mobile.png'/>" alt="" class="Bride__two-background">
        <div class="Bride__two-container container">
            <div class="Bride__two-content">
                <div class="Bride__two-content-one">
                    <h2>xxxx +</h2>
                    <h3>Bài học</h3>
                </div>
                <div class="Bride__two-content-two">
                    <h2>xxxxxx +</h2>
                    <h3>Thảo luận</h3>
                </div>
                <div class="Bride__two-content-three">
                    <h2>xxxxx +</h2>
                    <h3>Thành viên</h3>
                </div>
            </div>
            <div class="Bride_two-btn">
                <a>Tham gia ngay !</a>
            </div>
        </div>
    </div>
    <div class="Body__three ">

    </div>
    <div class="Body__four ">

    </div>
    <div class="Bride__last ">
        <img src="<c:url value='/assets/Images/banner-last.png'/>" alt="">
        <div class="Bride_last-content container">
            <h2>Không gì là không thể !</h2>
            <h3>Đăng ký tài khoản và tham gia ngay hôm nay !</h3>
            <a>Bắt đầu ngay !</a>
        </div>
    </div>
</div>
