package com.example.mwotest.player;

import java.util.List;

public interface PlayerUseCase {
	List<Player> getPlayers(Boolean sortByCountry);

	Player getPlayer(Long id);

	Player postPlayer(Player player);

	void putPlayer(Player player);

	void deletePlayer(Player player);
}
