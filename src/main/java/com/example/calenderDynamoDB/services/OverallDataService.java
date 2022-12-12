package com.example.calenderDynamoDB.services;

import com.example.calenderDynamoDB.entity.Overall;
import com.example.calenderDynamoDB.repository.EventRepository;
import com.example.calenderDynamoDB.repository.NoteRepository;
import com.example.calenderDynamoDB.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OverallDataService {
    @Autowired
    private EventRepository eventsRepository;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private TodoRepository todoRepository;

    public Overall getUserAllData(String userId){
        Overall overallReply = new Overall();
        overallReply.setUser_id(userId);
        overallReply.setEventList(eventsRepository.findAllByUserId(userId));
        overallReply.setNoteList(noteRepository.findAllByUserId(userId));
        overallReply.setTodoList(todoRepository.findAllByUserId(userId));
        return overallReply;
    }

}
