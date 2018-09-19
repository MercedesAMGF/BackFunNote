package com.back.funnote.users.test;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceService {

    private MongoOperations mongoOperations;

    public SequenceService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    int getNextSequence()
    {
        CustomSequences counter =  mongoOperations.findAndModify(
                            query(Criteria.where("id").is("sequenceName")),
                            new Update().inc("seq",1),
                            options().returnNew(true).upsert(true),
                            CustomSequences.class);
        return counter.getSeq();
    }
}
