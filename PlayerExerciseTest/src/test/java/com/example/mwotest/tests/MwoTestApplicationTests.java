package com.example.mwotest.tests;

import com.example.mwotest.player.Player;
import com.example.mwotest.player.PlayerRepository;
import com.example.mwotest.player.PlayerService;
import com.example.mwotest.player.PlayerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MwoTestApplicationTests {

	PlayerRepository playerRepository;
	PlayerUseCase playerUseCase;

	@BeforeEach
	public void setup() {
		playerRepository = mock(PlayerRepository.class);
		playerUseCase = new PlayerService(playerRepository);
	}

	@Test
	void testGetPlayer() {
		Player player = new Player();
		player.setId(1L);
		player.setName("Player");
		player.setSurname("Surname");
		player.setBirth(new Date());
		player.setTrainerId(1L);
		player.setLength(178);
		player.setWeight(69);
		Mockito.when(playerRepository.findById(anyLong())).thenReturn(Optional.of(player));

		Player playerFromUseCase = playerUseCase.getPlayer(1L);

		assertEquals(player, playerFromUseCase);
	}

	@Test
	void testGetPlayerWhenPlayerWithIdNotExist() {
		Mockito.when(playerRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> playerUseCase.getPlayer(1L));
	}

	@Test
	void testGetPlayers() {
		Mockito.when(playerRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> playerUseCase.getPlayer(1L));
	}

	@Test
	void testGetPlayersList() {
		Player playerA = new Player();
		playerA.setId(1L);
		playerA.setName("Player");
		playerA.setSurname("Surname");
		playerA.setBirth(new Date());
		playerA.setCountry("Poland");
		playerA.setTrainerId(1L);
		playerA.setLength(178);
		playerA.setWeight(69);
		Player playerB = new Player();
		playerB.setId(2L);
		playerB.setName("Player2");
		playerB.setSurname("Surname");
		playerB.setBirth(new Date());
		playerB.setTrainerId(1L);
		playerB.setCountry("Austria");
		playerB.setLength(178);
		playerB.setWeight(69);
		Player playerC = new Player();
		playerC.setId(3L);
		playerC.setName("Player3");
		playerC.setSurname("Surname");
		playerC.setCountry("China");
		playerC.setBirth(new Date());
		playerC.setTrainerId(1L);
		playerC.setLength(178);
		playerC.setWeight(69);
		Mockito.when(playerRepository.findAll()).thenReturn(List.of(playerA, playerB, playerC));

		List<Player> listOfPlayers = playerUseCase.getPlayers(false);

		assertEquals(3, listOfPlayers.size());
		assertEquals(playerA, listOfPlayers.get(0));
		assertEquals(playerB, listOfPlayers.get(1));
		assertEquals(playerC, listOfPlayers.get(2));
	}

	@Test
	void testGetPlayersListWithSortCountries() {
		Player playerA = new Player();
		playerA.setId(1L);
		playerA.setName("Player");
		playerA.setSurname("Surname");
		playerA.setBirth(new Date());
		playerA.setCountry("Poland");
		playerA.setTrainerId(1L);
		playerA.setLength(178);
		playerA.setWeight(69);
		Player playerB = new Player();
		playerB.setId(2L);
		playerB.setName("Player2");
		playerB.setSurname("Surname");
		playerB.setBirth(new Date());
		playerB.setTrainerId(1L);
		playerB.setCountry("Austria");
		playerB.setLength(178);
		playerB.setWeight(69);
		Player playerC = new Player();
		playerC.setId(3L);
		playerC.setName("Player3");
		playerC.setSurname("Surname");
		playerC.setCountry("China");
		playerC.setBirth(new Date());
		playerC.setTrainerId(1L);
		playerC.setLength(178);
		playerC.setWeight(69);
		Player playerD = new Player();
		playerD.setId(3L);
		playerD.setName("Player4");
		playerD.setSurname("Surname");
		playerD.setCountry(null);
		playerD.setBirth(new Date());
		playerD.setTrainerId(1L);
		playerD.setLength(178);
		playerD.setWeight(69);
		Mockito.when(playerRepository.findAll()).thenReturn(List.of(playerA, playerB, playerC, playerD));

		List<Player> listOfPlayers = playerUseCase.getPlayers(true);

		assertEquals(4, listOfPlayers.size());
		assertEquals(playerB, listOfPlayers.get(0));
		assertEquals(playerC, listOfPlayers.get(1));
		assertEquals(playerA, listOfPlayers.get(2));
		assertEquals(playerD, listOfPlayers.get(3));
	}

	@Test
	void postTest() {
		Player player = new Player();
		player.setId(null);
		player.setName("Player");
		player.setSurname("Surname");
		player.setBirth(new Date());
		player.setCountry("Poland");
		player.setTrainerId(1L);
		player.setLength(178);
		player.setWeight(69);
		Player playerToReturnFromRepo = new Player();
		playerToReturnFromRepo.setId(1L);
		playerToReturnFromRepo.setName("Player");
		playerToReturnFromRepo.setSurname("Surname");
		playerToReturnFromRepo.setBirth(new Date());
		playerToReturnFromRepo.setCountry("Poland");
		playerToReturnFromRepo.setTrainerId(1L);
		playerToReturnFromRepo.setLength(178);
		playerToReturnFromRepo.setWeight(69);
		Mockito.when(playerRepository.save(any(Player.class))).thenReturn(playerToReturnFromRepo);

		Player createdPlayer = playerUseCase.postPlayer(player);

		assertEquals(playerToReturnFromRepo, createdPlayer);
	}

	@Test
	void postTestWhenIdInRequest() {
		Player player = new Player();
		player.setId(1L);
		player.setName("Player");
		player.setSurname("Surname");
		player.setBirth(new Date());
		player.setCountry("Poland");
		player.setTrainerId(1L);
		player.setLength(178);
		player.setWeight(69);

		assertThrows(IllegalArgumentException.class, () -> playerUseCase.postPlayer(player));

		verify(playerRepository, times(0)).save(any());
	}

	@Test
	void putTest() {
		Player player = new Player();
		player.setId(1L);
		player.setName("Player");
		player.setSurname("Surname");
		player.setBirth(new Date());
		player.setCountry("Poland");
		player.setTrainerId(1L);
		player.setLength(178);
		player.setWeight(69);
		Mockito.when(playerRepository.findById(anyLong())).thenReturn(Optional.of(player));
		Mockito.when(playerRepository.save(any(Player.class))).thenReturn(player);

		playerUseCase.putPlayer(player);

		verify(playerRepository, times(1)).save(any());
	}

	@Test
	void putTestWhenPlayerNotExists() {
		Player player = new Player();
		player.setId(1L);
		player.setName("Player");
		player.setSurname("Surname");
		player.setBirth(new Date());
		player.setCountry("Poland");
		player.setTrainerId(1L);
		player.setLength(178);
		player.setWeight(69);
		Mockito.when(playerRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> playerUseCase.putPlayer(player));

		verify(playerRepository, times(0)).save(any());
	}

	@Test
	void deleteTest() {
		Player player = new Player();
		player.setId(1L);
		player.setName("Player");
		player.setSurname("Surname");
		player.setBirth(new Date());
		player.setCountry("Poland");
		player.setTrainerId(1L);
		player.setLength(178);
		player.setWeight(69);
		Mockito.when(playerRepository.findById(anyLong())).thenReturn(Optional.of(player));

		playerUseCase.deletePlayer(player);

		verify(playerRepository, times(1)).delete(any());
	}

	@Test
	void deleteTestWhenPlayerNotExists() {
		Player player = new Player();
		player.setId(1L);
		player.setName("Player");
		player.setSurname("Surname");
		player.setBirth(new Date());
		player.setCountry("Poland");
		player.setTrainerId(1L);
		player.setLength(178);
		player.setWeight(69);
		Mockito.when(playerRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> playerUseCase.putPlayer(player));

		verify(playerRepository, times(0)).delete(any());

	}

}
