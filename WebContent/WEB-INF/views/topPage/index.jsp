<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <p>【論文一覧】（全 ${reports_count} 件）</p>
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th class="report_name">題名</th>
                    <th class="report_author">著者</th>
                    <th class="report_year">年</th>
                    <th class="report_magazine">収録刊行物</th>
                    <th class="report_department">診療科</th>
                    <th class="report_action">操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="report" items="${reports}" varStatus="status">
                    <tr class="thead-light">
                        <td class="report_name"><c:out value="${report.name}" />
                        <c:forEach var="label" items="${report.labelList}" varStatus="status">
                            <span class="badge badge-info"><c:out value="${label}" /></span>
                        </c:forEach>
                        </td>
                        <td class="report_author"><c:out value="${report.author}" /></td>
                        <td class="report_year"><c:out value="${report.year}" /></td>
                        <td class="report_magazine"><c:out value="${report.magazine}" /></td>
                        <td class="report_department"><c:out
                                value="${report.department}" /></td>
                        <td class="report_action"><a
                            href="<c:url value='/reports/show?id=${report.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${reports_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((reports_count - 1) / 15) + 1}"
                step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>