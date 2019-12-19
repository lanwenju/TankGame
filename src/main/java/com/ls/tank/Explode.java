package com.ls.tank;

import java.awt.*;

/**
 * @author wenjulan
 * @version v1.0
 * @email 512213588@qq.com
 * @create 2019/12/15 20:35
 **/
public class Explode {
    private int x, y;
    public static int WIDTH = ResourceMgr.explodes[0].getWidth(), HEIGHT = ResourceMgr.explodes[0].getHeight();
    private boolean living = true;
    private TankFrame tf;
    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Audio("com/ls/tank/audio/explode.wav").start();
    }


    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
            step = 0;
        }
    }

}
