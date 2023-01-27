package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Client;
import controller.Main;
import controller.Start;
import model.GameData;
public class AlertDialog extends JDialog{
	//弹窗模式
	public static final int OVER = 0;
	public static final int LOGIN = 1;
	public static final int SET = 2;
	
	//实例化自己
	static AlertDialog alertDialog = null;
	//工厂模式，实现父类
	static Father father;
	//需要修改的窗口对象
	JPanel mainpl;
	JLabel buttonLabel;
	GameData gamedata;
	MainWin mwn;
	
	//私有化构造函数
	private AlertDialog(MainWin mwn,GameData gamedata) {
		super(mwn,true);
		setSize(340,248);
		setLocationRelativeTo(mwn);
		this.mwn = mwn;
		this.gamedata = gamedata;
		//设置背景
		JLabel bgLabel = new JLabel(new ImageIcon("img/alert.png"));
		getContentPane().add(bgLabel);
		setUndecorated(true);
		//设置按钮字体
		buttonLabel = new JLabel("默认",JLabel.CENTER);
		buttonLabel.setFont(new Font("华文彩云", Font.BOLD, 21));
		buttonLabel.setForeground(new Color(252,30,211));
		buttonLabel.setBounds(230, 216, 95, 29);
		getLayeredPane().add(buttonLabel);
		
		//中央画布
		mainpl = new JPanel();
		mainpl.setBounds(0, 48, 340, 195);
		mainpl.setLayout(null);
		mainpl.setOpaque(false);
		getLayeredPane().add(mainpl);
		
		//添加点击事件
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}	
			@Override
			public void mousePressed(MouseEvent e) {}		
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getX()>257 && e.getX()<324) {
					if (e.getY()>16 && e.getY()<48) {
						closeDialog();
					}
				}
				if (e.getX()>230 && e.getX()<324) {
					if (e.getY()>216 && e.getY()<243) {
						//closeDialog();工厂模式
						father.clicked();
					}
				}
			}
		});
		
	}
	//对外接口
	public static AlertDialog getInstance(MainWin mwn,GameData gd, int mode) {
		synchronized (gd) {
			if (alertDialog == null) {
				alertDialog = new AlertDialog(mwn, gd);
			}
			switch (mode) {
				case OVER: 
					father = new OverChanger(alertDialog);
					break;
				case LOGIN: 
					father = new LogiChanger(alertDialog);
					break;
				case SET: 
					father = new SetChanger(alertDialog);
			}
			father.changeView();
			return alertDialog;
		}
	}
	
	//打开弹窗的方法
	public void openDialog() {
		setVisible(true);
	}
	public void closeDialog() {
		setVisible(false);
	}
	
}

interface changeable{
	public void clicked();
	public void changeView();
}

abstract class Father implements changeable{};
//结束弹窗
class OverChanger extends Father{
	AlertDialog ad;
	
	public OverChanger(AlertDialog ad) {
		this.ad = ad;
	}

	@Override
	public void clicked() {
		ad.gamedata.playerData.add(ad.gamedata.score);
		ad.gamedata.playerData.getInfo();
		ad.gamedata.score = 0;
		ad.mwn.repaint();
		ad.closeDialog();
	}

	@Override
	public void changeView() {
		ad.buttonLabel.setText("OK");
		JLabel overLabel = new JLabel("Game Over! Your Score is: " + ad.gamedata.score, JLabel.CENTER);
		overLabel.setFont(new Font("Consolas", Font.BOLD, 21));
		overLabel.setForeground(new Color(252,30,252));
		overLabel.setBounds(0, 0, 340, 130);
		ad.mainpl.removeAll();
		ad.mainpl.add(overLabel);
		//return false;
		
	}
	
}
//登录弹窗
class LogiChanger extends Father{
	AlertDialog ad;
	JLabel noteLabel;
	JPasswordField passField;
	JTextField nameField;
	public LogiChanger(AlertDialog ad) {
		
		this.ad = ad;
	}

	@Override
	public void clicked() {
		//System.out.println("登录");
		if (nameField.getText().equals("")) {
			noteLabel.setText("用户名不能为空");
		}else {
			if (ad.gamedata.playerData.Login(nameField.getText(),new String(passField.getPassword()))) {
				ad.gamedata.name = nameField.getText();
				ad.closeDialog();
			} else {
				noteLabel.setText("名称已经被注册，您需要输入正确密码");
			}
		}

	}

	@Override
	public void changeView() {
		ad.buttonLabel.setText("登录/注册");
		ad.buttonLabel.setFont(new Font("黑体", Font.BOLD, 14));
		//名称和密码
		JLabel nameLabel = new JLabel("Name",JLabel.CENTER);
		nameLabel.setFont(new Font("Consolas", Font.BOLD, 21));
		nameLabel.setBounds(46, 53, 81, 30);
		JLabel passLabel = new JLabel("Password" ,JLabel.CENTER);
		passLabel.setFont(new Font("Consolas", Font.BOLD,16));
		passLabel.setBounds(46, 90, 81, 30);
		//输入框
		nameField = new JTextField(ad.gamedata.name,20);
		passField = new JPasswordField(20);
		nameField.setBounds(142, 53, 135,30);
		passField.setBounds(142, 90, 135,30);
		//提示区
		noteLabel = new JLabel(ad.gamedata.name.equals("") ? "" : "该账号已经登录");
		noteLabel.setForeground(new Color(252,0,211));
		noteLabel.setBounds(35, 110, 300, 30);
		ad.mainpl.removeAll();
		ad.mainpl.add(nameLabel);
		ad.mainpl.add(passLabel);
		ad.mainpl.add(nameField);
		ad.mainpl.add(passField);
		ad.mainpl.add(noteLabel);
		//return false;
	}
}

//设置弹窗
class SetChanger extends Father{
	AlertDialog ad;
	
	public SetChanger(AlertDialog ad) {
		this.ad = ad;
	}

	@Override
	public void clicked() {
		
		ad.closeDialog();
	}

	@Override
	public void changeView() {
		JComboBox<String> modList = new JComboBox<String>();
		ad.buttonLabel.setText("确定");
		ad.buttonLabel.setFont(new Font("黑体", Font.BOLD, 14));
		modList.addItem("-单机模式-");
		modList.addItem("-对战模式-");
		modList.setBounds(120, 75, 142, 23);
		modList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((modList.getSelectedIndex()==0) ? false : true) {
					try {
//						Client client = new Client();
//						Start.startMusic();
//						Start.startGame();
//						setMod(true);
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
					//Start.startGame();
					System.out.println("index is: " + modList.getSelectedIndex() + ", istwoMod");
					resetMode();
				}
			}
//			private void setMod(boolean isMod_2,int score) {
//				// TODO Auto-generated method stub
//				if (isMod_2) {
//					Client.sendScore(score);
//				}
//			}
			private void resetMode() {
				// TODO Auto-generated method stub
				//ad.mainpl.removeAll();
				ad.mwn.repaint();
			}
		});
		
		ad.mainpl.removeAll();
		ad.mainpl.add(modList);
//		boolean mod = (modList.getSelectedIndex()==0) ? false : true;
//		return mod;
	}

	
	
}


