package com.example.calenderDynamoDB.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jdk.jfr.BooleanFlag;
import lombok.Data;

@Data
@DynamoDBTable(tableName = "todo_table")
public class Todo {

    @DynamoDBHashKey(attributeName = "user_id")
    private String user_id;
    @DynamoDBRangeKey(attributeName = "todo_id")
    private String todo_id;
    @DynamoDBAttribute(attributeName = "date")
    private String date;
    @DynamoDBAttribute(attributeName = "done")
    @BooleanFlag
    @DynamoDBNativeBoolean
    private Boolean done;
    @DynamoDBAttribute(attributeName = "text")
    private String text;

}
