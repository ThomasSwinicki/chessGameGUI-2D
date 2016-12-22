// Project 8: Rhythminator Part 3
// CSE1102 Spring 2016
// Trevor Svec
// 4/29/16
// TA: Badar Almarri
// Section: 013
// Instructor: Jeffrey A. Meunier

package gui;

import java.awt.event.MouseEvent;



/**
 * This class creates the general version of a button, so
 * other buttons can be created (quit,save,load,etc.)
 * @author Trevor Svec Local
 *
 */

public class Button extends Box
{

  private Controller _controller;

  private static final long serialVersionUID = 1L;

  public Button(Controller controller, String text)
  {
    super();
    _controller = controller;
    this.setText(text);
    this.setForeground(Colors.BUTTON_FG);
    this.setBackground(Colors.BUTTON_BG);
  }

  @Override
  public void mousePressed(MouseEvent mev)
  {
    _controller.buttonPressed(this);
  }

  @Override
  public String toString()
  {
    return "Button(" + getText() + ")";
  }

}
