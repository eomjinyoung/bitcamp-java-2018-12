<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" 
    contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%
request.setCharacterEncoding("UTF-8");
Collection<Part> parts = request.getParts();
int count = 0;
ArrayList<Part> files = new ArrayList<>();

out.println("{");

for (Part part : parts) {
  if (part.getContentType() == null) {
    out.println(String.format("  %s\"%s\": \"%s\"",
        (count++ > 0 ? "," : ""),
        part.getName(),
        request.getParameter(part.getName())));
  } else {
    files.add(part);
  }
}

if (count++ > 0) {
  out.print("  ,");
}

out.println("\"files\": [");
int fileCount = 0;

for (Part part : files) {
  out.println(String.format("    %s{\"filename\": \"%s\", \"filesize\": \"%d\"}",
      (fileCount++ > 0 ? "," : ""),
      part.getSubmittedFileName(),
      part.getSize()));
}
out.println("  ]");
out.println("}");
%>











