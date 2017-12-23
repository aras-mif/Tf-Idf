package com.sara.tfidf;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L; // Utile en cas de
														// sérialisation de cet
														// objet

	private JPanel _contentPane;
	private JTextField _pathField;
	private JButton _choseFolder;
	private JButton _startButton;

	public MainFrame() {
		super("File packaging");
		this.setSize(new Dimension(800, 200)); // Dimensions de la fenêtre

		buildGUIComponents();
//		StartListener listener = new StartListener(_pathField);

		this.setLocationRelativeTo(null); // Centrage à l'ecran
		this.setVisible(true); // On affiche la fenêtre
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // On définit la fermeture
														// du programme sur la
														// croix rouge
	}

	/**
	 * Methode de construction graphique de la fenêtre et de ses composants
	 * 
	 * @return
	 */
	public JTextField buildGUIComponents() {
		this.setSize(new Dimension(800, 200));
		_contentPane = new JPanel(new BorderLayout());
		_pathField = new JTextField(20);
		_choseFolder = new JButton("Browse ...");

		// Sub panel contenant le choix du dossier
		JPanel choicePanel = new JPanel();
		choicePanel.setBorder(BorderFactory
				.createTitledBorder("Folder choice "));
		choicePanel.add(_pathField);
		choicePanel.add(_choseFolder);

		_startButton = new JButton("Start");

		// Sub panel contenant la progression et le bouton de démarrage
		JPanel southPanel = new JPanel();
		southPanel.add(_startButton);

		// Ajout des subPanels dans le content pane
		_contentPane.add(choicePanel, BorderLayout.CENTER);
		_contentPane.add(southPanel, BorderLayout.SOUTH);

		this.setContentPane(_contentPane);
		_choseFolder.addActionListener(new ChooseListener(this._pathField));
		_startButton.addActionListener(new StartListener(this._pathField));

		return _pathField;

	}

}