<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<html>
<title>test</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<head>
    <style type="text/css">
        .upload {
            margin-top: 100px;
            margin-left: 100px;
            text-align: center;
        }

    </style>

</head>
<body>
    <h1 style="text-align: center;margin-top: 20px">test</h1>
    <div>
        <form class="upload" action="${path}/excel/fileUploadFiles" method="post" enctype="multipart/form-data">
            <p>
                选择文件:<input type="file" name="multipartFile"/>
                <input type="file" name="multipartFile"/>
                <input type="file" name="multipartFile"/>
            </p>
            <p></p>
            <p style="margin-top: 20px;">
                <input style="" type="submit" value="上传并检测"/> 
            </p>
        </form>
    </div>
</body>
</html>