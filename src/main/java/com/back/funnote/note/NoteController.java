package com.back.funnote.note;

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
@RequestMapping("/notes")
public class NoteController {

	private NoteRepository noteRepository;
	private NoteSequenceService noteSequenceService;

	public NoteController(NoteRepository noteRepository, NoteSequenceService noteSequenceService) {
		this.noteRepository = noteRepository;
		this.noteSequenceService = noteSequenceService;
	}

	@GetMapping("/{id}") public Note getNote(@PathVariable("id") String id) {
		if(this.noteRepository.findById(id).isPresent()){
			return this.noteRepository.findById(id).get();
		}
		else return null;
	}

	@GetMapping	public List<Note> getNotes() {
		return this.noteRepository.findAll();
	}

	@PostMapping public Note addNote(@RequestBody Note note) {
		return this.noteRepository.save(this.noteSequenceService.getNextSequence(note));
	}

	@PutMapping	public Note editNote(@RequestBody Note note) {
		return this.noteRepository.save(note);
	}

	@DeleteMapping("/{id}")	public Long deleteNote(@PathVariable("id") String id) {
		return this.noteRepository.deleteNoteById(id);
	}
}