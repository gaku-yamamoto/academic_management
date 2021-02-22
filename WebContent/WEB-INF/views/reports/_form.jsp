<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<div class="form-group row">
    <label for="text3a" class="col-sm-2 col-form-label">題名：</label>
    <div class="col-sm-10">
        <input name="name" class="form-control" type="text" placeholder="題名" value="${report.name}" required/>
    </div>
</div>
<div class="form-group row">
    <label for="text3a" class="col-sm-2 col-form-label">著者：</label>
    <div class="col-sm-10">
        <input name="author" class="form-control" type="text" placeholder="著者" value="${report.author}" required/>
    </div>
</div>
<div class="form-group row">
    <label for="text3a" class="col-sm-2 col-form-label">年：</label>
    <div class="col-sm-10">
        <input name="year" class="form-control" type="text" placeholder="年" value="${report.year}" required/>
    </div>
</div>
<div class="form-group row">
    <label for="text3a" class="col-sm-2 col-form-label">収録刊行物：</label>
    <div class="col-sm-10">
        <input name="magazine" class="form-control" type="text" placeholder="収録刊行物" value="${report.magazine}" required/>
    </div>
</div>
<div class="form-group row">
    <label for="text3a" class="col-sm-2 col-form-label">診療科：</label>
    <div class="col-sm-10">
        <select name="department" id="custom-select-1b" class="custom-select">
         <option value="消化器"<c:if test="${report.department == 0}"> selected</c:if>>消化器</option>
            <option value="循環器"<c:if test="${report.department == 1}"> selected</c:if>>循環器</option>
            <option value="代謝・内分泌"<c:if test="${report.department == 2}"> selected</c:if>>代謝・内分泌</option>
            <option value="呼吸器"<c:if test="${report.department == 3}"> selected</c:if>>呼吸器</option>
            <option value="血液"<c:if test="${report.department == 4}"> selected</c:if>>血液</option>
            <option value="免疫・膠原病・感染症"<c:if test="${report.department == 5}"> selected</c:if>>免疫・膠原病・感染症</option>
            <option value="脳・神経"<c:if test="${report.department == 6}"> selected</c:if>>脳・神経</option>
            <option value="腎・泌尿器"<c:if test="${report.department == 7}"> selected</c:if>>腎・泌尿器</option>
            <option value="婦人科・乳腺外科"<c:if test="${report.department == 8}"> selected</c:if>>婦人科・乳腺外科</option>
            <option value="産科"<c:if test="${report.department == 9}"> selected</c:if>>産科</option>
            <option value="運動器・整形外科"<c:if test="${report.department == 10}"> selected</c:if>>運動器・整形外科</option>
            <option value="眼科"<c:if test="${report.department == 11}"> selected</c:if>>眼科</option>
            <option value="耳鼻咽喉科"<c:if test="${report.department == 12}"> selected</c:if>>耳鼻咽喉科</option>
            <option value="皮膚科"<c:if test="${report.department == 13}"> selected</c:if>>皮膚科</option>
            <option value="東洋医学"<c:if test="${report.department == 14}"> selected</c:if>>東洋医学</option>
        </select>
    </div>
</div>
<div class="form-group row">
    <label for="text3a" class="col-sm-2 col-form-label">ファイル：</label>
    <div class="col-sm-10">
        <div class="custom-file">
            <input type="file" name="avatar" class="custom-file-input">
            <label class="custom-file-label" for="custom-file-1">論文ファイル...</label>
        </div>
    </div>
</div>