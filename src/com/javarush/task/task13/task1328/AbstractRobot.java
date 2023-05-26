package com.javarush.task.task13.task1328;

public abstract class AbstractRobot implements Attackable, Defensable {
    private static int hitCount;

    public BodyPart attack() {
        BodyPart attackedBodyPart = null;
        hitCount = hitCount + 1;

        if (hitCount == 1) {
            attackedBodyPart = BodyPart.ARM;
        } else if (hitCount == 2) {
            attackedBodyPart = BodyPart.HEAD;
        } else if (hitCount == 3) {
            attackedBodyPart = BodyPart.CHEST;
        } else {
            hitCount = 0;
            attackedBodyPart = BodyPart.LEG;
        }
        return attackedBodyPart;
    }

    public BodyPart defense() {
        BodyPart defendedBodyPart = null;
        hitCount = hitCount + 3;

        if (hitCount % 4 == 1) {
            defendedBodyPart = BodyPart.HEAD;
        } else if (hitCount % 4 == 2) {
            defendedBodyPart = BodyPart.CHEST;
        } else if (hitCount % 4 == 3) {
            defendedBodyPart = BodyPart.LEG;
        } else {
            defendedBodyPart = BodyPart.ARM;
        }
        hitCount = (hitCount + 1) % 4;

        return defendedBodyPart;
    }

    public abstract String getName();
}
