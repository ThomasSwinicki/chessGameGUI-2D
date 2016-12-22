// Project 8: Rhythminator Part 3
// CSE1102 Spring 2016
// Trevor Svec
// 4/29/16
// TA: Badar Almarri
// Section: 013
// Instructor: Jeffrey A. Meunier

package gui;

import java.util.Scanner;

import gui.Button;
import gui.Dialog;
import message.ISubscriber;
import message.Message;
import model.Clock;
import model.Model;
import view.NoteSquare;
import view.View;

/**
 * This class sets up the controller to interact with the GUI display,
 * using and changing the audio mixer.
 * @author Trevor Svec Local
 *
 */

public class Controller implements ISubscriber
{

  private Model       _model;
  private Clock       _clock;
  private View        _view;
  private long        _delay;
  private boolean[][] _beats;
  private String[]    _sounds;

  public Controller(Clock clock, Model model)
  {
   _clock = clock;
   _model = model;
   _sounds = new String[_model._numTracks];
   _beats  = new boolean[_model._numTracks][_model._numBeats];
   _clock.subscribe(this);
  }

  public void buttonPressed(Button button)
  {
	if(button.getText().equals("Save"))
      _buttonSave();
    else if(button.getText().equals("Load"))
      _buttonLoad();
    else if(button.getText().equals("Play"))
    {
      _model.startPlaying()  ;
      System.out.println("Controller.buttonPressed got Play button");
    }
    else if(button.getText().equals("Stop"))
    {
      _model.stopPlaying();
      System.out.println("Controller.buttonPressed got Stop button");
      _view.clearBeatNumbers();
      _model.setBeatNumber();
      
    }
    else if(button.getText().equals("Quit"))
      _buttonQuit();
    else
      System.out.println("Controller.buttonPressed " + button + " pressed");
  }

  private void _buttonQuit()
  {
    if(Dialog.askYesNo("Exiting program", "Really quit?"))
      System.exit(0);
  }

  private void _buttonLoad()
  {
		Scanner scan = new Scanner(System.in);
		System.out.print("Header name? : ");
		_view._header.setName(scan.nextLine());
		System.out.print("Delay? : ");
		_clock.setDelay((long) scan.nextInt());
		System.out.print("Tracks? : ");
		int tracks = scan.nextInt();
		System.out.print("Beats? : ");
		int beats = scan.nextInt();
		System.out.println("1 is true, 0 is false");
		_model = new Model(tracks, beats);
		Clock clock = _model.getClock();
		for (int i = 0; i < _model._numTracks; i++) {
			System.out.print("Sound name?: ");
			_model.setSoundName(i-1, scan.nextLine());
			for (int j = 0; j < beats; j++) {
				System.out.print("Is there a beat? : ");
				int option = scan.nextInt();
				if (option == 1)
					_model.setNote(i, j, true);
				else
					_model.setNote(i, j, false);
			}
		}
		System.out.println("Controller._buttonLoad called");
	}

  private void _buttonSave() 
  {
		System.out.println(" ");
	  	System.out.println("Rhythm Name: " + _view._header.getFileName() + "   Delay: " + _delay + "ms");
		System.out.println("Tracks: " + _model._numTracks + "   Beats: " + _model._numBeats);
		for (int i = 0; i < _sounds.length; i++) 
		{
			System.out.print("Sound" + (i + 1) + ": " + _sounds[i] + "   Beats: ");
			for (int j = 0; j < _beats[i].length; j++) 
			{
				System.out.print(_beats[i][j] + ", ");
			}
			System.out.println("");
		}
		System.out.println("Controller._buttonSave called");
		System.out.println(" ");
  }
  public void keyPressed(int keyCode)
  {
    System.out.println("Controller.keyPressed " + keyCode);
  }

  public void keyReleased(int keyCode)
  {
    System.out.println("Controller.keyReleased " + keyCode);
  }

  public void keyTyped(char keyChar)
  {
    System.out.println("Controller.keyTyped '" + keyChar + "'");
  }

  public void noteSquareClicked(NoteSquare noteSquare)
  {
    int trackNum = noteSquare.getTrack();
    int beatNum  = noteSquare.getBeat();
    int value    = noteSquare.getValue();
    if (value == 0) {
    	_model.setNote(trackNum, beatNum, false);
    	_beats[noteSquare.getTrack()][noteSquare.getBeat()] = false;
    }
    else {
    	_model.setNote(trackNum, beatNum, true);
    	_beats[noteSquare.getTrack()][noteSquare.getBeat()] = true;
    }
    System.out.println("Controller.noteSquareClicked " + noteSquare);
  }

  @Override
  public void notify(Message message)
  {
	  _view.setBeatNumber( _model.getBeatNumber()-1);
	  System.out.println("Controller.notify " + message);
  }

  public void soundNameSelected(int trackNumber, String soundName)
  {
    _model.setSoundName(trackNumber-1, soundName);
    _view.setSoundName(trackNumber-1, soundName);
    _sounds[trackNumber-1] = soundName;
    System.out.println("Controller.soundNameSelected for track " +  trackNumber + ": " + soundName);
  }

  public void setView(View view)
  {
    _view = view;
  }

  public void sliderChange(String name, int _value)
  {
	_delay = (int)( (double) (15.0 / _value) * 1000) ;
    _clock.setDelay(_delay);
    System.out.println("Controller.sliderChange " + name + " = " + _value);
  }

}
