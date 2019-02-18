package com.atguigu.test;

class Ticket {
    private int count = 20;

    public void sale(){
        while (true) {
            synchronized (this){
                if(count==0){
                    System.out.println("票已经卖完啦");
                    break;
                } else {
                    System.out.println(Thread.currentThread().getName() + "卖的第 "+count+" 张票");
                    count--;
                }
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * 售票窗口
 */
class SaleWindows extends Thread{
    private Ticket ticket;

    public SaleWindows(String name,Ticket ticket){
        super(name);
        this.ticket = ticket;
    }

    @Override
    public void run() {
        super.run();
        ticket.sale();
    }
}

public class TicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        SaleWindows windows1 = new SaleWindows("窗口1", ticket);
        SaleWindows windows2 = new SaleWindows("窗口2", ticket);
        SaleWindows windows3 = new SaleWindows("窗口3", ticket);

        windows1.start();
        windows2.start();
        windows3.start();
    }
}