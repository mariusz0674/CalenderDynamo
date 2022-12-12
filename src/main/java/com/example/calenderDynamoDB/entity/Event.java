package com.example.calenderDynamoDB.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

@Data
@DynamoDBTable(tableName = "events_table")
public class Event {
    @DynamoDBHashKey(attributeName = "user_id")
    private String user_id;
    @DynamoDBRangeKey(attributeName = "event_id")
    private String event_id;
    @DynamoDBAttribute(attributeName = "details")
    private String details;
    @DynamoDBAttribute(attributeName = "dsc")
    private String dsc;
    @DynamoDBAttribute(attributeName = "date")
    private String date;
    @DynamoDBAttribute(attributeName = "time")
    private String time;
    @DynamoDBAttribute(attributeName = "duration")
    private String duration;
}
