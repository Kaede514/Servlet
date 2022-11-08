package com.kaede.fruit.service;

import com.kaede.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author kaede
 * @create 2022-11-08
 */

public interface FruitService {

    //获取指定页码上的库存列表信息 , 每页显示5条
    List<Fruit> getFruitList(String keyword , Integer pageNo);

    //根据fid获取特定的水果库存信息
    Fruit getFruitByFid(Integer fid);

    //修改指定的库存记录
    void updateFruit(Fruit fruit);

    //根据fid删除指定的库存记录
    void delFruit(Integer fid);

    //添加新库存记录
    void addFruit(Fruit fruit);

    //获取总页数
    Integer getPageCount(String keyword);

}
