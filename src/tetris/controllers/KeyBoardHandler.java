//package tetris.controllers;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//public class KeyBoardHandler implements KeyListener {
//
//    private final Controller controller;
//    private final String moveSoundFileName = "move.wav";
//    private final SoundEffect moveSoundEffect;
//    private final String dropSoundFileName = "drop.wav";
//    private final SoundEffect dropSoundEffect;
//
//    public KeyBoardHandler(Controller controller) {
//        this.controller = controller;
//        this.moveSoundEffect = new SoundEffect(moveSoundFileName);
//        this.dropSoundEffect = new SoundEffect(dropSoundFileName);
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//
//        int keycode = e.getKeyCode();
//
//        if (keycode == 's' || keycode == 'S') {
//            controller.start();
//            return;
//        } else if (keycode == 'p' || keycode == 'P') {
//            controller.pause();
//            return;
//        }
//
//        if (!controller.isPaused()) {
//            int keyCode = e.getKeyCode();
//            switch (keyCode) {
//                case KeyEvent.VK_LEFT:
//                    controller.move(Action.LEFT);
//                    this.moveSoundEffect.play();
//                    break;
//                case KeyEvent.VK_RIGHT:
//                    controller.move(Action.RIGHT);
//                    this.moveSoundEffect.play();
//                    break;
//                case KeyEvent.VK_DOWN:
//                    controller.move(Action.DOWNONE);
//                    this.moveSoundEffect.play();
//                    break;
//                case KeyEvent.VK_UP:
//                    controller.move(Action.ROTATE);
//                    this.moveSoundEffect.play();
//                    break;
//                case KeyEvent.VK_SPACE:
//                    controller.move(Action.ALLDOWN);
//                    this.dropSoundEffect.play();
//                    break;
//            }
//        }
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//        // do nothing
//    }
//
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        // do nothing
//    }
//
//}
