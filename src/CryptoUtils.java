import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
 
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
 
/**
 *An implementation of AES(Advanced Encryption Standard) imported in JAVA libraries,
 *the complete code of that implementation was taken from www.codejava.net
 */
public class CryptoUtils {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
 
    public void  encrypt(String key, File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }
 
    public static void  decrypt(String key, File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }
 
    private static void  doCrypto(int cipherMode, String key, File inputFile,
            File outputFile) throws CryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);
             
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
             
            byte[] outputBytes = cipher.doFinal(inputBytes);
             
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);
             
            inputStream.close();
            outputStream.close();
             
        } 
        catch (NoSuchPaddingException ex)
        {
        	throw new CryptoException("Invalid Key");
        }
        catch(NoSuchAlgorithmException ex)
        {
        	throw new CryptoException("Algorithm Does not exist");
        }
        catch(InvalidKeyException ex)
        {
        	throw new CryptoException("Invalid Key");
        }
        catch(BadPaddingException ex)
        {
        	throw new CryptoException("Invalid Key");
        }
        catch(IllegalBlockSizeException ex)
        {
        	throw new CryptoException("File was not encrypted using AES 16 bit");
        }
        catch(IOException ex) 
        {
        	throw new CryptoException("Input / Output Error");
        }
            
        }
    }