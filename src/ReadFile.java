import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.JOptionPane;


public class ReadFile{//read a file given by the user

	public static BlockingQueue<Character> queue = new ArrayBlockingQueue<Character>(150);// a synchronized thread based blocking queue
	
	public ReadFile(File SourceFile){
		try {
			String input;
			FileReader fr=new FileReader(SourceFile);//accessing file 
			BufferedReader br=new BufferedReader(fr);//opening it
				while((input=br.readLine())!=null)//taking inputs until file ends
				{
					for(int i=0;i<input.length();i++)
					{
						queue.put(input.charAt(i));//insert them in q
					}
				queue.put('\n');
				}
			br.close();
		} 
		catch (FileNotFoundException e) 
		{
			JOptionPane.showMessageDialog(null,"File not Found","Error Message",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			JOptionPane.showMessageDialog(null,"Input Output Error Occured","Error Message",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null,"Illegal memory access","Error Message",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	
}
