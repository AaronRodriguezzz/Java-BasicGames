package chessClock;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class clock extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton b2,b1,panelB1, panelB2, panelB3, panelB4;
	int minutes = 5,seconds = 30;
	int minutes1 = 5, seconds1 = 30;
	int tempMinutes = 5, tempSeconds = 30, tempMinutes1 = 5, tempSeconds1 = 30;
	int zero = 0;
	int tapCount = 0;
	boolean check = false, check1 = false, check2;
	int playCount=0;
	ImageIcon start, stop;
	
	Timer timer2 = new Timer(1000, new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			 seconds--;
			 check2 = false;
			 if(minutes == 0 && seconds <=0) {
					b2.setText("00"+ " : " + "00");
				    b2.setBackground(Color.red);
				    timer2.stop();
				    check = true;
				    b2.setEnabled(false);
			 }
			 
			 if(seconds == 0 && check == false) {
					minutes--;
					seconds = 59;
			 }
			 b2.setText("" +minutes+ " : " + seconds);
		}
	});
	
	Timer timer1 = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			seconds1--;
			 check2 = true;
			if(minutes1 == 0 && seconds1 <=0) {
				b1.setText("00"+ " : " + "00");
				b1.setBackground(Color.red);
			    timer1.stop();
			    check1 = true;
			    b1.setEnabled(false);
		   }
			if(seconds1 == 0 && check1 == false) {
				minutes1--;
				seconds1 = 59;
		   }
		 b1.setText("" +minutes1+ " : " + seconds1);
		}
	});
	
	clock(){
		
		ImageIcon restart = new ImageIcon("restart.png");
		ImageIcon timer = new ImageIcon("timer.png");
		start = new ImageIcon("play.png");
		stop = new ImageIcon("pause.png");
		
		b1 = new JButton("" + minutes + " : " + seconds);
		b1.addActionListener(this);
		b1.setBackground(Color.BLACK);
		b1.setBounds(0, 310, 400, 250);
		b1.setForeground(Color.WHITE);
		b1.setFont(new Font("ARIAL",Font.BOLD,70));
		b1.setFocusable(false);

		b2 = new JButton("" + minutes +  " : " +  seconds);
		b2.addActionListener(this);
		b2.setBackground(Color.BLACK);
		b2.setBounds(0, 0, 400, 250);
		b2.setForeground(Color.white);
		b2.setFont(new Font("ARIAL",Font.BOLD,70));
		b2.setFocusable(false);
		
	

		
		JPanel panel = new JPanel();
		panel.setBounds(0, 250, 400, 60);
		panel.setBackground(Color.white);
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));

		
		
		panelB1 = new JButton();
		panelB1.setBounds(80,5, 50, 50);
		panelB1.setIcon(restart);
		panelB1.setFocusable(false);
		panelB1.addActionListener(this);
		panelB1.setBackground(Color.WHITE);
		panelB1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
		
		panelB2 = new JButton();
		panelB2.setBounds(170,5, 50, 50);
		panelB2.setIcon(timer);
		panelB2.setFocusable(false);
		panelB2.addActionListener(this);
		panelB2.setBackground(Color.WHITE);
		panelB2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
		
		panelB3 = new JButton();
		panelB3.setBounds(260,5, 50, 50);
		panelB3.setIcon(stop);
		panelB3.setFocusable(false);
		panelB3.addActionListener(this);
		panelB3.setBackground(Color.WHITE);
		panelB3.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));

	
		
		this.add(panel);
		panel.add(panelB1);
		panel.add(panelB2);
		panel.add(panelB3);

		this.add(b2);
		this.add(b1);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,600);
		this.getContentPane().setBackground(Color.black);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource() == b2) {
				if(tapCount == 0) {
					timer2.start();
					timer1.stop();
					b1.setBackground(Color.black);
					b2.setBackground(Color.GREEN);
					b1.setEnabled(false);
					b2.setEnabled(true);
					tapCount++;
				}else if(tapCount == 1) {
					timer1.start();
					timer2.stop();
					b1.setBackground(Color.green);
					b2.setBackground(Color.BLACK);
					b2.setEnabled(false);
					b1.setEnabled(true);
					tapCount--;
				}
			}else if(e.getSource() == b1) {
				if(tapCount == 0) {
					timer2.start();
					timer1.stop();
					b1.setBackground(Color.black);
					b2.setBackground(Color.GREEN);
					b1.setEnabled(false);
					b2.setEnabled(true);					
					tapCount++;
				}else if(tapCount == 1) {
					timer1.start();
					timer2.stop();
					b1.setBackground(Color.green);
					b2.setBackground(Color.BLACK);
					b2.setEnabled(false);
					b1.setEnabled(true);				
					tapCount--;
				}
			}
			
			
			if(e.getSource() == panelB1) {
				timer1.stop();
				timer2.stop();
				b2.setText("" + tempMinutes +  " : " +  tempSeconds);
				b1.setText("" + tempMinutes1 +  " : " +  tempSeconds1);
				b1.setBackground(Color.BLACK);
				b2.setBackground(Color.BLACK);
				minutes = tempMinutes;
				seconds = tempSeconds; 
				minutes1 = tempMinutes1;
				seconds1 = tempSeconds1;
				tapCount = 0;
				playCount = 0;
				b1.setEnabled(false);
				b2.setEnabled(true);
			}else if(e.getSource() == panelB2) {
				String min = JOptionPane.showInputDialog("WHITE MINUTES");
				minutes = Integer.parseInt(min);
				String sec = JOptionPane.showInputDialog("WHITE SECONDS");
				seconds = Integer.parseInt(sec);
				tempMinutes = minutes;  
				tempSeconds = seconds;
				b2.setText("" +minutes+ " : " + seconds);

				String min1 = JOptionPane.showInputDialog("BLACK MINUTES");
				minutes1 = Integer.parseInt(min1);
				String sec1 = JOptionPane.showInputDialog("BLACK SECONDS");
				seconds1 = Integer.parseInt(sec1);
				tempMinutes1 = minutes1;
				tempSeconds1 = seconds1;
				b1.setText("" +minutes1+ " : " + seconds1);
			}else if(e.getSource() == panelB3) {
				if(playCount == 0) {
					timer1.stop();
					timer2.stop();
					panelB3.setIcon(start);
					b1.setEnabled(false);
					b2.setEnabled(false);
					playCount++;
				}else if(playCount == 1){
					if(check2 == false) {
						timer2.start();
						b1.setEnabled(false);
						b2.setEnabled(true);
					}else if(check2 == true) {
						timer1.start();
						b1.setEnabled(true);
						b2.setEnabled(false);
					}
					panelB3.setIcon(stop);
					playCount--;
				}
			}
	}
}

