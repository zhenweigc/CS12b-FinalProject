import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class Gameframe extends JPanel {

  //in order to connect map interaction with info panel
  public int mapIndex;
  public int mouseMap;


  //constructor of GUI
	public Gameframe(){
		//super();
		JPanel content = this;
		content.setLayout(new BorderLayout());

    // create the header

      //create save and load buttons
      JButton save = new JButton("Save");
      JButton load = new JButton("Load");


		JPanel headerPanel = new JPanel();
		headerPanel.add(new JLabel("<html><h1>War Of Mine</h1></html>"));
		headerPanel.add(save);
		headerPanel.add(load);
		content.add(headerPanel,BorderLayout.PAGE_START);



    // create the map
    Map mapP = new Map();
    content.add(mapP,BorderLayout.CENTER);


		// create the info panel
		JPanel info = new JPanel();
		content.add(info,BorderLayout.LINE_END);

    //create the control JPanel
    JPanel control = new JPanel();
    control.setLayout(new GridBagLayout());


    ////create buttonP
    JPanel buttonP = new JPanel();
    buttonP.setLayout(new GridBagLayout());
    GridBagConstraints c1 = new GridBagConstraints();
    JButton up = new JButton("Up");up.setPreferredSize(new Dimension(70, 20));
    c1.gridx = 1;
    c1.gridy = 0;
    buttonP.add(up,c1);

    GridBagConstraints c2 = new GridBagConstraints();
    JButton left = new JButton("Left");left.setPreferredSize(new Dimension(70, 20));
    c2.gridx = 0;
    c2.gridy = 1;
    buttonP.add(left,c2);

    GridBagConstraints c3 = new GridBagConstraints();
    JButton stats = new JButton("Stats");stats.setPreferredSize(new Dimension(70, 20));
    c3.gridx = 1;
    c3.gridy = 1;
    buttonP.add(stats,c3);

    GridBagConstraints c4 = new GridBagConstraints();
    JButton right = new JButton("Right");right.setPreferredSize(new Dimension(70, 20));
    c4.gridx = 2;
    c4.gridy = 1;
    buttonP.add(right,c4);

    GridBagConstraints c5 = new GridBagConstraints();
    JButton down = new JButton("Down");down.setPreferredSize(new Dimension(70, 20));
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
    JPanel logP = new JPanel();
    logP.setLayout(new GridBagLayout());

    GridBagConstraints p1 = new GridBagConstraints();
    JTextArea logTA = new JTextArea("LOG WINDOW");
    logTA.setPreferredSize(new Dimension(400,100));
    p1.gridx = 0;
    p1.gridy = 0;
    p1.gridwidth = 4;
    p1.ipady = 40;
    logP.add(logTA, p1);

    GridBagConstraints p2 = new GridBagConstraints();
    JTextField inputTF = new JTextField("INPUT FIELD");
    inputTF.setPreferredSize(new Dimension(360,40));
    p2.gridx = 0;
    p2.gridy = 1;
    p2.gridwidth = 3;
    logP.add(inputTF, p2);

    GridBagConstraints p3 = new GridBagConstraints();
    JButton inputB = new JButton("O");
    inputB.setPreferredSize(new Dimension(40,40));
    p3.gridx = 3;
    p3.gridy = 1;
    logP.add(inputB, p3);

    GridBagConstraints logPc = new GridBagConstraints();
    logPc.gridx = 1;
    logPc.gridy =0;
    logPc.gridwidth = 2;
    logPc.weightx = 1;
    logPc.fill = GridBagConstraints.HORIZONTAL;
    control.add(logP, logPc);



    // start add dice panel
    GridBagConstraints dicePc = new GridBagConstraints();
    JPanel diceP = new JPanel();
    dicePc.gridx = 3;
    dicePc.gridy = 0;
    control.add(diceP, dicePc);

    content.add(control, BorderLayout.PAGE_END);
	}


  //constructing a interactive map of 15x11 scale
  public class Map extends JPanel implements MouseListener, MouseMotionListener{
      int mousemoveIndex;
      int currentx, currenty;
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
          g.setColor(color);
          g.fillRect( x - 20, y - 20, 40, 40 );
          g.setColor(Color.black);
          g.drawRect( x - 20, y - 20, 40, 40 );
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












	 /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
      //Create and set up the window.
      JFrame frame = new JFrame("WAR OF MINE");

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      //Create and set up the content pane.
      JComponent newContentPane = new Gameframe();
      newContentPane.setOpaque(true); //content panes must be opaque
      frame.setContentPane(newContentPane);

      //Display the window.
      frame.setSize(800,1000);
      frame.setVisible(true);
    }

    public static void main(String[] args) {
      //Schedule a job for the event-dispatching thread:
      //creating and showing this application's GUI.
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
              createAndShowGUI();
          }
      });
    }

}
