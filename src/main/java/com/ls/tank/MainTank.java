package com.ls.tank;

import javax.management.relation.RelationNotFoundException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * 主站坦克
 *
 * @author wenjulan
 * @version v1.0
 * @email 512213588@qq.com
 * @create 2019/12/13 14:43
 **/
public class MainTank {
    private static final int SPEED = 1;
    private DirectionEnum dir;
    private int x, y;
    public static int WIDTH = ResourceMgr.tankL.getWidth(), HEIGHT = ResourceMgr.tankL.getHeight();
    private boolean moving = true;
    private boolean living = true;
    private TankFrame tf;
    private Random random = new Random();
    private Group group = Group.BAD;
    public MainTank(int x, int y, DirectionEnum direction,Group group, TankFrame tf) {
        this.dir = direction;
        this.x = x;
        this.y = y;
        this.group = group;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if (!living) tf.tanks.remove(this);
        Color c = g.getColor();
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
        }
        move();
    }

    public void move() {
        if (!moving) return;
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

        if(random.nextInt(10)>5){
            this.fire();
        }

    }

    public void setDir(DirectionEnum dir) {
        this.dir = dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        int bx = this.x + MainTank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.y + MainTank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bx, by, this.dir,this.group, this.tf));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void die() {
        this.living = false;
    }
}
