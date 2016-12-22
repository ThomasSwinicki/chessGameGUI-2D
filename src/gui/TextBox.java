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
 * This class allows a textbox to be created as a version
 * of a box that allows the user to enter and register
 * a string.
 * @author Trevor Svec Local
 *
 */

public class TextBox extends Box
{

  private String _dialogTitle;
  private String _dialogPrompt;

  private static final long serialVersionUID = 1;

  public TextBox()
  {
    this("Click to enter text");
  }

  public TextBox(String text)
  {
    this(text, "Enter new text");
  }

  public TextBox(String text, String dialogPrompt)
  {
    this(text, dialogPrompt, "Question");
  }
  
  public TextBox(String text, String dialogPrompt, String dialogTitle)
  {
    super(text);
    _dialogPrompt = dialogPrompt;
    _dialogTitle = dialogTitle;
  }

  @Override
  public void mousePressed(MouseEvent mev)
  {
    String initialValue = this.getText();
    String text = Dialog.getString(_dialogTitle, _dialogPrompt, initialValue);
    if(text != null)
      setText(text);
  }

}
