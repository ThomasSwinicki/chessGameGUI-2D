import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class ChessGame extends JFrame implements ActionListener{
	
	private Container pane = getContentPane();
	
	public ChessGame(){
		initUI();
	}
	
	public void initUI(){
		
		setTitle("Chess");
		ImageIcon chessIcon = new ImageIcon("Chess_Icon.png");
		setIconImage(chessIcon.getImage());
		
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			ChessGame chess = new ChessGame();
			chess.setVisible(true);
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void createLayout(JComponent ... arg){
		MigLayout m1 = new MigLayout();
		pane.setLayout(m1);
	}

}
