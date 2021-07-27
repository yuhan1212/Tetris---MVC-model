package tetris.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardHandler implements KeyListener {

    private Controller controller;

    public KeyBoardHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keycode = e.getKeyCode();

        if (keycode == 's' || keycode == 'S') {
            controller.start();
            return;
        } else if (keycode == 'p' || keycode == 'P') {
            controller.pause();
            return;
        }

        if (!controller.isPaused() && controller.currentPiece != null) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    game.moveLeft();
                    cur_board = game.getColors();
                    break;
                case KeyEvent.VK_RIGHT:
                    controller.currentPiece = controller.currentPiece.moveRight();
                    break;
                case KeyEvent.VK_DOWN:
                    controller.currentPiece = controller.currentPiece.oneLineDown();
                    break;
                case KeyEvent.VK_UP:
                    controller.currentPiece = controller.currentPiece.rotateLeft();
                    break;
                case KeyEvent.VK_SPACE:
                    controller.currentPiece = controller.currentPiece.dropDown();
                    break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }


    @Override
    public void keyReleased(KeyEvent e) {
        // do nothing
    }

}
