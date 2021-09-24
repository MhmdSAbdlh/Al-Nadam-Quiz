import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class Quiz extends JFrame implements ActionListener, KeyListener{
	
	String question[] = {
			"Where Orwa saw Razan for the first time?",
			"Which sentence did Abou-Abdo always say?",
			"Why Orwa asked Hanaa who she is on his dream?",
			"What the state that Orwa passed to become healed?",
			"Which episode Hanaa passed away?",
			"Which drink does Hanna love the most?",
			"What is Hanaa illness?",
			"Why did Suhail leave?",
			"What's the name of the novel that Orwa wrote?",
			"The Show start in the events of war, in which country it is?"
	};
	
	String answers[][] = {
			{"At the gym","In a concert" ,"In the TV-Station"},
			{"Because I am his father, he will return","Does the regret help?","If you weren't a wolf, the dogs would eat you"},
			{"Becuase he has a nightmare","Because he forget her","Because he wanted to tell her that she is the lump of life"},
			{"Pain threshold","His Test","Head Pain"},
			{"Episode 29","Episode 25","Episode 27"},
			{"Nescafe","Very hot Tea","Coffee"},
			{"Lung Cancer","Heart weakness","Diabetes"},
			{"He has a new job","to discover other country","He think that his father loves his brother more than him"},
			{"Strangers in the night","The nightwith Hanaa","Love and War"},
			{"Syria","Iraq","Lebanon"}
	};
	
	private char[] answersKey = {'C','A','C','A','C','B','B','C','A','B'};
	
	static Color blackC = new Color(0x171717);
	static Color greenC = new Color(0x346751);
	static Color redC = new Color(0xC84B31);
	static Color whiteC = new Color(0xEDEDED);
	static Font smallF = new Font("Tahoma",Font.PLAIN,20);
	static Font medF = new Font("Tahoma",Font.ITALIC,25);
	static Font largeF = new Font("Tahoma",Font.BOLD,30);
	static Font bigF = new Font("Tahoma", Font.BOLD, 50);
	static Border border = new LineBorder(whiteC, 1);
	
	private int index = 0;
	private int correctA = 0;
	private int perCA = 0;
	private int timer = 10;
	private JLabel questionIndexL = new JLabel("Question "+(index+1));
	private JLabel questionL = new JLabel(question[index]);
	private JButton answersA = new JButton("A");
	private JButton answersB = new JButton("B");
	private JButton answersC = new JButton("C");
	private JButton scores = new JButton("SCORES");
	private JButton newGame = new JButton("NEW QUIZ");
	private JLabel answerAL = new JLabel(answers[index][0]);
	private JLabel answerBL = new JLabel(answers[index][1]);
	private JLabel answerCL = new JLabel(answers[index][2]);
	private JLabel correctAnswer = new JLabel("Correct Answers: "+correctA+"/"+question.length);
	private JLabel perCAL = new JLabel();
	private JLabel timerL = new JLabel(timer+"");
	
	//Timer
	Timer countDown = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			timer--;
			timerL.setText(timer+"");
			if(timer == 0 && index < question.length) {
				correctAnswer();
				nextQuestion();
				timer=10;
				timerL.setText(timer+"");
				}
			}
		});
	
	Quiz(){
		//Frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700,600);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Quiz");
		this.addKeyListener(this);
		this.getContentPane().setBackground(blackC);
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("icon.png")).getImage());
		
		//Label
		questionIndexL.setBounds(0, 20, 700, 40);
		questionIndexL.setFont(largeF);
		questionIndexL.setHorizontalAlignment(0);
		questionIndexL.setForeground(greenC);
		questionL.setBounds(0, 70, 700, 40);
		questionL.setFont(medF);
		questionL.setHorizontalAlignment(0);
		questionL.setForeground(whiteC);
		answerAL.setBounds(100, 150, 600, 50);
		answerAL.setFont(smallF);
		answerAL.setForeground(whiteC);
		answerBL.setBounds(100, 230, 600, 50);
		answerBL.setFont(smallF);
		answerBL.setForeground(whiteC);
		answerCL.setBounds(100, 310, 600, 50);
		answerCL.setFont(smallF);
		answerCL.setForeground(whiteC);
		timerL.setBounds(320, 440, 60, 50);
		timerL.setOpaque(true);
		timerL.setBackground(greenC);
		timerL.setFont(largeF);
		timerL.setForeground(blackC);
		timerL.setBorder(border);
		timerL.setHorizontalAlignment(0);
		correctAnswer.setBounds(0, 500, 700, 50);
		correctAnswer.setFont(medF);
		correctAnswer.setForeground(greenC);
		correctAnswer.setHorizontalAlignment(0);
		perCAL.setBounds(150,150,400,50);
		perCAL.setFont(medF);
		perCAL.setForeground(whiteC);
		perCAL.setHorizontalAlignment(0);
		perCAL.setVisible(false);
		
		//Button
		answersA.setFocusable(false);
		answersA.setForeground(whiteC);
		answersA.setBackground(blackC);
		answersA.setBounds(30,150,50,50);
		answersA.setFont(smallF);
		answersA.addActionListener(this);
		answersB.setFocusable(false);
		answersB.setForeground(whiteC);
		answersB.setBackground(blackC);
		answersB.setBounds(30,230,50,50);
		answersB.setFont(smallF);
		answersB.addActionListener(this);
		answersC.setFocusable(false);
		answersC.setForeground(whiteC);
		answersC.setBackground(blackC);
		answersC.setBounds(30,310,50,50);
		answersC.setFont(smallF);
		answersC.addActionListener(this);
		
		//GameEnd 
		countDown.start();
		newGame.setFont(medF);
		newGame.setForeground(greenC);
		newGame.setBackground(null);
		newGame.setFocusable(false);
		newGame.setBounds(250,300, 200, 50);
		newGame.addActionListener(e-> {
			this.dispose();
			new Intro();
			});
		//Scores
		scores.setFont(medF);
		scores.setForeground(greenC);
		scores.setBackground(null);
		scores.setFocusable(false);
		scores.setBounds(250,400, 200, 50);
		scores.addActionListener(e-> {
			this.dispose();
			new Scores();
			});
		scores.setVisible(false);
		newGame.setVisible(false);
		
		//End of Frame
		this.add(questionIndexL);
		this.add(questionL);
		this.add(answersA);
		this.add(answersB);
		this.add(answersC);
		this.add(answerAL);
		this.add(answerBL);
		this.add(answerCL);
		this.add(timerL);
		this.add(correctAnswer);
		this.add(perCAL);
		this.add(scores);
		this.add(newGame);
		this.setVisible(true);
	}
	
	private void correctAnswer() {
		if(answersKey[index] == 'A') {
			answersA.setEnabled(false);
			answersB.setEnabled(false);
			answersC.setEnabled(false);
			answerAL.setForeground(greenC);
			answerBL.setForeground(redC);
			answerCL.setForeground(redC);
		}
		else
			if(answersKey[index] == 'B') {
				answersA.setEnabled(false);
				answersB.setEnabled(false);
				answersC.setEnabled(false);
				answerAL.setForeground(redC);
				answerBL.setForeground(greenC);
				answerCL.setForeground(redC);
			}
			else {
				answersA.setEnabled(false);
				answersB.setEnabled(false);
				answersC.setEnabled(false);
				answerAL.setForeground(redC);
				answerBL.setForeground(redC);
				answerCL.setForeground(greenC);
			}
	}
		
	private void nextQuestion() {
		index++;
		correctAnswer.setText("Correct Answers: "+correctA+"/"+question.length);
		if(index<question.length) {
			Timer nextQ = new Timer(800, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					answersA.setEnabled(true);
					answersB.setEnabled(true);
					answersC.setEnabled(true);
					answerAL.setForeground(whiteC);
					answerBL.setForeground(whiteC);
					answerCL.setForeground(whiteC);
					questionIndexL.setText("Question "+(index+1));
					questionL.setText(question[index]);
					answerAL.setText(answers[index][0]);
					answerBL.setText(answers[index][1]);
					answerCL.setText(answers[index][2]);
				}
			});
			nextQ.setRepeats(false);
			nextQ.start();
		}
		else {
			Timer endQ = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					questionIndexL.setVisible(false);
					questionL.setVisible(false);
					answersA.setVisible(false);
					answersB.setVisible(false);
					answersC.setVisible(false);
					answerAL.setVisible(false);
					answerBL.setVisible(false);
					answerCL.setVisible(false);
					timerL.setVisible(false);
					correctAnswer.setBounds(150, 100, 400, 50);
					correctAnswer.setForeground(whiteC);
					perCA = (int)((correctA/(double)question.length)*100);
					perCAL.setText(perCA+"% TRUE!");
					perCAL.setVisible(true);
					newGame.setVisible(true);
					scores.setVisible(true);
				}
			});
			endQ.setRepeats(false);
			endQ.start();
			
			//Score Save
			try {
				FileWriter SavedScore = new FileWriter("Quiz_Result.csv",true);
				SavedScore.write(correctA+",");
				SavedScore.write(Intro.user_name.getText()+System.lineSeparator());
				SavedScore.close();
			} catch (NumberFormatException e) {}
			catch (IOException e) {}		
		}	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//ANSWER A
		if(e.getSource() == answersA)
			if(answersKey[index] == 'A') {
				answersA.setEnabled(false);
				answersB.setEnabled(false);
				answersC.setEnabled(false);
				answerAL.setForeground(greenC);
				answerBL.setForeground(redC);
				answerCL.setForeground(redC);
				correctA++; 
			}
			else
				if(answersKey[index] == 'B') {
					answersA.setEnabled(false);
					answersB.setEnabled(false);
					answersC.setEnabled(false);
					answerAL.setForeground(redC);
					answerBL.setForeground(greenC);
					answerCL.setForeground(redC);
				}
				else {
					answersA.setEnabled(false);
					answersB.setEnabled(false);
					answersC.setEnabled(false);
					answerAL.setForeground(redC);
					answerBL.setForeground(redC);
					answerCL.setForeground(greenC);
				}
		//ANSWER B
		if(e.getSource() == answersB)
			if(answersKey[index] == 'A') {
				answersA.setEnabled(false);
				answersB.setEnabled(false);
				answersC.setEnabled(false);
				answerAL.setForeground(greenC);
				answerBL.setForeground(redC);
				answerCL.setForeground(redC);
			}
			else
				if(answersKey[index] == 'B') {
					answersA.setEnabled(false);
					answersB.setEnabled(false);
					answersC.setEnabled(false);
					answerAL.setForeground(redC);
					answerBL.setForeground(greenC);
					answerCL.setForeground(redC);
					correctA++; 
				}
				else {
					answersA.setEnabled(false);
					answersB.setEnabled(false);
					answersC.setEnabled(false);
					answerAL.setForeground(redC);
					answerBL.setForeground(redC);
					answerCL.setForeground(greenC);
				}
		//ANSWER C
		if(e.getSource() == answersC)
			if(answersKey[index] == 'A') {
				answersA.setEnabled(false);
				answersB.setEnabled(false);
				answersC.setEnabled(false);
				answerAL.setForeground(greenC);
				answerBL.setForeground(redC);
				answerCL.setForeground(redC);
			}
			else
				if(answersKey[index] == 'B') {
					answersA.setEnabled(false);
					answersB.setEnabled(false);
					answersC.setEnabled(false);
					answerAL.setForeground(redC);
					answerBL.setForeground(greenC);
					answerCL.setForeground(redC);
				}
				else {
					answersA.setEnabled(false);
					answersB.setEnabled(false);
					answersC.setEnabled(false);
					answerAL.setForeground(redC);
					answerBL.setForeground(redC);
					answerCL.setForeground(greenC);
					correctA++; 
				}
		timer = 10;
		timerL.setText(timer+"");		
		nextQuestion();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//ANSWER A
				if((e.getKeyChar() == 'a' || e.getKeyChar() == 'A') && index<question.length) {
					if(answersKey[index] == 'A') {
						answersA.setEnabled(false);
						answersB.setEnabled(false);
						answersC.setEnabled(false);
						answerAL.setForeground(greenC);
						answerBL.setForeground(redC);
						answerCL.setForeground(redC);
						correctA++; 
					}
					else
						if(answersKey[index] == 'B') {
							answersA.setEnabled(false);
							answersB.setEnabled(false);
							answersC.setEnabled(false);
							answerAL.setForeground(redC);
							answerBL.setForeground(greenC);
							answerCL.setForeground(redC);
						}
						else {
							answersA.setEnabled(false);
							answersB.setEnabled(false);
							answersC.setEnabled(false);
							answerAL.setForeground(redC);
							answerBL.setForeground(redC);
							answerCL.setForeground(greenC);
						}
					timer = 10;
					timerL.setText(timer+"");		
					nextQuestion();
				}
				//ANSWER B
				if((e.getKeyChar() == 'b' || e.getKeyChar() == 'B') && index<question.length) {
					if(answersKey[index] == 'A') {
						answersA.setEnabled(false);
						answersB.setEnabled(false);
						answersC.setEnabled(false);
						answerAL.setForeground(greenC);
						answerBL.setForeground(redC);
						answerCL.setForeground(redC);
					}
					else
						if(answersKey[index] == 'B') {
							answersA.setEnabled(false);
							answersB.setEnabled(false);
							answersC.setEnabled(false);
							answerAL.setForeground(redC);
							answerBL.setForeground(greenC);
							answerCL.setForeground(redC);
							correctA++; 
						}
						else {
							answersA.setEnabled(false);
							answersB.setEnabled(false);
							answersC.setEnabled(false);
							answerAL.setForeground(redC);
							answerBL.setForeground(redC);
							answerCL.setForeground(greenC);
						}
					timer = 10;
					timerL.setText(timer+"");		
					nextQuestion();
				}
				//ANSWER C
				if((e.getKeyChar() == 'c' || e.getKeyChar() == 'C') && index<question.length) {
					if(answersKey[index] == 'A') {
						answersA.setEnabled(false);
						answersB.setEnabled(false);
						answersC.setEnabled(false);
						answerAL.setForeground(greenC);
						answerBL.setForeground(redC);
						answerCL.setForeground(redC);
					}
					else
						if(answersKey[index] == 'B') {
							answersA.setEnabled(false);
							answersB.setEnabled(false);
							answersC.setEnabled(false);
							answerAL.setForeground(redC);
							answerBL.setForeground(greenC);
							answerCL.setForeground(redC);
						}
						else {
							answersA.setEnabled(false);
							answersB.setEnabled(false);
							answersC.setEnabled(false);
							answerAL.setForeground(redC);
							answerBL.setForeground(redC);
							answerCL.setForeground(greenC);
							correctA++; 
						}
					timer = 10;
					timerL.setText(timer+"");		
					nextQuestion();
				}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
