package com.back.funnote.users.test;

//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
//import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class TestService {

    private TestRepository testRepository;
//    private MongoOperations mongoOperations;

    public TestService(TestRepository testRepository/*, MongoOperations mongoOperations*/) {
        this.testRepository = testRepository;
//        this.mongoOperations = mongoOperations;
    }

    /*
       List<ApplicationUser> getAllUsers(){
           Sort listUserSorted = new Sort(Sort.Direction.DESC,"lasName");
            return this.userRepository.findAll(listUserSorted);
        }
    */

    Test addTest(Test test, int sequence)
    {
        //test.setSequence(getNextSequence("sequence"));
        test.setSequence(sequence);
        return this.testRepository.save(test);
    }

    Test updateTest(Test test)
    {
       Test testToUpdate = this.testRepository.findById(test.getId()).get();

        testToUpdate.setUserName(test.getUserName());

       return this.testRepository.save(testToUpdate);
    }

/*
    public int getNextSequence(String sequenceName)
    {
        CustomSequences counter = mongoOperations.findAndModify(
                query(Criteria.where("id").is(sequenceName)),
                new Update().inc("seq",1),
                options().returnNew(true).upsert(true),
                CustomSequences.class);

        return counter.getSeq();
    }
*/
}
