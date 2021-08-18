package com.codeoftheweb.salvo;

import org.apache.catalina.Store;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository,
									  GameRepository gameRepository,
									  GamePlayerRepository gamePlayerRepository,
									  ShipRepository shipRepository,
									  SalvoRepository salvoRepository) {
		return (args) -> {
			Player player1 = new Player("vicky@gmail.com");
			Player player2 = new Player("paksu@gmail.com");
			playerRepository.save(player1);
			playerRepository.save(player2);
			Game game1 = new Game(LocalDateTime.now());
			Game game2 = new Game(LocalDateTime.now().plusHours(1));
			Game game3 = new Game(LocalDateTime.now().plusHours(2));
			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);
			GamePlayer gamePlayer1 = new GamePlayer(player1, game1, LocalDateTime.now());
			GamePlayer gamePlayer2 = new GamePlayer(player2, game1, LocalDateTime.now());
			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
			Ship ship1 = new Ship("Destroyer", gamePlayer1, Arrays.asList("H2","H3","H4"));
			Ship ship2 = new Ship("Submarine", gamePlayer1, Arrays.asList("E1","F1","G1"));
			Ship ship3 = new Ship("Patrol Boat", gamePlayer1, Arrays.asList("B4","B5"));
			Ship ship4 = new Ship("Destroyer", gamePlayer2, Arrays.asList("H2","H3","H4"));
			Ship ship5 = new Ship("Submarine", gamePlayer2, Arrays.asList("E1","F1","G1"));
			Ship ship6 = new Ship("Patrol Boat", gamePlayer2, Arrays.asList("B4","B5"));
			shipRepository.save(ship1);
			shipRepository.save(ship2);
			shipRepository.save(ship3);
			shipRepository.save(ship4);
			shipRepository.save(ship5);
			shipRepository.save(ship6);
			Salvo salvo1 = new Salvo(1, gamePlayer1, Arrays.asList("H2","H3","H4"));
			Salvo salvo2 = new Salvo(2, gamePlayer1, Arrays.asList("E1","F1","G1"));
			Salvo salvo3 = new Salvo(1, gamePlayer2, Arrays.asList("B4","B5"));
			Salvo salvo4 = new Salvo(2, gamePlayer2, Arrays.asList("E4","F5","G2"));
			salvoRepository.save(salvo1);
			salvoRepository.save(salvo2);
			salvoRepository.save(salvo3);
			salvoRepository.save(salvo4);
		};
	}
}
