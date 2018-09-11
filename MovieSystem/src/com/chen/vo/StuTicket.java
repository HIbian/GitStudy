package com.chen.vo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class StuTicket  extends Ticket{
	double count =0;
	public StuTicket(String name, String time, String seat, double price,double count) {
		super();
		this.name = name;
		this.time = time;
		this.seat = seat;
		this.price = price;
		this.count = count;
	}
	@Override
	public void show() {
		System.out.println("*****************************");
		System.out.println("\t万达电影院");
		System.out.println("电影名:"+name);
		System.out.println("时间:"+time);
		System.out.println("座位号:"+seat );
		System.out.println("票价:"+price*count );
		System.out.println("折扣:"+count);
		System.out.println("*****************************");
	}
	public void print() {
		File f = new File("F:\\test\\tiekcts\\"+name+"-"+seat+".txt");
		BufferedWriter bw = null;
		try {
			f.createNewFile();
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
			bw.write("*****************************\r\n");
			bw.write("\t万达电影院\r\n");
			bw.write("电影名:"+name+"\r\n");
			bw.write("时间:"+time+"\r\n");
			bw.write("座位号:"+seat +"\r\n");
			bw.write("票价:"+price*count +"\r\n");
			bw.write("折扣:"+count +"\r\n");
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
