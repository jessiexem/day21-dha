package vttp2022.paf.day1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vttp2022.paf.day1.model.Comment;
import vttp2022.paf.day1.model.Game;
import vttp2022.paf.day1.repositories.GameRepository;

import java.util.List;
import java.util.Optional;


@RestController
public class GameRestController {

    @Autowired
    private GameRepository gameRepo;


    @GetMapping("/comments/{gid}")
    public ResponseEntity<String> getComments(@PathVariable Integer gid) throws JsonProcessingException {
        List<Comment> list = gameRepo.getCommentsByGid(gid,10,0);

        //to convert list to json
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson = objectMapper.writeValueAsString(list);
        System.out.println("Convert List to JSON :");
        System.out.println(arrayToJson);
        return new ResponseEntity<String>(arrayToJson,HttpStatus.OK);

    }

    @GetMapping("/game/{gid}")
    public ResponseEntity<String> getGamesById(@PathVariable Integer gid) {
        Optional<Game> opt = gameRepo.getGameByGid(gid);
        if(opt.isEmpty()) {
            return ResponseEntity.status(404).body(Json.createObjectBuilder()
                    .add("message","Cannot find" + gid)
                    .build()
                    .toString());
        }
        return ResponseEntity.ok(opt.get().toJson().toString());
    }



}
