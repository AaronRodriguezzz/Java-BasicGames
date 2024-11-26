package Snakes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;
    static final int x[] = new int[GAME_UNITS];
    static final int y[] = new int[GAME_UNITS];
    int obsX[] = new int[68];
    int obsY[] = new int[68];;
    int startBodyParts = 6;
    int applesEaten = 0;
    int restartPoint = 0;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    JButton restartButton;

    GamePanel() {
        random = new Random();
        
        restartButton = new JButton("Restart");
    	restartButton.setBounds(250,400,100,50);
    	restartButton.setFont(new Font("Ink Free", Font.BOLD, 20));
    	restartButton.setForeground(Color.red);
    	restartButton.setBackground(Color.black);
    	restartButton.setBorder(new MatteBorder(0, 0, 0, 0, Color.RED));
    	restartButton.setVisible(false);
    	restartButton.addActionListener( e -> {
    		restartGame();
    	});
    	
        this.setPreferredSize(new Dimension(SCREEN_HEIGHT, SCREEN_WIDTH));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.setLayout(null);
        this.addKeyListener(new MyKeyAdapter());
        this.add(restartButton);
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void resetSnake() {
    	startBodyParts = 6;
        direction = 'R';
        for (int i = 0; i < startBodyParts; i++) {
            x[i] = 7 - i * UNIT_SIZE;
            y[i] = 7;
        }
    }

    public void restartGame() {
        timer.stop();
        applesEaten = 0;
        resetSnake();
        startGame();
        restartButton.setVisible(false);
    }
    
    public void obstacleDeployment() {
    	int r = 24;
    	for(int i=10;i<r;i+=15) {
    		
    		for(int q=i;q<=r;q++) {
    			if(i==10 || i==220) {
    				obsX[q] = q;
    				obsY[q] = q;
    			}else if(i==70 || i==160) {
    				if(q!=71 || q!=83 || q!=161 || q!= 173 ) {
    					obsX[q] = q;
        				obsY[q] = q;
    				}
    			}else {
    				if(q==i || q==r) {
    					obsX[q] = q;
        				obsY[q] = q;
    				}
    			}
    		}
    		
    		r += 15;
    	}
    }

    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
    	if (running) {
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }
            
        } else {
            gameOver(g);
        }
    	
    	
        
        g.setColor(Color.red);
        g.fillRect(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
      
       for(int i=restartPoint;i<startBodyParts;i++) {
        	if(i==0) {
        		g.setColor(Color.green);
        		g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        	}else {
        		g.setColor(Color.green);
        		g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        	}
        }	
       
       for(int i=0;i<obsX.length;i++) {
    	   g.setColor(Color.gray);
   		   g.fillOval(obsX[i], obsY[i], UNIT_SIZE, UNIT_SIZE);
       }
       
      
       
        
        
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 25));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score : " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score : ")) / 2, g.getFont().getSize());
    }
    

    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        for (int i = startBodyParts; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;		
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            startBodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollision() {
        for (int i = startBodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }

        if (x[0] < 0) {
            running = false;
        }

        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }

        if (y[0] < 0) {
            running = false;
        }

        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();            
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
        restartButton.setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollision();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
