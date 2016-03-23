package org.mifos.todo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.mifos.todo.domain.Todo;
import org.mifos.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

/**
 * @author Sachith Senarathne
 *         <p>
 *         This will act as main controller class of the application
 *         </p>
 *
 */
@RestController
@RequestMapping("api/todo")
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;

	/**
	 * @param todoMap
	 * @return response map with todo object and message
	 */
	
	@ApiOperation(value = "editTodo", nickname = "createTodo")
	@RequestMapping(method = RequestMethod.POST,produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Todo.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure")})
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
	 * @return relevant todo object to id
	 */
	@ApiOperation(value = "getTodo", nickname = "getTodo")
	@RequestMapping(method = RequestMethod.GET, value = "/{todoid}", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "todoid", value = "ID of the Todo task", required = true, dataType = "string", paramType = "path", defaultValue = "") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Todo.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public Todo getTodo(@PathVariable("todoid") String todoid) {
		return todoRepository.findOne(todoid);
	}

	/**
	 * @param todoid
	 * @param todomap
	 * @return response map with updated todo and message for user
	 */
	@ApiOperation(value = "editTodo", nickname = "editTodo")
	@RequestMapping(method = RequestMethod.PUT, value = "/{todoid}" , produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "todoid", value = "ID of the Todo task for updating", required = true, dataType = "string", paramType = "path", defaultValue = ""),
			@ApiImplicitParam(name = "todomap", value = "Request body as json", required = true, dataType = "json", paramType = "application/json", defaultValue = "")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Todo.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure")})
	public Map<String, Object> editTodo(@PathVariable("todoid") String todoid,@RequestBody Map<String, Object> todomap) {

		Todo todo = new Todo(todomap.get("title").toString(), todomap.get("message").toString());
		todo.setId(todoid);
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("msg", "Todo Updated successfully");
		response.put("todo", todoRepository.save(todo));
		return response;
	}

	/**
	 * @return response map with size of the list and list of todo objects
	 */
	@ApiOperation(value = "getAllTodos", nickname = "getAllTodos" , produces = "application/json")
	@RequestMapping(method = RequestMethod.GET)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Todo.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public Map<String, Object> getAllTodos() {
		List<Todo> todos = todoRepository.findAll();
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("allTodos", todos.size());
		response.put("todos", todos);
		return response;
	}

}
