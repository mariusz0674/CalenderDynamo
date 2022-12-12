package com.example.calenderDynamoDB.controllers;

import com.example.calenderDynamoDB.entity.Note;
import com.example.calenderDynamoDB.entity.Todo;
import com.example.calenderDynamoDB.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping(path = "/getAll")
    public List<Note> getAllNotes(){
        return noteRepository.findAllNotes();
    }

    @GetMapping(value = "/user:{user_id}")
    public List<Note> findAllUserNotes(@PathVariable(value = "user_id")String userId){
        return noteRepository.findAllNotesByUserId(userId);
    }

    @GetMapping(value = "/user:{user_id}/note:{note_id}")
    public Note findNoteUserNote(@PathVariable(value = "user_id") String user_id, @PathVariable(value = "note_id") String note_id){
        return noteRepository.findNoteUserNote( user_id,  note_id);
    }
    @PostMapping
    public Note saveNote(@RequestBody Note note){
        return noteRepository.saveNote(note);
    }

    @PutMapping
    public Note updateNote(@RequestBody Note note){
        return noteRepository.updateNote(note);
    }


}
