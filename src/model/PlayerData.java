package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerData {
	Connection conn;
	Statement stmt;
	List<String> nameList;
	List<Integer> scoreList;
	String newPlayerName = "unknown";
	String newPlayerPass = "";
	public PlayerData() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:data/player.db");
			stmt = conn.createStatement();
			System.out.println("数据库连接成功");
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载失败");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		creatable();
		//add(100);
		//顺序调整后就不会有删除逻辑bug，可以打开就删除零分选手
		dele();
		getInfo();
	}

	private void dele() {
		String sql = "DELETE FROM players WHERE score = 0" ;
		try {
			stmt.execute(sql);
			System.out.println("数据成功删除");
		} catch (SQLException e) {
			System.out.println("数据删除失败");
			e.printStackTrace();
		}
	}

	public void getInfo() {
		String sql = "select name, score from players order by score desc limit 5" ;
		nameList = new ArrayList<String>();
		scoreList = new ArrayList<Integer>();
		try {
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				System.out.println(res.getString("name") + res.getInt("score"));
				nameList.add(res.getString("name"));
				scoreList.add(res.getInt("score"));
			}
			System.out.println("数据成功读取");
		} catch (SQLException e) {
			System.out.println("数据读取失败");
			e.printStackTrace();
		}
	}

	public void add(int score) {
		String sql = "insert into players (name, pass, score) values('" + newPlayerName + "', '" + newPlayerPass + "',"+ score +")" ;
		try {
			stmt.execute(sql);
			System.out.println("数据成功插入");
		} catch (SQLException e) { 
			System.out.println("数据插入失败");
			e.printStackTrace();
		}
		
	}

	private void creatable() {
		String sql = "create table players(id INTEGER PRIMARY KEY AUTOINCREMENT, name CHAR(30) NOT NULL," +
									"pass CHAR(30) NOT NULL, score INT(8) NOT NULL)" ;
		try {
			stmt.execute(sql);
			System.out.println("数据表成功创建");
		} catch (SQLException e) {
			System.out.println("数据库已经存在");
		}
		
	}
	
	public List<String> getName(){
		return nameList;
	}
	
	public List<Integer> getScore(){
		return scoreList;
	}
	
	public boolean Login(String name, String pass) {
		String sql = "select pass from players where name = '" + name +"'";
		try {
			ResultSet res = stmt.executeQuery(sql);
			if (res.next()) {
				if (!pass.equals(res.getString("pass"))) {
					return false;
				}
			}
			newPlayerName = name;
			newPlayerPass = pass;
			System.out.println("登录成功");
		} catch (SQLException e) {
			System.out.println("登录失败");
			e.printStackTrace();
		}
		return true;
	}

	 
}
