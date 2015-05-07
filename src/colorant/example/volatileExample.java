package colorant.example;

public class volatileExample extends Thread {

	//private boolean stoped = false;
	private volatile boolean stoped = false;
	
	public void stopme() {
		stoped = true;
	}
	
	public void run(){
		long i = 0L;
		while (!stoped) {
			i++;
			if (i == Long.MAX_VALUE) {
				System.out.println("one big loop\n");
				i = 0;
			}
		}
		System.out.println("loop count = " + i);
	}
	
	
	public static void main(String[] args) throws InterruptedException {

		volatileExample a = new volatileExample();
		
		Long startts = System.currentTimeMillis();
		a.start();
		Thread.sleep(1000);
		a.stopme();
		a.join();
		Long stopts = System.currentTimeMillis();
		System.out.println("exited. time cost = " + (stopts - startts));

	}

}
