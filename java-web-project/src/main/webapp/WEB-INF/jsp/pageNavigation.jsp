<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${search != null}">
  <c:set var="searchKeyword">&search=${search}</c:set>
</c:if>
<nav aria-label="목록 페이지 이동">
  <ul class="pagination justify-content-center">
    <li class="page-item ${pageNo <= 1 ? 'disabled' : ''}">
      <a class="page-link" 
        href="?pageNo=${pageNo - 1}&pageSize=${pageSize}${searchKeyword}">이전</a></li>
    <li class="page-item active"><span class="page-link">${pageNo}</span></li>
    <li class="page-item ${pageNo >= totalPage ? 'disabled' : ''}">
      <a class="page-link" 
         href="?pageNo=${pageNo + 1}&pageSize=${pageSize}${searchKeyword}">다음</a></li>
  </ul>
</nav>