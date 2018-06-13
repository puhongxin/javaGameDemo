package cn.day1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.tools.Tool;

/**
 * �����ļ���ʾ��Ϸ�Ľ���
 * ȡ������һ������Ч�ķ�����һ����������Ȼ���ֱ�Ϊ0�����
 * @author chen
 * ctrl+/ ����ע��
 *javaһ��һ��Ϊǰ�պ�string.substring(0,6)//0-5��6ȡ����
 *strin.substring(7)//7-���
 *������¼������ڰ���+�ͷ� ������ͬһ����İ���+�ͷ�
 *ѡ�д���alt��ֱ��ѡ�д��������ƶ�
 *���Ľӿ���������һ����mouselisten�������ɿ�����mousemotionlisten����ק���ƶ���һ��Ҫ������ӽ����ںͻ���
 */

public class Gamepanel extends JPanel implements Runnable ,KeyListener, MouseListener{
	//��Ҵ��г����ͼƬ
	Scene ljcMScene;
	AWangShen ljcMAws;
	AZWJ ljcMAzwj;
	WCS ljcMWcs;
	Chicken ljcMChicken;
	Skip ljcMSkip;
	//��Ҵ����ͼƬ
	Scene ljcScene;
	AWangShen ljcAws;
	AZWJ ljcAzwj;
	WCS ljcWcs;
	Chicken ljcChicken;
	Skip ljcSkip;
	//�Ի���
	Image dialogBox;
	int diagX;
	int diagY;
	String[] diagMessages;
	int diagIndex;
	boolean isDiagShow;
	//����ңͼƬ��
	LXY lxy;
	//������ʾ�ĳ����Լ�ͼƬ
	Scene scene;
	AWangShen aws;
	AZWJ azwj;
	WCS wcs;
	Chicken chicken;
	Skip skip;
	public  Gamepanel() {
		
		String string1=JOptionPane.showInputDialog("�û���");
		String string2=JOptionPane.showInputDialog("����");
		//���ز��ļ��з��ڹ�����
		try {
			//����·�������̷���C:�����ߴӸ�Ŀ¼��ʼ��/��
			//���·�����ӹ����ļ������·����ʼ��д
			
			dialogBox=ImageIO.read(new File("�ز�/�Ի���/0.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//��ʼ��ljcM�ĳ���
		int ljcMX=-250;
		int ljcMY=-250;
		BufferedImage dataMap;
		try {
			Image sceneImage=ImageIO.read(new File("�ز�/��Ҵ��г�/0.png"));
			dataMap = ImageIO.read(new File("�ز�/��Ҵ��г�/RedMap.png"));
			ljcMScene=new Scene(sceneImage,dataMap, 1,ljcMX,ljcMY,-65536);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene=ljcMScene;
		ljcMAws= new AWangShen(880-scene.x, 270-scene.y);		
		ljcMAzwj =new AZWJ(730-scene.x, 30-scene.y);
		ljcMWcs=new WCS(1060-scene.x,210-scene.y);
		ljcMChicken=new Chicken(700-scene.x, 100-ljcMScene.y);
		ljcMSkip=new Skip(0-ljcMScene.x, 340-ljcMScene.y);
		isDiagShow=false;
		diagIndex=0;
		lxy =new LXY(430-scene.x,250-scene.y);
		//��ʼ�����Ｐ����
		aws=ljcMAws;
		azwj=ljcMAzwj;
		wcs=ljcMWcs;
		skip=ljcMSkip;
		chicken=ljcMChicken;
	}
	public void run(){
		while(true){
			aws.addIndex();
			azwj.addIndex();
			chicken.addIndex();
			wcs.addIndex();
			skip.addIndex();
			try {
				Thread.sleep(130);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
	
		}
	}
	public void paint(Graphics g){
		//���ַ������Ǻܺã����е�����������ͼƬ���Ͻǵ�x��y������
/*		g.drawImage(Toolkit.getDefaultToolkit().getImage(
				Gamepanel.class.getResource("0.png")),-250,-250,this);
*/
		//�����ǰ����
		super.paint(g);

		//���벻д�����ı���Ϸ���ں�Ҳ���Զ��仯
		scene.x=(this.getWidth()-lxy.width)/2-lxy.x;
		scene.y=(this.getHeight()-lxy.height)/2-lxy.y;
		if(scene.y>=0){
			scene.y=0;
		}else if(scene.y<=this.getHeight()-scene.height){
			scene.y=this.getHeight()-scene.height;
		}
		if(scene.x>=0){
			scene.x=0;
		}else if (scene.x<=this.getWidth()-scene.width) {
			scene.x=this.getWidth()-scene.width;
		}
		g.drawImage(scene.sceneImage, scene.x, scene.y, this);
		g.drawImage(aws.images[aws.index], aws.x+scene.x, aws.y+scene.y, this);
		g.drawImage(azwj.images[azwj.index], azwj.x+scene.x, azwj.y+scene.y, this);
		g.drawImage(chicken.images[chicken.index], chicken.x+scene.x, chicken.y+scene.y, this);
		g.drawImage(wcs.images[wcs.index], wcs.x+scene.x, wcs.y+scene.y, this);
		g.drawImage(skip.images[skip.index], skip.x+scene.x, skip.y+scene.y, this);
		g.drawImage(lxy.nowImage,lxy.x+scene.x, lxy.y+scene.y, this);
		//this.get����Ҫ�д��������²ſ����ã����Բ��ܷ��ڹ��췽������
		diagX=(this.getWidth()-dialogBox.getWidth(null))/2;
		diagY=(this.getHeight()-dialogBox.getHeight(null));
		if(isDiagShow){
			g.drawImage(dialogBox, diagX, diagY, this);
			g.setColor(Color.black);
			g.setFont(new Font("΢���ź�", Font.BOLD, 30));
			String string=diagMessages[diagIndex];
			int stringStartIndex=0;
			int stringEndIndex=15;
			int count=1;
			while(string.length()>=1){
				if(count<4){
					if(string.length()>15){
						g.drawString(string.substring(stringStartIndex,stringEndIndex), diagX+180, diagY+40+40*count);
						count+=1;
						string=string.substring(stringEndIndex);
					}else {
						g.drawString(string, diagX+180, diagY+20+40*count);
						string="";
					}	
				}
				else {		
					diagIndex--;
					count=1;
					String s1=diagMessages[diagIndex];
					diagMessages[diagIndex]=string;
					repaint();
					diagMessages[diagIndex]=s1;
				}
						
			}
		}

		}
	//���̼�����ʵ�ַ�����  source-->override/implement methods
	@Override
	public void keyTyped(KeyEvent e) {	
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode=e.getKeyCode();
//		System.out.println(keyCode);
		//��=37����=38����=39.��=40��w=87��s=83, A=65,d=68
		if (isDiagShow) {
			if (keyCode==KeyEvent.VK_SPACE) {
				diagIndex++;
				if (diagIndex>=diagMessages.length) {
					diagIndex=0;
					isDiagShow=false;
				}
			}
		}else {
			if(keyCode==KeyEvent.VK_UP){
				lxy.moveUP(scene);
				repaint();
			}else if(keyCode==KeyEvent.VK_DOWN){
				lxy.moveDown(scene);
				repaint();
			}else if(keyCode==KeyEvent.VK_LEFT){
				lxy.moveLeft(scene);
				repaint();
			}else if(keyCode==KeyEvent.VK_RIGHT){
				lxy.moveRight(scene);
				repaint();
			}else if(keyCode==KeyEvent.VK_SPACE){
				//�ж������Ƿ��ߵ����ǰ,
				int x=lxy.x;
				int y=lxy.y+lxy.height;
				if(aws.isDiagShow(x, y, lxy.dir)){
					//��������
					isDiagShow=true;		
					diagMessages =aws.toLxyMessages;
					repaint();
				}
//				int x4=wcsX;
//				int y4=wcsY+wcsiImages[wcsIndex].getHeight(null);
//				int x3=x4+20;
//				int y3=y4-20;
//				int x5=lxy.x+lxyImage.getWidth(null);
//				int y5=lxy.y+lxyImage.getHeight(null);		
//				if(lxyDir==KeyEvent.VK_RIGHT&&x5>x4-10&&x5<x3+20&&y5>y3&&y5<y4+30){
//					isDiagShow=true;
//					diagMessages =new String[]{"h","w","3","4"};
//					repaint();
//				}
				if(azwj.isDiagShow(x, y, lxy.dir)){
					diagMessages = azwj.toLxyMessages;
					isDiagShow=true;
					repaint();
				}
				
		}else if (keyCode==KeyEvent.VK_J) {
			if(lxy.y>0)
				lxy.y-=lxy.speed;
		}else if(keyCode==KeyEvent.VK_2){
			int ljcX=-400;
			int ljcY=-400;
			BufferedImage dataMap;
			try {
				Image sceneImage=ImageIO.read(new File("�ز�/��Ҵ�/0.png"));
				dataMap = ImageIO.read(new File("�ز�/��Ҵ�/RedMap.png"));
				ljcScene=new Scene(sceneImage,dataMap, 1,ljcX,ljcY,-521461);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			scene=ljcScene;
			ljcAws= new AWangShen(400-scene.x, 100-scene.y);		
			ljcAzwj =new AZWJ(100-scene.x, 100-scene.y);
			ljcWcs=new WCS(250-scene.x,150-scene.y);
			ljcChicken=new Chicken(60-scene.x, 140-scene.y);
			ljcSkip=new Skip(800-scene.x, 450-scene.y);
			isDiagShow=false;
			diagIndex=0;
			lxy =new LXY(430-scene.x,250-scene.y);
			//��ʼ�����Ｐ����
			aws=ljcAws;
			azwj=ljcAzwj;
			wcs=ljcWcs;
			skip=ljcSkip;
			chicken=ljcChicken;
			repaint();
		}else if(keyCode==KeyEvent.VK_1){
			scene=ljcMScene;
			aws=ljcMAws;
			azwj=ljcMAzwj;
			wcs=ljcMWcs;
			skip=ljcMSkip;
			chicken=ljcMChicken;
			lxy.x=430-scene.x;
			lxy.y=250-scene.y;
			repaint();
		}

		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == e.BUTTON1) {
			int mouseX = e.getX() - scene.x- lxy.width / 2;
			int mouseY = e.getY() - scene.y- lxy.height;
			System.out.println(mouseX+" "+mouseY);
			while (mouseX % lxy.speed != 0) {
				mouseX++;
			}
			while (mouseY % lxy.speed != 0) {
				mouseY++;
			}
			System.out.println(mouseX+" "+mouseY);
			final int x = mouseX ;
			final int y = mouseY ;
			if (scene.isStop(mouseX+lxy.width / 2, mouseY+lxy.height)) {
				isDiagShow = true;
				String[] a = new String[] { "����㲻����" };
				diagMessages = a;
			} else {
				new Thread() {
					@Override
					public void run() {
						int count = 0;
						while (lxy.x != x && lxy.y != y && count < 8) {
							while (lxy.x < x) {
								if (lxy.moveRight(scene))
									try {
										repaint();
										Thread.sleep(50);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								else {
									break;
								}
							}
							while (lxy.x > x) {
								if (lxy.moveLeft(scene))
									try {
										repaint();
										Thread.sleep(50);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								else {
									break;
								}
							}
							while (lxy.y < y) {
								if (lxy.moveDown(scene))
									try {
										repaint();
										Thread.sleep(50);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								else {
									break;
								}
							}
							while (lxy.y > y) {
								if (lxy.moveUP(scene))
									try {
										repaint();
										Thread.sleep(50);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								else {
									break;
								}
							}
							System.out.println(lxy.x+" "+lxy.y+" "+x+" "+y);
							count++;
						}
						lxy.moveDown(scene);
					}
				}.start();
			}
		}	
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {	
	}
	
	
}
