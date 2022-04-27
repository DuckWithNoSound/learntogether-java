<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="share__body">
    <div class="container">
        <div class="share__container">
            <div class="share__first">
                <div class="share__search">
                    <form class="share__search__block" onsubmit="return validateForm()" name="discussion_search_form" action="<?php echo base_url('Discussion/DiscussionSearch') ?>" method="GET">
                        <input type="text" name="discussionSearch" value="" class="searchTerm" placeholder="Nhập nội dung tìm kiếm?">
                        <button type="submit" class="searchButton">
                            <i class="fa fa-search"></i>
                        </button>
                    </form>
                </div>
                <security:authorize access="isAnonymous()">
                <div class="check__logged">
                    <a href="#" id="#discussion_login">Vui lòng đăng nhập để đăng bài thảo luận !</a>
                </div>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                <div class="share__create">
                    <a href="<c:url value="/discussion/post/create"/>">+ Viết bài thảo luận mới</a>
                </div>
                </security:authorize>
            </div>

            <div class="discussion__order">
                <div class="discussion__orderby btn-group btn-group-toggle" data-toggle="buttons">
                    <label id="viewNumber" class="btn btn-secondary discussion__orderby__btn active" onclick="changeSortBy(this)">Nổi bật</label>
                    <label id="score" class="btn btn-secondary discussion__orderby__btn" onclick="changeSortBy(this)">Điểm cao</label>
                    <label id="id" class="btn btn-secondary discussion__orderby__btn" onclick="changeSortBy(this)">Mới nhất</label>
                </div>
                <div class="discussion__orderdir">
                    <label id="desc" class="btn btn-light active" onclick="changeSortDir(this)"><i class="fas fa-sort-amount-down"></i></label>
                    <label id="asc" class="btn btn-light" onclick="changeSortDir(this)"><i class="fas fa-sort-amount-up"></i></label>
                </div>
            </div>

            <div class="share__content">
                <!-- API will fetch data here -->

            </div>
            <div class="pagination__container">
                <ul id="pagination" class="pagination-lg"></ul>
            </div>
        </div>
    </div>
</div>
<script>
    let currentSortBy = "viewNumber";
    let currentSortDir = "desc";
    let test;
    $(document).ready(function (){
        $.get("<c:url value='/api/post/all/count'/>", function (result){
            let totalPosts = result.totalPosts;
            $("#pagination").twbsPagination({
                totalPages: Math.ceil(totalPosts/10),
                visiblePages: 5,
                next: "Next",
                prev: "Prev",
                onPageClick: function (event, page){
                    //fetch content and render here
                    clearAndGetNewData(page);
                }
            });
        });
    });

    function clearAndGetNewData(page){
        $(".share__content").empty();
        let apiUrl = "<c:url value='/api/post/all'/>" + "?page=" + page + "&size=10" + "&sort=" + currentSortBy + "&dir=" + currentSortDir;
        $.ajax({
            url: apiUrl,
            type: "GET",
            dataType: "json"
        }).done(function (result){
            appendPosts(result);
        });
    }

    function changeSortBy(btn){
        document.getElementById("viewNumber").classList.remove("active");
        document.getElementById("score").classList.remove("active");
        document.getElementById("id").classList.remove("active");
        btn.classList.add("active");
        currentSortBy = btn.id;
        clearAndGetNewData(1);
    }
    function changeSortDir(btn){
        document.getElementById("desc").classList.remove("active");
        document.getElementById("asc").classList.remove("active");
        btn.classList.add("active");
        currentSortDir = btn.id;
        clearAndGetNewData(1);
    }


    function appendPosts(posts){
        $.each(posts, function (key, value) {
            let createdDate = new Date(value.createdDate);
            let dateString = createdDate.getDate() + "/" + createdDate.getMonth() + "/" + createdDate.getFullYear();
            let hrefLocation = location.origin + "/SpringMVC_war";
            $(".share__content").append("<div class='share__content__block'><div class='share__content__one post" + key + "'></div><div class='share__content__two post" + key + "'></div></div>");
            $(".share__content__one.post" + key).append("<img src='" + hrefLocation + value.author.avatar + "' class='img__avatar__82'>");
            $(".share__content__one.post" + key).append("<label class='user_level'>" + value.author.roleName + "</label>");
            $(".share__content__one.post" + key).append("<div class='UpAndDown'><i class='fas fa-caret-up'></i><label>" + value.score + "</label><i class='fas fa-caret-down'></i>");
            $(".share__content__two.post" + key).append("<div class='share__block__content'><div class='discussion__block__content__fisrt'><a href='" + hrefLocation + "/post/" + value.id + "'>" + value.title + "</a></div><div class='discussion__block__content__second'>" + listTagToString(value.listTagSlug) + "</div><div class='discussion__block__content__third'><pre wrap='true'>" + value.content + "</pre></div></div>");
            $(".share__content__two.post" + key).append("<div class='share__block__detail'><label>Tác giả: <a class='link__profile' href=''>" + value.author.username + "</a></label><label>Ngày đăng: " + dateString + "</label><label><a href='" + hrefLocation + "/post/" + value.id + "'><i class='far fa-comment-dots'></i>" + value.comments.length + "</a></label><label><i class='far fa-eye'></i>" + value.viewNumber + "</label></div>");
        });
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