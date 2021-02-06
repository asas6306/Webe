
public class test {
	public static void main(String[] args) {
		int lengthCnt = (int) (Math.random() * 3) + 8;

		String email = "";

		while (email.length() <= lengthCnt) {
			int asciiCode = (int) (Math.random() * 122);
			
			if((asciiCode>=48&&asciiCode<=57)||(asciiCode>=65&&asciiCode<=90)||(asciiCode>=97&&asciiCode<=122)) {
				email = email + (char)asciiCode;
			}
		}
		
		System.out.println(email);
	}
}
