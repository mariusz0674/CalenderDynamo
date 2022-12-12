package com.example.calenderDynamoDB.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.calenderDynamoDB.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DynamoDBTable(tableName = "notes_table")
@Repository
public class NoteRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Note saveNote(Note note) {
        dynamoDBMapper.save(note);
        return note;
    }
    public Note updateNote(Note note) {
        dynamoDBMapper.save(note);
        return note;
    }

    public List<Note> findAllNotesByUserId(String userId) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(userId));
        return dynamoDBMapper.query(Note.class, new DynamoDBQueryExpression<Note>().withKeyConditionExpression("user_id = :val1").withExpressionAttributeValues(eav));
    }

    public List<Note> findAllNotes() {
        return dynamoDBMapper.scan(Note.class, new DynamoDBScanExpression());
    }

    public Note findNoteUserNote(String user_id, String note_id) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(user_id));
        eav.put(":val2", new AttributeValue().withS(note_id));
        return dynamoDBMapper.query(Note.class, new DynamoDBQueryExpression<Note>().withKeyConditionExpression("user_id = :val1 and note_id = :val2").withExpressionAttributeValues(eav)).get(0);
    }



}