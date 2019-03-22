package Application;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

import java.awt.Cursor;

@SuppressWarnings("serial")
public class APropos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	public APropos() {
		setTitle("\u00C0 propos de Mojam");
		setBounds(100, 100, 311, 140);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setForeground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLeQamous = new JLabel("Le Mojam (version Beta)\r\n\r\n");
		lblLeQamous.setFont(new Font("Dialog", Font.BOLD, 23));
		lblLeQamous.setForeground(new Color(51, 51, 51));
		contentPanel.add(lblLeQamous, BorderLayout.NORTH);
		
		JTextArea txtrLeQamousEst = new JTextArea();
		txtrLeQamousEst.setFocusTraversalKeysEnabled(false);
		txtrLeQamousEst.setFocusable(false);
		txtrLeQamousEst.setCaretColor(new Color(255, 255, 255));
		txtrLeQamousEst.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtrLeQamousEst.setEditable(false);
		txtrLeQamousEst.setWrapStyleWord(true);
		txtrLeQamousEst.setText("\r\nDictionnaire Arabe - Fran\u00E7ais et Fran\u00E7ais - Arabe");
		txtrLeQamousEst.setLineWrap(true);
		contentPanel.add(txtrLeQamousEst, BorderLayout.CENTER);
		
		JLabel lblProgrammParFabrice = new JLabel("Programm\u00E9 par Fabrice Hamza");
		lblProgrammParFabrice.setForeground(Color.BLACK);
		contentPanel.add(lblProgrammParFabrice, BorderLayout.SOUTH);
	}
}
