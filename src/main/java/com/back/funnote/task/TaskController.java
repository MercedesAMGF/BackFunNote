package com.back.funnote.task;

import java.util.List;

import org.springframework.util.Assert;
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
@RequestMapping("/tasks")
public class TaskController {

	private TaskRepository taskRepository;

	public TaskController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@PostMapping
	public Task addTask(@RequestBody Task task) {
		return this.taskRepository.save(task);
	}

	@GetMapping
	public List<Task> getTasks() {
		return this.taskRepository.findAll();
	}

	@GetMapping("/{id}")
	public Task getTask(@PathVariable("id") String id) {
		return this.taskRepository.findById(id).get();
	}

	@PutMapping("/{id}")
	public void editTask(@PathVariable String id, @RequestBody Task task) {
		Task existingTask = taskRepository.findById(id).get();
		Assert.notNull(existingTask, "Task not found");
		existingTask.setDescription(task.getDescription());
		this.taskRepository.save(existingTask);
	}

	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable("id") String id) {
		this.taskRepository.deleteById(id);
	}
}