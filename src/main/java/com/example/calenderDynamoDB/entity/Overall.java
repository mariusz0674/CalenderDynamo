package com.example.calenderDynamoDB.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBNativeBoolean;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import jdk.jfr.BooleanFlag;
import lombok.Data;

import java.util.List;

@Data
public class Overall {
    private String user_id;
    private List<Todo> todoList;
    private List<Event> eventList;
    private List<Note> noteList;

}
