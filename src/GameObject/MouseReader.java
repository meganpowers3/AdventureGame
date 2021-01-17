package GameObject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseReader implements MouseListener {
    Music m1;
    String smallSFX;

    public MouseReader(){
        smallSFX = "Click.wav";
        m1 = new Music(2, smallSFX);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    // help, load, quit, scores, start
    @Override
    public void mousePressed(MouseEvent e) {
        //public static final int SCREEN_WIDTH = 638;
        //    public static final int SCREEN_HEIGHT = 479;

        int mouseXCoordinate = e.getX();
        int mouseYCoordinate = e.getY();
        if(mouseYCoordinate >= 425 && mouseYCoordinate <= 455){

            if(mouseXCoordinate >= 22 && mouseXCoordinate <= 120 && MainRoom.currentState == MainRoom.currentState.TITLE_STATE){
                m1.play();
                MainRoom.currentState = MainRoom.currentState.MENU_STATE;
            }else if(mouseXCoordinate >= 140 && mouseXCoordinate <= 240 && MainRoom.currentState == MainRoom.currentState.TITLE_STATE){
                m1.play();
                MainRoom.currentState = MainRoom.currentState.ABOUT_STATE;
            }else if(mouseXCoordinate >= 260 && mouseXCoordinate <= 360 && MainRoom.currentState == MainRoom.currentState.TITLE_STATE){
                m1.play();
                System.exit(0);
            }else if(mouseXCoordinate >= 380 && mouseXCoordinate <= 480 && MainRoom.currentState == MainRoom.currentState.TITLE_STATE){
                m1.play();
                MainRoom.currentState = MainRoom.currentState.SCORE_STATE;
            }else if(mouseXCoordinate >= 500 && mouseXCoordinate <= 600 && MainRoom.currentState == MainRoom.currentState.TITLE_STATE){
                m1.play();
                MainRoom.currentState = MainRoom.currentState.MAIN_STATE;
            }else if(mouseXCoordinate >= 500 && mouseXCoordinate <= 600 && MainRoom.currentState == MainRoom.currentState.LOSE_STATE || MainRoom.currentState == MainRoom.currentState.VICTORY_STATE){
                m1.play();
                System.exit(0);
            }else if(mouseXCoordinate >= 22 && mouseXCoordinate <= 120 && MainRoom.currentState == MainRoom.currentState.LOSE_STATE || MainRoom.currentState == MainRoom.currentState.VICTORY_STATE){
                m1.play();
                MainRoom.currentState = MainRoom.currentState.MENU_STATE;
            }else if(mouseXCoordinate >= 22 && mouseXCoordinate <= 120 && MainRoom.currentState == MainRoom.currentState.SCORE_STATE){
                m1.play();
                MainRoom.currentState = MainRoom.currentState.TITLE_STATE;
            }
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
