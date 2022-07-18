package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameStoreTest {

    @Test // добавление 1 игры в каталог
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        assertTrue(store.containsGame(game));
    }

    @Test // наличие нескольких игр в каталоге
    public void shouldCheckContainsGame() {
        Game[] gameTest = new Game[2];
        GameStore store = new GameStore();
        boolean[] assertContainGame = new boolean[2];
        gameTest[0] = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        gameTest[1] = store.publishGame("Симс", "Симулятор");
        for (int i = 0; i < gameTest.length; i++) {
            assertContainGame[i] = store.containsGame(gameTest[i]);
        }
        boolean[] expected = new boolean[]{true, true};
        assertArrayEquals(expected, assertContainGame);
    }

    @Test // НЕ наличие игры в каталоге
    public void shouldCheckNullContainsGame() {
        GameStore store = new GameStore();
        Game game = new Game("Нетология Баттл Онлайн", "Аркады", store);
        assertFalse(store.containsGame(game));
    }

    @Test // Регистрирует количество времени, которое проиграл игрок за игрой этого каталога.
    public void shouldAddPlayedTime() {
        Player player = new Player("Kate69");
        GameStore store = new GameStore();
        store.addPlayTime("Kate69", 2);
        store.addPlayTime("Kate69", 1);
        int expected = 3;
        int actual = store.getSumPlayedTime();
        Assertions.assertEquals(expected, actual);
    }

    @Test // ищет игрока, который проиграл больше всего времени среди отсутствующих игроков(граничные)
    public void shouldShowMostPlayedPlayer() {
        GameStore store = new GameStore();
        assertEquals(null, store.getMostPlayer());
    }

    @Test // ищет игрока, который проиграл больше всего времени среди 1 игрока(граничные)
    public void shouldShowMostPlayedPlayer1() {
        GameStore store = new GameStore();
        Player player1 = new Player("Kate69");
        store.addPlayTime("Kate69", 1);
        assertEquals("Kate69", store.getMostPlayer());
    }

    @Test // ищет игрока, который проиграл больше всего времени среди 2 игроков(граничные)
    public void shouldShowMostPlayedPlayer2() {
        GameStore store = new GameStore();
        Player player1 = new Player("Kate69");
        Player player2 = new Player("Mate");
        store.addPlayTime("Kate69", 1);
        store.addPlayTime("Mate", 2);
        assertEquals("Mate", store.getMostPlayer());
    }

    @Test // ищет игрока, который проиграл больше всего времени среди 2 игроков c одинаковым временем(граничные)
    public void shouldShowMostPlayedPlayer2EqualTime() {
        GameStore store = new GameStore();
        Player player1 = new Player("Kate69");
        Player player2 = new Player("Mate");
        store.addPlayTime("Kate69", 1);
        store.addPlayTime("Mate", 1);
        assertEquals(null, store.getMostPlayer());
    }

    @Test // сумма часов с 0 игроков
    public void shouldShowSumTimeAbsencePlayers() {
        GameStore store = new GameStore();
        assertEquals(0, store.getSumPlayedTime());
    }

    @Test // сумма часов с 1 игроком
    // метод getSumPlayedTime не дописан
    public void shouldShowSumTimeOnePlayer() {
        GameStore store = new GameStore();
        Player player1 = new Player("Kate69");
        store.addPlayTime("Kate69", 1);
        store.addPlayTime("Kate69", 0);
        store.addPlayTime("Kate69", 3);
        assertEquals(4, store.getSumPlayedTime());
    }

    @Test // сумма часов 2х игроков в 2 каталогах
    // метод getSumPlayedTime не дописан
    public void shouldShowSumTimeTwoPlayersTwoCatalogs() {
        GameStore store = new GameStore();
        GameStore store2 = new GameStore();
        Player player1 = new Player("Kate69");
        Player player2 = new Player("Mate");
        store.addPlayTime("Kate69", 1);
        store2.addPlayTime("Mate", 2);
        assertEquals(1, store.getSumPlayedTime());
        assertEquals(2, store2.getSumPlayedTime());
    }

    @Test // сумма часов с 2х игроков, один из них 0ч
    // метод getSumPlayedTime не дописан
    public void shouldShowSumTimeTwoPlayers0Hours() {
        GameStore store = new GameStore();
        Player player1 = new Player("Kate69");
        Player player2 = new Player("Mate");
        store.addPlayTime("Kate69", 3);
        store.addPlayTime("Mate", 0);
        assertEquals(3, store.getSumPlayedTime());
    }
}

