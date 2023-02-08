package com.start.springBoot.controller;


import com.start.springBoot.dto.TestDTO;
import com.start.springBoot.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @Autowired
    private final TestService testService;

    public TestController(TestService testService){
        this.testService = testService;
    }

    @GetMapping
    public List<TestDTO> getTest(){
        return testService.getAllTest();
    }
    @GetMapping(value = "/{id}")
    public TestDTO findById(@PathVariable("id") Integer id){
        return testService.findOneById(id);
    }

    //    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity postTest(@RequestBody TestDTO dto){
        TestDTO insertDto = testService.insertTest(dto);
        return new ResponseEntity<>(insertDto, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<TestDTO> updateData(
            @PathVariable("id") Integer id,
            @RequestBody TestDTO dto
    ){
        ResponseEntity<TestDTO> responseEntity;
        TestDTO afterData = testService.updateTest(id, dto);
        if(afterData==null){
            responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            responseEntity = new ResponseEntity<>(afterData, HttpStatus.OK);
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteData(@PathVariable("id") Integer id){
        ResponseEntity responseEntity;
        if(testService.deleteTest(id))
            responseEntity = new ResponseEntity<>(HttpStatus.ACCEPTED);
        else
          responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return responseEntity;
    }
    @PostMapping(value = "/init")
    public boolean initTestDate(){
        return testService.initData();
    }
}
