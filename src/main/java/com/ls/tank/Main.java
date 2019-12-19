package com.ls.tank;

import java.util.concurrent.TimeUnit;

/**主程序
 * @author wenjulan
 * @version v1.0
 * @email 512213588@qq.com
 * @create 2019/12/14 15:19
 **/
public class Main {
    public static void main(String[] args) {
       TankFrame tankFrame = new TankFrame();
       //初始化坦克
        for(int i=0;i<5;i++){
            tankFrame.tanks.add(new MainTank(50+80*i,200,DirectionEnum.DOWN,Group.BAD,tankFrame));
        }
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tankFrame.repaint();
        }
    }
}
