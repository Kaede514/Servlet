package com.kaede.myssm.trans;

import com.kaede.myssm.basedao.JDBCUtils;

import java.sql.SQLException;

public class TransactionManager {

    //开启事务
    public static void beginTrans() throws SQLException {
        JDBCUtils.getConnection().setAutoCommit(false);
    }

    //提交事务
    public static void commit() throws SQLException {
        JDBCUtils.getConnection().commit();
        JDBCUtils.closeConnection();
    }

    //回滚事务
    public static void rollback() throws SQLException {
        JDBCUtils.getConnection().rollback();
        JDBCUtils.closeConnection();
    }

}
