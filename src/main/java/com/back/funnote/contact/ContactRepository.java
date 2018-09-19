package com.back.funnote.contact;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    Long deleteContactById(String id);
    Contact findContactById(String id);
}
