import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*Ceasar Cipher is an encryption algorithm that basically shift all letters for a given value
 * does not change digit or special characters
 * 
 */
public class CeasarCipher {

	public CeasarCipher(int shift_value,String FileName) throws IOException, InterruptedException//encryption constructor
	{
		FileWriter fw = new FileWriter("src/Encrypted_Files/"+"CeasarCipher_"+FileName);//create file
		BufferedWriter bw=new BufferedWriter(fw);//open file
		Thread.sleep(1000);//provide time for my other thread to put some items in queue
				while(ReadFile.queue.isEmpty()!=true)//while there are items in queue
				{
					char value=ReadFile.queue.take();//take a value
					for(int i=0;i<shift_value;i++)//shifting value
					{
						if(Character.isLetter(value))//if is letter
						{
							if(value=='z')
								value='a';
							else if(value=='Z')
								value='A';
							else
								value++;
						}
					}
					if(value=='\n')
						bw.newLine();
					else
						bw.write((char)value);
				}
			bw.close();
	}
	
	public CeasarCipher(int shift_value,String FileName,String decrypt) throws IOException, InterruptedException//decryption constructor
	{
		FileWriter fw = new FileWriter("src/Decrypted_Files/"+"CeasarCipher_"+FileName);
		BufferedWriter bw=new BufferedWriter(fw);
		Thread.sleep(1000);
				while(ReadFile.queue.isEmpty()!=true)
				{
					char value=ReadFile.queue.take();
					for(int i=0;i<shift_value;i++)
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
					}
					if(value=='\n')
						bw.newLine();
					else
						bw.write((char)value);
				}
			bw.close();
			} 
}
