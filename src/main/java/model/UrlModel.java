package model;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

public class UrlModel {

	private JTextField labelOne = new JTextField();
	private JTextField labelTwo = new JTextField();
	private JTextField labelThree = new JTextField();
	private JTextField labelFour = new JTextField();
	private JTextField labelFive = new JTextField();
	private JTextField labelSix = new JTextField();
	private JTextField labelSeven = new JTextField();
	private JTextField labelEight = new JTextField();
	private JTextField labelNine = new JTextField();
	private JTextField labelTen = new JTextField();
	private ArrayList<JTextField> labelList = new  ArrayList<JTextField>();
	private JPanel spamPanel;
	private JPanel notSpamPanel;
	private JSplitPane splitPanel;
	private JScrollPane spamScrollPane;
	private JScrollPane notSpamScrollPane;
	private TitledBorder spamTitle;
	private TitledBorder notSpamTitle;
	private String text;
	private JPanel topOccuredUrls ;
	private JTextPane textPane;
	private JTextPane verifiedTextPane;
	private String mostUsedUrlText;


	public JTextPane getVerifiedTextPane() {
		return verifiedTextPane;
	}
	public void setVerifiedTextPane(JTextPane verifiedTextPane) {
		this.verifiedTextPane = verifiedTextPane;
	}
	public JTextPane getTextPane() {
		return textPane;
	}
	public void setTextPane(JTextPane textPane) {
		this.textPane = textPane;
	}
	public JPanel getTopOccuredUrls() {
		return topOccuredUrls;
	}
	public void setTopOccuredUrls(JPanel topOccuredUrls) {
		this.topOccuredUrls = topOccuredUrls;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public JPanel getSpamPanel() {
		return spamPanel;
	}
	public void setSpamPanel(JPanel spamPanel) {
		this.spamPanel = spamPanel;
	}
	public JPanel getNotSpamPanel() {
		return notSpamPanel;
	}
	public void setNotSpamPanel(JPanel notSpamPanel) {
		this.notSpamPanel = notSpamPanel;
	}
	public JSplitPane getSplitPanel() {
		return splitPanel;
	}
	public void setSplitPanel(JSplitPane splitPanel) {
		this.splitPanel = splitPanel;
	}
	public JScrollPane getSpamScrollPane() {
		return spamScrollPane;
	}
	public void setSpamScrollPane(JScrollPane spamScrollPane) {
		this.spamScrollPane = spamScrollPane;
	}
	public JScrollPane getNotSpamScrollPane() {
		return notSpamScrollPane;
	}
	public void setNotSpamScrollPane(JScrollPane notSpamScrollPane) {
		this.notSpamScrollPane = notSpamScrollPane;
	}
	public TitledBorder getSpamTitle() {
		return spamTitle;
	}
	public void setSpamTitle(TitledBorder spamTitle) {
		this.spamTitle = spamTitle;
	}
	public TitledBorder getNotSpamTitle() {
		return notSpamTitle;
	}
	public void setNotSpamTitle(TitledBorder notSpamTitle) {
		this.notSpamTitle = notSpamTitle;
	}

	public JTextField getLabelOne() {
		return labelOne;
	}
	public void setLabelOne(JTextField labelOne) {
		this.labelOne = labelOne;
	}
	public JTextField getLabelTwo() {
		return labelTwo;
	}
	public void setLabelTwo(JTextField labelTwo) {
		this.labelTwo = labelTwo;
	}
	public JTextField getLabelThree() {
		return labelThree;
	}
	public void setLabelThree(JTextField labelThree) {
		this.labelThree = labelThree;
	}
	public JTextField getLabelFour() {
		return labelFour;
	}
	public void setLabelFour(JTextField labelFour) {
		this.labelFour = labelFour;
	}
	public JTextField getLabelFive() {
		return labelFive;
	}
	public void setLabelFive(JTextField labelFive) {
		this.labelFive = labelFive;
	}
	public JTextField getLabelSix() {
		return labelSix;
	}
	public void setLabelSix(JTextField labelSix) {
		this.labelSix = labelSix;
	}
	public JTextField getLabelSeven() {
		return labelSeven;
	}
	public void setLabelSeven(JTextField labelSeven) {
		this.labelSeven = labelSeven;
	}
	public JTextField getLabelEight() {
		return labelEight;
	}
	public void setLabelEight(JTextField labelEight) {
		this.labelEight = labelEight;
	}
	public JTextField getLabelNine() {
		return labelNine;
	}
	public void setLabelNine(JTextField labelNine) {
		this.labelNine = labelNine;
	}
	public JTextField getLabelTen() {
		return labelTen;
	}
	public void setLabelTen(JTextField labelTen) {
		this.labelTen = labelTen;
	}
	public String getMostUsedUrlText() {
		return mostUsedUrlText;
	}
	public void setMostUsedUrlText(String mostUsedUrlText) {
		this.mostUsedUrlText = mostUsedUrlText;
	}
	public ArrayList<JTextField> getLabelList() {
		return labelList;
	}
	public void setLabelList(ArrayList<JTextField> labelList) {
		this.labelList = labelList;
	}
}
