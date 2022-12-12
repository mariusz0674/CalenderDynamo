package com.example.calenderDynamoDB.controllers;

import com.example.calenderDynamoDB.entity.Overall;
import com.example.calenderDynamoDB.services.OverallDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/overall")
public class OverallController {

    @Autowired
    private OverallDataService overallDataService;

    @GetMapping(value = "/user:{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Overall getAllUserData(@PathVariable(value = "user_id")String userId){
        return overallDataService.getUserAllData(userId);
    }
}
