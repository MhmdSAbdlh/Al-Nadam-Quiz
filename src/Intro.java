import javax.swing.*;

@SuppressWarnings("serial")
public class Intro extends JFrame{
	
	public static JTextField user_name;
	
	Intro(){	
		//Frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(640, 800);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setTitle("Quiz");
		this.getContentPane().setBackground(Quiz.blackC);
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("icon.png")).getImage());
		
		//Label
		JLabel gameName = new JLabel("AL NADAM QUIZ");
		JLabel credit = new JLabel("Created with Love by MhmdSAbdlh©");
		gameName.setBounds(0, 50, 640, 100);
		gameName.setHorizontalAlignment(0);
		gameName.setFont(Quiz.bigF);
		gameName.setForeground(Quiz.greenC);
		credit.setHorizontalAlignment(0);
		credit.setBounds(0, 730, 640, 20);
		credit.setForeground(Quiz.whiteC);
		
		//TextField
		user_name = new JTextField("Your Name here");
		user_name.setHorizontalAlignment(0);
		user_name.setFont(Quiz.medF);
		user_name.setBounds(200	, 200, 220, 50);
		user_name.setBackground(Quiz.whiteC);
		user_name.setForeground(Quiz.greenC);
		user_name.setBorder(Quiz.border);
		user_name.setSelectionStart(0);
		user_name.setSelectionEnd(14);
		
		//Button
		JButton newGame = new JButton("NEW GAME");
		JButton scores = new JButton("SCORES");
		JButton Exit = new JButton("EXIT GAME");
		newGame.setFont(Quiz.largeF);
		newGame.setBounds(150, 350, 320, 50);
		newGame.setBorder(Quiz.border);
		newGame.setBackground(Quiz.greenC);
		newGame.setForeground(Quiz.whiteC);
		newGame.setFocusable(false);
		newGame.addActionListener(e-> {
			this.dispose();
			new Quiz();
			});
		scores.setFont(Quiz.largeF);
		scores.setBounds(150, 450, 320, 50);
		scores.setBorder(Quiz.border);
		scores.setBackground(Quiz.greenC);
		scores.setForeground(Quiz.whiteC);
		scores.setFocusable(false);
		scores.addActionListener(e-> {
			this.dispose();
			new Scores();
		});
		Exit.setFont(Quiz.largeF);
		Exit.setBounds(150, 550, 320, 50);
		Exit.setBorder(Quiz.border);
		Exit.setBackground(Quiz.greenC);
		Exit.setForeground(Quiz.whiteC);
		Exit.setFocusable(false);
		Exit.addActionListener(e-> System.exit(0));
		
		//Add to frame
		this.add(gameName);
		this.add(user_name);
		this.add(newGame);
		this.add(scores);
		this.add(Exit);
		this.add(credit);
		
		//Show frame
		this.getRootPane().setDefaultButton(newGame);
		this.setVisible(true);
	}
}