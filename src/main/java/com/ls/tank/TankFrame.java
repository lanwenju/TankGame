package com.ls.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wenjulan
 * @version v1.0
 * @email 512213588@qq.com
 * @create 2019/12/13 14:50
 **/
public class TankFrame extends Frame {
    MainTank mainTank = new MainTank(200, 400, DirectionEnum.UP,Group.GOOD, this);
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    List<Bullet> bullets = new ArrayList<>();
    List<MainTank> tanks = new ArrayList<>();

    Explode e = new Explode(100,100,this);

    public TankFrame() {
        setTitle("坦克大战");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        addKeyListener(new KeyListenerHandler());
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }

        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }


    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullets.size(), 10, 60);
        g.drawString("敌人的数量：" + tanks.size(), 10, 80);
        g.setColor(c);
        //创建主战坦克
        mainTank.paint(g);
        //创建子弹
        for (int i=0;i<bullets.size();i++) {
            bullets.get(i).paint(g);
        }
        for (int i=0;i<tanks.size();i++) {
            tanks.get(i).paint(g);
        }
        for (int i=0;i<bullets.size();i++) {
            for (int j=0;j<tanks.size();j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }

        }
        e.paint(g);
    }

    class KeyListenerHandler extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bD = false;
        boolean bR = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    mainTank.fire();
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bR && !bD && !bU) {
                mainTank.setMoving(false);
            } else {
                mainTank.setMoving(true);
                if (bL) mainTank.setDir(DirectionEnum.LEFT);
                if (bR) mainTank.setDir(DirectionEnum.RIGHT);
                if (bU) mainTank.setDir(DirectionEnum.UP);
                if (bD) mainTank.setDir(DirectionEnum.DOWN);
            }

        }
    }


}
