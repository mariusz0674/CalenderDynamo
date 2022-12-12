package com.example.calenderDynamoDB.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.calenderDynamoDB.entity.Event;
import com.example.calenderDynamoDB.entity.Note;
import com.example.calenderDynamoDB.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DynamoDBTable(tableName = "events_table")
@Repository
public class EventRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;
    public Event saveEvent(Event event){
        dynamoDBMapper.save(event);
        return event;
    }
    public Event findByIdsEvent(String userId){
        return dynamoDBMapper.load(Event.class, userId);
    }

    public List<Event> findAllNotes() {
        return dynamoDBMapper.scan(Event.class, new DynamoDBScanExpression());
    }
    public List<Event> findAllByUserId(String userId){
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(userId));
        return dynamoDBMapper.query(Event.class, new DynamoDBQueryExpression<Event>().withKeyConditionExpression("user_id = :val1").withExpressionAttributeValues(eav));
    }
    public List<Event> findByUserIdEventId(String userId, String eventId){
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(userId));
        eav.put(":val2", new AttributeValue().withS(eventId));
        return dynamoDBMapper.query(Event.class, new DynamoDBQueryExpression<Event>().withKeyConditionExpression("user_id = :val1 and event_id = :val2").withExpressionAttributeValues(eav));
    }
    public Event updateEvent(Event event) {
        dynamoDBMapper.save(event);
        return event;
    }

    public String deleteEvent(String userId, String eventId) {
        Event object = new Event();
        object.setUser_id(userId);
        object.setEvent_id(eventId);
        dynamoDBMapper.delete(object);
        return "Deleted";
    }


    public List<Event> findByUserDate(String userId, String date) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(userId));
        eav.put(":val2", new AttributeValue().withS(date));
        return dynamoDBMapper.query(Event.class, new DynamoDBQueryExpression<Event>().withKeyConditionExpression("user_id = :val1 and begins_with (event_id, :val2)").withExpressionAttributeValues(eav));
    }
}
