package com.example.calenderDynamoDB.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jdk.jfr.BooleanFlag;
import lombok.Data;

@Data
@DynamoDBTable(tableName = "notes_table")
public class Note {

    @DynamoDBHashKey(attributeName = "user_id")
    private String user_id;
    @DynamoDBRangeKey(attributeName = "note_id")
    private String note_id;
    @DynamoDBAttribute(attributeName = "title")
    private String title;
    @DynamoDBAttribute(attributeName = "text")
    private String text;

}
