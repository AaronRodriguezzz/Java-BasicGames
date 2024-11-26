package bombsProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class bombsFrame extends JFrame implements ActionListener {
	Random r = new Random();
	JButton[]buttons;
	JButton goldminButt, bombminButt, goldaddButt, bombaddButt, betButt;
	JLabel bombs,goldLabel, balanceTxt; 
	JTextField bettxt;
	int bombArr[];
	int x = 15, y=70;
	int bomb = 4;
	int gold = 28;
	double balance = 5000;
	int numWins = 0;
	double convertBet =0,timesBet=0;
	int betButtonCount = 1;

	bombsFrame(){
		
		buttons= new JButton[32];
		for(int i=0;i<32;i++) {
			buttons[i]=new JButton("?");
			buttons[i].setFont(new Font("ARIAL", Font.BOLD, 25));
			buttons[i].setFocusable(false);
			buttons[i].setForeground(Color.white);
			buttons[i].addActionListener(this);
			buttons[i].setBounds(x, y, 40, 40);
			buttons[i].setBackground(new Color(33, 121, 218));
			buttons[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,new Color(33, 121, 218)));
			buttons[i].setEnabled(false);
			this.add(buttons[i]);
			x+=45;
			if(i==7||i==15||i==23||i==31) {
				y+=60;
				x = 15;
			}
		}
		
		JLabel textbomb = new JLabel("BOMBS : ");
		textbomb.setForeground(Color.white);
		textbomb.setFont(new Font("ARIAL", Font.BOLD, 20));
		textbomb.setBounds(30, 320, 120, 40);
		this.add(textbomb);
		
		JLabel textgold = new JLabel("GOLDS : ");
		textgold.setForeground(Color.white);
		textgold.setFont(new Font("ARIAL", Font.BOLD, 20));
		textgold.setBounds(30, 360, 120, 40);
		this.add(textgold);
		
		bombs = new JLabel("" + bomb);
		bombs.setForeground(Color.white);
		bombs.setFont(new Font("ARIAL", Font.BOLD, 20));
		bombs.setBounds(195, 320, 40, 40);
		this.add(bombs);


		goldLabel = new JLabel("" + gold);
		goldLabel.setForeground(Color.white);
		goldLabel.setFont(new Font("ARIAL", Font.BOLD, 20));
		goldLabel.setBounds(195, 363, 40, 40);
		this.add(goldLabel);
		
		goldminButt = new JButton("-");
		goldminButt.addActionListener(this);
		goldminButt.setForeground(Color.white);
		goldminButt.setFont(new Font("ARIAL", Font.BOLD, 20));
		goldminButt.setBounds(160, 370, 25, 25);
		goldminButt.setFocusable(false);
		goldminButt.setBackground(new Color(30, 40, 50));
		goldminButt.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.white));
		this.add(goldminButt);
		
		bombminButt = new JButton("-");
		bombminButt.addActionListener(this);
		bombminButt.setForeground(Color.white);
		bombminButt.setFont(new Font("ARIAL", Font.BOLD, 20));
		bombminButt.setBounds(160, 325, 25, 25);
		bombminButt.setFocusable(false);
		bombminButt.setBackground(new Color(30, 40, 50));
		bombminButt.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.white));
		this.add(bombminButt);
		
		
		goldaddButt = new JButton("+");
		goldaddButt.addActionListener(this);
		goldaddButt.setForeground(Color.white);
		goldaddButt.setFont(new Font("ARIAL", Font.BOLD, 20));
		goldaddButt.setBounds(230, 370, 25, 25);
		goldaddButt.setFocusable(false);
		goldaddButt.setBackground(new Color(30, 40, 50));
		goldaddButt.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.white));
		this.add(goldaddButt);
		
		bombaddButt = new JButton("+");
		bombaddButt.addActionListener(this);
		bombaddButt.setForeground(Color.white);
		bombaddButt.setFont(new Font("ARIAL", Font.BOLD, 20));
		bombaddButt.setBounds(230, 325, 25, 25);
		bombaddButt.setFocusable(false);
		bombaddButt.setBackground(new Color(30, 40, 50));
		bombaddButt.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.white));
		this.add(bombaddButt);
		
		JLabel bal = new JLabel("BALANCE : P");
		bal.setForeground(Color.white);
		bal.setFont(new Font("ARIAL", Font.BOLD, 20));
		bal.setBounds(30, 400, 160, 40);
		this.add(bal);
		
		balanceTxt = new JLabel("" + balance);
		balanceTxt.setForeground(Color.white);
		balanceTxt.setFont(new Font("ARIAL", Font.BOLD, 20));
		balanceTxt.setBounds(160, 400, 120, 40);
		this.add(balanceTxt);
		
		betButt = new JButton("BET");
		betButt.addActionListener(this);
		betButt.setForeground(Color.white);
		betButt.setFont(new Font("ARIAL", Font.BOLD, 20));
		betButt.setBounds(30, 450, 140, 40);
		betButt.setFocusable(false);
		betButt.setBackground(new Color(30, 40, 50));
		betButt.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.white));
		this.add(betButt);
		
		bettxt = new JTextField("0");
		bettxt.setOpaque(false);
		bettxt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,Color.white));
		bettxt.setBounds(190, 460, 120, 30);
		bettxt.setForeground(Color.white);
		bettxt.setFont(new Font("ARIAL", Font.BOLD, 20));
		bettxt.setHorizontalAlignment(SwingConstants.CENTER);
		bettxt.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				if(bettxt.getText().equals("0")) bettxt.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(bettxt.getText().isEmpty()) bettxt.setText("0");
			}	
		});
		this.add(bettxt);
		
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,550);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(30, 40, 50));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static boolean contains(int[] arr, int num) {
	    for (int i = 0; i < arr.length; i++) {
	        if (arr[i] == num) {
	            return true;
	        }
	    }
	    return false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == betButt) {
			numWins=0;
			convertBet = Double.parseDouble(bettxt.getText());
			
			if(betButtonCount ==1) {
				if(balance < convertBet || bettxt.getText().isEmpty()) {
					bettxt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,Color.RED));
					bettxt.setForeground(Color.RED);
				}else {
					bettxt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,Color.white));
					bettxt.setForeground(Color.white);
					balance -= convertBet;
					betButtonCount++;

					for(int loopback = 0;loopback<buttons.length;loopback++) {
						buttons[loopback].setBackground(new Color(33, 121, 218));
						buttons[loopback].setText("?");
						buttons[loopback].setEnabled(true);
					}
				
					betButt.setText(bettxt.getText());
					balanceTxt.setText(""+balance);
				 
					bombArr = new int[bomb];	
					for(int i=0;i<bombArr.length;i++) {
						int randNums = r.nextInt(31);
						if(contains(bombArr,randNums)) i--;
						else bombArr[i] = randNums;
					}
				}
			}else {
				if(betButt.getText().equals("BET")) {
				}else {
					double bettotal = Double.parseDouble(betButt.getText());
					balance += bettotal ;
					balanceTxt.setText(""+balance);
					betButt.setText("BET");
					bettxt.setText("0");
					betButtonCount--;
					System.out.println(bettotal);

				}
			}
		}
		for(int i=0;i<buttons.length;i++) {
			if(e.getSource() == buttons[i] ) {
				numWins++;
				
				for(int j=0;j<bombArr.length;j++) {
					if(i == bombArr[j]) {
						betButt.setText("BET");
						bettxt.setText("0");
						
						for(int q=0;q<buttons.length;q++) {
							buttons[q].setEnabled(false);
							buttons[q].setBackground(Color.yellow);
							buttons[q].setText("O");
						}
						
						for(int f=0;f<bombArr.length;f++) {
							buttons[bombArr[f]].setBackground(Color.RED);
							buttons[bombArr[f]].setText("X");
						}
						
						buttons[i].setBackground(Color.RED);
						buttons[i].setText("X");
						betButtonCount=1;

						break;
					}else {
						buttons[i].setBackground(Color.YELLOW);
						buttons[i].setText("O");

						if(numWins == 1) {
							timesBet = (convertBet * 1.3);
							betButt.setText(""+timesBet);
						}else if(numWins == 2) {
							timesBet = convertBet *1.4;
							betButt.setText(""+timesBet);
						}else if(numWins == 3) {
							timesBet = convertBet *1.6;
							betButt.setText(""+timesBet);
						}else if(numWins == 4) {
							timesBet = convertBet *2.0;
							betButt.setText(""+timesBet);
						}else if(numWins == 5) {
							timesBet = convertBet *2.3;
							betButt.setText(""+timesBet);
						}else if(numWins == 6) {
							timesBet = convertBet *2.6;
							betButt.setText(""+timesBet);
						}else if(numWins == 7) {
							timesBet = convertBet *3;
							betButt.setText(""+timesBet);
						}else if(numWins == 8) {
							timesBet = convertBet *3.4;
							betButt.setText(""+timesBet);
						}else if(numWins == 9) {
							timesBet = convertBet *3.8;
							betButt.setText(""+timesBet);
						}else if(numWins == 10) {
							timesBet = convertBet *4.2;
							betButt.setText(""+timesBet);
						}else if(numWins == 11) {
							timesBet = convertBet *4.5;
							betButt.setText(""+timesBet);
						}else if(numWins == 12) {
							timesBet = convertBet *5.0;
							betButt.setText(""+timesBet);
						}else if(numWins == 13) {
							timesBet = convertBet *5.3;
							betButt.setText(""+timesBet);
						}else if(numWins == 14) {
							timesBet = convertBet *6.0;
							betButt.setText(""+timesBet);
						}else if(numWins == 15) {
							timesBet = convertBet *6.5;
							betButt.setText(""+timesBet);
						}else if(numWins == 16) {
							timesBet = convertBet *7.0;
							betButt.setText(""+timesBet);
						}else if(numWins == 17) {
							timesBet = convertBet *7.5;
							betButt.setText(""+timesBet);
						}else if(numWins == 18) {
							timesBet = convertBet *8.0;
							betButt.setText(""+timesBet);
						}else if(numWins == 19) {
							timesBet = convertBet *8.7;
							betButt.setText(""+timesBet);
						}else if(numWins == 20) {
							timesBet = convertBet *9.0;
							betButt.setText(""+timesBet);
						}else if(numWins == 21) {
							timesBet = convertBet *9.8;
							betButt.setText(""+timesBet);
						}else if(numWins == 22) {
							timesBet = convertBet *10.3;
							betButt.setText(""+timesBet);
						}else if(numWins == 23) {
							timesBet = convertBet *12.0;
							betButt.setText(""+timesBet);
						}else if(numWins == 24) {
							timesBet = convertBet *12.6;
							betButt.setText(""+timesBet);
						}else if(numWins == 25) {
							timesBet = convertBet *13.3;
							betButt.setText(""+timesBet);
						}else if(numWins == 26) {
							timesBet = convertBet *14.4;
							betButt.setText(""+timesBet);
						}else if(numWins == 27) {
							timesBet = convertBet *15.7;
							betButt.setText(""+timesBet);
						}else if(numWins == 28) {
							timesBet = convertBet *16.5;
							betButt.setText(""+timesBet);
						}else if(numWins == 29) {
							timesBet = convertBet *17.4;
							betButt.setText(""+timesBet);
						}else if(numWins == 30) {
							timesBet = convertBet *18.0;
							betButt.setText(""+timesBet);
						}
					}
				}
				
			}
		}
			
	}
	
	

}
