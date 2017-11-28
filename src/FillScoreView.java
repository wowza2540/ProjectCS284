
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class FillScoreView extends JFrame implements ListSelectionListener, ActionListener{
	private JPanel mainPanel;
	private JLabel course;
	private JButton submit;
	private JButton save;
	private JButton cancel;
	private JTable table;
	private DefaultTableModel model;
	private FillScoreController fillcontroller;
	private String[] gradeGrilienia;
	
	public FillScoreView(String courseName) {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel fillPanel = new JPanel();
		fillPanel.setLayout(new BorderLayout());
		course = new JLabel(courseName);
		course.setFont(new Font("Impact", Font.BOLD, 26));
		fillPanel.add(course);
		mainPanel.add(fillPanel, BorderLayout.NORTH);
		
		fillcontroller = new FillScoreController(course.getText() + "Score.txt", courseName);
		gradeGrilienia = new String[fillcontroller.getGrilienia().length];
		gradeGrilienia(fillcontroller.getGrilienia());
	
		
		//Table
		model = new DefaultTableModel();
		model.addColumn("ID"); model.addColumn("Firstname"); model.addColumn("Lastname");
		addColumn();
		
		table = new JTable(model);
		table.setRowSelectionAllowed(false);	
		table.getSelectionModel().addListSelectionListener(this);
		mainPanel.add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		mainPanel.add(scrollPane);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		
		//Button
		JPanel buttonPanel = new JPanel();
		JPanel bPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		submit = new JButton("Submit");
		submit.addActionListener(this);
		save = new JButton("Save");
		save.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		bPanel.add(submit); 
		bPanel.add(save); 
		bPanel.add(cancel); 
		buttonPanel.add(bPanel, BorderLayout.EAST);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		addData();
		
		add(mainPanel);
		pack();
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
		
	}
	public void addData(){
		if(fillcontroller.getStatus().equals("true")){
			table.setEnabled(false);
			submit.setEnabled(false);
			save.setEnabled(false);
		}
		for(int i=0; i<fillcontroller.getRow(); i++){
			model.addRow(fillcontroller.getData()[i]);
		}
	}
	public void gradeGrilienia(String[] g){
		String[] str;
		for(int i=0; i<g.length; i++){
			str = g[i].split(",");
			gradeGrilienia[i] = str[0] + "(" + str[1];
		}
	}
	public void addColumn(){
		for(int i=0; i<gradeGrilienia.length; i++){
			model.addColumn(gradeGrilienia[i]);	
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(fillcontroller.getStatus().equals("false")){
			if(table.getSelectedColumn() > 2){
				String score = JOptionPane.showInputDialog(null, "Input Score");
				if(fillcontroller.addScore(score, table.getSelectedRow(),  table.getSelectedColumn()))
					table.getModel().setValueAt(score, table.getSelectedRow(), table.getSelectedColumn());
				else
					JOptionPane.showMessageDialog(null, "Invalid value");		
			}
		}
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Save")){
			fillcontroller.setCheck(true);
			fillcontroller.saveScoreFile(course.getText() + "Score.txt");
			dispose();
		}
		else if(e.getActionCommand().equals("Submit")){
			JOptionPane.showConfirmDialog(null, "If you submit grade \n you'll no longer change score");
			fillcontroller.setStatus("true");
			fillcontroller.submitScoreFile(course.getText() + "Score.txt");
			dispose();
		}
		else{
			dispose();
		}
	}
}