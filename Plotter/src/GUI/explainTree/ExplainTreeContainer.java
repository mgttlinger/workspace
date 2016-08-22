package GUI.explainTree;

import static GUI.explainTree.ExplainTreeLabel.height;
import static GUI.explainTree.ExplainTreeLabel.width;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import javax.imageio.ImageIO;

import explainTree.ExplainTree;
import explainTree.Node;

public class ExplainTreeContainer extends Container {
	private static final long serialVersionUID = 1L;

	private final HashSet<Link> links = new HashSet<Link>();

	private class Link extends Container {
		final ExplainTreeLabel oben, unten;

		Link(ExplainTreeLabel oben, ExplainTreeLabel unten) {
			this.oben = oben;
			this.unten = unten;
			this.setVisible(true);
			this.setEnabled(true);
		}

		@Override
		public void paint(Graphics g) {
			int x1 = oben.getLocation().x + oben.getWidth() / 2;
			int y1 = oben.getLocation().y + oben.getHeight() / 2;
			int x2 = unten.getLocation().x + unten.getWidth() / 2;
			int y2 = unten.getLocation().y + oben.getHeight() / 2;
			g.drawLine(x1, y1, x2, y2);
		}
	}

	private ExplainTreeLabel setup(Node t) {
		GridBagConstraints c = new GridBagConstraints();
		ExplainTreeLabel label = new ExplainTreeLabel(t);
		c.ipadx = 10;
		c.ipady = 10;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = t.getOrder();
		c.gridy = t.getDepth();
		add(label, c);
		for (Node child : t.getChildren()) {
			Link l = new Link(label, setup(child));
			links.add(l);
		}
		return label;
	}

	public ExplainTreeContainer(ExplainTree et) {
		// nodes = new HashSet<Node>();
		// et.getAll(nodes);

		this.setLayout(new GridBagLayout());
		setup(et);

		/*
		 * for (Node n : nodes) add(new ExplainTreeLabel(n));
		 * 
		 * this.setPreferredSize(new Dimension((et.getMaxOrder() + 1) * width,
		 * (et.getMaxDepth() + 1) * height));
		 */
		this.setVisible(true);
	}

	private int getX(Node n) {
		return n.getOrder() * width + width / 2;
	}

	private int getY(Node n) {
		return n.getDepth() * height + height / 2;
	}

	private void line(Graphics g, Node n, Node c) {
		int x1 = getX(n);
		int y1 = getY(n);
		int x2 = getX(c);
		int y2 = getY(c);

		// int dx=x1-x2;
		// int dy=y1-y2;

		g.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void paint(Graphics g) {
		for (Link l : links)
			l.paint(g);
		// for (Node n : nodes)
		// for (Node c : n.getChildren())
		// line(g, n, c);
		// g.drawLine(getX(n), getY(n), getX(c), getY(c));

		super.paint(g);
	}

	public void output() {
		File file = new File("img\\explainTree.png");
		try {
			BufferedImage img = new BufferedImage(getWidth(), getHeight(),
					BufferedImage.TYPE_3BYTE_BGR);
			paint(img.getGraphics());
			ImageIO.write(img, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(file.getAbsolutePath());
	}
}
