<%@ page language="java" 
         contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
[
<c:forEach items="${list}" var="b">
  {
    "no":${b.no}, 
    "title":"${b.title}", 
    "writer":"${b.writer}", 
    "viewCount":${b.viewCount},
    "createdDate":"${b.createdDate}"
  },
</c:forEach>
]