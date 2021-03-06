"""
  Provide front interface enabling selection between the above 
features or an option to exit the programme, and enabling required user input.
It does not matter if this is command-line or graphical,
as long as functionality/error checking is provided. 
You are required to provide error checking and show appropriate messages in the case of erroneous inputs
– eg bus stop doesn’t exist, wrong format for time for bus stop (eg letters instead of numbers), no route possible etc.

"""

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUIFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIFrame frame = new GUIFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public GUIFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 240, 650, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Shortest Path Between 2 Bus Stops");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
				try {
					ShortestPath.printShortestPath();
				} 
				catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
					
					
			}
		});
		btnNewButton.setBounds(24, 85, 278, 47);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.setForeground(new Color(255, 0, 0));
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		
				
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(396, 180, 117, 56);
		contentPane.add(btnNewButton_1);
		
		JLabel lblPleaseChooseThe = new JLabel("Please choose the program you wish to run (or press exit):");
		lblPleaseChooseThe.setBounds(40, 19, 378, 16);
		contentPane.add(lblPleaseChooseThe);
		
		JButton btnNewButton_2 = new JButton("All Trips With A Given Arrival Time");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sorting.sortArrivalTime();
			}
		});
		btnNewButton_2.setBounds(24, 176, 312, 64);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Stop Information Matching Given Search Criteria");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = Integer.parseInt(JOptionPane.showInputDialog(null, "Do you wish to search by first word of stop name or full stop name? \n Enter 0 for first word "
						+ "or 1 for full stop name"));

				if(choice == 0)
				{
					TernarySearch.fewCharSearch();
				}
				else if (choice == 1)
					TernarySearch.stopsTST();

				else {
					JOptionPane.showMessageDialog(null, "Invalid option");

				}
			}
		});
		btnNewButton_3.setBounds(320, 85, 313, 47);
		contentPane.add(btnNewButton_3);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
			
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
