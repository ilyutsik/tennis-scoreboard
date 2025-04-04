package model;

import javax.persistence.*;

@Entity
@Table(name = "Matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player1_id", referencedColumnName = "id", nullable = false)
    private Player Player1;

    @ManyToOne
    @JoinColumn(name = "player2_id", referencedColumnName = "id", nullable = false)
    private Player Player2;

    @ManyToOne
    @JoinColumn(name = "winner_id", referencedColumnName = "id", nullable = false)
    private Player winner;

    public Match() {
    }

    public Match(Player player1, Player player2, Player winner) {
        Player1 = player1;
        Player2 = player2;
        this.winner = winner;
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
}
