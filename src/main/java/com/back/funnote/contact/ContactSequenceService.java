package com.back.funnote.contact;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ContactSequenceService {

	private MongoOperations mongoOperations;

	public ContactSequenceService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	Contact getNextSequence(Contact contact) {
		ContactSequence contactSequence = mongoOperations.findAndModify(
				query(Criteria.where("id").is("sequenceContact")), new Update().inc("seq", 1),
				options().returnNew(true).upsert(true), ContactSequence.class);
		assert contactSequence != null;
		contact.setSequence(contactSequence.getSeq());
		return contact;
	}
}
