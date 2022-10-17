package com.example.mwotest.controller;

import com.example.mwotest.player.Player;
import com.example.mwotest.player.PlayerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PlayerController {
	private final PlayerUseCase playerUseCase;

	@GetMapping
	public List<Player> getPlayers(@RequestBody Boolean sortByCountry) {
		return playerUseCase.getPlayers(sortByCountry);
	}

	@GetMapping("/{id}")
	public Player getPlayer(@PathVariable long id) {
		return playerUseCase.getPlayer(id);
	}

	@PostMapping
	public Player postPlayer(@RequestBody Player player) {
		return playerUseCase.postPlayer(player);
	}


	@PutMapping
	public void putPlayer(@RequestBody Player player) {
		playerUseCase.putPlayer(player);
	}

	@DeleteMapping
	public void deletePlayer(@RequestBody Player player) {
		playerUseCase.deletePlayer(player);
	}
}
