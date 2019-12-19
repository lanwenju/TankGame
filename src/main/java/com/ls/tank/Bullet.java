package com.ls.tank;

import java.awt.*;
import java.util.HashMap;

/**
 * @author wenjulan
 * @version v1.0
 * @email 512213588@qq.com
 * @create 2019/12/14 14:21
 **/
public class Bullet {
    private static final int SPEED = 10;
    private int x, y;
    public static int WIDTH = ResourceMgr.bulletD.getWidth(), HEIGHT = ResourceMgr.bulletD.getHeight();
    private DirectionEnum dir;
    private boolean living = true;
    private TankFrame tf;
    private Group group=Group.BAD;
    public Bullet(int x, int y, DirectionEnum dir,Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }


    public void paint(Graphics g) {
        if (!living) {
            tf.bullets.remove(this);
        }
        Color color = g.getColor();
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        move();
    }

    public void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    public void collideWith(MainTank mainTank) {
        if(this.group.equals(mainTank.getGroup()))return;
        //TODO
        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(mainTank.getX(), mainTank.getY(), MainTank.WIDTH, MainTank.HEIGHT);
        if (rect1.intersects(rect2)) {
            mainTank.die();
            this.die();
        }
    }

    private void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
