package com.example.calenderDynamoDB.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.calenderDynamoDB.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class StandardRepository<T> implements IRepository<T>{

    @Autowired
    private DynamoDBMapper dynamoDBMapper;
    final Class<T> typeParameterClass;
    final String className;

    protected StandardRepository(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
        className= typeParameterClass.getName().toLowerCase(Locale.ROOT);
    }

    @Override
    public T save(T object) {
        dynamoDBMapper.save(object);
        return object;
    }

    @Override
    public T update(T object) {
        dynamoDBMapper.save(object);
        return object;
    }

    @Override
    public List<T> findAll() {
        return dynamoDBMapper.scan(typeParameterClass,new DynamoDBScanExpression());
    }

    @Override
    public List<T> findByUserIdAndObjectId(String userId, String id) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(userId));
        eav.put(":val2", new AttributeValue().withS(id));
        return dynamoDBMapper.query(typeParameterClass, new DynamoDBQueryExpression<T>().withKeyConditionExpression("user_id = :val1 and "+className+"_id = :val2").withExpressionAttributeValues(eav));
    }

    @Override
    public List<T> findAllByUserId(String userId){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(userId));
        return dynamoDBMapper.query(typeParameterClass, new DynamoDBQueryExpression<T>().withKeyConditionExpression("user_id = :val1").withExpressionAttributeValues(eav));
    }

    @Override
    public String delete(String userId,String id) {
        Todo object = new Todo();
        object.setUser_id(userId);
        object.setTodo_id(id);
        dynamoDBMapper.delete(object);
        return "Deleted";
    }

    @Override
    public T findById(String id) {
        return dynamoDBMapper.load(typeParameterClass, id);
    }

    @Override
    public List<T> findByUserIdAndDate(String userId, String date) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(userId));
        eav.put(":val2", new AttributeValue().withS(date));
        return dynamoDBMapper.query(typeParameterClass, new DynamoDBQueryExpression<T>().withKeyConditionExpression("user_id = :val1 and begins_with ("+className+", :val2)").withExpressionAttributeValues(eav));
    }
}
