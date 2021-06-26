package model;

import java.util.HashMap;

/**
 * This interface implements two main methods to the well functionnement of the game engine.
 * @author Titouan LE BERRE
 * @version 1.0
 */

public interface IGame{

    /**
     * This method handles the beginning of the game (board organization, designation of the player who begin).
     */

    public void start();

    /**
     * This method handles the end of the game (points counting, score display, ranking display).
     */

    public HashMap<String, Integer> endOfGame();
}