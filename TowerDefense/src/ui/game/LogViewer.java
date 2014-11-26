package ui.game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import core.applicationservice.gameservices.GameLogManager;

public class LogViewer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;
	private GameLogManager gameLogManager;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			LogViewer dialog = new LogViewer();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public LogViewer(GameLogManager gameLogManager) {
		this.gameLogManager = gameLogManager;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			textArea = new JTextArea();
			textArea.setColumns(50);
			textArea.setRows(10);
			contentPanel.add(textArea);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		getGlobalGameLog();
	}

	private void getGlobalGameLog() {
		textArea.setText(gameLogManager.getGlobalLog());
	}

}
