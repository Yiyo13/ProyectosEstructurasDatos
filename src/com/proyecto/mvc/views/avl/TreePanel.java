package com.proyecto.mvc.views.avl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.proyecto.mvc.models.AVLTree;
import com.proyecto.mvc.models.NodeAVL;

public class TreePanel extends JPanel {

	private static final int GAP_X = 58;
	private static final int GAP_Y = 75;
	private static final int W = 54;
	private static final int H = 36;

	private AVLTree tree;
	private int highlight;
	private int position;

	public TreePanel() {
		setBackground(Color.WHITE);
		this.highlight = -1;
	}

	public void setTree(AVLTree tree) {
		this.tree = tree;
		int size = tree == null ? 0 : tree.size();
		int height = tree == null ? 0 : tree.height(tree.getRoot());
		setPreferredSize(new Dimension(size * GAP_X + 40, height * GAP_Y + 40));
		revalidate();
		repaint();
	}

	public void setHighlight(int highlight) {
		this.highlight = highlight;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (tree == null || tree.isEmpty()) {
			g.drawString("Arbol vacio", 20, 30);
			return;
		}

		position = 0;
		draw(g, tree.getRoot(), 0);
	}

	private int draw(Graphics g, NodeAVL node, int level) {// x por inorden

		if (node == null) {
			return -1;
		}

		int y = 20 + level * GAP_Y;

		int leftX = draw(g, node.getLeft(), level + 1);
		int x = 20 + position * GAP_X;
		position++;
		int rightX = draw(g, node.getRight(), level + 1);

		g.setColor(Color.GRAY);
		if (leftX != -1) {
			g.drawLine(x + W / 2, y + H, leftX + W / 2, y + GAP_Y);
		}
		if (rightX != -1) {
			g.drawLine(x + W / 2, y + H, rightX + W / 2, y + GAP_Y);
		}

		g.setColor(node.getKey() == highlight ? new Color(255, 215, 120) : new Color(200, 225, 255));
		g.fillRoundRect(x, y, W, H, 12, 12);
		g.setColor(Color.DARK_GRAY);
		g.drawRoundRect(x, y, W, H, 12, 12);

		g.setFont(new Font("SansSerif", Font.BOLD, 12));
		g.drawString(String.valueOf(node.getKey()), x + 6, y + 15);
		g.setFont(new Font("SansSerif", Font.PLAIN, 10));
		g.drawString("FB " + tree.getBalance(node) + "  h " + node.getHeight(), x + 6, y + 29);

		return x;
	}
}
