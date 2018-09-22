import java.io.UnsupportedEncodingException;

public class TestEncoding {

	public static void main(String[] args) {
		// byte ahmet [] = {112, -60, -79, 110, 97, 114, 97, 58, 112, -60, -79, 110, 97, 114, 97};
		
		byte ahmet [] = {60, 117, 115, 101, 114, 73, 110, 102, 111, 62, 13, 10, 32, 32, 60, 105, 100, 62, 50, 60, 47, 105, 100, 62, 13, 10, 32, 32, 60, 117, 115, 101, 114, 110, 97, 109, 101, 62, 112, -60, -79, 110, 97, 114, 97, 60, 47, 117, 115, 101, 114, 110, 97, 109, 101, 62, 13, 10, 32, 32, 60, 112, 97, 115, 115, 119, 111, 114, 100, 62, 109, 105, 99, 55, 48, 70, 81, 78, 74, 79, 71, 99, 85, 84, 121, 81, 103, 47, 105, 99, 66, 97, 84, 53, 47, 120, 52, 61, 60, 47, 112, 97, 115, 115, 119, 111, 114, 100, 62, 13, 10, 32, 32, 60, 114, 111, 108, 101, 105, 110, 102, 111, 62, 65, 68, 77, 73, 78, 60, 47, 114, 111, 108, 101, 105, 110, 102, 111, 62, 13, 10, 32, 32, 60, 115, 116, 97, 116, 117, 105, 110, 102, 111, 62, 65, 67, 84, 73, 86, 69, 60, 47, 115, 116, 97, 116, 117, 105, 110, 102, 111, 62, 13, 10, 60, 47, 117, 115, 101, 114, 73, 110, 102, 111, 62};
		                
		String userpass = null;
		userpass = new String(ahmet);
		System.out.println(userpass);
		try {
			// userpass = new String(ahmet, "latin5");
			userpass = new String(ahmet, "utf8");
			System.out.println(userpass);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
