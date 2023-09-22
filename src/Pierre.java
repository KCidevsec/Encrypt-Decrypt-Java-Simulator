import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*Pierre De fermet algorithm implementation steps:
 * 1. transform a letter to integer
 * 2. transform integer to letter
 */
public class Pierre {

	private char letterArray[]=new char[52];
	private char intArray[]={'A','B','C','D','E','F','G','K','L','J'};
	
	public Pierre(String FileName) throws IOException, InterruptedException//encryption constructor
	{
		FileWriter fw = new FileWriter("src/Encrypted_Files/"+"PierreDeFermet_"+FileName);//create file
		BufferedWriter bw=new BufferedWriter(fw);//open file
		SetletterArray();//initialize matching positions
		Thread.sleep(1000);//delay thread in order for my queue to have some items in it
				while(ReadFile.queue.isEmpty()!=true)//while i have items in my queue
				{
					char value=ReadFile.queue.take();//get an item
					
					if(Character.isLetter(value))//if is letter
					{
						for(int i=0;i<letterArray.length;i++)
						{
							if(letterArray[i]==value)//matching letter to an integer value
							{
								if(i>=10)
								{
									String temp=Integer.toString(i);
									bw.write((char)temp.charAt(0));
									bw.write((char)temp.charAt(1));
									bw.write((char)'-');
								}
								else
								{
									String temp=Integer.toString(i);
									bw.write((char)temp.charAt(0));
									bw.write((char)'-');
								}
							}
						}
					}
					else if(Character.isDigit(value))//if item is digit
					{
						value=intArray[Character.getNumericValue(value)];//matching integer to letter value
						bw.write((char)value);
					}
					else if(value=='\n')
						bw.newLine();
					else
						bw.write((char)value);
				}
			bw.close();//close file
	}
	
	public Pierre(String FileName,String Decrypt) throws IOException, InterruptedException//decryption constructor
	{
		FileWriter fw = new FileWriter("src/Decrypted_Files/"+"PierreDeFermet_"+FileName);
		BufferedWriter bw=new BufferedWriter(fw);
		SetletterArray();
		Thread.sleep(1000);
				while(ReadFile.queue.isEmpty()!=true)
				{
					char value=ReadFile.queue.take();
					
					if(Character.isLetter(value))
					{
						for(int i=0;i<intArray.length;i++)
						{
							if(intArray[i]==value)
							{
								String temp=Integer.toString(i);
								value=temp.charAt(0);
								bw.write((char)value);
							}
						}
					}
					else if(Character.isDigit(value))
					{
						char value1=ReadFile.queue.take();
						if(Character.isDigit(value1))
						{
							int letIndex=(Character.getNumericValue(value)*10)+(Character.getNumericValue(value1));
							value=letterArray[letIndex];
							bw.write((char)value);
							ReadFile.queue.take();
						}
						else if(value1=='-')
						{
							value=letterArray[Character.getNumericValue(value)];
							bw.write((char)value);
						}
					}
					else if(value=='\n')
						bw.newLine();
					else
						bw.write((char)value);
				}
			bw.close();
	}
	public void SetletterArray(){//method to initialize array of matching letters to integers
		char clet='A';
		char slet='a';
		
		for(int i=0;i<letterArray.length;i++)
		{
			if(i<26)
			{
				letterArray[i]=clet;
				clet++;
			}
			else if(i>=26)
			{
				letterArray[i]=slet;
				slet++;
			}
		}
	}
}
