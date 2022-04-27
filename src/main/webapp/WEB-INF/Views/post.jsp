<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="post__body">
    <div class="container">
        <div class="post__cover">
            <div class="post__container">
                <div class="post__main">
                    <div class="post__content__one">

                       <!-- data will fetch here -->

                    </div>
                    <div class="post__content__two">
                        <div class="post__block__content">

                            <!-- data will fetch here -->

                        </div>
                        <div class="post__block__detail">

                            <!-- data will fetch here -->

                        </div>
                    </div>
                </div>
                <div class="post__comment">
                    <div class='post__bridge'>

                        <!-- data will fetch here -->

                    </div>

                        <!-- data will fetch here -->

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    let ckeditor;
    $(document).ready(function (){
        let postId = location.pathname.match(/\d+/g);
        $.ajax({
            url: "<c:url value='/api/post'/>" + "/" + postId,
            type: "GET",
            dataType: "json"
        }).done(function (result){
            appendData(result);
            <security:authorize access="isAuthenticated()">
            $.ajax({
                url: "<c:url value='/api/post/score/currently?postid='/>" + postId,
                type: "GET",
                dataType: "json"
            }).done(function (data){
                if(data.currentVote == 1){
                    $(".post__content__one").append("<div class='UpAndDown'><a id='postUpScore' href='#'><i class='fas fa-caret-up isActive' id='button__score'></i></a><label id='postScore'>" + result.score + "</label><a onclick='voteScorePost(-1)' id='postDownScore' href='#'><i class='fas fa-caret-down' id='button__score'></i></a></div>");
                } else if(data.currentVote == -1){
                    $(".post__content__one").append("<div class='UpAndDown'><a onclick='voteScorePost(1)' id='postUpScore' href='#'><i class='fas fa-caret-up' id='button__score'></i></a><label id='postScore'>" + result.score + "</label><a id='postDownScore' href='#'><i class='fas fa-caret-down isActive' id='button__score'></i></a></div>");
                } else {
                    $(".post__content__one").append("<div class='UpAndDown'><a onclick='voteScorePost(1)' id='postUpScore' href='#'><i class='fas fa-caret-up' id='button__score'></i></a><label id='postScore'>" + result.score + "</label><a onclick='voteScorePost(-1)' id='postDownScore' href='#'><i class='fas fa-caret-down' id='button__score'></i></a></div>");
                }
            });
            //
            ckeditor = CKEDITOR.replace("comment_editor");
            </security:authorize>
        });
        $.ajax({
            url: "<c:url value='/api/post/view?postid='/>" + postId,
            type: "GET",
            dataType: "json"
        }).done(function (result){
            $('post__viewnumber').text = "Lượt xem: " + result;
        });

    });

function appendData(data){
    $(".post__content__one").append("<img src='" + hrefLocation + data.author.avatar + "' class='img__avatar__82' alt='avatar'>");
    $(".post__content__one").append("<label class='user_level'>" + data.author.roleName + "</label>");

    <security:authorize access="isAnonymous()">
        $(".post__content__one").append("<div class='UpAndDown'><a id='postUpScore' href='#'><i class='fas fa-caret-up' id='button__score'></i></a><label id='postScore'>" + data.score + "</label><a id='postDownScore' href='#'><i class='fas fa-caret-down' id='button__score'></i></a></div>");
    </security:authorize>

    $(".post__block__content").append("<div class='post__block__content__first'><label>" + data.title + "</label></div>");

    //


    <security:authorize access="isAuthenticated()">
        let currentUsername = "<security:authentication property='principal.username'/>";

        let temp = "<security:authentication property='principal'/>";
        let currentRoleId = temp.substr(temp.length-1);

        if(currentUsername.includes(data.author.username)){
            $(".post__block__content__first").append("<div class='dropdown__postblock'><button class='dropdownBtn__postblock' onclick='dropdownFunction()'><i class='fas fa-ellipsis-h post__block__content__more'></i></button><div class='arrow-up' id='Arrow-up'></div><div class='dropdown-content__postblock' id='Dropdown-content__postblock'><a href='" + hrefLocation + "/discussion/post/edit?postid=" + data.id + "'>Sửa bài viết</a><a href='#'>Xóa bài viết</a><a href='#'>Bật thông báo</a></div></div>");
        } else if(currentRoleId <= 3){
            $(".post__block__content__first").append("<div class='dropdown__postblock'><button class='dropdownBtn__postblock' onclick='dropdownFunction()'><i class='fas fa-ellipsis-h post__block__content__more'></i></button><div class='arrow-up' id='Arrow-up'></div><div class='dropdown-content__postblock' id='Dropdown-content__postblock'><a href='#'>Xóa bài viết</a><a href='#'>Bật thông báo</a></div></div>");
        }
        else {
            $(".post__block__content__first").append("<div class='dropdown__postblock'><button class='dropdownBtn__postblock' onclick='dropdownFunction()'><i class='fas fa-ellipsis-h post__block__content__more'></i></button><div class='arrow-up' id='Arrow-up'></div><div class='dropdown-content__postblock' id='Dropdown-content__postblock'><a href='#'>Báo cáo</a><a href='#'>Bật thông báo</a></div></div>");
        }
    </security:authorize>

    //
    $(".post__block__content").append("<div class='post__block__content__second'><pre wrap='true'>" + data.content + "</pre></div>");


    let createdDate = new Date(data.createdDate);
    let dateString = createdDate.getDate() + "/" + createdDate.getMonth() + "/" + createdDate.getFullYear();
    $(".post__block__detail").append("<label>Tác giả: <a href='#'>" + data.author.username + "</a></label>");
    $(".post__block__detail").append("<label>Ngày đăng: " + dateString + "</label>");
    if(data.modifiedDate != null){
        $(".post__block__detail").append("<label>Cập nhật lần cuối: " + getModifiedDateFormat(data.modifiedDate) + "</label>");
    }
    $(".post__block__detail").append("<label id='post__viewnumber'>Lượt xem: " + data.viewNumber + "</label>");

    // Comment

    // bridge
    let a = "post__bridge__text", b = "post__bridge__form", c = "switch1__comment", d = "switch2__comment";
    <security:authorize access="isAuthenticated()">
        $(".post__bridge").append("<div id='post__bridge__text'><label>" + data.comments.length + " bình luận</label><label id='switch1__comment' class='change new__comment' onclick=\"js_switch('" + a + "', '" + b + "', '" + c + "', '" + d + "')\">+ Viết Bình luận</label></div>");
        $(".post__bridge").append("<form action='#' method='post' id='post__bridge__form' style='display: none;'><label>Bình luận</label><textarea form='post__bridge__form' id='comment_editor' name='comment_editor' cols='30' rows='7'></textarea><input type='button' name='submitComment' id='submitComment' onclick='postComment()' value='Đăng'><label for='' id='switch2__comment' class='change' style='display: none;' onclick=\"js_switch('" + b + "', '" + a + "', '" + d + "', '" + c + "')\">Hủy bỏ</label><input id='post_id' type='hidden' name='post_id' value='" + data.id + "'></form>");
    </security:authorize>
    <security:authorize access="isAnonymous()">
        $(".post__bridge").append("<div id='post__bridge__text'><label>" + data.comments.length + " bình luận</label><div class='check__logged'><a href='#'>Vui lòng đăng nhập bình luận !</a></div></div>");
    </security:authorize>
    // list comment
    appendComments(data.comments);
}

function appendComments(data){
    $.each(data, function (key, value){
        let createdDate = new Date(value.createdDate);
        let dateString = createdDate.getDate() + "/" + createdDate.getMonth() + "/" + createdDate.getFullYear();
        $(".post__comment").append("<div class='post__comment__block'><div class='post__comment__block__first'><div class='UpAndDown'><a href='#'><i class='fas fa-caret-up' id='button__score'></i></a><label>" + value.score + "</label><a href='#'><i class='fas fa-caret-down' id='button__score'></i></a></div></div><div class='post__comment__block__second'><div class='comment__block__second__one'><img src='" + hrefLocation + value.authorAvatar + "' class='img__avatar__52' alt=''><div><label><a href='#'>" + value.authorName + "</a></label><label class='user_level'>" + value.authorRole + "</label></div><label>Ngày đăng: " + dateString + "</label></div><div class='comment__block__second__two'><p>" + value.content + "</p></div></div></div>");
    });
}
function getModifiedDateFormat(data){
    let modifiedDate = data;
    let modifiedDateForNow = Date.parse(new Date()) - modifiedDate;
    let minutes = 60 * 1000, hours = 60 * 60 * 1000, days = 24 * 60 * 60 * 1000;
    if(modifiedDateForNow <= minutes){
        modifiedDateForNow = "vừa xong";
    } else if(modifiedDateForNow > minutes && modifiedDateForNow <= hours){
        modifiedDateForNow = Math.round(modifiedDateForNow / minutes) + " phút trước";
    } else if(modifiedDateForNow > hours && modifiedDateForNow <= days){
        modifiedDateForNow = Math.round(modifiedDateForNow / hours) + " giờ trước";
    } else if(modifiedDateForNow > days && modifiedDateForNow <= days * 30){
        modifiedDateForNow = Math.round(modifiedDateForNow / days) + " ngày trước";
    } else {
        modifiedDate = new Date(data);
        modifiedDateForNow = modifiedDate.getDate() + "/" + modifiedDate.getMonth() + "/" + modifiedDate.getFullYear();
    }
    return modifiedDateForNow;
}

function voteScorePost(scoreType){
    let postId = location.pathname.match(/\d+/g);
    $.ajax({
        url: hrefLocation + "/api/post/score?postid=" + postId + "&scoretype=" + scoreType,
        type: "GET",
        dataType: "json"
    }).done(function (result){
        location.reload();
    });
}
function postComment(){
    let data = {}, dataJson;
    data["postId"] = $('#post_id').val();
    data["content"] = ckeditor.getData();
    dataJson = JSON.stringify(data);
    console.log(dataJson);
    $.ajax({
        url: hrefLocation + "/api/commentpost/",
        type: "POST",
        data: dataJson,
        dataType: "json",
        contentType: "application/json"
    }).done(function (result) {
        if(result.Message != null){
            alert("Bình luận thất bại!");
            console.log(result.Message);
        } else {
            location.reload();
        }
    })
}
</script>