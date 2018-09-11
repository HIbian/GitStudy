package com.chen.movieSystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.chen.vo.Ticket;

public class FreeTicket extends Ticket{
	String gifter = null;
	
	public FreeTicket() {
	}

	public FreeTicket(String name, String time, String seat, double price,String gifter) {
		super(name, time, seat, price);
		this.gifter = gifter;
	}
	@Override
	public void show() {
		System.out.println("*****************************");
		System.out.println("\t万达电影院");
		System.out.println("电影名:"+super.getName());
		System.out.println("时间:"+super.getTime());
		System.out.println("座位号:"+super.getSeat() );
		System.out.println("票价:"+super.getPrice());
		System.out.println("赠送人::"+gifter);
		System.out.println("*****************************");
	}
	@Override
	public void print() {
		File f = new File("F:\\test\\tiekcts\\"+super.getName()+"-"+super.getSeat()+".txt");
		BufferedWriter bw = null;
		try {
			f.createNewFile();
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
			bw.write("*****************************\r\n");
			bw.write("\t万达电影院\r\n");
			bw.write("电影名:"+super.getName()+"\r\n");
			bw.write("时间:"+super.getTime()+"\r\n");
			bw.write("座位号:"+super.getSeat() +"\r\n");
			bw.write("票价:"+super.getPrice()+"\r\n");
			bw.write("赠送人::"+gifter+"\r\n");
			bw.write("*****************************\r\n");
			bw.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
