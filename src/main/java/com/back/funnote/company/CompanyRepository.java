package com.back.funnote.company;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
    Long deleteCompanyById(String id);
    Company findBySequence(int sequence);
}
