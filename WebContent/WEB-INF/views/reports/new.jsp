<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>論文 新規登録ページ</h2>

        <form method="POST" action="<c:url value='/reports/create' />" enctype="multipart/form-data">
            <c:import url="_form.jsp" />
            <input type="hidden" name="_token" value="${_token}" />
            <div class="col-auto">
                <button type="submit" class="btn btn-primary mb-2">登録</button>
            </div>
        </form>

        <p>
            <a href="<c:url value='/' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>