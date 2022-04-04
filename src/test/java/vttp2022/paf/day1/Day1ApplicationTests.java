package vttp2022.paf.day1;

import vttp2022.paf.day1.model.Comment;
import vttp2022.paf.day1.model.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vttp2022.paf.day1.repositories.GameRepository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class Day1ApplicationTests {

	@Autowired
	private GameRepository gameRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldReturnAGame() {
		Optional<Game> opt = gameRepo.getGameByGid(10);
		Assertions.assertTrue(opt.isPresent());
	}

	@Test
	void shouldReturnEmpty() {
		Optional<Game> opt = gameRepo.getGameByGid(10000);
		Assertions.assertFalse(opt.isPresent(),"gid=10000");
	}

	@Test
	void shouldReturn42Comments() {
		List<Comment> comments = gameRepo.getCommentsByGid(10);
		Assertions.assertEquals(comments.size(),42, "no. of comments for gid = 10 is 42.");
	}



}
