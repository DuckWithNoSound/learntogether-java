<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="question__body">
    <div class="container">
        <div class="question__cover">
            <div class="question__content">
                <h1>Viết bài</h1>
                <form action="" id="question_form" method="POST">
                    <label>Tiêu đề<i class="fas fa-asterisk asterisk--rule"></i></label>
                    <input type="text" id="post_title" name="title" placeholder="Tiêu đề bài viết... " value="">
                    <label>Tags<i class="fas fa-asterisk asterisk--rule"></i></label>
                    <select id="post_tags" name="post_tags" multiple="multiple">

                    </select>
                    <label>Nội dung<i class="fas fa-asterisk asterisk--rule"></i></label>
                    <div class="textarea__container">
                        <textarea name="content_editor" id="content_editor"></textarea>
                    </div>
                    <div>
                        <input type="submit" name="post_upload" value="Đăng bài" id="question_submit">
                        <a href="<c:url value="/discussion"/>"><button type="button" id="question_cancel">Hủy bỏ</button></a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    let ckeditor, pageType, postTags;

    $(document).ready(function (){
        ckeditor = CKEDITOR.replace("content_editor");
        if(getUrlParameter('postid')){
            pageType = "update";
        } else {
            pageType = "create";
        }
        if(pageType.includes("update")){
            $.ajax({
                url: "<c:url value='/api/post/'/>" + getUrlParameter('postid'),
                method: "GET",
                dataType: "json"
            }).done(function (result) {
                $("#post_title").val(result.title);
                CKEDITOR.instances["content_editor"].setData(result.content);
                tags = result.listTagSlug;
                $.each(tags, function (key, value){
                    $("#post_tags").append("<option value="+ value+">"+ value +"</option>");
                })
                postTags = $("#post_tags").filterMultiSelect();
                postTags.selectAll();
                postTags.disable();

            });
        } else {
            $.ajax({
                url: "<c:url value='/api/tag/all'/>",
                method: "GET",
                dataType: "json"
            }).done(function (tags){
                $.each(tags, function (key, value){
                    $("#post_tags").append("<option value="+ value.tagSlug +">"+ value.tagName +"</option>");
                })
                postTags = $("#post_tags").filterMultiSelect();
            });
        }
    });
    $('#question_submit').click(function(e){
        e.preventDefault();
        let data = {};
        let dataJson;
        let formData = $('#question_form').serializeArray();
        let tags = new Array();
        $.each(formData, function(key, value){
            let name = value.name + "";
            if(!name.includes("content_editor") && !name.includes("post_tags")) data[name] = value.value;
            if(name.includes("post_tags")){
                tags.push(value.value);
            }
        });
        if(pageType == "create"){
            data["listTagSlug"] = tags;
            data["content"] = ckeditor.getData();
            dataJson = JSON.stringify(data);
            createPostAPI(dataJson);
        } else{
            data["id"] = getUrlParameter('postid');
            data["content"] = ckeditor.getData();
            dataJson = JSON.stringify(data);
            updatePostAPI(dataJson);
        }
    });

    function createPostAPI(dataJson){
        $.ajax({
            url: "<c:url value='/api/post'/>",
            method: "POST",
            data: dataJson,
            dataType: "json",
            contentType: "application/json"
        }).done(function (result) {
            if(result.Message != null){
                alert("Tạo mới bài viết thất bại!");
                console.log(result.Message);
            } else {
                location.href = "<c:url value='/discussion'/>";
            }
        });

    }
    function updatePostAPI(dataJson){
        console.log(dataJson);
        $.ajax({
            url: "<c:url value='/api/post'/>",
            method: "PUT",
            data: dataJson,
            dataType: "json",
            contentType: "application/json"
        }).done(function (result) {
            if(result.Message != null){
                alert("Sửa bài viết thất bại!");
                console.log(result.Message);
            } else {
                location.href = "<c:url value='/discussion'/>";
            }
        });
    }
</script>
