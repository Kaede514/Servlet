package com.kaede.fruit.dao.impl;

import com.kaede.fruit.dao.FruitDAO;
import com.kaede.fruit.pojo.Fruit;
import com.kaede.myssm.basedao.BaseDAO;
import com.kaede.myssm.basedao.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList(String keyword , Integer pageNo) {
        Connection conn = null;
        List<Fruit> list = null;
        try {
            conn = JDBCUtils.getConnection();
            list = super.getForList(conn, "select * from t_fruit where fname like ? or remark like ? limit ? , 5" ,"%" +
                keyword + "%", "%" + keyword + "%", (pageNo-1)*5);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn);
        }
        return list;
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        Connection conn = null;
        Fruit fruit = null;
        try {
            conn = JDBCUtils.getConnection();
            fruit = super.getInstance(conn, "select * from t_fruit where fid = ? ", fid);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn);
        }
        return fruit;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "update t_fruit set fname = ? , price = ? , fcount = ? , remark = ? where fid = ? " ;
            super.update(conn, sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn);
        }
    }

    @Override
    public void delFruit(Integer fid) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            super.update(conn,"delete from t_fruit where fid = ? " , fid) ;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn);
        }
    }

    @Override
    public void addFruit(Fruit fruit) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "insert into t_fruit values(0,?,?,?,?)";
            super.update(conn, sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn);
        }
    }

    @Override
    public int getFruitCount(String keyword) {
        Connection conn = null;
        int count = 0;
        try {
            conn = JDBCUtils.getConnection();
            count = ((Long) super.getValue(conn,"select count(*) from t_fruit where fname like ? or remark like ?",
                "%" + keyword + "%", "%" + keyword + "%")).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn);
        }
        return count;
    }
}
