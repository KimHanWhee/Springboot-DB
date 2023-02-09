package com.start.springBoot.repository;

import com.start.springBoot.dto.TestDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestRepository1 {

    @Autowired
    SqlSessionTemplate sessionTemplate;

    public List<TestDTO> findAll(){
        return sessionTemplate.selectList("test.findAll");
    }

    public TestDTO findOneById(Integer id){
        return sessionTemplate.selectOne("test.findOneById", id);
    }

    public Integer insertTest(TestDTO dto){
        return sessionTemplate.insert("test.insertTest", dto);
    }

    public Integer updateTest(TestDTO dto)
    {
        return sessionTemplate.update("test.updateTest", dto);
    }

    public Integer deleteTest(Integer id){
        return sessionTemplate.delete("test.deleteTest", id);
    }
}
