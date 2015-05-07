<%@page import="com.sds.icto.guestbook.vo.GuestBookVo"%>
<%@page import="com.sds.icto.guestbook.dao.GuestBookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//메시지 등록
request.setCharacterEncoding("utf-8");
//방명록을 등록합니다.
String name=request.getParameter("name");
String pw=request.getParameter("pass");
String text=request.getParameter("content");

GuestBookVo vo=new GuestBookVo();
vo.setName(name);
vo.setPw(pw);
vo.setText(text);

GuestBookDao dao=new GuestBookDao();
dao.insert(vo);
response.sendRedirect("");
%>

