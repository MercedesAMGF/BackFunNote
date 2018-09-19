package com.back.funnote.company;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class CompanySequenceService {

	private MongoOperations mongoOperations;

	public CompanySequenceService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	int getNextSequence() {
		CompanySequence companySequence = mongoOperations.findAndModify(
				query(Criteria.where("id").is("sequenceCompany")), new Update().inc("seq", 1),
				options().returnNew(true).upsert(true), CompanySequence.class);
		return companySequence.getSeq();
	}
}
