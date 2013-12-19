import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class GUI{
	public JTextArea oriArea, toArea;
	JButton convert, clear;
	public GUI()
	{
		JFrame frame = new JFrame("SimpleConverter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//FlowLayout flowLayout = new FlowLayout();
		
		  
		frame.setLayout(null);
		oriArea = new JTextArea("", 5, 10);
		//oriArea.setPreferredSize(new Dimension(400, 400));
		oriArea.setBounds(20, 20, 800, 300);
		JScrollPane scrollPane1 = new JScrollPane(oriArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane1.setBounds(20, 20, 800, 300);
		toArea = new JTextArea("", 5, 10);
		//toArea.setPreferredSize(new Dimension(400, 400));
		toArea.setBounds(20, 330, 800, 300);
		JScrollPane scrollPane2 = new JScrollPane(toArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane2.setBounds(20, 330, 800, 300);
		convert = new JButton("Convert");
		convert.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) {
						toArea.setText(	Main.objcMethodToCMethod(oriArea.getText()));
					}
				});
		convert.setBounds(20, 640, 100, 20);
		clear = new JButton("Clear");
		clear.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) {
						toArea.setText("");
						oriArea.setText("");
					}
				});
		clear.setBounds(140, 640, 100, 20);
		frame.add(scrollPane1);
		frame.add(scrollPane2);
		frame.add(convert);
		frame.add(clear);
		frame.setBounds(0, 0, 840, 700);
		
		
		Font f = oriArea.getFont();
		Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize()-3);
		oriArea.setFont(f2);
		toArea.setFont(f2);
		
		
		frame.setVisible(true);
	}
	
}
