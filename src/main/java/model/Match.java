package model;

import javax.persistence.*;

@Entity
@Table(name = "Match")
public class Match implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "player1_id", nullable = false)
    private Player Player1;

    @ManyToOne
    @JoinColumn(name = "player2_id", nullable = false)
    private Player Player2;

    @ManyToOne
    @JoinColumn(name = "winner_id", nullable = false)
    private Player winner;

    public Match() {
    }

    public Match(Player player1, Player player2, Player winner) {
        Player1 = player1;
        Player2 = player2;
        this.winner = winner;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return Player1;
    }

    public void setPlayer1(Player player1) {
        Player1 = player1;
    }

    public Player getPlayer2() {
        return Player2;
    }

    public void setPlayer2(Player player2) {
        Player2 = player2;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }


    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", Player1=" + Player1 +
                ", Player2=" + Player2 +
                ", winner=" + winner +
                '}';
    }
}
