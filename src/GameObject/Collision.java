
/**
 *
 * Collision.java
 * Written by: Megan (Em) Powers
 *
 * Detects object collisions.
 *
 */


package GameObject;

public class Collision {
    MainRoom room;
    Controller controller;
    String smallSFX;
    Music m1;

    public Collision(){
        this.room = room;
        smallSFX = "Treasure.wav";
        m1 = new Music(2, smallSFX);
    }

    public void collisionCheck(PlayerObject o1, PlayerObject o2){

        if(o1 instanceof Shell && o2 instanceof BreakableWall && o1 != null){
            if (((Shell) o1).getHitBox().intersects(((BreakableWall) o2).getHitBox()) && !((Shell) o1).collision) {
                ((BreakableWall) o2).collided();
                ((Shell) o1).hitObject(true);
            }
        }

        if(o1 instanceof Shell && o2 instanceof UnpushableWall && o1 != null){
            if (((Shell) o1).getHitBox().intersects(((UnpushableWall) o2).getHitBox()) && !((Shell) o1).collision) {
                ((Shell) o1).hitObject(true);
            }
        }

        if(o1 instanceof Player && o2 instanceof BreakableWall && o1 != null){
            if(((Player) o1).playerCollision().intersects(((BreakableWall) o2).getHitBox())){
                if(((BreakableWall) o2).getShowing() == true) {
                    o1.setX(((Player) o1).getPreviousX());
                    o1.setY(((Player) o1).getPreviousY());
                }
            }
        }

        if(o1 instanceof Player && o2 instanceof UnpushableWall && o2 != null){
            if(((Player) o1).playerCollision().intersects(((UnpushableWall) o2).getHitBox())){
                o1.setX(((Player) o1).getPreviousX());
                o1.setY(((Player) o1).getPreviousY());
            }
        }

        if(o1 instanceof Player && o2 instanceof PushableWall && ((PushableWall) o2).getHorizontalPushable()){
            if(((Player) o1).playerCollision().intersects(((PushableWall) o2).getHitBox())){
                ((PushableWall) o2).setExplorerPushing(true);
                ((PushableWall) o2).pushHorizontalWall(((Player) o1).getVX());
            }
        }

        if(o1 instanceof Player && o2 instanceof PushableWall && ((PushableWall) o2).getVerticalPushable()){
            if(((Player) o1).playerCollision().intersects(((PushableWall) o2).getHitBox())){
                ((PushableWall) o2).setExplorerPushing(true);
                ((PushableWall) o2).pushVerticalWall(((Player) o1).getVY());
            }
        }


        if(o1 instanceof Beetle && o2 instanceof Player && o1 != null){
            if (((Beetle) o1).getHitBox().intersects(((Player) o2).getHitBox())) {
                ((Player) o2).loseHealth(10);
            }
        }

        if(o1 instanceof Scorpion && o2 instanceof Player && o1 != null){
            if (((Scorpion) o1).getHitBox().intersects(((Player) o2).getHitBox())) {
                ((Player) o2).loseHealth(10);
            }
        }

        if(o1 instanceof Mummy && o2 instanceof Player && o1 != null){
            if (((Mummy) o1).getHitBox().intersects(((Player) o2).getHitBox())) {
                if(Mummy.getCanDamageMummy() == true){
                    ((Player) o2).addScore(50);
                    ((Mummy) o1).setShowing(false);
                }else{
                    ((Player) o2).loseHealth(25);
                }
            }
        }

        if(o1 instanceof Shell && o2 instanceof Scorpion && o1 != null){
            if(((Shell) o1).getHitBox().intersects(((Scorpion) o2).getHitBox())){
                ((Shell) o1).hitObject(true);
                ((Scorpion) o2).reduceHP(1);
            }
        }

        if(o1 instanceof Shell && o2 instanceof Beetle && o1 != null){
            if(((Shell) o1).getHitBox().intersects(((Beetle) o2).getHitBox())){
                ((Shell) o1).hitObject(true);
                ((Beetle) o2).reduceHP(1);
            }
        }

        if(o1 instanceof Player && o2 instanceof PushableWall && ((PushableWall) o2).getSpecialPushable()){
            if(((Player) o1).playerCollision().intersects(((PushableWall) o2).getHitBox())){
                if(((Player) o1).getActiveSword() == true) {
                    ((Player) o1).setMotion(false);
                    ((PushableWall) o2).setBeingPushed(false);
                    ((PushableWall) o2).pushVerticalWall(((Player) o1).getVY());
                    MainRoom.currentState = MainRoom.currentState.VICTORY_STATE;
                }
            }
        }


        if(o1 instanceof Wall && o2 instanceof Beetle && o1 != null){
            if(((Wall) o1).getHitBox().intersects(((Beetle) o2).getHitBox())){
                ((Beetle) o2).switchMotionDirection();
            }
        }

        if(o1 instanceof Wall && o2 instanceof Scorpion && o1 != null){
            if(((Wall) o1).getHitBox().intersects(((Scorpion) o2).getHitBox())){
                ((Scorpion) o2).switchMotionDirection();
            }
        }

        if(o1 instanceof Mummy && o2 instanceof Wall){
            if(((Mummy) o1).mummyCollision().intersects(((Wall) o2).getHitBox())){
                ((Mummy) o1).setMotion(false);
            }
        }

        // GET HIT BOX RECTANGLE
        if(o1 instanceof Player && o2 instanceof Collectable && o2 != null) {
            if (((Player) o1).getHitBox().intersects(((Collectable) o2).hitBoxRectangle)) {
                m1.play();

                if (((Collectable) o2).gun) {
                    ((Player) o1).setShoot(true);
                    ((Collectable) o2).setShowing(false);
                }

                if (((Collectable) o2).ankh) {
                    ((Player) o1).setScore(15);
                    ((Collectable) o2).setShowing(false);
                }

                if (((Collectable) o2).statue) {
                    ((Player) o1).setScore(30);
                    ((Collectable) o2).setShowing(false);
                }

                if (((Collectable) o2).scarab) {
                    ((Player) o1).setScore(50);
                    ((Player) o1).addScarab(1);
                    ((Collectable) o2).setShowing(false);
                }

                if (((Collectable) o2).papyrus) {
                    if(((Player) o1).getLives() <= 3) {
                        ((Player) o1).addLife(1);
                    }
                    ((Collectable) o2).setShowing(false);
                }

                if (((Collectable) o2).potion) {
                    ((Player) o1).addHealth(50);
                    ((Collectable) o2).setShowing(false);
                }

                if (((Collectable) o2).sword) {
                    ((Player) o1).playerHasSword();
                    ((Collectable) o2).setShowing(false);
                }

            }
        }
    }
}

