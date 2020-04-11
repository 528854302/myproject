package com.a528854302.service;

import com.a528854302.entity.TbUser;
import com.a528854302.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class TbUserService implements BaseService<TbUser> {
    @Autowired
    TbUserMapper mapper;

    @Override
    public List<TbUser> selectAll() {
        return mapper.selectList(null);
    }

    @Override
    public TbUser selectById(String id) {
        return mapper.selectById(id);
    }

    @Override
    public boolean update(TbUser tbUser) {
        return mapper.updateById(tbUser)==1;
    }

    @Override
    public boolean deleteById(String id) {
        return mapper.deleteById(id)==1;
    }

    @Override
    public boolean insert(TbUser tbUser) {
        return mapper.insert(tbUser)==1;
    }

    public List<TbUser> selectListByMap(Map map){
        return  mapper.selectByMap(map);
    }

}
