<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="home__body">
    <div class="welcome">
        <div class="container">
            <div class="welcome__quote">
                <h1>Chào mừng bạn đến với LearnTogether</h1>
            </div>
        </div>
    </div>
    <div class="news">
        <div class="container">
            <h2 class="title">Tin tức</h2>
            <div class="selection">
                <div class="selection__one">
                    <label class="home__news__trendy__btn">Nổi bật</label>
                    <label class="home__news__new__btn">Mới nhất</label>
                </div>
                <a href="" class="see_all">Xem tất cả</a>
            </div>
            <div class="news__trendy">
                <div class="trendy__block_1">
                    <div class="trendy__block__content">
                        <img src="<c:url value='/assets/Images/thuat-toan-chuoi-nhi-phan-do-dai-n.jpg'/>" alt="">
                        <a class="a__title" onclick="return notAvaibleFunction()" >Thuật Toán Đưa Ra Chuỗi Nhị Phân...</a>
                        <div class="rating">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                        </div>
                        <div class="block__content">
                            Chắc hẳn ai học về giải thuật cũng đã từng nghe qua và làm về bài toán đưa ra chuỗi nhị phân độ ...
                        </div>
                    </div>
                    <div class="trendy__block__detail">
                        <label>Tác giả: ABCCC</label>
                        <label>Ngày đăng: 01/08/2021</label>
                    </div>
                </div>
                <div class="trendy__block_2">
                    <div class="trendy__block__content">
                        <img src="<c:url value='/assets/Images/Codeigniter-framework-la-gi.jpg'/>" alt="">
                        <a class="a__title" href="">Code Igniter là gì ???</a>
                        <div class="rating">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                        </div>
                        <div class="block__content">
                            Codeigniter căn bản chúng ta sẽ tìm hiểu sơ lược cách tổ chức folder trong CI, cách sử dụng các thư viện có sẵn...
                        </div>
                    </div>
                    <div class="trendy__block__detail">
                        <label>Tác giả: ABCCC</label>
                        <label>Ngày đăng: 01/08/2021</label>
                    </div>
                </div>
                <div class="trendy__block_3">
                    <div class="trendy__block__content">
                        <img src="<c:url value='/assets/Images/mo-hinh-mvc.jpg'/>" alt="">
                        <a class="a__title" href="">MVC là gì ? Ứng dụng của mô hình MVC.</a>
                        <div class="rating">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star nostar"></i>
                        </div>
                        <div class="block__content">
                            MVC (MVC Design Pattern) là viết tắt của Model — View — learntogether.Controller. Đó là một
                            mẫu kiến ​​trúc, mô hình lập trình...
                        </div>
                    </div>
                    <div class="trendy__block__detail">
                        <label>Tác giả: ABCCC</label>
                        <label>Ngày đăng: 01/08/2021</label>
                    </div>
                </div>
            </div>
            <div class="news__new">
                <div class="new__block_1">
                    <div class="new__block__content">
                        <img src="<c:url value='/assets/Images/temp_news.png'/>" alt="">
                        <a class="a__title" href=""></a>
                        <div class="rating">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                        </div>
                        <div class="content"></div>
                    </div>
                    <div class="new__block__detail">
                        <label>Tác giả: </label>
                        <label>Ngày đăng: </label>
                    </div>
                </div>
                <div class="new__block_2">
                    <div class="new__block__content">
                        <img src="<c:url value='/assets/Images/temp_news.png'/>" alt="">
                        <a class="a__title" href=""></a>
                        <div class="rating">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                        </div>
                        <div class="content"></div>
                    </div>
                    <div class="new__block__detail">
                        <label>Tác giả: </label>
                        <label>Ngày đăng: </label>
                    </div>
                </div>
                <div class="new__block_3">
                    <div class="new__block__content">
                        <img src="<c:url value='/assets/Images/temp_news.png'/>" alt="">
                        <a class="a__title" href=""></a>
                        <div class="rating">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                        </div>
                        <div class="content"></div>
                    </div>
                    <div class="new__block__detail">
                        <label>Tác giả: </label>
                        <label>Ngày đăng: </label>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="studies">
        <div class="container">
            <h2 class="title">Bài học</h2>
            <a href="" class="see_all">Xem tất cả</a>
            <div class="studies__content">
                <div href="" class="studies__content__block_1">
                    <div class="studies__block__content">
                        <img src="<c:url value='/assets/Images/course_cpp.jpg'/>" alt="">
                        <div class="studies__content__one">
                            <a class="a__title" href="">C++ cơ bản</a>
                            <div class="block__content">Học lập trình C++ cơ bản đang là một xu hướng của ngành lập trình hiện nay. Bởi vì C++ là ngôn ngữ phổ biến...</div>
                        </div>
                    </div>
                    <div class="studies__block__detail">
                        <div>
                            <label><i class="far fa-clock"></i> 01/08/2021</label>
                            <a href="">Vào học</a>
                        </div>
                    </div>
                </div>
                <div class="studies__content__block_2">
                    <div class="studies__block__content">
                        <img src="<c:url value='/assets/Images/course_java.jpg'/>" alt="">
                        <div class="studies__content__one">
                            <a class="a__title" href="">Java dành cho người mới</a>
                            <div class="block__content">
                                Java là ngôn ngữ lập trình máy tính có tính chất hướng đối tượng, dựa trên các lớp...
                            </div>
                        </div>
                    </div>
                    <div class="studies__block__detail">
                        <div>
                            <label><i class="far fa-clock"></i> 01/08/2021</label>
                            <a href="">Vào học</a>
                        </div>
                    </div>
                </div>
                <div class="studies__content__block_3">
                    <div class="studies__block__content">
                        <img src="<c:url value='/assets/Images/course_javaoop.jpg'/>" alt="">
                        <div class="studies__content__one">
                            <a class="a__title" href="">Lập trình java hướng đối tượng</a>
                            <div class="block__content">
                                Khái niệm về lập trình hướng đối tượng trong java. Đối tượng (Object), lớp (Class), kế thừa ...
                            </div>
                        </div>
                    </div>
                    <div class="studies__block__detail">
                        <div>
                            <label><i class="far fa-clock"></i> 01/08/2021</label>
                            <a href="">Vào học</a>
                        </div>
                    </div>
                </div>
                <div class="studies__content__block_4">
                    <div class="studies__block__content">
                        <img src="<c:url value='/assets/Images/course_sql.jpg'/>" alt="">
                        <div class="studies__content__one">
                            <a class="a__title" href="">SQL cơ bản</a>
                            <div class="block__content">SQL là viết tắt của từ Structured Query Language, nghĩa là ngôn ngữ...

                            </div>
                        </div>
                    </div>
                    <div class="studies__block__detail">
                        <div>
                            <label><i class="far fa-clock"></i> 01/08/2021</label>
                            <a href="">Vào học</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="shares">
        <div class="container">
            <h2 class="title" style="text-align: center;">Thảo luận</h2>
            <div class="shares__content--cover">
                <div class="shares__content">
                    <!-- data will fetch here -->
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function (){
        let apiUrl = "<c:url value='/api/post/all'/>" + "?page=1" + "&size=3" + "&sort=score" + "&dir=desc";
        $.ajax({
            url: apiUrl,
            type: "GET",
            dataType: "json"
        }).done(function (result){
            appendPosts(result);
        });
    })
    function appendPosts(posts){
        let hrefLocation = location.origin + "/SpringMVC_war";
        $.each(posts, function (key, value) {
            let createdDate = new Date(value.createdDate);
            let dateString = createdDate.getDate() + "/" + createdDate.getMonth() + "/" + createdDate.getFullYear();
            $(".shares__content").append("<div class='shares__content__block_1'><div class='share__content__one post" + key + "'></div><div class='share__content__two post" + key + "'></div></div>");
            $(".share__content__one.post" + key).append("<img src='" + hrefLocation + value.author.avatar + "' class='img__avatar__82'>");
            $(".share__content__one.post" + key).append("<label class='user_level'>" + value.author.roleName + "</label>");
            $(".share__content__one.post" + key).append("<div class='UpAndDown'><i class='fas fa-caret-up'></i><label>" + value.score + "</label><i class='fas fa-caret-down'></i>");
            $(".share__content__two.post" + key).append("<div class='share__block__content'><div class='discussion__block__content__fisrt'><a href='" + hrefLocation + "/post/" + value.id + "'>" + value.title + "</a></div><div class='discussion__block__content__second'>" + listTagToString(value.listTagSlug) + "</div><div class='discussion__block__content__third'><pre wrap='true'>" + value.content + "</pre></div></div>");
            $(".share__content__two.post" + key).append("<div class='share__block__detail'><label>Tác giả: <a class='link__profile' href=''>" + value.author.username + "</a></label><label>Ngày đăng: " + dateString + "</label><label><a href='" + hrefLocation + "/post/" + value.id + "'><i class='far fa-comment-dots'></i>" + value.comments.length + "</a></label><label><i class='far fa-eye'></i>" + value.viewNumber + "</label></div>");
        });

        $(".shares__content").append("<a href='" + hrefLocation + "/discussion" + "' class='see_all'>Xem thêm</a>");
    }
    function listTagToString(data){
        let tagsString = "Tags: ";
        $.each(data, function (key, value){
            if(key == 0){
                tagsString += value;
            } else {
                tagsString += (", " + value);
            }
        })
        return tagsString;
    }
</script>