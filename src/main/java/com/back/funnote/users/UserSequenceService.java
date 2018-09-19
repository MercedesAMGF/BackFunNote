package com.back.funnote.users;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class UserSequenceService {

    private MongoOperations mongoOperations;

    public UserSequenceService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    int getNextSequence()
    {
        UserSequence counter =  mongoOperations.findAndModify(
                            query(Criteria.where("id").is("sequenceName")),
                            new Update().inc("seq",1),
                            options().returnNew(true).upsert(true),
                            UserSequence.class);
        return counter.getSeq();
    }
}
