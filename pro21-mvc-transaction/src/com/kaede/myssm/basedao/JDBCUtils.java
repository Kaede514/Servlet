package com.kaede.myssm.basedao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 操作数据库的工具类
 */

public class JDBCUtils {

    /**
     * 使用Druid数据库连接池技术
     */
    private static DataSource dataSource;

    static {
        InputStream is = null;
        try {
            Properties pros = new Properties();
            is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            System.out.println(is);
            pros.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(pros);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection getConnection() {
        Connection conn = threadLocal.get();
        if(conn == null) {
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                throw new DAOException("DAO Exception in JDBCUtils.getConnection...");
            }
            threadLocal.set(conn);
        }
        return conn;
    }

    public static void closeConnection() {
        Connection conn = threadLocal.get();
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DAOException("DAO Exception in JDBCUtils.closeConnection...");
            }
            threadLocal.remove();
        }
    }

    public static void closeResource(PreparedStatement ps) {
        try {
            ps.close();
        } catch (SQLException e) {
            throw new DAOException("DAO Exception in JDBCUtils.closeResource...");
        }
    }

    public static void closeResource(PreparedStatement ps, ResultSet rs) {
        try {
            ps.close();
            rs.close();
        } catch (SQLException e) {
            throw new DAOException("DAO Exception in JDBCUtils.closeResource...");
        }
    }

}
