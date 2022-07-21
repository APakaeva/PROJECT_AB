package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class GameStoreTest {

    @Test // добавление и наличие 1 игры в каталог
    public void shouldAddGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        assertTrue(store.containsGame(game));
    }

    @Test // добавление и наличие 1 игры мз двух добавленных в каталог
    public void shouldAdd2Game() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Симс", "Симулятор");
        assertTrue(store.containsGame(game1));
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

    @Test // отсутствие игры в каталоге
    public void shouldCheckNullContainsGame() {
        GameStore store = new GameStore();
        Game game = new Game("Нетология Баттл Онлайн", "Аркады", store);
        assertFalse(store.containsGame(game));
    }

    @Test // регистрирует количество времени, которое проиграл игрок за игрой этого каталога.
    public void shouldAddPlayedTime() {
        Player player = new Player("Kate69");
        GameStore store = new GameStore();
        store.addPlayTime("Kate69", 2);
        store.addPlayTime("Kate69", 1);
        assertEquals(3, store.getPlayedTime("Kate69"));
    }

    @Test // сумма часов игроков в 1 каталоге
    public void shouldSumTime() {
        GameStore store = new GameStore();
        store.addPlayTime("Kate69", 1);
        store.addPlayTime("Mate", 1);
        store.addPlayTime("Starroxy", 1);
        store.addPlayTime("Yuki", 3);
        assertEquals(6, store.getSumPlayedTime());
    }

    @Test // ищет игрока, который проиграл больше всего времени среди отсутствующих игроков(граничные)
    public void shouldShowMostPlayerIfNone() {
        GameStore store = new GameStore();
        assertEquals(null, store.getMostPlayer());
    }

    @Test // ищет игрока, который проиграл больше всего времени среди 1 игрока(граничные)
    public void shouldShowMostWhenOnePlayer() {
        GameStore store = new GameStore();
        Player player1 = new Player("Kate69");
        store.addPlayTime(player1.getName(), 1);
        HashSet<String> expected = new HashSet<>();
        expected.add("Kate69");
        assertEquals(expected, store.getMostPlayer());
    }

    @Test // ищет игрока, который проиграл больше всего времени среди 2 игроков(граничные)
    public void shouldShowMostOfTwoPlayers() {
        GameStore store = new GameStore();
        Player player1 = new Player("Kate69");
        Player player2 = new Player("Mate");
        store.addPlayTime(player1.getName(), 1);
        store.addPlayTime(player2.getName(), 2);
        HashSet<String> expected = new HashSet<>();
        expected.add("Mate");
        assertEquals(expected, store.getMostPlayer());
    }

    @Test // 2 игрока с одинаковым временем игры - будут показаны оба
    public void shouldShowMostPlayerOfTwoIfEqualTime() {
        GameStore store = new GameStore();
        Player player1 = new Player("Kate69");
        Player player2 = new Player("Mate");
        store.addPlayTime(player1.getName(), 1);
        store.addPlayTime(player2.getName(), 1);
        HashSet<String> expected = new HashSet<>();
        expected.add("Kate69");
        expected.add("Mate");
        assertEquals(expected, store.getMostPlayer());
    }

    @Test // 2 игрока с одинаковым временем игры - будут показаны оба, всего трое
    public void shouldShowMostPlayerOfThreeIfEqualTimeOfTwo() {
        GameStore store = new GameStore();
        Player player1 = new Player("Kate69");
        Player player2 = new Player("Mate");
        Player player3 = new Player("Lydia");
        store.addPlayTime(player1.getName(), 3);
        store.addPlayTime(player2.getName(), 3);
        store.addPlayTime(player3.getName(), 1);
        HashSet<String> expected = new HashSet<>();
        expected.add("Kate69");
        expected.add("Mate");
        assertEquals(expected, store.getMostPlayer());
    }

    @Test    // игроки установили игру, но не играли - ожидается null
    public void shouldFindPlayerWhenPlayedTime0() {
        GameStore store = new GameStore();
        store.addPlayTime("Kate69", 0);
        store.addPlayTime("Mate", 0);
        store.addPlayTime("Naggets", 0);
        assertEquals(null, store.getMostPlayer());
    }

    @Test // сумма часов с 0 игроков
    public void shouldShowSumTimeAbsencePlayers() {
        GameStore store = new GameStore();
        assertEquals(0, store.getSumPlayedTime());
    }

    @Test // сумма часов с 1 игроком
    public void shouldShowSumTimeOnePlayer() {
        GameStore store = new GameStore();
        Player player1 = new Player("Kate69");
        store.addPlayTime("Kate69", 1);
        store.addPlayTime("Kate69", 3);
        assertEquals(4, store.getSumPlayedTime());
    }

    @Test // сумма часов 2х игроков в 2 каталогах
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
    public void shouldShowSumTimeTwoPlayers0Hours() {
        GameStore store = new GameStore();
        Player player1 = new Player("Kate69");
        Player player2 = new Player("Mate");
        store.addPlayTime("Kate69", 3);
        store.addPlayTime("Mate", 0);
        assertEquals(3, store.getSumPlayedTime());
    }
}