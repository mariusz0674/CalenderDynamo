package com.example.calenderDynamoDB.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.calenderDynamoDB.entity.Note;
import com.example.calenderDynamoDB.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DynamoDBTable(tableName = "todo_table")
@Repository
public class TodoRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;
    public Todo saveTodo(Todo todo){
        dynamoDBMapper.save(todo);
        return todo;
    }
    public Todo findByIdsTodo(String userId){
        return dynamoDBMapper.load(Todo.class, userId);
    }
    public List<Todo> findAllByUserId(String userId){
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(userId));
        return dynamoDBMapper.query(Todo.class, new DynamoDBQueryExpression<Todo>().withKeyConditionExpression("user_id = :val1").withExpressionAttributeValues(eav));
    }
    public List<Todo> findByUserIdTodoIdTodo(String userId, String todoId){
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(userId));
        eav.put(":val2", new AttributeValue().withS(todoId));
        return dynamoDBMapper.query(Todo.class, new DynamoDBQueryExpression<Todo>().withKeyConditionExpression("user_id = :val1 and todo_id = :val2").withExpressionAttributeValues(eav));
    }
    public Todo updateTodo(Todo todo) {
        dynamoDBMapper.save(todo);
        return todo;
    }

    public String deleteTodo(String userId, String todoId) {
        Todo object = new Todo();
        object.setUser_id(userId);
        object.setTodo_id(todoId);
        dynamoDBMapper.delete(object);
        return "Deleted";
    }


    public List<Todo> finfByUserDate(String userId, String date) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(userId));
        eav.put(":val2", new AttributeValue().withS(date));
        return dynamoDBMapper.query(Todo.class, new DynamoDBQueryExpression<Todo>().withKeyConditionExpression("user_id = :val1 and begins_with (todo_id, :val2)").withExpressionAttributeValues(eav));
    }
}
