package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    Player player1 = new Player("Petya");
    GameStore store = new GameStore();
    Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game theWitcher = store.publishGame("Блондин и чудовища", "Приключения");
    Game assassinsCreed = store.publishGame("Парень с капюшоном", "Приключения");


    @Test
    public void shouldInstallGameTwice() {
        player1.installGame(game);
        player1.play(game, 10);
        player1.installGame(game);

        int expected = 15;
        int actual = player1.play(game, 5);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenPlayUninstalledGame() {
        assertThrows(RuntimeException.class, () -> {
            player1.play(game, 3);
        });
    }

    @Test
    public void shouldIncreaseNewGamePlaytime() {
        player1.installGame(game);

        int expected = 10;
        int actual = player1.play(game, 10);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldIncreaseNotNullPlaytime() {
        player1.installGame(game);
        player1.play(game, 3);

        int expected = 13;
        int actual = player1.play(game, 10);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfOneGame() {
        player1.installGame(game);
        player1.play(game, 3);

        int expected = 3;
        int actual = player1.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMostPlayedGameByGenre() {

        player1.installGame(theWitcher);
        player1.installGame(assassinsCreed);
        player1.play(theWitcher, 30);
        player1.play(assassinsCreed, 300);

        Game expected = assassinsCreed;
        Game actual = player1.mostPlayerByGenre("Приключения");
        assertEquals(expected, actual);
    }
}