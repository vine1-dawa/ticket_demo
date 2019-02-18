package com.atguigu.test.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketDemo_Lock {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        int num = ticket.getNumber();
        new Thread(()->{for(int i = 1;i<=num;i++)ticket.sale();},"a").start();
        new Thread(()->{for(int i = 1;i<=num;i++)ticket.sale();},"b").start();
        new Thread(()->{for(int i = 1;i<=num;i++)ticket.sale();},"c").start();
    }

}

class Ticket{

    private int number = 30;
    private Lock lock = new ReentrantLock();

    public int getNumber(){
        return number;
    }

    public void sale() {
        lock.lock();
        try{
            if(number > 0){
                System.out.println(Thread.currentThread().getName()+"窗口正在售出票"+(number--)+"，还剩"+number+"张票");
            }
        }finally {
            lock.unlock();
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
