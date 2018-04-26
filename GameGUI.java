import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Scanner;
import java.lang.Object.*;
import javax.swing.event.*;
import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class GameGUI extends JFrame{
  //in order to connect map interaction with info panel
public int mapIndex;
public int mouseMap;
public JPanel content,headerPanel,info,control,buttonP,logP,diceP,infoimage;
public Map mapP;
public JButton stats,inputB;
public JButton up = new JButton("U");
public JButton left = new JButton("L");
public JButton down = new JButton("D");
public JButton right = new JButton("R");
public JTextArea showInfo;
public JTextArea logTA;
public JTextField inputTF;
public JScrollPane scroll;
public static Final core;
public Boolean enterB=false;
public static statswindow display2;
public Boolean intOnly=false;
public static Boolean movingTime=false;
public static Boolean MoveDone=false;
public int[] SelectedCoordinate={14,0};

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
	This subroutine is used for updating postion location
*/
	public String posRepo(int[] selCoor){
		String temp="";
    temp=temp+"Selected coordinate is ("+SelectedCoordinate[1]+","+SelectedCoordinate[0]+"), which is a (x,y) coordinate.";
    String raw=core.Map[selCoor[0]][selCoor[1]];
    String status;
    status=core.PositionStatusReport(raw.charAt(1));
    switch(raw.charAt(0)){
      case 'A':
        temp=temp+"\nYou encounter an apartment that "+status+"\n";
        break;
      case 'T':
        temp=temp+"\nYou come across a supermarket that "+status+"\n";
        break;
      case 'M':
        temp=temp+"\nYou are at the front of a Military base.\n'A sign of government power and a guarantee of safety.', the president said. \n'Yeah, definitely,' You tell youself.\nThe building "+status+"\n";
        break;
      case 'F':
        temp=temp+"\nYou stand before a construction field.\n'Politicians promised to finish this building, but they could not fulfill their promises,' You tell youself.\n'This place may never be completed...' The field "+status+"\n";
        break;
      case 'W':
        temp=temp+"\nYou are at the front of a warehouse, a place to store food, medicine, etc.\nIt now stores hope, too.\nThe warehouse "+status+"\n";
        break;
      case 'S':
        temp=temp+"\nYou stand before a school.\n'......' You began to remember your time in school.\nBut then you tell yourself it is not a time for memories.\nThe school in front of you "+status+"\n";
        break;
      case 'O':
        temp=temp+"\nAn office building...You used to be one of those white-collar workers inside.\nThis place seems so familiar, if you ignore the signs of war.\nThere are now no people working inside, it "+status+"\n";
        break;
      case 'R':
        temp=temp+"\nYou approach what looks like a slum, home of bottom class people.---But war changed everything.\nDisplaced persons, refugees whose homes were destroyed all lived here now.\nThe slum "+status+"\n";
        break;
      case 'P':
        temp=temp+"\nYou stumble upon a sewer entrance.\nYou can smell it before see it---War does not change this.\nThis place, full of disease, is now a safe and fast way for you to move to different parts of the city.\n";
        break;
      case 'H':
        temp=temp+"\nYou encounter a hospital, which "+status+"\n";
        break;
      case 'D':
        temp=temp+"\nYou see something beckoning to an earlier time.\nIt's a church steeple.\nPerhaps it can provide holy refuge in this time of war?\nThe church "+status+"\n";
        break;
      default:
        temp=temp+"\nYou are now at the gate of this city.\nIf you can sneak out and trick all those rebels into believing that you are one of their spies, you could escape the city.\nBut they are not fools, so you need to prove yourself.\n";
        break;
    }
		return temp;
	}
/**

*/

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
		content.add(headerPanel,BorderLayout.PAGE_START);

    // create the map
    mapP = new Map();
    //content.add(mapP,BorderLayout.CENTER);


		// create the info panel
		info = new JPanel();
		infoimage=new JPanel();
		GridBagLayout G4infoimage= new GridBagLayout();
		infoimage.setLayout(G4infoimage);
		G4infoimage.setConstraints(mapP,new GridBagConstraints(0, 0, 1,2, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(40, 40, 40, 40),0, 0));
		infoimage.add(mapP);


		//HERE
		showInfo=new JTextArea("");
		showInfo.setEditable(false);
		G4infoimage.setConstraints(showInfo,new GridBagConstraints(GridBagConstraints.RELATIVE, 0, 4,1, 1.0, 1.0,GridBagConstraints.EAST,GridBagConstraints.BOTH, new Insets(40, 40, 40, 40),0, 0));
		infoimage.add(showInfo);
		content.add(infoimage,BorderLayout.CENTER);

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
		scroll.setPreferredSize(new Dimension(1200,160));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    p1.gridx = 0;
    p1.gridy = 0;
    p1.gridwidth = 4;
    p1.ipady = 40;
    logP.add(scroll, p1);

    GridBagConstraints p2 = new GridBagConstraints();
    inputTF = new JTextField("");
    inputTF.setPreferredSize(new Dimension(1000,20));
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
		control.setPreferredSize(new Dimension(0,300));

/**
    // start add dice panel
    GridBagConstraints dicePc = new GridBagConstraints();
    diceP = new JPanel();
    dicePc.gridx = 3;
    dicePc.gridy = 0;
    control.add(diceP, dicePc);
*/
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

      public void mousePressed(MouseEvent evt) {
				int mouseX = evt.getX();
        int mouseY = evt.getY();
				for(Block i : blocks){
					if(i.isinBlock(mouseX,mouseY)){
						SelectedCoordinate[0]=((i.y-20)/40);
						SelectedCoordinate[1]=((i.x-20)/40);
						//HERE
						String T="";
						T=posRepo(SelectedCoordinate);
						showInfo.setText(T);
						break;
					}
				}
			}
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
          if(i.isinBlock(mouseX,mouseY)){
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
	      core.PositionReport();
				movingTime=true;
				System.out.println("Click one of the direction button to move.");
				while(!MoveDone){
					movingTime=true;
					display.mapP.repaint();
				}
				movingTime=false;
	      core.PositionReport();
				display.refocus();
				core.action();
				display2.update();
	      core.sleep();
				display2.update();
				MoveDone=false;
	    }while(!core.win());
	    core.End();
			display.refocus();
    }

}
