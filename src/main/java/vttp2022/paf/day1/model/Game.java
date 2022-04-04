package vttp2022.paf.day1.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public class Game {

    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer users_rated;
    private String url;
    private String image;
    private List<Comment> commentList;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getUsers_rated() {
        return users_rated;
    }

    public void setUsers_rated(Integer users_rated) {
        this.users_rated = users_rated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public static Game create(SqlRowSet rs) {
        Game game = new Game();
        game.setGid(rs.getInt("gid"));
        game.setName(rs.getString("name"));
        game.setYear(rs.getInt("year"));
        game.setRanking(rs.getInt("ranking"));
        game.setUsers_rated(rs.getInt("users_rated"));
        game.setUrl(rs.getString("url"));
        game.setImage(rs.getString("image"));

        game.setCommentList(Comment.createComments(rs));
        return game;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("gid",getGid())
                .add("name",getName())
                .add("year",getYear())
                .add("ranking",getRanking())
                .add("usersRated",getUsers_rated())
                .add("url",getUrl())
                .add("image",getImage())
                .build();
    }
}
