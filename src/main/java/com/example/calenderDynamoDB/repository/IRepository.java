package com.example.calenderDynamoDB.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.calenderDynamoDB.entity.Note;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IRepository<T> {

    T save(T object);
    T update(T object);
    List<T> findAllByUserId(String userId);
    List<T> findAll();
    List<T> findByUserIdAndObjectId(String userId, String id);
    String  delete(String userId, String id);
    T findById(String id);
    List<T> findByUserIdAndDate(String userId, String date);

}
