import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GameGUI extends JFrame{
	//in order to connect map interaction with info panel
	public int mapIndex;
	public int mouseMap;
	public JPanel content,headerPanel,info,control,buttonP,logP,diceP;
	public Map mapP;
	public JButton stats,inputB;
	public JButton up = new JButton("U");
	public JButton left = new JButton("L");
	public JButton down = new JButton("D");
	public JButton right = new JButton("R");
	public int saveKey;

	//create save and load buttons - colin
	JButton save = new JButton("Save");
	JButton load = new JButton("Load");


	public JTextArea logTA;
	public JTextField inputTF;
	public JScrollPane scroll;
	public static Final core;
	public Boolean enterB=false;
	public static statswindow display2;
	public Boolean intOnly=false;
	public static Boolean movingTime=false;
	public static Boolean MoveDone=false;

	/**
	 This is the ActionListener used for the "Enter" Button
	 */
	protected class EnterButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			inputTF.setEditable(false);
			enterB=true;
			core.SDelay();
			enterB=false;
			inputTF.setEditable(true);
			inputTF.setText("");
		}
	}

/**

 */
	//protected
	/**
	 This is the ActionListener used for the "Enter Button"
	 */
	protected class checkstats implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			display2.setVisible(true);
		}
	}

	/**
	 This is the keyListener for inputTF JTextField.
	 */
	protected class restrictInputType implements KeyListener{
		@Override
		public void keyTyped(KeyEvent e){
			if(intOnly){
				int keyChar = e.getKeyChar();
				if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){
				}else{
					e.consume(); //Block non-int input
				}
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyChar()==KeyEvent.VK_ENTER){
				inputTF.setEditable(false);
				enterB=true;
				core.SDelay();
				enterB=false;
				inputTF.setEditable(true);
				inputTF.setText("");
			}
		}
	}

	public GameGUI(){






		this.setTitle("This War of Mine 2.0");
		content = new JPanel();
		content.setLayout(new BorderLayout());

		// create the header
		headerPanel = new JPanel();
		headerPanel.add(new JLabel("<html><h1>This War Of Mine</h1></html>"));
		headerPanel.add(save);
		headerPanel.add(load) ;
		content.add(headerPanel,BorderLayout.PAGE_START);

		// create the map
		mapP = new Map();
		content.add(mapP,BorderLayout.CENTER);


		// create the info panel
		info = new JPanel();
		content.add(info,BorderLayout.LINE_END);

		//create the control JPanel
		control = new JPanel();
		control.setLayout(new GridBagLayout());


		////create buttonP
		buttonP = new JPanel();
		buttonP.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		up.setPreferredSize(new Dimension(70, 20));
		c1.gridx = 1;
		c1.gridy = 0;
		buttonP.add(up,c1);


		GridBagConstraints c2 = new GridBagConstraints();
		left.setPreferredSize(new Dimension(70, 20));
		c2.gridx = 0;
		c2.gridy = 1;
		buttonP.add(left,c2);

		GridBagConstraints c3 = new GridBagConstraints();
		stats = new JButton("Stats");stats.setPreferredSize(new Dimension(70, 20));
		c3.gridx = 1;
		c3.gridy = 1;
		buttonP.add(stats,c3);
		stats.addActionListener(new checkstats());

		GridBagConstraints c4 = new GridBagConstraints();
		right.setPreferredSize(new Dimension(70, 20));
		c4.gridx = 2;
		c4.gridy = 1;
		buttonP.add(right,c4);

		GridBagConstraints c5 = new GridBagConstraints();
		down.setPreferredSize(new Dimension(70, 20));
		c5.gridx = 1;
		c5.gridy = 2;
		buttonP.add(down,c5);


		//add buttonP constrain and add to control panel
		GridBagConstraints buttonPc  = new GridBagConstraints();
		buttonPc.gridx = 0;
		buttonPc.gridy = 0;
		buttonPc.fill = GridBagConstraints.HORIZONTAL;
		control.add(buttonP, buttonPc);
		// ending added buttonP

		// start add log panel
		logP = new JPanel();
		logP.setLayout(new GridBagLayout());

		GridBagConstraints p1 = new GridBagConstraints();
		logTA = new JTextArea("");
		logTA.setEditable(false);
		JTextAreaOutputStream out = new JTextAreaOutputStream(logTA);
		System.setOut (new PrintStream (out));
		System.setErr(new PrintStream(out));

		scroll = new JScrollPane(logTA);
		scroll.setPreferredSize(new Dimension(1000,200));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		p1.gridx = 0;
		p1.gridy = 0;
		p1.gridwidth = 4;
		p1.ipady = 40;
		logP.add(scroll, p1);

		GridBagConstraints p2 = new GridBagConstraints();
		inputTF = new JTextField("");
		inputTF.setPreferredSize(new Dimension(1000,40));
		p2.gridx = 0;
		p2.gridy = 1;
		p2.gridwidth =1;
		logP.add(inputTF, p2);
		inputTF.addKeyListener(new restrictInputType());

		GridBagConstraints p3 = new GridBagConstraints();
		inputB = new JButton("Enter");
		inputB.setPreferredSize(new Dimension(100,40));
		p3.gridx = 3;
		p3.gridy = 1;
		logP.add(inputB, p3);
		inputB.addActionListener(new EnterButton());

		GridBagConstraints logPc = new GridBagConstraints();
		logPc.gridx = 1;
		logPc.gridy =0;
		logPc.gridwidth = 2;
		logPc.weightx = 1;
		logPc.fill = GridBagConstraints.HORIZONTAL;
		control.add(logP, logPc);



		// start add dice panel
		GridBagConstraints dicePc = new GridBagConstraints();
		diceP = new JPanel();
		dicePc.gridx = 3;
		dicePc.gridy = 0;
		control.add(diceP, dicePc);

		content.add(control, BorderLayout.PAGE_END);

		up.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(movingTime){
					if(core.CurrentCoordinate[0]==0){
						System.out.println("You cannot go further up.");
					}else{
						core.CurrentCoordinate[0]-=1;
						MoveDone=true;
						core.SDelay();
					}
				}

			}
		});

		down.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(movingTime){
					if(core.CurrentCoordinate[0]==14){
						System.out.println("You cannot go further down.");
					}else{
						core.CurrentCoordinate[0]+=1;
						MoveDone=true;
						core.SDelay();
					}
				}
			}
		});

		right.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(movingTime){
					if(core.CurrentCoordinate[1]==10){
						System.out.println("You cannot go further right.");
					}else{
						core.CurrentCoordinate[1]+=1;
						MoveDone=true;
						core.SDelay();
					}
				}
			}
		});

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				HashMap<Integer, ArrayList<Object>> savedFile =  core.getSavedFile();

				//takes in the value from the text field to use as a save key in order to store
				//thier save file in a unique hash location that the user will remember

				String saveKey = inputTF.getText();

				//we have to create an array list of many objects to add into our savedFile hashmap
				ArrayList<Object> newData = new ArrayList<Object>();

				Collections.addAll(newData,core.CurrentCoordinate, core.getDay(), core.getHp(), core.getFood(), core.getPistol(),core.getAutomaticRifle(), core.getAmmo(), core.getMedicine(),core.getBandage(), core.getValuables(), core.getMoral(), core.getSick(), core.getHungry(), core.getFastRecover(), core.getInjured(), core.getSpyID(), core.getCipher(), core.getBadgeValue(),core.getTryEscape() );
				core.createSaveFile(saveKey,newData);
				System.out.print("Your save key has been set as : " + saveKey + "\n Press Load and enter 0 if you wish to start a new game. ");






			}
		});

		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String loadKey = inputTF.getText();
				ArrayList<Object> loadedFile = core.savedFile.get(loadKey);
				if(loadedFile.size()< 1){
					System.out.print("\n Save File Not Found");
				}
				else{
					core.setCurrentCoordinate ((int[]) loadedFile.get(0));
					core.setDay  ((int) loadedFile.get(1));
					core.setHp((int) loadedFile.get(2));
					core.setFood ((int) loadedFile.get(3));
					core.setPistol( (boolean) loadedFile.get(4));
					core.setAutomaticRifle( (boolean) loadedFile.get(5));
					core.setAmmo ((int) loadedFile.get(6));
					core.setMedicine ( (int) loadedFile.get(7));
					core.setBandage ((int) loadedFile.get(8));
					core.setValuables ((int) loadedFile.get(9));
					core.setMoral  ((int) loadedFile.get(10));
					core.setSick ((boolean) loadedFile.get(11));
					core.setHungry ((int) loadedFile.get(12));
					core.setFastRecover ( (boolean) loadedFile.get(13));
					core.setInjured ( (boolean) loadedFile.get(14));
					core.setSpyID ((boolean) loadedFile.get(14));
					core.setCipher( (boolean) loadedFile.get(15));
					core.setBadge ((boolean) loadedFile.get(16));
					core.setTryEscape ( (boolean) loadedFile.get(17));

					System.out.print("Game loaded.");




				}


			}
		});


		left.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(movingTime){
					if(core.CurrentCoordinate[1]==0){
						System.out.println("You cannot go further left.");
					}else{
						core.CurrentCoordinate[1]-=1;
						MoveDone=true;
						core.SDelay();
					}
				}
			}
		});

		this.setContentPane(content);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLocation(0,0);
		//this.setSize(1500,1500);
		this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}


	public void refocus(){
		this.logTA.setCaretPosition(this.logTA.getText().length());
	}

	//constructing a interactive map of 15x11 scale
	public class Map extends JPanel implements MouseListener, MouseMotionListener{
		int mousemoveIndex;
		int currentx,currenty;
		ArrayList<Block> blocks = new ArrayList<Block>();
		public Map(){
			setBackground(Color.black);
			//setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			//setPreferredSize(new Dimension(600,440));
			addMouseListener(this);
			addMouseMotionListener(this);

			int blockx =20;
			int blocky =20;
			for(int j=0; j<15;j++){
				for(int i=0; i<11; i++){
					Block z = new Block(blockx,blocky);
					blocks.add(z);
					blockx +=40;
				}
				blockx=20;
				blocky+=40;
			}
		}


		public void paintComponent(Graphics g){
			// make a wihte sketchpad
			g.setColor(Color.white);
			g.fillRect(0,0,getWidth(),getHeight());
			for(Block z : blocks){
				z.draw(g);
			}


		}

		public void mousePressed(MouseEvent evt) { }
		public void mouseEntered(MouseEvent evt) { }
		public void mouseExited(MouseEvent evt) { }
		public void mouseClicked(MouseEvent evt) { }
		public void mouseReleased(MouseEvent evt) { }


		// these are the MouseMotionEvent methods
		// when mouse moved in map, mouse move index change
		public void mouseMoved(MouseEvent evt){
			int mouseX = evt.getX();
			int mouseY = evt.getY();
			for(Block i : blocks){
				if(i.isinBlock(mouseX,mouseY)==true){
					i.color = Color.blue;
					mousemoveIndex = blocks.indexOf(i);
				} else{
					i.color = Color.white;
				}
			}
			repaint();
		}
		public void mouseDragged(MouseEvent evt){ }

		class Block{
			Color color = Color.white;
			int x,y;
			public Block(int x, int y){
				this.x = x;
				this.y = y;
			}

			public void draw(Graphics g){
				int cX=core.CurrentCoordinate[1];
				int cY=core.CurrentCoordinate[0];
				if(((x-20)/40==cX)&&((y-20)/40)==cY){
					g.setColor(Color.RED);
					g.fillRect( x - 20, y - 20, 40, 40 );
					g.setColor(Color.black);
					g.drawRect( x - 20, y - 20, 40, 40 );
				}else{
					g.setColor(color);
					g.fillRect( x - 20, y - 20, 40, 40 );
					g.setColor(Color.black);
					g.drawRect( x - 20, y - 20, 40, 40 );
				}
			}

			public Boolean isinBlock(int a, int b){
				if(a >= x-20 && a < x+20 && b >= y-20 && b < y+20){
					return true;
				} else{
					return false;
				}
			}
		}
	}
	//end of map

	class JTextAreaOutputStream extends OutputStream{
		private final JTextArea destination;
		public JTextAreaOutputStream (JTextArea destination){
			if (destination == null)
				throw new IllegalArgumentException ("Destination is null");


			this.destination = destination;
		}
		@Override
		public void write(byte[] buffer, int offset, int length) throws IOException{
			final String text = new String (buffer, offset, length);
			SwingUtilities.invokeLater(new Runnable (){
				@Override
				public void run(){
					destination.append (text);
				}
			});
		}
		@Override
		public void write(int b) throws IOException{
			write (new byte [] {(byte)b}, 0, 1);
		}
	}

	public static void main(String[] args) {
		GameGUI display=new GameGUI();
		core=new Final(display);
		display2=new statswindow(core);
		core.Introduction();
		display.refocus();
		do{
			display.refocus();
			core.PositionReport();
			display.refocus();
			movingTime=true;
			System.out.println("Click one of the direction button to move.");
			while(!MoveDone){
				movingTime=true;
				display.mapP.repaint();
			}
			movingTime=false;
			core.PositionReport();
			//core.PlayerStatus();
			core.action();
			display2.update();
			display.refocus();
			core.sleep();
			display2.update();
			display.refocus();
			MoveDone=false;
		}while(!core.win());
		core.End();
		display.refocus();
	}

}
