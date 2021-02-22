<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report != null}">
                <h2>詳細ページ</h2>

                <table class="table table-striped">
                    <tbody>
                        <tr>
                            <th>管理番号</th>
                            <td><c:out value="${report.id}" /></td>
                        </tr>
                        <tr>
                            <th>題名</th>
                            <td><c:out value="${report.name}" /></td>
                        </tr>
                        <tr>
                            <th>ラベル</th>
                            <td><c:forEach var="label" items="${report.labelList}" varStatus="status">
                                <span class="badge badge-info"><c:out value="${label}" /></span>
                            </c:forEach></td>
                        </tr>
                        <tr>
                            <th>著者</th>
                            <td><c:out value="${report.author}" /></td>
                        </tr>
                        <tr>
                            <th>年</th>
                            <td><c:out value="${report.year}" /></td>
                        </tr>
                        <tr>
                            <th>収録刊行物</th>
                            <td><c:out value="${report.magazine}" /></td>
                        </tr>
                        <tr>
                            <th>診療科</th>
                            <td><c:out value="${report.department}" /></td>
                        </tr>
                        <tr>
                            <th>ファイル</th>
                            <td>
                                <a href="<c:url value='/reports/download?id=${report.id}' />"><c:out value="${report.filename}"/></a>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${report.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${report.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/reports/edit?id=${report.id}' />">この論文情報を編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
        <hr/>
        <c:out value="${report.searchdata}" />
        <hr/>
        <p><a href="<c:url value='/reports/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>