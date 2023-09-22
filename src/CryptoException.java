import javax.swing.JOptionPane;

/*
*An implementation of AES(Advanced Encryption Standard) imported in JAVA libraries,
*the complete code of that implementation was taken from www.codejava.net. 
*This is the exception handling for this algorithm.
*/
@SuppressWarnings("serial")
public class CryptoException extends Exception {
 
    public CryptoException(String message) {
    	JOptionPane.showMessageDialog(null, message,"Error Message",JOptionPane.ERROR_MESSAGE);
    }
}