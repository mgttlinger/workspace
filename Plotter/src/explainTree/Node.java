package explainTree;

import java.util.Collection;

import term.ITerm;

public class Node {
	private final Node[] children;
	private final ITerm source;
	private final int order, depth;

	public Node(ITerm source, int depth, int order) {
		this.source = source;
		this.depth = depth;
		ITerm[] terms = source.getTerms();
		if (terms != null) {
			children = new Node[terms.length];
			int n = children.length;
			int n2 = n / 2;

			int tempOrder = order;
			for (int i = 0; i < n2; i++) {
				children[i] = new Node(terms[i], depth + 1, tempOrder);
				tempOrder = children[i].getMaxOrder() + 1;
			}
			if (n % 2 == 1) {
				children[n2] = new Node(terms[n2], depth + 1, tempOrder);
				tempOrder = children[n2].getMaxOrder() + 1;
				this.order = children[n2].getOrder();
			} else {
				this.order = tempOrder;
				tempOrder++;
			}
			for (int i = n2 + n % 2; i < n; i++) {
				children[i] = new Node(terms[i], depth + 1, tempOrder);
				tempOrder = children[i].getMaxOrder() + 1;
			}
		} else {
			children = new Node[0];
			this.order = order;
		}
	}

	public void getAll(Collection<Node> c) {
		c.add(this);
		for (Node n : children)
			n.getAll(c);
	}

	protected void explain(String s) {
		System.out.printf("%s%s %d%n", s, getText(), children.length);
		for (int i = 0; i < children.length; i++) {
			children[i].explain(s + "    ");
		}
	}

	public int getOrder() {
		return order;
	}

	public int getDepth() {
		return depth;
	}

	public int getMaxOrder() {
		int m = order;
		for (Node n : children)
			m = Math.max(m, n.getMaxOrder());
		return m;
	}

	public int getMaxDepth() {
		int m = depth;
		for (Node n : children)
			m = Math.max(m, n.getMaxDepth());
		return m;
	}

	public String getText() {
		return source.toString();
	}

	public Node[] getChildren() {
		return children;
	}
}
