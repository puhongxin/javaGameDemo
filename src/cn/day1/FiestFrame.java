package cn.day1;

import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;
/**
 * 
 * @author chen
 *
 */
public class FiestFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ʵ����һ������
		JFrame jf=new JFrame();
		//���ò���
//		jf.setBounds(100, 100,1024,768);
		jf.setSize(1024,666);
		jf.setTitle("game");
		jf.setLocationRelativeTo(null);
		//ʵ��Ĭ�Ϲر�
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//�ô��ڲ��ܸı��С
		//jf.setResizable(false);
		jf.addWindowListener(new WindowAdapter() {
			//���������ڹرյ�ʱ����Զ�ִ�и÷���
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				//stringֻ���ڵ�ȷ���Żḳֵ����
				String string=JOptionPane.showInputDialog("hello");
				JOptionPane.showMessageDialog(null, "hello2");
				int num = JOptionPane.showConfirmDialog(null, "�˳���","�˳�",JOptionPane.YES_NO_OPTION);
				if(num==JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
		Gamepanel gp=new Gamepanel();
		jf.add(gp);
		//�����߳�
		Thread paneltThread= new Thread(gp);
		paneltThread.start();
		//������̼�����
		jf.addKeyListener(gp);
		gp.addKeyListener(gp);
		//������������
		jf.addMouseListener(gp);
		gp.addMouseListener(gp);
		//����ɼ�,��дȻ����ǰ���
		jf.setVisible(true);
		
	}

}
