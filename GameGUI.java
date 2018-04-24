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
public JPanel content,headerPanel,info,control,buttonP,logP,diceP;
public Map mapP;
public JButton up,left,stats,right,down,inputB;
public JTextArea logTA;
public JTextField inputTF;
public JScrollPane scroll;
public static Final core;
public Boolean enterB=false;
public static statswindow display2;

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
	This is the ActionListener used for the "Enter Button"
*/
	protected class checkstats implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			display2.setVisible(true);
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
    up = new JButton("Up");up.setPreferredSize(new Dimension(70, 20));
    c1.gridx = 1;
    c1.gridy = 0;
    buttonP.add(up,c1);

    GridBagConstraints c2 = new GridBagConstraints();
    left = new JButton("Left");left.setPreferredSize(new Dimension(70, 20));
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
    right = new JButton("Right");right.setPreferredSize(new Dimension(70, 20));
    c4.gridx = 2;
    c4.gridy = 1;
    buttonP.add(right,c4);

    GridBagConstraints c5 = new GridBagConstraints();
    down = new JButton("Down");down.setPreferredSize(new Dimension(70, 20));
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
		scroll.setPreferredSize(new Dimension(1200,300));
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



    this.setContentPane(content);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //this.setLocation(0,0);
  	//this.setSize(1500,1500);
		this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);
    this.setVisible(true);

/**
		statsContent=new JPanel();
		gridbag = new GridBagLayout();
		statsContent.setLayout(gridbag);
		String temp=core.getDay()+"";
		dayNum=new JLabel(temp,JLabel.CENTER);
		gridbag.setConstraints(dayNum, new GridBagConstraints(0, 0, 1,
              1, 1.0, 1.0,GridBagConstraints.CENTER,
          GridBagConstraints.BOTH, new Insets(20, 20, 20, 20	),
            0, 0));
		statsContent.add(dayNum);
		statsWindow.setContentPane(statsContent);
    statsWindow.setLocation(0,0);
  	statsWindow.setSize(400,400);
    statsWindow.setVisible(false);
		*/
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
			//System.out.println(core.getDay());

	    core.Introduction();
			display.refocus();
	    do{
	      Scanner s = new Scanner(System.in);
	      core.PlayerStatus();
				display.refocus();
	      core.PositionReport();
				display.refocus();
	      System.out.println("Please enter a char to move. U for upwards, D for downwards, L for Leftwards and R for rightwards.");
	      Boolean ValidMove=true;
	      do{
	      //  char M=s.nextLine().charAt(0);
	        ValidMove=core.Move(s.nextLine().charAt(0));
	      }while(!ValidMove);
				display.mapP.repaint();
	      core.PositionReport();
				display.refocus();
	      core.PlayerStatus();
				display.refocus();
	      core.action();
				display2.update();
				display.refocus();
	      core.sleep();
				display2.update();
				display.refocus();
	    }while(!core.win());
	    core.End();
			display.refocus();
    }

}
