package com.example.question_rank;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
//android studio疑似不支持mysql 8.0，连接数据库未报错但不显示数据，已加网络权限
public class DBUtils {
    private static String driver = "com.mysql.jdbc.Driver";
    //private static String url = "jdbc:mysql://localhost:3306/test";
    private static String user = "root";
    private static String password = "root1234";

    private static Connection getConnection(String dbName){
        Connection conn = null;
        try{
            Class.forName(driver);//动态加载类
            String ip = "192.168.43.240";//本机地址，代替localhost
            conn = DriverManager.getConnection("jdbc:mysql://" + "ip" + ":3306/" + dbName, user, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static HashMap<String, Object> getInfoById(int id){
        HashMap<String, Object> map = new HashMap<>();
        Connection conn = getConnection("demo");//根据数据库名称建立连接

        try{
            String sql = "select * from Common_sense_question_bank where id = ?";
            //已建立连接
            if(conn != null){
                PreparedStatement ps = conn.prepareStatement(sql);//PreparedStatement可以传入带占位符的SQL语句
                if(ps != null){
                    ps.setInt(1, id);//保存一个整数值
                    //执行sql语句并返回结果集
                    ResultSet rs = ps.executeQuery();
                    if(rs != null){
                        int count = rs.getMetaData().getColumnCount();
                        Log.e("DBUtils","列总数" + count);
                        while(rs.next()){
                            //下标从1开始
                            for(int i = 1; i <= count; ++i){
                                String field = rs.getMetaData().getCatalogName(i);
                                map.put(field, rs.getString(field));
                            }
                        }
                        conn.close();
                        ps.close();
                        return map;
                    }
                    else {
                        return null;
                    }
                }
                else{
                    return null;
                }
            }
            else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
