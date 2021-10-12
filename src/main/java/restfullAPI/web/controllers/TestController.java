package restfullAPI.web.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restfullAPI.entities.*;
import restfullAPI.repositories.TaskRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/tasks")
public class TestController {

	@Autowired
	TaskRepository taskRepository;

	@GetMapping
	public List<Task> listTasks() {

		return taskRepository.findAll();

	}

	@PostMapping
	public Task save(@RequestBody Task t) {

		return taskRepository.save(t);
	}

}
