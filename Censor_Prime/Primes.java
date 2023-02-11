
public class Primes {
	
	private int[] primes = {2, 0, 0};
	private int size = 1;
	
	public Primes() {}

	public void printPrimes(int n) {
		
		int num = 1;
		while (num < n) {
			if (this.size == num) {
				this.expandArr();
			}
			
			int last = this.primes[num - 1] + 1;
			while (true) {
				
				boolean bool = true;
				for (int i = 2; i < last; i++) {
					if (last % i == 0) {
						bool = false;
						break;
					}
				}
				
				if (bool == true) {
					this.primes[num] = last;
					num++;
					break;
				}
				last++;
			}
		}
		
		System.out.println("Here are the first " + n + " prime numbers:");
		
		for (int i = 0; i < n; i++) {
			System.out.print(this.primes[i] + " ");
		}
		System.out.println();
	}
	
	private void expandArr() {
		
		int[] primesCopy = this.primes;
		
		this.size = 2 * this.size;
		this.primes = new int[this.size];
		
		for (int i = 0; i < this.size / 2; i++) {
			this.primes[i] = primesCopy[i];
		}
	}
	
}