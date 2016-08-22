package GUI.explainTree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import explainTree.Node;

public class ExplainTreeLabel extends JLabel {
	
	private final Node source;
	
	private static final long serialVersionUID = 1L;
	public static int width =20, height=20;
	
	
	public ExplainTreeLabel(Node source){
		super(source.getText(), SwingConstants.CENTER);
		this.source=source;
		//this.setLocation(source.getOrder()*width-width/2, source.getDepth()*height);
		//this.setSize(width*2, height);
		//setBounds((source.getOrder())*width-width/2, source.getDepth()*height, width*2, height);
		this.setPreferredSize(new Dimension(2*width, height));
		this.setMinimumSize(new Dimension(width, height));
		setVerticalTextPosition(SwingConstants.CENTER);
		setVisible(true);
		this.setBackground(Color.white);
	}

	
	@Override
	public void paint(Graphics g){
		g.setColor(Color.white);
		g.fillOval(0, 0, getWidth()-1, getHeight()-1);
		g.setColor(Color.black);
		g.drawOval(0, 0, getWidth()-1, getHeight()-1);
		super.paint(g);
		//Rectangle r=this.getBounds();
		//g.drawRect(0, 0, r.width-1, r.height-1);
	}
}
