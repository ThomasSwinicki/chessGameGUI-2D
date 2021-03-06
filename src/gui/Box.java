// Project 8: Rhythminator Part 3
// CSE1102 Spring 2016
// Trevor Svec
// 4/29/16
// TA: Badar Almarri
// Section: 013
// Instructor: Jeffrey A. Meunier

package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * This class creates the general box structure, set up for other classes to extend it.
 * @author Trevor Svec Local
 *
 */

public class Box extends JPanel implements MouseListener
{

  private String _text = "";

  private static final long serialVersionUID = 1L;

  public Box()
  {
    this("");
  }

  public Box(String text)
  {
    this.setBorder(BorderFactory.createLineBorder(Color.black));
    this.addMouseListener(this);
    _text = text;
  }

  public String getText()
  {
    return _text;
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.drawString(_text, 5, 20);
  }

  @Override
  public void mouseClicked(MouseEvent mev)
  {}

  @Override
  public void mouseEntered(MouseEvent mev)
  {}

  @Override
  public void mouseExited(MouseEvent mev)
  {}

  @Override
  public void mousePressed(MouseEvent mev)
  {}

  @Override
  public void mouseReleased(MouseEvent mev)
  {}

  public void setText(String text)
  {
    _text = text;
    this.repaint();
  }

  @Override
  public String toString()
  {
    return "Square(" + _text + ")";
  }

}
