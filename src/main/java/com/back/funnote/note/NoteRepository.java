package com.back.funnote.note;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    Long deleteNoteById(String id);
    Note findNoteById(String id);
}
