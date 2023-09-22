import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Component;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;


@SuppressWarnings("serial")
public class MainScreen extends JFrame {

	@SuppressWarnings("unused")
	private JPanel contentPane;
	private JTextField txtPath;
	public File SourceFile;
	String fileName;
	public boolean filesubmit=false; 
	public static final Integer[] ceasarkey={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
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
	public MainScreen() {
		getContentPane().setForeground(new Color(154, 205, 50));
		setForeground(new Color(154, 205, 50));
		getContentPane().setBackground(new Color(0, 191, 255));
		
		JRadioButton rdbtn1 = new JRadioButton("Symmetric Ceasar Cipher");
		rdbtn1.setSelected(true);
		rdbtn1.setBackground(new Color(0, 191, 255));
		rdbtn1.setForeground(new Color(255, 255, 255));
		rdbtn1.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtn1.setActionCommand("Ceasar");
		
		JRadioButton rdbtn2 = new JRadioButton("Symmetric Integer Key");
		rdbtn2.setBackground(new Color(0, 191, 255));
		rdbtn2.setForeground(new Color(255, 255, 255));
		rdbtn2.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtn2.setActionCommand("SymmetricKey");
		
		JLabel lblEncryptionAlgorithms = new JLabel("Choose Algorithm");
		lblEncryptionAlgorithms.setHorizontalAlignment(SwingConstants.CENTER);
		lblEncryptionAlgorithms.setForeground(new Color(178, 34, 34));
		lblEncryptionAlgorithms.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEncryptionAlgorithms.setBackground(new Color(0, 191, 255));
		
		JRadioButton rdbtn3 = new JRadioButton("Pierre De Fermat Puzzle");
		rdbtn3.setForeground(new Color(255, 255, 255));
		rdbtn3.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtn3.setBackground(new Color(0, 191, 255));
		rdbtn3.setActionCommand("Pierre");
		
		JRadioButton rdbtn4 = new JRadioButton("AES");
		rdbtn4.setForeground(new Color(255, 255, 255));
		rdbtn4.setBackground(new Color(0, 191, 255));
		rdbtn4.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtn4.setActionCommand("AES");
		
		final ButtonGroup BGRatio = new ButtonGroup();
		BGRatio.add(rdbtn1);
		BGRatio.add(rdbtn2);
		BGRatio.add(rdbtn3);
		BGRatio.add(rdbtn4);
		
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setBackground(Color.LIGHT_GRAY);
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(filesubmit==true)
				{
				String radiovalue=BGRatio.getSelection().getActionCommand();
					if(radiovalue=="Ceasar")
					{
						int key_temp = 0;
						boolean flag=true;
						try{
						key_temp = (int)JOptionPane.showInputDialog(null,"Choose a key","Integer Key Required for Ceasar Cipher Algorithm", 
						        JOptionPane.QUESTION_MESSAGE,null,ceasarkey,ceasarkey[0]);
						}
						catch(NullPointerException ex)
						{
							JOptionPane.showMessageDialog(null,"You did not specify integer key","Error Message",JOptionPane.ERROR_MESSAGE);
							flag=false;
						}
						if(flag)
						{
							final int key=key_temp;
						 Thread read = new Thread(new Runnable() {
					            public void run() 
					            {
					                new ReadFile(SourceFile);
					            } 
					        });
						 
						 Thread ceasar =new Thread(new Runnable(){
							 public void run() 
					            {
					                try {
										new CeasarCipher(key,SourceFile.getName());
									} catch (IOException | InterruptedException e) {
											e.printStackTrace();
										JOptionPane.showMessageDialog(null, "Unexpected Thread Interupt","Error Message",JOptionPane.ERROR_MESSAGE);
									}
					            } 
					        });
						 
						 read.start();
						 ceasar.start();
						 
						 try {
							read.join();
							ceasar.join();
						} catch (InterruptedException e1) {
							JOptionPane.showMessageDialog(null, "Unexpected Thread Interupt","Error Message",JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
					JOptionPane.showMessageDialog(null, "Encrypted File Created in src/Encrypted_Files","Notification",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else if(radiovalue=="SymmetricKey")
					{
						boolean flag=true;
						while(flag)
						{
							try
							{
								final int skey=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter an Integer Key to be used for Symmetric Encryption",
								"Integer Key Required",JOptionPane.QUESTION_MESSAGE));
								flag=false;
								
								Thread read = new Thread(new Runnable() {
						            public void run() 
						            {
						                new ReadFile(SourceFile);
						            } 
						        });
								
								Thread symmetrickey =new Thread(new Runnable(){
									 public void run() 
							            {
							                try {
												new SymmetricKey(skey,SourceFile.getName());
											} catch (IOException | InterruptedException e) {
													e.printStackTrace();
												JOptionPane.showMessageDialog(null, "Unexpected Thread Interupt","Error Message",JOptionPane.ERROR_MESSAGE);
											}
							            } 
							        });
								 
								 read.start();
								 symmetrickey.start();
								 
								 try {
									read.join();
									symmetrickey.join();
								} catch (InterruptedException e1) {
									JOptionPane.showMessageDialog(null, "Unexpected Thread Interupt","Error Message",JOptionPane.ERROR_MESSAGE);
									e1.printStackTrace();
								}
								 JOptionPane.showMessageDialog(null, "Encrypted File Created in src/Encrypted_Files","Notification",JOptionPane.INFORMATION_MESSAGE);
							}
							catch(NumberFormatException ex)
							{
								JOptionPane.showMessageDialog(null,"Invalid Input,Try Again"," Integer Key Required",JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					else if(radiovalue=="Pierre")
					{
						Thread read = new Thread(new Runnable() {
				            public void run() 
				            {
				                new ReadFile(SourceFile);
				            } 
				        });
					 
					 Thread pierre =new Thread(new Runnable(){
						 public void run() 
				            {
				                try {
									new Pierre(SourceFile.getName());
								} catch (IOException | InterruptedException e) {
										e.printStackTrace();
									JOptionPane.showMessageDialog(null, "Unexpected Thread Interupt","Error Message",JOptionPane.ERROR_MESSAGE);
								}
				            } 
				        });
					 
					 read.start();
					 pierre.start();
					 
					 try 
					 {
						read.join();
						pierre.join();
					 } 
					 catch (InterruptedException e1) 
					 {
						JOptionPane.showMessageDialog(null, "Unexpected Thread Interupt","Error Message",JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					 }
					 JOptionPane.showMessageDialog(null, "Encrypted File Created in src/Encrypted_Files","Notification",JOptionPane.INFORMATION_MESSAGE);
					}
					else if(radiovalue=="AES")
					{
						boolean flag=true;
						String originalKey=new String();
						while(flag)
						{
						try{
						originalKey=(String)JOptionPane.showInputDialog(null,"Enter a sentence to be used as Encryption key."
								+ "\nIMPORTANT NOTE: This spesific implementation of AES requires 16 "
								+ "\nbit lenght key so your input will be transformed", "Advanced "
								+ "Encryption Standard",JOptionPane.QUESTION_MESSAGE);
						if(originalKey.length()>0)
							flag=false;
						if(originalKey.length()>16)
						{
							String aesKey=new String();
							for(int i=0;i<16;i++)
							{
							aesKey=aesKey+originalKey.charAt(i);
							}
							originalKey= aesKey;
						}
						else if(originalKey.length()<16)
						{
							String aesKey=originalKey;
							char let='a';
							for(int i=originalKey.length();i<16;i++)
							{
							aesKey=aesKey+let++;
							}
							originalKey= aesKey;
						}
						
						}
						catch(NullPointerException ex)
						{
							JOptionPane.showMessageDialog(null, "You did not input a key","Error Message",JOptionPane.ERROR_MESSAGE);
						}
						}
						
						final String aeskey=originalKey;
						
						Thread aes = new Thread(new Runnable() {
				            public void run() 
				            {
				            	File output=new File("src/Encrypted_Files/"+"AES_"+SourceFile.getName());
				                CryptoUtils cryptoencry=new CryptoUtils();				                
				                try {
									cryptoencry.encrypt(aeskey, SourceFile,output);
								} catch (CryptoException e) {
									e.printStackTrace();
									JOptionPane.showMessageDialog(null, "Unexpected Thread Interupt","Error Message",JOptionPane.ERROR_MESSAGE);
								}
				            } 
				        });
						
						aes.start();
						try 
						 {
							aes.join();
						 } 
						 catch (InterruptedException e1) 
						 {
							JOptionPane.showMessageDialog(null, "Unexpected Thread Interupt","Error Message",JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						 }
						JOptionPane.showMessageDialog(null, "Encrypted File Created in src/Encrypted_Files","Notification",JOptionPane.INFORMATION_MESSAGE);						
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You did not spesify file path","Error Message",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEncrypt.setForeground(new Color(178, 34, 34));
		btnEncrypt.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setBackground(Color.LIGHT_GRAY);
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filesubmit==true)
				{
				String radiovalue=BGRatio.getSelection().getActionCommand();
					if(radiovalue=="Ceasar")
					{
						int key_temp = 0;
						boolean flag=true;
						try{
						key_temp = (int)JOptionPane.showInputDialog(null,"Choose a key","Integer Key Required for Ceasar Cipher Algorithm", 
						        JOptionPane.QUESTION_MESSAGE,null,ceasarkey,ceasarkey[0]);
						}
						catch(NullPointerException ex)
						{
							JOptionPane.showMessageDialog(null,"You did not specify integer key","Error Message",JOptionPane.ERROR_MESSAGE);
							flag=false;
						}
						if(flag)
						{
						final int key=key_temp;
						 Thread read = new Thread(new Runnable() {
					            public void run() 
					            {
					                new ReadFile(SourceFile);
					            } 
					        });
						 
						 Thread ceasar =new Thread(new Runnable(){
							 public void run() 
					            {
					                try {
										new CeasarCipher(key,SourceFile.getName(),"decrypt");
									} catch (IOException | InterruptedException e) {
											e.printStackTrace();
										JOptionPane.showMessageDialog(null, "Unexpected Thread Interupt","Error Message",JOptionPane.ERROR_MESSAGE);
									}
					            } 
					        });
						 
						 read.start();
						 ceasar.start();
						 
						 try {
							read.join();
							ceasar.join();
						 } 
						 catch (InterruptedException e1) 
						 {
							JOptionPane.showMessageDialog(null, "Unexpected Thread Interupt","Error Message",JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						 }	
					JOptionPane.showMessageDialog(null, "Decrypted File Created in src/Decrypted_Files","Notification",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else if(radiovalue=="SymmetricKey")
					{
						boolean flag=true;
						while(flag)
						{
							try
							{
								final int skey=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter an Integer Key to be used for Symmetric Encryption",
								"Integer Key Required",JOptionPane.QUESTION_MESSAGE));
								flag=false;
								
								Thread read = new Thread(new Runnable() {
						            public void run() 
						            {
						                new ReadFile(SourceFile);
						            } 
						        });
								
								Thread symmetrickey =new Thread(new Runnable(){
									 public void run() 
							            {
							                try {
												new SymmetricKey(skey,SourceFile.getName(),"decrypt");
											} catch (IOException | InterruptedException e) {
													e.printStackTrace();
												JOptionPane.showMessageDialog(null, "Unexpected Thread Interupt","Error Message",JOptionPane.ERROR_MESSAGE);
											}
							            } 
							        });
								 
								 read.start();
								 symmetrickey.start();
								 
								 try {
									read.join();
									symmetrickey.join();
								} catch (InterruptedException e1) {
									JOptionPane.showMessageDialog(null, "Unexpected Thread Interupt","Error Message",JOptionPane.ERROR_MESSAGE);
									e1.printStackTrace();
								}
								 JOptionPane.showMessageDialog(null, "Decrypted File Created in src/Decrypted_Files","Notification",JOptionPane.INFORMATION_MESSAGE);
							}
							catch(NumberFormatException ex)
							{
								JOptionPane.showMessageDialog(null,"Invalid Input,Try Again"," Integer Key Required",JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					else if(radiovalue=="Pierre")
					{
						Thread read = new Thread(new Runnable() {
				            public void run() 
				            {
				                new ReadFile(SourceFile);
				            } 
				        });
					 
					 Thread pierre =new Thread(new Runnable(){
						 public void run() 
				            {
				                try {
									new Pierre(SourceFile.getName(),"decrypt");
								} catch (IOException | InterruptedException e) {
										e.printStackTrace();
									JOptionPane.showMessageDialog(null, "Unexpected Thread Interupt","Error Message",JOptionPane.ERROR_MESSAGE);
								}
				            } 
				        });
					 
					 read.start();
					 pierre.start();
					 
					 try 
					 {
						read.join();
						pierre.join();
					 } 
					 catch (InterruptedException e1) 
					 {
						JOptionPane.showMessageDialog(null, "Unexpected Thread Interupt","Error Message",JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					 }
					 JOptionPane.showMessageDialog(null, "Decrypted File Created in src/Decrypted_Files","Notification",JOptionPane.INFORMATION_MESSAGE);
					}
					else if(radiovalue=="AES")
					{

						boolean flag=true;
						String originalKey=new String();
						while(flag)
						{
						try{
						originalKey=(String)JOptionPane.showInputDialog(null,"Enter a sentence to be used as Decryption key."
								+ "\nIMPORTANT NOTE: This spesific implementation of AES requires 16 "
								+ "\nbit lenght key so your input will be transformed", "Advanced "
								+ "Encryption Standard",JOptionPane.QUESTION_MESSAGE);
						if(originalKey.length()>0)
							flag=false;
						if(originalKey.length()>16)
						{
							String aesKey=new String();
							for(int i=0;i<16;i++)
							{
							aesKey=aesKey+originalKey.charAt(i);
							}
							originalKey= aesKey;
						}
						else if(originalKey.length()<16)
						{
							String aesKey=originalKey;
							char let='a';
							for(int i=originalKey.length();i<16;i++)
							{
							aesKey=aesKey+let++;
							}
							originalKey= aesKey;
						}
						
						}
						catch(NullPointerException ex)
						{
							JOptionPane.showMessageDialog(null, "You did not input a key","Error Message",JOptionPane.ERROR_MESSAGE);
						}
						}
						final String aeskey=originalKey;
						
						boolean bit=true;
				            	File output=new File("src/Decrypted_Files/"+"AES_"+SourceFile.getName());		                
									try {
										CryptoUtils.decrypt(aeskey, SourceFile,output);
									} catch (CryptoException e1) {
										bit=false;
									}
							
				        if(bit==true)
						JOptionPane.showMessageDialog(null, "Decrypted File Created in src/Decrypted_Files","Notification",JOptionPane.INFORMATION_MESSAGE);	
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You did not spesify file path","Error Message",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDecrypt.setForeground(new Color(178, 34, 34));
		btnDecrypt.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtPath = new JTextField();
		txtPath.setForeground(new Color(255, 255, 255));
		txtPath.setBackground(new Color(192, 192, 192));
		txtPath.setEditable(false);
		txtPath.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtPath.setColumns(10);
		txtPath.setText("Click on Browser to select a file");
		
		FileNameExtensionFilter FileFilter = new FileNameExtensionFilter("DOCX & TXT DOCUMENTS", "docx", "txt");
		final JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Choose a file:");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(FileFilter);
		
		JButton btnBrowser = new JButton("Browser");
		btnBrowser.setBackground(Color.LIGHT_GRAY);
		btnBrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int pickVal = fc.showOpenDialog((Component)e.getSource());
			    if (pickVal == JFileChooser.APPROVE_OPTION) 
			    {
			        SourceFile = fc.getSelectedFile();
			        filesubmit=true;
			        try {
			           fileName = SourceFile.toString();
			           txtPath.setText(fileName);
			        	} 
			        catch (Exception ex) {
			        	JOptionPane.showMessageDialog(null, "problem accessing file"+SourceFile.getAbsolutePath(),"ERROR",JOptionPane.ERROR_MESSAGE);
			        }
			    } 
			    else 
			    {
			    	JOptionPane.showMessageDialog(null, "File Pick Cancelled","Event Handling",JOptionPane.INFORMATION_MESSAGE);
			    }       				
			}
		});
		btnBrowser.setForeground(new Color(178, 34, 34));
		btnBrowser.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnHelp1 = new JButton("?");
		btnHelp1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHelp1.setForeground(new Color(0, 0, 255));
		btnHelp1.setBackground(new Color(255, 255, 0));
		btnHelp1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Each letter in the plaintext is replaced by a \nletter some fixed number "
						+ "of positions \ndown the alphabet. For example, \nwith a left shift of 3, "
						+ "D would \nbe replaced by A, E would become B, \nand so "
						+ "on","Symmetric Ceasar Cipher",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JButton btnHelp2 = new JButton("?");
		btnHelp2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHelp2.setForeground(new Color(0, 0, 255));
		btnHelp2.setBackground(new Color(255, 255, 0));
		btnHelp2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Symmetric-key algorithms are algorithms for \ncryptography "
						+ "that use the same \ncryptographic keys for both encryption \nof plaintext and decryption "
						+ "of ciphertext. The keys \nmay be identical or there may be a \nsimple transformation to go "
						+ "between \nthe two keys. " ,"Symmetric Key",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JButton btnHelp3 = new JButton("?");
		btnHelp3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHelp3.setForeground(new Color(0, 0, 255));
		btnHelp3.setBackground(new Color(255, 255, 0));
		btnHelp3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Each letter of the plaintext corresponds to a "
						+ "\npredetermine number(or two) and each \ndigit to a "
						+ "predetermine \nletter" ,"Pierre De Format Puzzle",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JButton btnHelp4 = new JButton("?");
		btnHelp4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHelp4.setForeground(new Color(0, 0, 255));
		btnHelp4.setBackground(new Color(255, 255, 0));
		btnHelp4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Is a specification for the encryption of \nelectronic data "
						+ "established by \nthe U.S. National Institute of Standards "
						+ "and \nTechnology (NIST) in 2001. There "
						+ "are \nseveral versions of AES, program simulates an \neasy version "
						+ "of it that uses a string \n16bit key" ,"Advanced Encryption Standard (AES)",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JLabel lblHelp = new JLabel("Help");
		lblHelp.setBackground(new Color(0, 191, 255));
		lblHelp.setForeground(new Color(178, 34, 34));
		lblHelp.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnEncryptedFiles = new JButton("Encrypted Files");
		btnEncryptedFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file = new File("src/Encrypted_Files");
			    Desktop desktop = Desktop.getDesktop();
				 try 
				 {
	                    desktop.open(file);
	             } 
				 catch (IOException e) 
				 {
	                	JOptionPane.showMessageDialog(null, "Access Denied in the folder","Error Message",JOptionPane.ERROR_MESSAGE);
	                    e.printStackTrace();
	             }
			}
		});
		btnEncryptedFiles.setBackground(new Color(154, 205, 50));
		btnEncryptedFiles.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEncryptedFiles.setForeground(new Color(128, 0, 0));
		
		JButton btnDecryptedFiles = new JButton("Decrypted Files");
		btnDecryptedFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File("src/Decrypted_Files");
			    Desktop desktop = Desktop.getDesktop();
				 try 
				 {
	                    desktop.open(file);
	             } 
				 catch (IOException ex) 
				 {
	                	JOptionPane.showMessageDialog(null, "Access Denied in the folder","Error Message",JOptionPane.ERROR_MESSAGE);
	                    ex.printStackTrace();
	             }
			}
		});
		btnDecryptedFiles.setBackground(new Color(154, 205, 50));
		btnDecryptedFiles.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDecryptedFiles.setForeground(new Color(128, 0, 0));
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setForeground(new Color(248, 248, 255));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnDecrypt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnEncrypt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
							.addGap(57)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEncryptionAlgorithms, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblHelp, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(31)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(rdbtn4)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(rdbtn3)
												.addPreferredGap(ComponentPlacement.RELATED))
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(rdbtn2)
													.addPreferredGap(ComponentPlacement.RELATED))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(rdbtn1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addGap(18)))))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnHelp4)
										.addComponent(btnHelp2)
										.addComponent(btnHelp1)
										.addComponent(btnHelp3))
									.addGap(66)))
							.addGap(115))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(btnBrowser)
							.addGap(18)
							.addComponent(txtPath, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(94)
							.addComponent(btnEncryptedFiles)
							.addGap(29)
							.addComponent(btnDecryptedFiles)
							.addGap(31)
							.addComponent(btnExit)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEncryptedFiles)
						.addComponent(btnDecryptedFiles)
						.addComponent(btnExit))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnEncrypt, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnDecrypt, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(23)
									.addComponent(btnHelp1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnHelp2)
										.addComponent(rdbtn2))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnHelp3)
										.addComponent(rdbtn3)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblEncryptionAlgorithms, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblHelp))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtn1)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(btnHelp4))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtn4)))))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBrowser, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPath, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		
		
		getContentPane().setLayout(groupLayout);
		setTitle("Encryption - Decryption Simulation Tool");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 335);
	
	}
}
