package com.back.funnote.note;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class NoteSequenceService {

	private MongoOperations mongoOperations;

	public NoteSequenceService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	Note getNextSequence(Note note) {
		NoteSequence noteSequence = mongoOperations.findAndModify(
				query(Criteria.where("id").is("sequenceNote")),
				new Update().inc("seq", 1),
				options().returnNew(true).upsert(true), NoteSequence.class);
		note.setSequence(noteSequence.getSeq());
		return note;
	}
}
