<%@page import="com.sds.icto.guestbook.vo.GuestBookVo"%>
<%@page import="com.sds.icto.guestbook.dao.GuestBookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
//방명록 삭제
Long no=Long.parseLong(request.getParameter("no"));
String pw=request.getParameter("password");

GuestBookVo vo=new GuestBookVo();
vo.setNo(no);
vo.setPw(pw);

GuestBookDao dao=new GuestBookDao();
dao.delete(vo);

response.sendRedirect("/guestbook");

%>
