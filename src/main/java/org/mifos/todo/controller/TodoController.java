package org.mifos.todo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.mifos.todo.domain.Todo;
import org.mifos.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sachith Senarathne
 *
 */
@RestController
@RequestMapping("api/todo")
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;

	/**
	 * @param todoMap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> createTodo(@RequestBody Map<String, Object> todoMap) {

		Todo todo = new Todo(todoMap.get("title").toString(), todoMap.get("message").toString());
		todoRepository.save(todo);
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("msg", "Todo created successfully");
		response.put("todo", todo);

		return response;
	}

	/**
	 * @param todoid
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{todoid}")
	public Todo getTodo(@PathVariable("todoid") String todoid) {
		return todoRepository.findOne(todoid);
	}

	/**
	 * @param todoid
	 * @param todomap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{todoid}")
	public Map<String, Object> editTodo(@PathVariable("todoid") String todoid,@RequestBody Map<String, Object> todomap) {

		Todo todo = new Todo(todomap.get("title").toString(), todomap.get("message").toString());
		todo.setId(todoid);
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("msg", "Todo Updated successfully");
		response.put("todo", todoRepository.save(todo));
		return response;
	}

	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, Object> getAllTodos() {
		List<Todo> todos = todoRepository.findAll();
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("allTodos", todos.size());
		response.put("todos", todos);
		return response;
	}

}
