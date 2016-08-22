package GUI.explainTree;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import explainTree.Node;

public class ExplainTreeLabel extends JLabel {
	
	//private final Node source;
	
	private static final long serialVersionUID = 1L;
	public static int width =65, height=40;
	
	
	public ExplainTreeLabel(Node source){
		super(source.getText(), SwingConstants.CENTER);
		//this.source=source;
		this.setLocation(source.getOrder()*width-width/2, source.getDepth()*height);
		this.setSize(width*2, height);
		//setBounds((source.getOrder())*width-width/2, source.getDepth()*height, width*2, height);
		setVerticalTextPosition(SwingConstants.CENTER);
		setVisible(true);
		this.setBackground(Color.white);
	}

	
	@Override
	public void paint(Graphics g){
		//System.out.println(this.)
		super.paint(g);
		Rectangle r=this.getBounds();
		g.drawRect(0, 0, r.width-1, r.height-1);
	}
}
