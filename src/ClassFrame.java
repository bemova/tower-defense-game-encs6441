import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClassFrame extends JFrame {
	
	MainCanva canva = new MainCanva();

	ClassFrame(){
		
		  FlowLayout layout = new FlowLayout();
		  layout.setAlignment(FlowLayout.CENTER);
		  add(canva);
		  
		  
		  layout.setAlignment(FlowLayout.RIGHT);
		  add(new JTextField("I'm a JTextField", 25));
	}

}
