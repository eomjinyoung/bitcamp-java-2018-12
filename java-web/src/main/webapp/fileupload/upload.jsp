<%@page import="java.util.Collection"%>
<%@ page language="java" 
    contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%
request.setCharacterEncoding("UTF-8");
Collection<Part> parts = request.getParts();
int count = 0;
out.println("{");
for (Part part : parts) {
  if (count++ > 0) {
    out.print(",");
  }
  if (part.getContentType() == null) {
    out.println(String.format("\"%s\": \"%s\"",
        part.getName(),
        request.getParameter(part.getName())));
  } else {
    out.println(String.format("\"%s\": \"%s\"",
        part.getName(),
        part.getSubmittedFileName()));
  }
}
out.println("}");
%>