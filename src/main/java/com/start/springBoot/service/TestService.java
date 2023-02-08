package com.start.springBoot.service;

import com.start.springBoot.dto.TestDTO;
import com.start.springBoot.repository.TestRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        Optional<TestDTO> findOneById = db.stream().filter(dto -> Objects.equals(dto.getId(), id)).findFirst();
        return findOneById.orElse(null);
    }

    public TestDTO insertTest(TestDTO dto){
        int id = db.size() + 1;
        dto.setId(id);
        db.add(dto);
        return dto;
    }

    public TestDTO updateTest(Integer id, TestDTO dto){
        dto.setId(id);
        try {
            db.set(id - 1, dto);
        }catch (IndexOutOfBoundsException e){
            return null;
        }
        return dto;
    }

    public Boolean deleteTest(Integer id) {
        try {
            db.remove(id - 1);
        }catch (IndexOutOfBoundsException e){
            return false;
        }
        return true;
    }

    public boolean initData() {
        Integer size = db.size();
        for(int i = size + 1; i <= size + 5; i++){
            TestDTO dto = new TestDTO(i, "park", 10 * i);
            db.add(dto);
        }
        return true;
    }

}
