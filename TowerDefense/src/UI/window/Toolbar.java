package UI.window;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Toolbar extends JPanel implements ActionListener{
	private JButton helloButton;
	private JButton goodbyeButton;
	//private TextPanel textPanel;
	//private StringListener listener;
	
//	public void setListener(StringListener listener) {
//		this.listener = listener;
//	}
//
	public Toolbar() {
//		setBorder(BorderFactory.createEtchedBorder());
		helloButton = new JButton("Hello");
		goodbyeButton = new JButton("goodbye");
//		helloButton.addActionListener(this);
//		goodbyeButton.addActionListener(this);
//
		setLayout(new FlowLayout(FlowLayout.LEADING));
		add(helloButton);
		add(goodbyeButton);
		setVisible(true);
//
//
	}
//	
////	public void setTextPanel(TextPanel textPanel) {
////		this.textPanel = textPanel;
////	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		JButton clicked = (JButton)e.getSource();
//		if (clicked == helloButton) {
//			//this.textPanel.append("hello\n");
//			if(listener != null)
//				this.listener.textEmitted("Hello\n");
//		} else {
//			//this.textPanel.append("goodbye\n");
//			this.listener.textEmitted("Goodbye\n");
//		}
//
//	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
