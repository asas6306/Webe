
public class test {
	public static void main(String[] args) {
		int hp1 = (int) (Math.random() * 10000);
		int hp2 = (int) (Math.random() * 10000);
		String hp3 = String.valueOf(hp1);
		String hp4 = String.valueOf(hp2);

		while (hp3.length() < 4) {
			for (int i = 0; i < (4 - hp3.length()); i++) {
				hp3 = "0" + hp3;
			}
		}
		while (hp4.length() < 4) {
			for (int i = 0; i < (4 - hp4.length()); i++) {
				hp4 = "0" + hp4;
			}
		}
		
		System.out.println("010-" + hp3 + "-" + hp4);
	}
}
