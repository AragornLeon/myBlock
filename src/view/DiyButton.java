package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class DiyButton extends JButton{
	
	public DiyButton(ImageIcon btic) {
		//设置按钮背景透明
		setContentAreaFilled(false);
		//改成自定义图片
		setIcon(btic);
		//去除边框
		setBorder(null);
		//取消截获按键
		setFocusable(false);
		//添加事件监听(按键点击)
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//抽象方法
				clicked();
			}
		});
	}
	public abstract void clicked();
}
