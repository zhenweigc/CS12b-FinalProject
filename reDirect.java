import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
public class reDirect{
  public reDirect(){
    JFrame jf=new JFrame();
    jf.setBounds(100,100,700,400);
    JTextArea jta=new JTextArea();
    jta.setFont(new Font(null,Font.BOLD,20));
    jta.setEditable(false);
    JTextAreaOutputStream out = new JTextAreaOutputStream(jta);
    System.setOut (new PrintStream (out));
    System.setErr(new PrintStream(out));
    JScrollPane jsp=new JScrollPane(jta);
    jf.add(jsp);jf.setVisible(true);
  }
  public static void main(String[] args){
    reDirect rd=new reDirect();
    System.out.println("reDirect");
    System.out.println(12345);
    System.out.println(true);
    System.out.println("reDirect");
    System.out.println("reDirect");
    System.out.println("reDirect");
    System.out.println("reDirect");
    System.out.println("reDirect");
    try{
      int[] s={1,2,3,4,5};
      int i=s[8];
    }catch(Exception e){
      e.printStackTrace();
    }
  }
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
}
