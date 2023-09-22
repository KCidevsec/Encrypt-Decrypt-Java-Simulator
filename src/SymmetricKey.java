import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*Symmetric Key encryption is a type of encryption that is commonly used, the user input an integer key
 * and the input is encrypted based on that key. The steps is doing it is a bit more complicated than Ceasar Cipher even 
 * if both algorithms use shift. Symmetric Key choose a digit from the password given and shift a letter or digit for the value 
 * taken from key. Since the key is shorter than your text when the encryption of the items same with the key lenght are done
 * the algorithm start from the begining of the key until there are is no item left. So the longer your key it is the harder is to
 * break this encryption. 
 */
public class SymmetricKey {

	public SymmetricKey(int key,String FileName) throws IOException, InterruptedException//encryption constructor
	{
		if(key<0)//making key a positive if is negative integer
			key=key*-1;
		
		String key_s=Integer.toString(key);//transforming key to string in order to access each digit 
		
		FileWriter fw = new FileWriter("src/Encrypted_Files/"+"SymmetricKey_"+FileName);//create file
		BufferedWriter bw=new BufferedWriter(fw);//open file
		Thread.sleep(1000);//providing time to other thread to input some items in queue
			if(ReadFile.queue.isEmpty()!=true)//while there are items in queue
			{
					for(int i=0;i<key_s.length();i++)//taking one digit of the key each time
					{
						char value=ReadFile.queue.take();//taking an item form queue
						char digit=key_s.charAt(i);//choosing one of the digits of key
						for(int j=0;j<(int)digit-'0';j++)//shifting letter base on that digit taken from key
						{
							if(Character.isLetter(value))
							{
								if(value=='z')
									value='a';
								else if(value=='Z')
									value='A';
								else
									value++;
							}
							else if(Character.isDigit(value))
							{
								if(value=='9')
									value='0';
								else
									value++;
							}
						}
					if(value=='\n')
						bw.newLine();
					else
						bw.write((char)value);
					
					if(ReadFile.queue.isEmpty()==true)
						i=key_s.length();
					else if(i==key_s.length()-1)
						i=0;
					}
			bw.close();
			}
	}
	
	public SymmetricKey(int key,String FileName,String decrypt) throws IOException, InterruptedException//decryption constructor
	{
		if(key<0)
			key=key*-1;
		
		String key_s=Integer.toString(key);
		
		FileWriter fw = new FileWriter("src/Decrypted_Files/"+"SymmetricKey_"+FileName);
		BufferedWriter bw=new BufferedWriter(fw);
		Thread.sleep(1000);
			if(ReadFile.queue.isEmpty()!=true)
			{
					for(int i=0;i<key_s.length();i++)
					{
						char value=ReadFile.queue.take();
						char digit=key_s.charAt(i);
						for(int j=0;j<(int)digit-'0';j++)
						{
							if(Character.isLetter(value))
							{
								if(value=='a')
									value='z';
								else if(value=='A')
									value='Z';
								else
									value--;
							}
							else if(Character.isDigit(value))
							{
								if(value=='0')
									value='9';
								else
									value--;
							}
						}
					if(value=='\n')
						bw.newLine();
					else
						bw.write((char)value);
					
					if(ReadFile.queue.isEmpty()==true)
						i=key_s.length();
					else if(i==key_s.length()-1)
						i=0;
					}
			bw.close();
			}
	}
}
