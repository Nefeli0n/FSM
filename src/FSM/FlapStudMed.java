package FSM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class FlapStudMed implements ActionListener, MouseListener {

	public static FlapStudMed fsm;
	public final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public Renderer rend;
	public Rectangle s;
	public ArrayList<Rectangle> columns;
	public Random rand;
	public int ticks, yMotion;
	public double score=4.0;
	public int speed;
	public boolean gameOver=false;
	public boolean started=false;

	public FlapStudMed() {
		JFrame jf = new JFrame();
		Timer timer = new Timer(20,this);

		rend = new Renderer();
		rand = new Random();

		jf.add(rend);
		jf.setTitle("FSM");
		jf.setSize(WIDTH, HEIGHT);
		jf.setVisible(true);
		jf.setResizable(false);
		jf.validate();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.addMouseListener(this);

		s = new Rectangle(WIDTH / 4 - 10, HEIGHT / 2 - 10, 20, 20);
		columns = new ArrayList<Rectangle>();

		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
		
		timer.start();

	}

	public static void main(String[] args) {
		fsm = new FlapStudMed();

	}

	public void repaint(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.green);
		g.fillRect(0, HEIGHT - 120, WIDTH, 120);

		g.setColor(Color.red);
		g.fillRect(0, HEIGHT - 120, WIDTH, 20);

		g.setColor(Color.black);
		g.fillRect(s.x, s.y, s.width, s.height);

		for (Rectangle column : columns) {
			paintColumn(g, column);
		}

		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 100));

		if (!started) {
			g.drawString("Beginn!", 75, HEIGHT / 2 - 50);
		}
		if (gameOver) {
			g.drawString("EXMATRIKULIERT!", HEIGHT / 2, WIDTH/5);
		}
		if (started==true) {
			g.drawString(String.valueOf(score),WIDTH/2-25,100);
		}
		
	

	}

	public void actionPerformed(ActionEvent e) {

		int speed = 10;
		ticks++;

		if (started) {
			for (int i = 0; i < columns.size(); i++) {
				Rectangle column = columns.get(i);
			}

			if (ticks % 2 == 0 && yMotion < 15) {
				yMotion += 2;
			}

			

			for (int j = 0; j < columns.size(); j++) {
				Rectangle column = columns.get(j);

				column.x -= speed;
				if (column.x + column.width < 0) {
					columns.remove(column);
					if (column.y == 0) {
						addColumn(false);
					}
				}
			}

			for (int f = 0; f < columns.size(); f++) {
				Rectangle column = columns.get(f);
				if (column.x + column.width < 0) {
					columns.remove(column);
				}
			}
			
			s.y += yMotion;

			for (Rectangle column : columns) {
				if (column.y==0 && s.x + s.width / 2 > column.x + column.width / 2 - 10
						&& s.x + s.width / 2 < column.x + column.width / 2 + 10) {
					score-=0.3;

				}
				if (column.intersects(s)) {
					score+=0.3;
					if(score>4.0) {
					gameOver = true;
					s.x = column.x - s.width;
					}
				}
			}
			if (s.y > HEIGHT - 120 || s.y < 0) {
				gameOver = true;
			}
			if (gameOver) {
				s.y = HEIGHT - 120 - s.height;
			}
			if (s.y + yMotion > HEIGHT) {
				s.y = HEIGHT - 120 - s.height;
			}
		}

		rend.repaint();
	}

	public void paintColumn(Graphics g, Rectangle column) {
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);

	}

	public void addColumn(boolean start) {
		int space = 300;
		int width = 100;
		int height = 50 + rand.nextInt(300);

		if (start) {
			columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
		} else {
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
		}
	}

	public void jump() {
		if (gameOver) {
			s = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
			columns.clear();
			yMotion=0;

			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);

			//gameOver = false;

		}
		if (started==false) {
			started = true;
		} else if (!gameOver) {
			if (yMotion > 0) {
				yMotion = 0;
			}
			yMotion -= 10;
		}
	}

	public void mouseClicked(MouseEvent e) {
		jump();
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}
}
