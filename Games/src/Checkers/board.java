package Checkers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class board extends JFrame implements ActionListener {
	
	JButton[]buttons = new JButton[32];
	JButton startButton, whiteButt, blackButt;
	JPanel timePanel;
	ImageIcon white = new ImageIcon();
	ImageIcon black = new ImageIcon();
	ImageIcon blank = new ImageIcon();
	JLabel colon1, colon2;
	JTextField p1Minute, p1Sec; 
	JTextField p2Minute, p2Sec;
	Thread thread,thread1;

	int x=80, y=70;
	int blackCount = 20;
	int three=3, four=4, five=5;
	int count = 1;
	int tempVal,tempVal2;
	int p1Min,p2Min;
	int p1Secs,p2Secs;
	int[]whitePosition = new int[12];
	int[]blackPosition = new int[12];
 	
	boolean check = true;
	boolean posCheck = false, posCheck2 = false;
	boolean empSpace = true, empSpace2 = true;

	board(){
		black = new ImageIcon("black.png");
		white = new ImageIcon("white.png");
		blank = new ImageIcon("");
		
		timePanel = new JPanel();
		timePanel.setBounds(0,0,700,70);
		timePanel.setBackground(Color.black);
		timePanel.setLayout(null);
		add(timePanel);
		
		startButton = new JButton("START");
		startButton.setBounds(270,0,100,70);
		startButton.setFont(new Font("ARIAL",Font.PLAIN,20));
		startButton.setFocusable(false);
		startButton.addActionListener(this);
		startButton.setForeground(Color.white);
		startButton.setBackground(Color.black);
		timePanel.add(startButton);
		
		whiteButt = new JButton("P1");
		whiteButt.setBounds(0,0,70,70);
		whiteButt.setFont(new Font("ARIAL",Font.PLAIN,20));
		whiteButt.setFocusable(false);
		whiteButt.addActionListener(this);
		whiteButt.setForeground(Color.white);
		whiteButt.setBackground(Color.black);
		whiteButt.setEnabled(false);
		timePanel.add(whiteButt);
		
		p1Minute = new JTextField("02");
		p1Minute.setBounds(120,15,50,50);
		p1Minute.setFont(new Font("ARIAL", Font.PLAIN,30));
		p1Minute.setForeground(Color.white);
		p1Minute.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.black));
		p1Minute.setOpaque(false);
		timePanel.add(p1Minute);
		
		colon1 = new JLabel();
		colon1.setText(":");
		colon1.setFont(new Font("ARIAL", Font.PLAIN,40));
		colon1.setBounds(160,10,40,50);
		colon1.setForeground(Color.white);
		timePanel.add(colon1);
		
		p1Sec = new JTextField("00");
		p1Sec.setBounds(180,15,50,50);
		p1Sec.setFont(new Font("ARIAL", Font.PLAIN,30));
		p1Sec.setForeground(Color.white);
		p1Sec.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.black));
		p1Sec.setOpaque(false);
		timePanel.add(p1Sec);
		
		blackButt = new JButton("P2");
		blackButt.setBounds(565,0,70,70);
		blackButt.setFont(new Font("ARIAL",Font.PLAIN,20));
		blackButt.addActionListener(this);
		blackButt.setFocusable(false);
		blackButt.setForeground(Color.white);
		blackButt.setBackground(Color.black);
		blackButt.setEnabled(false);
		timePanel.add(blackButt);	
		
		p2Minute = new JTextField("02");
		p2Minute.setBounds(410,15,50,50);
		p2Minute.setFont(new Font("ARIAL", Font.PLAIN,30));
		p2Minute.setForeground(Color.white);
		p2Minute.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.black));
		p2Minute.setOpaque(false);
		timePanel.add(p2Minute);

		colon2 = new JLabel();
		colon2.setText(":");
		colon2.setFont(new Font("ARIAL", Font.PLAIN,40));
		colon2.setBounds(450,10,40,50);
		colon2.setForeground(Color.white);
		timePanel.add(colon2);
		
		p2Sec = new JTextField("00");
		p2Sec.setBounds(470,15,50,50);
		p2Sec.setFont(new Font("ARIAL", Font.PLAIN,30));
		p2Sec.setForeground(Color.white);
		p2Sec.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.black));
		p2Sec.setOpaque(false);
		timePanel.add(p2Sec);
		
		for(int i=0;i<buttons.length;i++) {
			buttons[i] = new JButton();
			buttons[i].setBounds(x,y,80,80);
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
			buttons[i].setBackground(Color.GREEN);
			buttons[i].setEnabled(false);
			add(buttons[i]);

			if(x==560) {
				y+=80;
				x=0;
			}else if(x==480) {
				y+=80;
				x=80;
			}else {
				x+=160;
			}
		}

		this.setSize(650,750);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	void p1timer() {	
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				p1Min = Integer.parseInt(p1Minute.getText());
				p1Secs = Integer.parseInt(p1Sec.getText());
				while(check == true) {
					try {
						if(p1Secs == 0) {
							p1Min--;
							p1Minute.setText(""+p1Min);
							p1Secs = 59;
							p1Sec.setText(""+p1Secs);
						}else {
							p1Secs--;
							Thread.sleep(1000);
							p1Sec.setText(""+p1Secs);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}
	
	void p2timer() {
		thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				p2Min = Integer.parseInt(p2Minute.getText());
				p2Secs = Integer.parseInt(p2Sec.getText());
				while(check == false) {
					try {
						if(p2Secs == 0) {
							p2Min--;
							p2Minute.setText(""+p2Min);
							p2Secs = 59;
							p2Sec.setText(""+p2Secs);
						}else {
							p2Secs--;
							Thread.sleep(1000);
							p2Sec.setText(""+p2Secs);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread1.start();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==startButton) {
			p1timer();
			startButton.setEnabled(false);
			whiteButt.setEnabled(true);
			
			for(int i=0;i<12;i++) {
				buttons[i].setIcon(white);
				whitePosition[i] = i;
				blackPosition[i] = blackCount;
				blackCount++;
			}
			for(int i=20;i<32;i++) buttons[i].setIcon(black);
			for(int i=0;i<buttons.length;i++) buttons[i].setEnabled(true);
			for(int i=0;i<blackPosition.length;i++) buttons[blackPosition[i]].setEnabled(false);
		}
		
		for(int i=0;i<buttons.length;i++) {
			if(e.getSource()==buttons[i]) {
				for(int j=0;j<buttons.length;j++) buttons[j].setBackground(Color.green);
				if(check==true) {
					switch(count) {
					case 1:
					if(i==3||i==11||i==19||i==27) { //right side
						tempVal = i;
						checkPlacement(i+4,i);
						if(posCheck && posCheck2 && empSpace || empSpace2) buttons[i+4].setBackground(Color.red);
						placetoMove(i+4,0);
					}else if(i==4||i==12||i==20) { //left side
						tempVal = i;
						checkPlacement(i+4,i);
						if(posCheck && posCheck2 && empSpace || empSpace2) buttons[i+4].setBackground(Color.red);
						placetoMove(i+4,0);
					}else if(i>3&&i<8||i>11&&i<16||i>19&&i<24) { //middles
						tempVal = i;
						checkPlacement(i+3,i);
						checkPlacement(i+4,i);
						if(posCheck && posCheck2 && empSpace || empSpace2) buttons[i+3].setBackground(Color.red);
						if(posCheck && posCheck2 && empSpace || empSpace2) buttons[i+4].setBackground(Color.red);
						placetoMove(i+3,i+4);
					}else {
						tempVal = i;
						checkPlacement(i+4,i);
						checkPlacement(i+5,i);
						if(posCheck && posCheck2 && empSpace || empSpace2) buttons[i+4].setBackground(Color.red);
						if(posCheck && posCheck2 && empSpace || empSpace2) buttons[i+5].setBackground(Color.red);
						placetoMove(i+4,i+5);
					}
					count++;
					break;
					
					case 2:
					buttons[i].setIcon(white);
					buttons[tempVal].setIcon(blank);
					for(int j=0;j<whitePosition.length;j++) {
						if(whitePosition[j] == tempVal) {
							int temp = whitePosition[tempVal];
							whitePosition[j] = temp;
							break;
						}
					}
					
					for(int p=0;p<whitePosition.length;p++) {
						System.out.println(whitePosition[p]);
					}
					count--;
					break;
					
					}
				}else if(check == false){
					if(i==27||i==19||i==11) {                                                                                                                                                                                                 
						if(posCheck && posCheck2 && empSpace || empSpace2) buttons[i-4].setBackground(Color.red);
					}else if(i==28||i==20|i==12||i==4) {
						if(posCheck && posCheck2 && empSpace || empSpace2) buttons[i-4].setBackground(Color.red);
					}else if(i>23&&i<27||i>14&&i<19||i>7&&i<11) {
						if(posCheck && posCheck2 && empSpace || empSpace2) buttons[i-3].setBackground(Color.red);
						if(posCheck && posCheck2 && empSpace || empSpace2) buttons[i-4].setBackground(Color.red);
					}else {
						if(posCheck && posCheck2 && empSpace || empSpace2) buttons[i-4].setBackground(Color.red);
						if(posCheck && posCheck2 && empSpace || empSpace2) buttons[i-5].setBackground(Color.red);
					}
					
				}
			}
		}
		
		if(e.getSource()==whiteButt) {
			check = false; 
			whiteButt.setEnabled(false);
			blackButt.setEnabled(true);
			p1timer();
			p2timer();
			for(int i=0;i<blackPosition.length;i++) buttons[blackPosition[i]].setEnabled(true);
			for(int i=0;i<whitePosition.length;i++) buttons[whitePosition[i]].setEnabled(false);
		}
		
		if(e.getSource()==blackButt) {
			check = true; 
			whiteButt.setEnabled(true);
			blackButt.setEnabled(false);
			p1timer();
			p2timer();
			for(int i=0;i<blackPosition.length;i++) buttons[blackPosition[i]].setEnabled(false);
			for(int i=0;i<whitePosition.length;i++) buttons[whitePosition[i]].setEnabled(true);
		}
	}
	
	void checkPlacement(int temp1, int temp2) {
		for(int i=0;i<whitePosition.length;i++) {
			if(whitePosition[i] == temp1) {
				posCheck = false;
				break;
			}else posCheck = true;
			
			if(temp2 == whitePosition[i]) {
				empSpace = true;
				break;
			}else empSpace = false;
		}	
		
		for(int i=0;i<blackPosition.length;i++) {
			if(blackPosition[i] == temp1) {
				posCheck2 = false;
				break;
			}else posCheck2 = true;
			
			if(temp2 == blackPosition[i]) {
				empSpace2 = true;
				break;
			}else empSpace2 = false;
		}
	}
	
	void placetoMove(int temp1, int temp2) {
		
		for(int i=0;i<buttons.length;i++) {
			if(temp1 == i || temp2 == i) buttons[i].setEnabled(true);
			else buttons[i].setEnabled(false);
		}
	}
	
	public static void main(String[] args) {
		new board();
	}

}
