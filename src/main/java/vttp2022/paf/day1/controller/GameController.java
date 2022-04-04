package vttp2022.paf.day1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vttp2022.paf.day1.model.Comment;
import vttp2022.paf.day1.model.Game;
import vttp2022.paf.day1.repositories.GameRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class GameController {

    @Autowired
    GameRepository gameRepo;

    @GetMapping("/search/game/{gid}")
    public String getGamesById(@PathVariable Integer gid, Model model) {
        Optional<Game> opt = gameRepo.getGameByGid(gid);
        List<Comment> comments = gameRepo.getCommentsByGid(gid);
        if(opt.isEmpty()) {
            return "404";
        }
        Game game = opt.get();
        model.addAttribute("game",game);
        model.addAttribute("comments",comments);
        return "display";
    }
}
