import java.io.*;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Scores extends JFrame{
	
	Scores(){
		//Frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(640, 800);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setTitle("Top Scores");
		this.getContentPane().setBackground(Quiz.blackC);
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("icon.png")).getImage());
		
		//Label
		JLabel gameName = new JLabel("TOP 5");
		JLabel credit = new JLabel("Created with Love by MhmdSAbdlh©");
		JLabel scores[] = new JLabel[5];
		gameName.setBounds(0, 70, 640, 100);
		gameName.setHorizontalAlignment(0);
		gameName.setFont(Quiz.bigF);
		gameName.setForeground(Quiz.greenC);
		credit.setHorizontalAlignment(0);
		credit.setBounds(0, 730, 640, 20);
		credit.setForeground(Quiz.whiteC);
		
		//Scores
		ArrayList<Integer> scoreNum = new ArrayList<Integer>();
		ArrayList<String> scoreName = new ArrayList<String>();
		
		BufferedReader SavedScore=null;
		String line ="";
		try{
			SavedScore = new BufferedReader(new FileReader(new File("Quiz_Result.csv")));
			while((line = SavedScore.readLine())!=null){
				String[] row = line.split(",");
				scoreNum.add(Integer.valueOf(row[0]));
				scoreName.add(row[1]);
			}
			SavedScore.close();
		} catch (Exception e) {}
		for (int i = 0; i < scoreNum.size(); i++)
	        for (int j = 0; j < scoreNum.size(); j++)
	            if (scoreNum.get(i) >= scoreNum.get(j)) {
	                int x = scoreNum.get(i);
	                String y = scoreName.get(i);
	                scoreNum.set(i, scoreNum.get(j));
	                scoreName.set(i, scoreName.get(j));
	                scoreNum.set(j, x);
	                scoreName.set(j, y);
	            }
		
		//Button
		JButton back = new JButton("Back");
		back.setFont(Quiz.largeF);
		back.setBackground(Quiz.greenC);
		back.setForeground(Quiz.blackC);
		back.setBorder(Quiz.border);
		back.setBounds(120, 620, 400, 50);
		back.setFocusable(false);
		back.addActionListener(e-> {
			this.dispose();
			new Intro();
			});
		if(scoreNum.size()>=5)
			for(int i=0;i<5;i++) {
				scores[i] = new JLabel("");
				scores[i].setFont(Quiz.largeF);
				scores[i].setBounds(50, 180+i*70, 700, 100);
				scores[i].setFocusable(false);
				scores[i].setForeground(Quiz.whiteC);
				scores[i].setText(i+1+"- "+scoreName.get(i)+" ---->"+ scoreNum.get(i));
				this.add(scores[i]);
			}
		else
			for(int i=0;i<scoreNum.size();i++) {
				scores[i] = new JLabel("");
				scores[i].setFont(Quiz.largeF);
				scores[i].setForeground(Quiz.whiteC);
				scores[i].setBounds(50, 180+i*70, 700, 100);
				scores[i].setFocusable(false);
				scores[i].setText(i+1+") "+scoreName.get(i)+"-->"+ scoreNum.get(i));
				this.add(scores[i]);
			}
		
		//Add to frame
		this.add(gameName);
		this.add(back);
		this.add(credit);
		
		//Show frame
		this.getRootPane().setDefaultButton(back);
		this.setVisible(true);
		}
	}