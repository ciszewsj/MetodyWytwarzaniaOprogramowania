package com.example.mwotest.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService implements PlayerUseCase {
	private final PlayerRepository playerRepository;

	@Override
	public List<Player> getPlayers(Boolean sortByCountry) {
		List<Player> playersList = playerRepository.findAll();
		if (Boolean.TRUE.equals(sortByCountry)) {
			playersList = new ArrayList<>(playersList);
			playersList.sort((o1, o2) -> {
				if (o1.getCountry() != null && o2.getCountry() != null) {
					return o1.getCountry().compareTo(o2.getCountry());
				} else if (o1.getCountry() == null) {
					return 1;
				}
				return -1;
			});
		}
		return playersList;
	}

	@Override
	public Player getPlayer(Long id) {
		return playerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public Player postPlayer(Player player) {
		if (player.getId() != null) {
			throw new IllegalArgumentException();
		}
		return playerRepository.save(player);
	}

	@Override
	public void putPlayer(Player player) {
		if (playerRepository.findById(player.getId()).isPresent()) {
			playerRepository.save(player);
			return;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public void deletePlayer(Player player) {
		if (playerRepository.findById(player.getId()).isPresent()) {
			playerRepository.delete(player);
			return;
		}
		throw new IllegalArgumentException();
	}
}
