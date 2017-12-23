package com.sara.tfidf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class ChooseListener implements ActionListener {

	private JTextField _textField;
	File currenDir = new File("CorpusDirectory");

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser(currenDir);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File folder = chooser.getSelectedFile();
			this._textField.setText(folder.getAbsolutePath());
		}
	}

	public ChooseListener(JTextField param) {
		this._textField = param;
		this._textField.setText(currenDir.getAbsolutePath());
	}

}
