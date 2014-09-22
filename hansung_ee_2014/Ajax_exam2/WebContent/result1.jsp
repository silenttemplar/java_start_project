<%@ page contentType="text/xml; charset=utf-8" %>
<%@page import="java.io.*" %>
<%@page import="Human.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%  
  Get_XML gx= new Get_XML();
  StringBuffer sb=gx.getXml();    
  response.setContentType("text/xml;charset=utf-8");
  PrintWriter pw= response.getWriter();
  pw.println(sb.toString());
  pw.close();  
%>
