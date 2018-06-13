package cn.day1;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/*
 * �����ʾ��Ϸ�����еİ�������
 */
public class AWangShen extends Role{
	//���尢����������ԣ��������ԣ�
	public Image[] images;
	public String[] toLxyMessages;
	public AWangShen(int x, int y){
		super.x=x;
		super.y=y;
		super.index=0;
		this.images= new Image[17];
		for (int i = 0; i < images.length; i++) {
			try {
				this.images[i]=ImageIO.read(new File("�ز�/������/"+i+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		super.width=images[0].getWidth(null);
		super.height=images[0].getHeight(null);
		this.toLxyMessages =new String[]{"66666","9999999"};
	}
	public void  addIndex(){
		index=(index+1)%(images.length-1);
	}
	public boolean isDiagShow(int x, int y,int lxyDir){
		int x2= width+super.x+10;
		int y2= height+super.y+10;
		int x1= x2-30;
		int y1=y2-30;
		if(lxyDir==KeyEvent.VK_LEFT&&x>x1&&x<x2&&y>y1&&y<y2){	
			return true;
		}
		else 
			return false;
	}
}
