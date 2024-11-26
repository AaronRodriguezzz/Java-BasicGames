package snake_and_ladder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class boardFrame extends JFrame implements ActionListener {
	JPanel []buttons;
	JButton startButt;
	JLabel numOne, numTwo, x,y,total;
	JLabel rookP, knightP;
	Random r = new Random();
	int tempX = 0, tempY = 480;
	boolean check = true;
	Color color1 = new Color(0x00FF00), color2 = Color.white;
	int randOne = 0,randOne2 = 0;
	int randTwo = 0, randTwo2 = 0;
	int player = 0;
	int totalP1 = 0, totalP2 = 0;
	int moveTotal1=0, moveTotal2 =0;
	int temp1 =0, temp2=0;
	int fTotalX = 10, fTotal2X = 10; 
	int fTotalY = 500, fTotal2Y = 470; 

	
	boardFrame(){
		ImageIcon longLadder = new ImageIcon("longLadder.png");
		ImageIcon shortLadd = new ImageIcon("shortLaddB.png");
		ImageIcon midLadd = new ImageIcon("midLadder.png");
		ImageIcon midLaddTop = new ImageIcon("medLadderTop.png");
		ImageIcon snakeLong = new ImageIcon("snakeL.png");
		ImageIcon snakeMed = new ImageIcon("snakeM.png");
		ImageIcon rookPlay = new ImageIcon("rook.png");
		ImageIcon knightPlay = new ImageIcon("knight.png");


		JPanel board = new JPanel();
		board.setBounds(0,0,500,540);
		board.setBackground(Color.gray);
		board.setLayout(null);
		
		JLabel bigLadder = new JLabel();
		bigLadder.setIcon(longLadder);
		bigLadder.setBounds(25,0,500,540);
		
		JLabel shortLadder = new JLabel();
		shortLadder.setIcon(shortLadd);
		shortLadder.setBounds(55,70,500,540);
		
		JLabel shortLadderSecond = new JLabel();
		shortLadderSecond.setIcon(shortLadd);
		shortLadderSecond.setBounds(-140,-50,500,540);
		
		JLabel shortLadderThird = new JLabel();
		shortLadderThird.setIcon(shortLadd);
		shortLadderThird.setBounds(55,-230,500,540);
		
		JLabel middLadder = new JLabel();
		middLadder.setIcon(midLadd);
		middLadder.setBounds(70,-120,500,540);
		
		JLabel middLadder2 = new JLabel();
		middLadder2.setIcon(midLadd);
		middLadder2.setBounds(-280,-240,500,540);
		
		JLabel middLadderT = new JLabel();
		middLadderT.setIcon(midLaddTop);
		middLadderT.setBounds(-15,10,500,540);
	
		JLabel snakeL = new JLabel();
		snakeL.setIcon(snakeLong);
		snakeL.setBounds(10,30,500,540);
		
		JLabel snakeM = new JLabel();
		snakeM.setIcon(snakeMed);
		snakeM.setBounds(25,-20,500,540);
		
		rookP = new JLabel();
		rookP.setIcon(rookPlay);
		rookP.setBounds(10,500,50,50);
		
		knightP = new JLabel();
		knightP.setIcon(knightPlay);
		knightP.setBounds(10,470,50,50); //460 max //+50 PER TILE
		
		board.add(bigLadder);
		board.add(shortLadder);
		board.add(shortLadderSecond);
		board.add(shortLadderThird);
		board.add(middLadder);
		board.add(middLadder2);
		board.add(middLadderT);
		board.add(snakeL);
		board.add(snakeM);
		
		board.add(rookP);
		board.add(knightP);


		buttons = new JPanel[100];
		for(int i=0;i<100;i++) {
			buttons[i] = new JPanel();
			buttons[i].setLayout(null);
			int mod = i % 2;
			if(mod == 0) {
				buttons[i].setBackground(color1);
			}else if(mod != 0){
				buttons[i].setBackground(color2);
			}
			buttons[i].setBounds(tempX, tempY, 50, 60);
			tempX+=50;
			board.add(buttons[i]);
			if(i == 9 || i == 29||i == 39||i == 49||i == 59||i == 69||i == 79||i == 89||i == 99&& check == true) {
				tempY-=60;
				check = false;
				tempX=0;
				if(i == 9||i == 39||i == 59||i == 79||i == 99) {
					color1= Color.white;
					color2=Color.green;
				}else {
					color1= new Color(0x00FF00);
					color2=Color.white;
				}
			}
		}
		
		numOne = new JLabel();
		numOne.setText("" + randOne);
		numOne.setBounds(70,60,70,70);
		numOne.setForeground(new Color(0x00FF00));
		numOne.setFont(new Font("Consoles", Font.BOLD, 50));
		numOne.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,new Color(0x00FF00)));

		
		numTwo = new JLabel();
		numTwo.setText("" + randTwo);
		numTwo.setBounds(160,60,70,70);
		numTwo.setForeground(new Color(0x00FF00));
		numTwo.setFont(new Font("Consoles", Font.BOLD, 50));
		numTwo.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,new Color(0x00FF00)));

		
		startButt = new JButton("START");
		startButt.addActionListener(this);
		startButt.setBounds(10,450,265,80);
		startButt.setFocusable(false);
		startButt.setBackground(Color.black);
		startButt.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,new Color(0x00FF00)));
		startButt.setForeground(new Color(0x00FF00));
		startButt.setFont(new Font("Consoles", Font.BOLD, 50));
		
		total = new JLabel();
		total.setText("TOTAL : ");
		total.setBounds(10,390,265,50);
		total.setFont(new Font("Consoles", Font.BOLD, 30));
		total.setForeground(new Color(0x00FF00));
		total.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,new Color(0x00FF00)));

		JPanel dice = new JPanel();
		dice.setBounds(500,0,290,540);
		dice.setBackground(Color.black);
		dice.setLayout(null);
		dice.add(startButt);
		dice.add(numOne);
		dice.add(numTwo);
		dice.add(total);
		
		this.add(board);
		this.add(dice);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,580);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButt) {
			if(player == 0) {
				player++;
				startButt.setText("PLAYER 1");
			}
			
			if(player == 1) {
				System.out.println("one");
				startButt.setText("PLAYER 2");
				randOne = r.nextInt(6)+1;
				randTwo = r.nextInt(6)+1;
				numOne.setText(" " +randOne);
				numTwo.setText(" "+ randTwo);
				totalP1 = (randOne + randTwo)*50;
				fTotalX+=totalP1;
				sleepOne();
				player++;
			}else if(player == 2) {
				System.out.println("two");
				startButt.setText("PLAYER 1");
				randOne2 = r.nextInt(6)+1;
			 	randTwo2 = r.nextInt(6)+1;
			 	numOne.setText(" " +randOne2);
				numTwo.setText(" "+ randTwo2);
				totalP2 = (randOne2 + randTwo2)*50;
				fTotal2X+=totalP2;
				sleepTwo();
				player--;
			}
		}
	}
	
	public void sleepOne() {	
			if(fTotalX > 460) {
				fTotalX -= 500;
				fTotalY -= 60; 
				rookP.setBounds(fTotalX,fTotalY,50,50);
			}else if(fTotalX == 460) {
				rookP.setBounds(fTotalX,fTotalY,50,50);
			}else {
				rookP.setBounds(fTotalX,fTotalY,50,50);
			}
			
			if(fTotalX == 310 && fTotalY == 500) { //first ladder
				fTotalX = 360;
				fTotalY = 380;
				rookP.setBounds(fTotalX,fTotalY,50,50);
			}
			
			if(fTotalX == 260 && fTotalY == 380) { //second ladder
				fTotalX = 160;
				fTotalY = 160;
				rookP.setBounds(fTotalX,fTotalY,50,50);
			}
			
			if(fTotalX == 110 && fTotalY == 380) { //third ladder
				fTotalX = 160;
				fTotalY = 240;
				rookP.setBounds(fTotalX,fTotalY,50,50);
			}
			
			if(fTotalX == 260 && fTotalY == 260) {
				fTotalX = 210;
				fTotalY = 60;
				rookP.setBounds(fTotalX,fTotalY,50,50);
			}
			
			if(fTotalX == 410 && fTotalY == 260) {
				fTotalX = 460;
				fTotalY = 180;//
				rookP.setBounds(fTotalX,fTotalY,50,50);
			}
			
			if(fTotalX == 410 && fTotalY == 200) {
				fTotalX = 360;
				fTotalY = 60;
				rookP.setBounds(fTotalX,fTotalY,50,50);
			}
			
			if(fTotalX == 310 && fTotalY == 180) {
				fTotalX = 160;
				fTotalY = 440;
				rookP.setBounds(fTotalX,fTotalY,50,50);
			}
			
			if(fTotalX == 410 && fTotalY == 120) {
				fTotalX = 310;
				fTotalY = 260;
				rookP.setBounds(fTotalX,fTotalY,50,50);
			}
			
			System.out.println(fTotalX);
			System.out.println(fTotalY);
			
			int total0 = randOne + randTwo;
			total.setText("TOTAL : " + total0);		
	}
	
	public void sleepTwo() {

				if(fTotal2X > 460) {
					fTotal2X -= 500;
					fTotal2Y -= 60;
					knightP.setBounds(fTotal2X,fTotal2Y,50,50); 
				}else if(fTotal2X == 460) {
					knightP.setBounds(fTotal2X,fTotal2Y,50,50); 
				}else {
					knightP.setBounds(fTotal2X,fTotal2Y,50,50); 
				}
				
				if(fTotal2X == 310 && fTotal2Y == 470) { //first ladder
					fTotal2X = 360;
					fTotal2Y = 380;
					knightP.setBounds(fTotal2X,fTotal2Y,50,50);
				}
				
				if(fTotal2X == 260 && fTotal2Y == 350) { //second ladder
					fTotal2X = 160;
					fTotal2Y = 110;
					knightP.setBounds(fTotal2X,fTotal2Y,50,50);
				}
				
				if(fTotal2X == 110 && fTotal2Y == 350) { //third ladder
					fTotal2X = 160;
					fTotal2Y = 240;
					knightP.setBounds(fTotal2X,fTotal2Y,50,50);
				}
				
				if(fTotal2X == 260 && fTotal2Y == 230) {
					fTotal2X = 210;
					fTotal2Y = 60;
					knightP.setBounds(fTotal2X,fTotal2Y,50,50);
				}
				
				if(fTotal2X == 410 && fTotal2Y == 230) {
					fTotal2X = 460;
					fTotal2Y = 120;
					knightP.setBounds(fTotal2X,fTotal2Y,50,50);
				}
				
				if(fTotal2X == 410 && fTotal2Y == 170) {
					fTotal2X = 360;
					fTotal2Y = 60;
					knightP.setBounds(fTotal2X,fTotal2Y,50,50);
				}
				
				if(fTotalX == 310 && fTotalY == 120) {
					fTotal2X = 160;
					fTotal2Y = 410;
					knightP.setBounds(fTotal2X,fTotal2Y,50,50);
				}
				
				if(fTotalX == 410 && fTotalY == 60) {
					fTotal2X = 310;
					fTotal2Y = 240;
					knightP.setBounds(fTotal2X,fTotal2Y,50,50);
				}
				
				System.out.println(fTotal2X);
				System.out.println(fTotal2Y);
				
				int total0 = randOne2 + randTwo2;
				total.setText("TOTAL : " + total0);
		
	}
	
}
