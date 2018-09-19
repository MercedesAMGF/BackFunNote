package com.back.funnote.users.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class TestController {

    private TestService testService;
    private SequenceService sequenceService;
    private TestRepository testRepository;

    public TestController(TestService testService, SequenceService sequenceService, TestRepository testRepository) {
        this.testService = testService;
        this.sequenceService = sequenceService;
        this.testRepository = testRepository;
    }

    @GetMapping("/test")
    public List<Test> getAllTests() {
        return this.testRepository.findAll();
    }

    @GetMapping(value="/test/{id}")
    public ResponseEntity<Test> getTestById(@PathVariable("id") String id){

        Test test = testRepository.findById(id).get();
        if(test == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(test,HttpStatus.OK);
        }
    }


    @PostMapping("/test")
    public Test addTest(@Valid @RequestBody Test test){
        int sequence = this.sequenceService.getNextSequence();
        return this.testService.addTest(test, sequence);
    }

    @PutMapping("/test")
    public ResponseEntity<Test> updateTest( @Valid @RequestBody Test test){

        Test updatedTest = this.testService.updateTest(test);
        return new ResponseEntity<>(updatedTest, HttpStatus.OK);
    }

    @DeleteMapping("/test/{id}")
    public void deleteTest(@PathVariable("id") String id) {
        this.testRepository.deleteById(id);
    }
}
