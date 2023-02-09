package com.start.springBoot.service;

import com.start.springBoot.dto.TestDTO;
import com.start.springBoot.repository.TestRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    TestRepository1 repository1;
    List<TestDTO> db = new ArrayList<>();

    public List<TestDTO> getAllTest(){
        return repository1.findAll();
    }

    public TestDTO findOneById(Integer id){
        return repository1.findOneById(id);
    }

    public TestDTO insertTest(TestDTO dto){
        repository1.insertTest(dto);
        return dto;
    }

    public TestDTO updateTest(Integer id, TestDTO dto){
        dto.setId(id);
        try {
            System.out.println(dto.toString());
            repository1.updateTest(dto);
        }catch (IndexOutOfBoundsException e){
            return null;
        }
        return dto;
    }

    public Boolean deleteTest(Integer id) {
        try {
            repository1.deleteTest(id);
        }catch (IndexOutOfBoundsException e){
            return false;
        }
        return true;
    }

    public boolean initData() {
        for(int i = 1; i <= 5; i++){
            TestDTO dto = new TestDTO(null, "park", 10 * i);
            repository1.insertTest(dto);
        }
        return true;
    }

}
