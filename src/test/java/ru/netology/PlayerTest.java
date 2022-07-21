package ru.netology;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    Player player1 = new Player("Petya");
    GameStore store = new GameStore();
    Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game theWitcher = store.publishGame("Блондин и чудовища", "Приключения");
    Game assassinsCreed = store.publishGame("Парень с капюшоном", "Приключения");

    @Test
    public void shouldGetPlayedTimeWhenNoGameInstalled() {
        int expected = -1;
        int actual = player1.getPlayedTimeGame(game);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetPlayedTimeForOneInstalledGame() {
        player1.installGame(game);
        player1.play(game, 10);

        int expected = 10;
        int actual = player1.getPlayedTimeGame(game);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetPlayedTimeForTwoInstalledGames() {
        player1.installGame(theWitcher);
        player1.installGame(assassinsCreed);
        player1.play(theWitcher, 10);
        player1.play(assassinsCreed, 20);

        int expected1 = 10;
        int expected2 = 20;
        int actual1 = player1.getPlayedTimeGame(theWitcher);
        int actual2 = player1.getPlayedTimeGame(assassinsCreed);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    public void shouldInstallOneGame() {
        player1.installGame(game);

        int expected = 0;
        int actual = player1.getPlayedTimeGame(game);
        assertEquals(expected, actual);
    }
    @Test
    public void shouldNotInstallTheSameGameTwice() {
        player1.installGame(game);
        player1.play(game, 10);
        player1.installGame(game);

        int expected = 10;
        int actual = player1.getPlayedTimeGame(game);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenPlayUninstalledGame() {
        assertThrows(RuntimeException.class, ()-> {
            player1.play(game, 3);
        });
    }

    @Test
    public void shouldIncreaseNewGamePlaytime() {
        player1.installGame(game);
        player1.play(game, 10);

        int expected = 10;
        int actual = player1.getPlayedTimeGame(game);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldIncreaseNotNullPlaytime() {
        player1.installGame(game);
        player1.play(game, 3);
        player1.play(game, 10);

        int expected = 3 + 10;
        int actual = player1.getPlayedTimeGame(game);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotDecreasePlaytime() {
        player1.installGame(game);
        player1.play(game, 10);
        player1.play(game, -1);

        int expected = 10;
        int actual = player1.getPlayedTimeGame(game);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfNoInstalledGames() {
        int expected = 0;
        int actual = player1.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfOneInstalledGame() {
        player1.installGame(game);
        player1.play(game, 3);

        int expected = 3;
        int actual = player1.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTwoGamesOfOneGenreAreInstalled() {
        player1.installGame(theWitcher);
        player1.installGame(assassinsCreed);
        player1.play(theWitcher, 30);
        player1.play(assassinsCreed, 300);

        int expected = 30 + 300;
        int actual = player1.sumGenre("Приключения");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTwoGameOfDifferentGenreAreInstalled() {
        player1.installGame(theWitcher);
        player1.installGame(game);
        player1.play(theWitcher, 30);
        player1.play(game, 300);

        int expected = 30;
        int actual = player1.sumGenre("Приключения");
        assertEquals(expected, actual);
    }


    @Test
    public void shouldFindMostPlayedGameByGenreIfNoInstalledGames() {
        Game actual = player1.mostPlayerByGenre("Приключения");
        assertEquals(null, actual);
    }

    @Test
    public void shouldFindMostPlayedGameByGenreIfOneGameAndLookingForItsGenre() {
        player1.installGame(theWitcher);
        player1.play(theWitcher, 30);

        Game expected = theWitcher;
        Game actual = player1.mostPlayerByGenre("Приключения");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMostPlayedGameByGenreIfOneGameAndLookingForTheOtherGenre() {
        player1.installGame(theWitcher);
        player1.play(theWitcher, 30);

        Game actual = player1.mostPlayerByGenre("Аркады");
        assertEquals(null, actual);
    }

    @Test
    public void shouldFindMostPlayedGameByGenreIfTwoGamesOfOneGenre() {
        player1.installGame(theWitcher);
        player1.installGame(assassinsCreed);
        player1.play(theWitcher, 30);
        player1.play(assassinsCreed, 300);

        Game expected = assassinsCreed;
        Game actual = player1.mostPlayerByGenre("Приключения");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMostPlayedGameByGenreIfTwoGamesOfDifferentGenre() {
        player1.installGame(game);
        player1.installGame(assassinsCreed);
        player1.play(game, 30);
        player1.play(assassinsCreed, 300);

        Game expected = assassinsCreed;
        Game actual = player1.mostPlayerByGenre("Приключения");
        assertEquals(expected, actual);
    }
}