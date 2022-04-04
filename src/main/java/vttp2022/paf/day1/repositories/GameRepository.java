package vttp2022.paf.day1.repositories;

import vttp2022.paf.day1.model.Comment;
import vttp2022.paf.day1.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class GameRepository implements Queries{

    @Autowired
    private JdbcTemplate template;

    public List<Comment> getCommentsByGid(Integer gid) {
        return getCommentsByGid(gid,Integer.MAX_VALUE,0);
    }

    public List<Comment> getCommentsByGid(Integer gid,Integer limit) {
        return getCommentsByGid(gid,limit,0);
    }

    public List<Comment> getCommentsByGid(Integer queryGid, Integer limit, Integer offset) {

        final SqlRowSet result = template.queryForRowSet(SQL_SELECT_COMMENTS_BY_GID,queryGid, limit, offset);

        return Comment.createComments(result);
    }

    public Optional<Game> getGameByGid(Integer queryGid) {

        //vulnerable to sql injection
        //final SqlRowSet result = template.queryForRowSet(SQL_SELECT_GAME_BY_GID+gid);

        final SqlRowSet result = template.queryForRowSet(SQL_SELECT_GAME_BY_GID,queryGid);

        if(!result.next()) {
            return Optional.empty();
        }

        return Optional.of(Game.create(result));
    }
}
