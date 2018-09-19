package com.back.funnote.contact;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class ContactController {

	private ContactRepository contactRepository;
    private ContactSequenceService contactSequenceService;

	public ContactController(ContactRepository contactRepository, ContactSequenceService contactSequenceService) {
		this.contactRepository = contactRepository;
		this.contactSequenceService = contactSequenceService;
	}

	@GetMapping("/{id}") public Contact getContact(@PathVariable("id") String id) {
		return this.contactRepository.findById(id).get();
	}

	@GetMapping public List<Contact> getContacts() {
		return this.contactRepository.findAll();
	}

    @PostMapping public Contact addContact(@RequestBody Contact contact) {
        return this.contactRepository.save(this.contactSequenceService.getNextSequence(contact));
    }

	@PutMapping public Contact editContact(@RequestBody Contact contact) {
		return this.contactRepository.save(contact);
	}

	@DeleteMapping("/{id}")	public Long deleteContact(@PathVariable("id") String id) {
		return this.contactRepository.deleteContactById(id);
	}

}