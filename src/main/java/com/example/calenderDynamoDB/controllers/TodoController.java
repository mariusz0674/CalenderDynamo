package com.example.calenderDynamoDB.controllers;

import com.example.calenderDynamoDB.entity.Todo;
import com.example.calenderDynamoDB.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todoos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public List<Todo> findAllTodos(){
        return todoRepository.findByUserIdAndObjectId("1", "1");
    }

    @PostMapping
    public Todo save(@RequestBody Todo todo){
        return todoRepository.save(todo);
    }

    @PutMapping
    public Todo update(@RequestBody Todo todo){
        return todoRepository.update(todo);
    }

    @DeleteMapping(value = {"/{user_id}/{todo_id}"})
    public String delete(@PathVariable(value = "user_id") String user_id, @PathVariable(value = "todo_id") String todo_id){
        return todoRepository.delete( user_id,  todo_id);
    }

    @GetMapping(value = "/user:{user_id}/todo:{todo_id}")
    public List<Todo> findUserTodo(@PathVariable(value = "user_id") String user_id, @PathVariable(value = "todo_id") String todo_id){
        return todoRepository.findByUserIdAndObjectId( user_id,  todo_id);
    }

    @GetMapping(value = "/user:{user_id}/date:{date}")
    public List<Todo> finfByUserDate(@PathVariable(value = "user_id") String userId, @PathVariable(value = "date") String date){
        return todoRepository.findByUserIdAndDate( userId,  date);
    }

    @GetMapping(value = "/user:{user_id}")
    public List<Todo> findByUserAll(@PathVariable(value = "user_id") String userId){
        return todoRepository.findAllByUserId(userId);
    }
}
