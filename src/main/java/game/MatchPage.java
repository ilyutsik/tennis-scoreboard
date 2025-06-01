package game;

import model.Match;

import java.util.List;

public class MatchPage {

    private Integer pageNumber;

    private Integer countOfPages;

    private List<Match> matches;

    public MatchPage(List<Match> matches, Integer pageNumber, Integer countOfPages) {
        this.pageNumber = pageNumber;
        this.matches = matches;
        this.countOfPages = countOfPages;
    }


    public Integer getCountOfPages() {
        return countOfPages;
    }

    public void setCountOfPages(Integer countOfPages) {
        this.countOfPages = countOfPages;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

}
