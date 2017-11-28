import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JPanel implements ActionListener{
	private BufferedImage img;
	private JButton logIn;
	private JTextField user;
	private JPasswordField pass;
	private LoginController loginCon;
	private boolean check;
	private JFrame frame;

	public LoginView() throws IOException {
		loginCon = new LoginController();
		
		setOpaque(true);
		img = ImageIO.read(new File("dome.jpg"));
		
		setLayout(new GridBagLayout());
		JPanel loginPanel = new JPanel();
        loginPanel.setOpaque(false);
        loginPanel.setLayout(new GridLayout(2, 2, 4, 2));
        JLabel userLabel = new JLabel("USERNAME : ");
        userLabel.setForeground(Color.WHITE);
        user = new JTextField(10);
        JLabel passLabel = new JLabel("PASSWORD : ");
        passLabel.setForeground(Color.WHITE);
        pass = new JPasswordField(10);
        logIn = new JButton("Log in");
        logIn.setForeground(Color.BLACK);
        logIn.addActionListener(this);
        

        loginPanel.add(userLabel);
        loginPanel.add(user);
        loginPanel.add(passLabel);
        loginPanel.add(pass);
        
        JPanel mainpanel = new JPanel(); 
        mainpanel.setOpaque(false);
        mainpanel.add(loginPanel);
        logIn.setLocation(0,0);
        
        JPanel p = new JPanel();
        p.add(logIn);
        p.setOpaque(false);
        mainpanel.add(p);
        mainpanel.add(logIn);
        add(mainpanel);
        frame = new JFrame();
        frame.add(this, BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(700, 400));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.pack();
	}
	public void setCheck(boolean c){
		check = c;
	}
	public boolean getCheck(){
		return check;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String password = new String(pass.getPassword());
		if(loginCon.checkID(user.getText(), password)){
			SelectCourseView scv = new SelectCourseView();
			scv.setNameUser(loginCon.getUser(user.getText()).getName());
			scv.setUpComplete();
			setCheck(true);
			frame.dispose();
		}
		else{
			JOptionPane.showMessageDialog(null, "Invalid user or password, please try again");
			user.setText(null);
			pass.setText(null);
		}
	}
	public static void main(String[] args) throws IOException{
		new LoginView();
	}
}