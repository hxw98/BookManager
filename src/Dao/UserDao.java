package Dao;

import utils.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private UserBean user;
    private PreparedStatement pstmt2; //预编译语句对象
    
    public UserBean findUser(String name,String Pwd){
    	//?为占位符
    	String sql = "select * from user where username = ? AND userpsw = ?;";
    	pstmt2 = DBUtil.getPstmt(sql);
    	try {
			pstmt2.setString(1, name); //1是第一个问号
			pstmt2.setString(2, Pwd);
			ResultSet rs = pstmt2.executeQuery(sql); //执行查询语句
			if (rs.next()) {
				user = new UserBean();
				user.setUsername(name);
				user.setPwd(Pwd);
			}else {
				user = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
    }
}
