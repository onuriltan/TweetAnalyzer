



package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;




import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.DatabaseModel;
import model.UrlModel;

public class UrlView extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UrlModel model;



	public UrlView(UrlModel model){
		this.model = model;



	}

	public void populateUrlPanel(){	

		this.setLayout(new BorderLayout());
		this.setBackground(Color.white);
		model.getLabelOne().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelOne().setForeground(Color.BLACK);

		model.getLabelTwo().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelTwo().setForeground(Color.BLACK);

		model.getLabelThree().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelThree().setForeground(Color.BLACK);

		model.getLabelFour().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelFour().setForeground(Color.BLACK);

		model.getLabelFive().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelFive().setForeground(Color.BLACK);

		model.getLabelSix().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelSix().setForeground(Color.BLACK);

		model.getLabelSeven().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelSeven().setForeground(Color.BLACK);

		model.getLabelEight().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelEight().setForeground(Color.BLACK);

		model.getLabelNine().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelNine().setForeground(Color.BLACK);

		model.getLabelTen().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelTen().setForeground(Color.BLACK);



		JPanel spamPanel = new JPanel();
		JPanel	notSpamPanel = new JPanel();
		spamPanel.setLayout(new BorderLayout());
		notSpamPanel.setLayout(new BorderLayout());



		JTextPane textPane = new JTextPane();
		model.setTextPane(textPane); 
		JScrollPane	spamScrollPane = new JScrollPane(textPane);
		TitledBorder spamTitle = new TitledBorder(new LineBorder(Color.WHITE, 1),"URL STREAM");
		spamTitle.setTitleFont(new Font("Arial", Font.BOLD, 14));
		spamTitle.setTitleJustification(TitledBorder.CENTER);
		spamTitle.setTitlePosition(TitledBorder.TOP);
		model.setSpamTitle(spamTitle);
		Color red = new Color(255, 0, 0);
		spamTitle.setTitleColor(red);
		spamScrollPane.setBorder(spamTitle);
		spamScrollPane.setPreferredSize((new Dimension(250,500)));
		spamScrollPane.setBackground(Color.white);
		model.setSpamScrollPane(spamScrollPane);
		spamPanel.setPreferredSize((new Dimension(250,500)));
		spamPanel.setBackground(Color.white);
		spamPanel.add(spamScrollPane, BorderLayout.NORTH);
		model.setSpamPanel(spamPanel);


		JTextPane verifiedTextPane = new JTextPane();
		model.setVerifiedTextPane(verifiedTextPane); 

		JScrollPane notSpamScrollPane = new JScrollPane(verifiedTextPane);
		model.setNotSpamScrollPane(notSpamScrollPane);
		TitledBorder notSpamTitle = new TitledBorder(new LineBorder(Color.WHITE, 1),"VERIFIED URLS");
		model.setNotSpamTitle(notSpamTitle);
		notSpamTitle.setTitleFont(new Font("Arial", Font.BOLD, 14));
		notSpamTitle.setTitleJustification(TitledBorder.CENTER);
		notSpamTitle.setTitlePosition(TitledBorder.TOP);
		Color green = new Color(34,139,34);
		notSpamTitle.setTitleColor(green);
		notSpamScrollPane.setBorder(notSpamTitle);
		notSpamScrollPane.setPreferredSize((new Dimension(300,500)));
		notSpamPanel.setPreferredSize((new Dimension(300,500)));
		notSpamPanel.setBackground(Color.white);
		notSpamScrollPane.setBackground(Color.white);
		notSpamPanel.add(notSpamScrollPane, BorderLayout.NORTH);
		model.setNotSpamScrollPane(notSpamScrollPane);
		model.setNotSpamPanel(notSpamPanel);
		model.getLabelList().add(model.getLabelOne());
		model.getLabelList().add(model.getLabelTwo());
		model.getLabelList().add(model.getLabelThree());
		model.getLabelList().add(model.getLabelFour());
		model.getLabelList().add(model.getLabelFive());
		model.getLabelList().add(model.getLabelSix());
		model.getLabelList().add(model.getLabelSeven());
		model.getLabelList().add(model.getLabelEight());
		model.getLabelList().add(model.getLabelNine());
		model.getLabelList().add(model.getLabelTen());

		JPanel topOccuredUrls = new JPanel();
		topOccuredUrls.setLayout(new GridLayout(0,1));
		topOccuredUrls.add(model.getLabelList().get(0));
		topOccuredUrls.add(model.getLabelList().get(1));
		topOccuredUrls.add(model.getLabelList().get(2));
		topOccuredUrls.add(model.getLabelList().get(3));
		topOccuredUrls.add(model.getLabelList().get(4));
		topOccuredUrls.add(model.getLabelList().get(5));
		topOccuredUrls.add(model.getLabelList().get(6));
		topOccuredUrls.add(model.getLabelList().get(7));
		topOccuredUrls.add(model.getLabelList().get(8));
		topOccuredUrls.add(model.getLabelList().get(9));
		TitledBorder topOccuredTitle = new TitledBorder(new LineBorder(Color.WHITE, 1),"TOP URLS");
		topOccuredTitle.setTitleFont(new Font("Arial", Font.BOLD, 14));
		topOccuredTitle.setTitleJustification(TitledBorder.CENTER);
		topOccuredTitle.setTitlePosition(TitledBorder.TOP);
		Color redd = new Color(255, 0, 0);
		spamTitle.setTitleColor(redd);
		topOccuredUrls.setBorder(topOccuredTitle);
		topOccuredUrls.setPreferredSize((new Dimension(300,250)));
		topOccuredUrls.setBackground(Color.white);

		JSplitPane	splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, notSpamPanel, topOccuredUrls);
		splitPanel.setResizeWeight(0.5);
		splitPanel.setBackground(Color.white);
		model.setSplitPanel(splitPanel);

		JSplitPane	splitPanel1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPanel, spamPanel);
		splitPanel1.setResizeWeight(0.5);
		splitPanel1.setBackground(Color.white);
		model.setSplitPanel(splitPanel);

		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, notSpamPanel, topOccuredUrls);
		JSplitPane sp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp, spamPanel);

		add(sp2,BorderLayout.CENTER);



		// add(splitPanel,BorderLayout.PAGE_START);
		// add(topOccuredUrls,BorderLayout.LINE_END);
		//  add(spamPanel,BorderLayout.PAGE_START);//TODO Model ve view klası değiştir sonra appframe e ekle sonra streamdeyken textleri ayarlat .



	}	



	public void updateUrl(DatabaseModel database,Hashtable<String, Integer> topSimilarTrends){

		ArrayList<Entry<String, Integer>> sortedHashTable = sortValue(topSimilarTrends);
		ArrayList<String> sortedList = new ArrayList<String>();
		for (int i = 0; i < sortedHashTable.size(); i++) {
			sortedList.add(sortedHashTable.get(i).toString());
		}



		for (int i = 0; i < sortedList.size(); i++) {

			if(i == 0){
				String[] parts = sortedList.get(0).toString().split("=", 2);
				String tokenName = parts[0];
				String tokenValue = parts[1];
				model.getLabelList().get(0).setText(tokenName+"      "+tokenValue);
			}
			if(i == 1){
				String[] parts = sortedList.get(1).toString().split("=", 2);
				String tokenName = parts[0];
				String tokenValue = parts[1];
				model.getLabelList().get(1).setText(tokenName+"      "+tokenValue);			}
			if(i == 2){
				String[] parts = sortedList.get(2).toString().split("=", 2);
				String tokenName = parts[0];
				String tokenValue = parts[1];
				model.getLabelList().get(2).setText(tokenName+"      "+tokenValue);			}
			if(i == 3){
				String[] parts = sortedList.get(3).toString().split("=", 2);
				String tokenName = parts[0];
				String tokenValue = parts[1];
				model.getLabelList().get(3).setText(tokenName+"      "+tokenValue);			}
			if(i == 4){
				String[] parts = sortedList.get(4).toString().split("=", 2);
				String tokenName = parts[0];
				String tokenValue = parts[1];
				model.getLabelList().get(4).setText(tokenName+"      "+tokenValue);
			}
			if(i == 5){
				String[] parts = sortedList.get(5).toString().split("=", 2);
				String tokenName = parts[0];
				String tokenValue = parts[1];
				model.getLabelList().get(5).setText(tokenName+"      "+tokenValue);
			}
			if(i == 6){
				String[] parts = sortedList.get(6).toString().split("=", 2);
				String tokenName = parts[0];
				String tokenValue = parts[6];
				model.getLabelList().get(2).setText(tokenName+"      "+tokenValue);			}			
			if(i == 7){
				String[] parts = sortedList.get(7).toString().split("=", 2);
				String tokenName = parts[0];
				String tokenValue = parts[1];
				model.getLabelList().get(7).setText(tokenName+"      "+tokenValue);
			}
			if(i == 8){
				String[] parts = sortedList.get(8).toString().split("=", 2);
				String tokenName = parts[0];
				String tokenValue = parts[8];
				model.getLabelList().get(2).setText(tokenName+"      "+tokenValue);			}
			if(i == 9){
				String[] parts = sortedList.get(9).toString().split("=", 2);
				String tokenName = parts[0];
				String tokenValue = parts[1];
				model.getLabelList().get(9).setText(tokenName+"      "+tokenValue);

			}


		}

		model.getTextPane().setText(model.getText());
		model.getVerifiedTextPane().setText(database.getUrlText());








	}
	public ArrayList<Map.Entry<String, Integer>>  sortValue(Hashtable<String, Integer> t){

		//Transfer as List and sort it
		ArrayList<Map.Entry<String, Integer>> l = new ArrayList<Entry<String, Integer>>(t.entrySet());
		Collections.sort(l, new Comparator<Map.Entry<String, Integer>>(){

			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return Integer.compare(o2.getValue(), o1.getValue());
			}});

		return l;
	}
}


