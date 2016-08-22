package gui

import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.Color

class GUIFrame(S: modell.System) extends JFrame {
  val G = new GUI(S)
  this.add(G)

  val ticker = new Ticker(G)
  this.addKeyListener(ticker)
  ticker.start

  this.setJMenuBar(setupJMenu());
  this.setPreferredSize(new Dimension(1000, 800))
  this.pack();
  this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
  this.setVisible(true);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  private def setupJMenu(): JMenuBar = {
    val menu = new JMenuBar()

    val menuTool: JMenu = new JMenu("Tools")
    var menuItem = new JMenuItem("select");
    menuItem.addActionListener(new Listener(() => G.setActive(G.selectAction)));
    menuTool.add(menuItem);
    menuItem = new JMenuItem("delete");
    menuItem.addActionListener(new Listener(() => G.setActive(G.deleteAction)));
    menuTool.add(menuItem);
    menuItem = new JMenuItem("add Goo");
    menuItem.addActionListener(new Listener(() => G.setActive(G.addGooAction)));
    menuTool.add(menuItem);
    menuItem = new JMenuItem("add Heavy Goo");
    menuItem.addActionListener(new Listener(() => G.setActive(G.addHGooAction)));
    menuTool.add(menuItem);
    menuItem = new JMenuItem("add Fix");
    menuItem.addActionListener(new Listener(() => G.setActive(G.addFixAction)));
    menuTool.add(menuItem);
    menu.add(menuTool)

    val menuLink = new JMenuItem("link")
    menuLink.addActionListener(new Listener(() => G.linkSelected()))
    menu.add(menuLink)
    menu
  }
}

import java.awt.event.ActionListener
import java.awt.event.ActionEvent
class Listener(val f: () => Unit) extends ActionListener {
  def actionPerformed(e: ActionEvent) = f()
}