<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="login__body">
    <div class="container">
        <div class="modal__body--login">
            <label>
                Đăng nhập
            </label>
            <form action="login" method="POST">
                <input type="text" placeholder="Tài khoản" name="username" maxlength="60" minlength="6">
                <!--<div class='notify-error'></div>-->
                <input type="password" name="password" placeholder="Mật khẩu" maxlength="120" minlength="6">
                <!--<div class='notify-error'></div>-->
                <button type="submit" name="logIn">Đăng nhập</button>
                <div class="zxz">
                    <a href="Notfound">Quên mật khẩu?</a>
                </div>
            </form>
            <p>Chưa có tài khoản? <a href="">Đăng ký ngay</a></p>
        </div>
    </div>
</div>
