package GUI.start;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListModel;

import builder.model.DefaultBuilderSpirale;

import model.Spirale;
import model.pencils.PencilLine;
import model.pencils.PencilSet;

import GUI.animation.AnimationFrame;
import GUI.animation.AnimationPanel;

public class StartFrame extends JFrame {

	public static void main(String[] args) {
		Spirale s = new DefaultBuilderSpirale().getInstance();
		s.Animation();
	}
}
