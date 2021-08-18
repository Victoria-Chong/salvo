package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class GamePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private LocalDateTime joinDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game")
    private Game game;

    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    private Set<Ship> ships;

    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    private Set<Salvo> salvos;

    public GamePlayer(){}

    public GamePlayer(Player player, Game game, LocalDateTime joinDate) {
        this.joinDate = joinDate;
        this.player = player;
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<Ship> getShips() {
        return ships;
    }

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }

    public Set<Salvo> getSalvos() {
        return salvos;
    }

    public void setSalvos(Set<Salvo> salvos) {
        this.salvos = salvos;
    }

    public Map<String, Object> makeGamePlayerDTO(){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("player", this.getPlayer().makePlayerDTO());
        return dto;
    }

    public Map<String, Object> makeGameViewDTO(){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getGame().getId());
        dto.put("created", this.getGame().getCreationDate());
        dto.put("gamePlayers", this.getGame().getGamePlayer()
                .stream()
                .map(x -> x.makeGamePlayerDTO())
                .collect(Collectors.toList()));
        dto.put("ships", this.getShips()
                .stream()
                .map(x -> x.makeShipDTO())
                .collect(Collectors.toList()));
        dto.put("salvoes", this.getGame().getGamePlayer()
                .stream()
                .flatMap(x -> x.getSalvos()
                        .stream()
                        .map(y -> y.makeSalvoDTO()))
                .collect(Collectors.toList()));
        return dto;
    }
}
