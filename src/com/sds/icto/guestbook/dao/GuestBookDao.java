package com.sds.icto.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.sds.icto.guestbook.vo.GuestBookVo;

public class GuestBookDao {
	private Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection conn = null;
		// 1. 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2. connection 생성
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(dbURL, "webdb", "webdb");

		return conn;
	}

	public void insert(GuestBookVo vo) throws ClassNotFoundException,
			SQLException {
		// 1. Connection 생성
		Connection conn = getConnection();
		// 2. Statement 준비, SQL 문 날리기
		String sql = "insert into GUESTBOOK"
				+ " values(guestbook_seq.nextval, ?, ?, ?, sysdate)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 3. Binding
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPw());
		pstmt.setString(3, vo.getText());
		// 4. query 실행
		pstmt.executeUpdate();
		// 5. 자원정리
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public void delete(GuestBookVo vo) throws ClassNotFoundException,
			SQLException {
		// 1. Connection 생성
		Connection conn = getConnection();
		// 2. Statement 준비, SQL 날리기
		String sql = "delete from guestbook" + " where no=? and password=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 3. Binding
		pstmt.setLong(1, vo.getNo());
		pstmt.setString(2, vo.getPw());
		// 4. query 실행
		pstmt.executeUpdate();
		// 5. 자원정리
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public List<GuestBookVo> fetchList() throws SQLException, ClassNotFoundException {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		// 1. Connection 생성
		Connection conn = getConnection();
		// 2. Statement 준비
		Statement stmt = conn.createStatement();
		// 3. SQL 문 작성
		String sql = "select * from guestbook";
		// 4. 
		ResultSet rs = stmt.executeQuery(sql);
		// 5. 결과 출력
		while(rs.next()){
			Long no=rs.getLong(1);
			String name=rs.getString(2);
			String pw=rs.getString(3);
			String text=rs.getString(4);
			String date=rs.getString(5);
			
			GuestBookVo vo=new GuestBookVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setPw(pw);
			vo.setText(text);
			vo.setDate(date);
			
			list.add(vo);
		}
		// 6. 자원정리
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
		
		return list;
	}
}
