package game;

import game.enums.Point;

public class Score {

    private Integer extraPoints;

    private Point points;

    private Integer games;

    private Integer sets;

    public Score() {
        this.extraPoints = 0;
        this.points = Point.zero;
        this.games = 0;
        this.sets = 0;
    }

    public void addGame() {
        games++;
    }

    public void addExtraPoint() {
        extraPoints++;
    }

    public void addPoint() {
        switch (points) {
            case zero -> points = Point.fifteen;
            case fifteen -> points =  Point.thirty;
            case thirty -> points = Point.forty;
            case forty -> points = Point.more;
            case more -> points = Point.game;
        };
    }


    public void addSet() {
        sets++;
    }

    public Integer getExtraPoints() {
        return extraPoints;
    }

    public void setExtraPoints(Integer extraPoints) {
        this.extraPoints = extraPoints;
    }

    public Point getPoints() {
        return points;
    }

    public void setPoints(Point points) {
        this.points = points;
    }

    public Integer getGames() {
        return games;
    }

    public void setGames(Integer games) {
        this.games = games;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }
}
