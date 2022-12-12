package com.example.calenderDynamoDB.controllers;

import com.example.calenderDynamoDB.entity.Event;
import com.example.calenderDynamoDB.entity.Todo;
import com.example.calenderDynamoDB.repository.EventRepository;
import com.example.calenderDynamoDB.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventRepository eventsRepository;

    @GetMapping(path = "/getAll")
    public List<Event> getAllNotes(){
        return eventsRepository.findAll();
    }

    @GetMapping(value = "/user:{user_id}")
    public List<Event> findAllUserEvents(@PathVariable(value = "user_id")String userId){
        return eventsRepository.findAllByUserId(userId);
    }

    @GetMapping(value = "/user:{user_id}/event:{event_id}")
    public List<Event> findNoteUserEvent(@PathVariable(value = "user_id") String user_id, @PathVariable(value = "event_id") String note_id){
        return eventsRepository.findByUserIdAndObjectId( user_id,  note_id);
    }
    @PostMapping
    public Event saveEvent(@RequestBody Event event){
        return eventsRepository.save(event);
    }

    @PutMapping
    public Event updateEvent(@RequestBody Event event){
        return eventsRepository.update(event);
    }

    @GetMapping(value = "/user:{user_id}/date:{date}")
    public List<Event> finfByUserDate(@PathVariable(value = "user_id") String userId, @PathVariable(value = "date") String date){
        return eventsRepository.findByUserIdAndDate( userId,  date);
    }
}
