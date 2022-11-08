package com.kaede.fruit.dao.impl;

import com.kaede.fruit.dao.FruitDAO;
import com.kaede.fruit.pojo.Fruit;
import com.kaede.myssm.basedao.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList(String keyword , Integer pageNo) {
        return super.getForList("select * from t_fruit where fname like ? or remark like ? limit ? , 5" ,"%" +
            keyword + "%", "%" + keyword + "%", (pageNo-1)*5);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return super.getInstance("select * from t_fruit where fid = ? ", fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname = ? , price = ? , fcount = ? , remark = ? where fid = ? " ;
        super.update(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
    }

    @Override
    public void delFruit(Integer fid) {
        super.update("delete from t_fruit where fid = ? " , fid) ;
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        super.update(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());
    }

    @Override
    public int getFruitCount(String keyword) {
        return ((Long) super.getValue("select count(*) from t_fruit where fname like ? or remark like ?",
            "%" + keyword + "%", "%" + keyword + "%")).intValue();
    }
}
