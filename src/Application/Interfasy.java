package Application;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdesktop.xswingx.PromptSupport;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Interfasy extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField saisieMot;
	private JTextArea traductionMot = new JTextArea();
	private DefaultListModel<String> modele = new DefaultListModel<String>();
	private JList<String> listeMot = new JList<String>(modele);
	private String motAchercher = "";
	private ArrayList<String> mot;
	private ArrayList<String> explication;
	private ArrayList<String> traduction;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfasy frame = new Interfasy();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interfasy() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Interfasy.class.getResource("/icon/dict-icon.png")));
		setTitle(" Le Mojam");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 670, 525);
		notifZone();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenuItem itemQuitter = new JMenuItem("Quitter");
		itemQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFichier.add(itemQuitter);
		
		JMenu mnAffichage = new JMenu("Affichage");
		menuBar.add(mnAffichage);
		
		JMenu mnZoom = new JMenu("Zoom   ");
		mnAffichage.add(mnZoom);
		
		JMenuItem mntmZoomAvant = new JMenuItem("  Zoom avant");
		mntmZoomAvant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zoomPlus();
			}
		});
		mnZoom.add(mntmZoomAvant);
		
		JMenuItem mntmZoomArrire = new JMenuItem("  Zoom arri\u00E8re");
		mntmZoomArrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoomMoins();
			}
		});
		mnZoom.add(mntmZoomArrire);
		
		JMenuItem mntmNormal = new JMenuItem("  Normal");
		mntmNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				annulerZoom();
			}
		});
		mnZoom.add(mntmNormal);
		
		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);
		
		JMenuItem mntmAPropos = new JMenuItem("A propos");
		mntmAPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				APropos dialog = new APropos();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(contentPane);
				dialog.setVisible(true);
			}
		});
		mnAide.add(mntmAPropos);
		contentPane = new JPanel();
		contentPane.setForeground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(7, 8));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnZoomPlus = new JButton("Zoom avant");
		btnZoomPlus.setToolTipText("Zoom avant");
		btnZoomPlus.setIcon(new ImageIcon(Interfasy.class.getResource("/icon/magnifier_plus_green.png")));
		//Personnaliser btnZoomPlus
		btnZoomPlus.setBorder(null);
		btnZoomPlus.setBorderPainted(false);
		toolBar.add(btnZoomPlus);
		
		JButton btnZoomMoins = new JButton("Zoom arri\u00E8re");
		btnZoomMoins.setToolTipText("Zoom arri\u00E8re");
		btnZoomMoins.setIcon(new ImageIcon(Interfasy.class.getResource("/icon/magnifier_minus_green.png")));
		//Personnaliser btnZooPlus
		btnZoomMoins.setBorder(null);
		btnZoomMoins.setBorderPainted(false);
		toolBar.add(btnZoomMoins);
		
		JButton btnAnnulerZoom = new JButton("Normal");
		btnAnnulerZoom.setToolTipText("Normal");
		btnAnnulerZoom.setIcon(new ImageIcon(Interfasy.class.getResource("/icon/magnifier.png")));
		//Personnaliser btnZoomPlus
		btnAnnulerZoom.setBorder(null);
		btnAnnulerZoom.setBorderPainted(false);
		toolBar.add(btnAnnulerZoom);
		
		JPanel panneauDroite = new JPanel();
		contentPane.add(panneauDroite, BorderLayout.WEST);
		panneauDroite.setLayout(new BorderLayout(0, 2));
		
		saisieMot = new JTextField();
		saisieMot.setToolTipText("Saisir un mot puis appuyer sur la touche ENTREE pour le chercher dans le dictionnaire");
		saisieMot.setColumns(10);
		panneauDroite.add(saisieMot, BorderLayout.NORTH);
		DefaultContextMenu.addDefaultContextMenu(saisieMot);
		PromptSupport.setPrompt(" Saisir un mot", saisieMot);
		PromptSupport.setForeground(Color.GRAY, saisieMot);
		PromptSupport.setBackground(new Color(255, 255, 255), saisieMot);
		
		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panneauDroite.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		panel.setPreferredSize(new Dimension(100,100)); 
		
		traductionMot.setEditable(false);
		//Retour à la ligne automatique
		traductionMot.setLineWrap(true);
		traductionMot.setFont(new Font("Traditional Arabic", Font.PLAIN, 28));
		DefaultContextMenu.addDefaultContextMenu(traductionMot);
		
		JScrollPane scrollPane = new JScrollPane(traductionMot);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		btnZoomPlus.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				zoomPlus();
			}
		});
		btnZoomMoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zoomMoins();
			}
		});
		btnAnnulerZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				annulerZoom();
			}
		});
		listeMot.setFont(new Font("Dialog", Font.BOLD, 12));
		
		listeMot.setBackground(new Color(255, 255, 255));
		listeMot.setBorder(new LineBorder(new Color(192, 192, 192), 0));
		
		JScrollPane scrollPane2 = new JScrollPane(listeMot);
		scrollPane2.setBorder(new LineBorder(Color.GRAY));
		scrollPane2.setPreferredSize(new Dimension(114, 100));
		panel.add(scrollPane2, BorderLayout.WEST);
		
		saisieMot.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent a) {
				if(((KeyEvent)a).getKeyCode()==KeyEvent.VK_ENTER)
	            {
					if (!saisieMot.getText().isEmpty()){
						chercherMot();
						saisieMot.setText(saisieMot.getText());
						if (getMot().isEmpty()){
							traductionMot.setText("Mot Introuvable !\nVérifiez l'orthographe !");
						}
					}
	            }
			}
		});
		
		listeMot.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting() == false) {
					if (listeMot.getSelectedIndex() > -1){
						int i = listeMot.getSelectedIndex();
						String motS = getMot().get(i);
						String explicationS = getExplication().get(i);
						String traductionS = getTraduction().get(i);
						traductionMot.setText("\n  "+motS+"  "+explicationS+"\n \n"+traductionS);
					}
				}
			}
		});
		
	}
	public void setMotAChercher(String motA){
		motAchercher = motA;
	}
	public String getMotAChercher(){
		return this.motAchercher;
	}
	
	public ArrayList<String> getMot() {
		return mot;
	}

	public void setMot(ArrayList<String> motSa) {
		this.mot = motSa;
	}

	public ArrayList<String> getExplication() {
		return explication;
	}

	public void setExplication(ArrayList<String> explicationSa) {
		this.explication = explicationSa;
	}

	public ArrayList<String> getTraduction() {
		return traduction;
	}

	public void setTraduction(ArrayList<String> traductionSa) {
		this.traduction = traductionSa;
	}
	
	public void ranger(){
		String motAChercher = getMotAChercher();
		String repl = new String();
		ArrayList<String> motR = getMot();
		ArrayList<String> explicationR = getExplication();
		ArrayList<String> traductionR = getTraduction();
		
		for(int i=1; i<motR.size();i++){
			for (int j=1; j<motR.size();j++)
				if(motR.get(j).equals(motAChercher)){
					repl = motR.get(j);
					motR.set(j, motR.get(j-1));
					motR.set(j-1, repl);
					
					repl = explicationR.get(j);
					explicationR.set(j, explicationR.get(j-1));
					explicationR.set(j-1, repl);
					
					repl = traductionR.get(j);
					traductionR.set(j, traductionR.get(j-1));
					traductionR.set(j-1, repl);
				}
				else if (enleverHarakat(motR.get(j)).equals(motAChercher)){
					repl = motR.get(j);
					motR.set(j, motR.get(j-1));
					motR.set(j-1, repl);
					
					repl = explicationR.get(j);
					explicationR.set(j, explicationR.get(j-1));
					explicationR.set(j-1, repl);
					
					repl = traductionR.get(j);
					traductionR.set(j, traductionR.get(j-1));
					traductionR.set(j-1, repl);
				}
		}
		setMot(motR);
		setExplication(explicationR);
		setTraduction(traductionR);
	}
	private void chercherMot(){
			setMotAChercher(saisieMot.getText().trim());
			Dictionnaire dict = new Dictionnaire(getMotAChercher());
			if (modele.getSize() != 0){
				modele.clear();
				listeMot.removeAll();
				dict.word.clear();
				dict.explination.clear();
				dict.meaning.clear();
				dict = new Dictionnaire(getMotAChercher());
			}
			setMot(dict.getWord());
			setExplication(dict.getExplination());
			setTraduction(dict.getMeaning());
			ranger();
			
			for (int i=0; i<getMot().size(); i++){
				if (i > 50)
					break;
				modele.addElement(getMot().get(i));
			}
			listeMot.setSelectedIndex(0);
	}
	
	public String enleverHarakat(String mot){
		String[] harakat = {"\u064B","\u064C","\u064D","\u064E","\u064F","\u0650","\u0651",
				"\u0652"};
		for (int i=0; i<harakat.length; i++)
			mot = mot.replaceAll(harakat[i], "");
		return mot;
	}
	
	private void zoomPlus(){
		int size = traductionMot.getFont().getSize();
		traductionMot.setFont(new Font("Traditional Arabic", Font.PLAIN, size+10));
	}
	private void zoomMoins(){
		int size = traductionMot.getFont().getSize();
		traductionMot.setFont(new Font("Traditional Arabic", Font.PLAIN, size-10));
	}
	private void annulerZoom(){
		traductionMot.setFont(new Font("Traditional Arabic", Font.PLAIN, 28));
	}
	private void notifZone(){
		final PopupMenu popup = new PopupMenu();
		Image image = Toolkit.getDefaultToolkit().getImage(Interfasy.class.getResource("/icon/1.png"));
        final TrayIcon trayIcon =
                new TrayIcon(image);
        final SystemTray tray = SystemTray.getSystemTray();
       
        // Create a pop-up menu components
        MenuItem afficherItem = new MenuItem("Afficher");
        afficherItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfasy.this.setVisible(true);
			}
		});
        MenuItem exitItem = new MenuItem("Quitter");
        exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
        MenuItem aideItem = new MenuItem("À propos");
        aideItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				APropos dialog = new APropos();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
		});
        //Add components to pop-up menu
        popup.add(afficherItem);
        popup.addSeparator();
        popup.add(aideItem);
        popup.add(exitItem);
       
        trayIcon.setPopupMenu(popup);
        trayIcon.setToolTip("Le Mojam");
        trayIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				if (m.getButton() == MouseEvent.BUTTON1)
					Interfasy.this.setVisible(true);
			}
		});
       
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
        }
	}
}
