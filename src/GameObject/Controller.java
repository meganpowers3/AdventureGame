/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author anthony-pc
 * @additions made by Em Powers
 *
 * Controls key events.
 *
 */

public class Controller implements KeyListener {

    private Player playerCharacter;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;
    private final int activateSword;
    private final int activateScarab;


    public Controller(Player playerCharacter, int up, int down, int left, int right, int shoot, int activateScarab, int activateSword) {
        this.playerCharacter = playerCharacter;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
        this.activateSword = activateSword;
        this.activateScarab = activateScarab;

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == up) {
            this.playerCharacter.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.playerCharacter.toggleDownPressed();
        }
        if (keyPressed == left) {
            this.playerCharacter.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.playerCharacter.toggleRightPressed();
        }
        if (keyPressed == shoot) {
            this.playerCharacter.toggleShoot();
        }

        if (keyPressed == activateScarab) {
            this.playerCharacter.activateScarab();
        }

        if (keyPressed == activateSword) {
            this.playerCharacter.activateSword();
        }

        if (keyPressed == KeyEvent.VK_RIGHT && MainRoom.currentState == MainRoom.GameState.MENU_STATE) {
            MainRoom.currentState = MainRoom.GameState.POWERUP_STATE;
        }

        if (keyPressed == KeyEvent.VK_LEFT && MainRoom.currentState == MainRoom.GameState.POWERUP_STATE) {
            MainRoom.currentState = MainRoom.GameState.MENU_STATE;
        }

        // use arrow keys to navigate

        if(MainRoom.currentState == MainRoom.currentState.MENU_STATE && keyPressed == KeyEvent.VK_LEFT){
            MainRoom.currentState = MainRoom.currentState.TITLE_STATE;
        }

        if(MainRoom.currentState == MainRoom.currentState.MENU_STATE && keyPressed == KeyEvent.VK_RIGHT){
            MainRoom.currentState = MainRoom.currentState.ENEMY_STATE;
        }

        if(MainRoom.currentState == MainRoom.currentState.ENEMY_STATE && keyPressed == KeyEvent.VK_LEFT){
           MainRoom.currentState = MainRoom.currentState.TITLE_STATE;
        }

        if(MainRoom.currentState == MainRoom.currentState.ENEMY_STATE && keyPressed == KeyEvent.VK_UP){
            MainRoom.currentState = MainRoom.currentState.POWERUP_STATE;
        }

        if(MainRoom.currentState == MainRoom.currentState.ENEMY_STATE && keyPressed == KeyEvent.VK_LEFT){
            MainRoom.currentState = MainRoom.currentState.TITLE_STATE;
        }

        if(MainRoom.currentState == MainRoom.currentState.POWERUP_STATE && keyPressed == KeyEvent.VK_RIGHT){
            MainRoom.currentState = MainRoom.currentState.WALL_STATE;
        }

        if(MainRoom.currentState == MainRoom.currentState.POWERUP_STATE && keyPressed == KeyEvent.VK_LEFT){
            MainRoom.currentState = MainRoom.currentState.TITLE_STATE;
        }

        if(MainRoom.currentState == MainRoom.currentState.WALL_STATE && keyPressed == KeyEvent.VK_LEFT){
            MainRoom.currentState = MainRoom.currentState.TITLE_STATE;
        }




    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == up) {
            this.playerCharacter.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.playerCharacter.unToggleDownPressed();
        }
        if (keyReleased  == left) {
            this.playerCharacter.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.playerCharacter.unToggleRightPressed();
        }
        if (keyReleased  == shoot) {
            this.playerCharacter.unToggleShoot();
        }

        if (keyReleased == activateScarab) {
            this.playerCharacter.deactivateScarab();
        }

        if (keyReleased == activateSword) {
            this.playerCharacter.deactivateSword();
        }

    }
}