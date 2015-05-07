package colorant.example;

class volatileCheckThread extends Thread {
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
}


class LoopCountThread extends Thread {
	private volatile boolean stoped = false;
	private boolean stoped2 = false;
	private Long loopcount = 1000000000L;
	
	public void stopme() {
		stoped = true;
	}
	
	public void stopme2() {
		stoped2 = true;
	}
	
	public void countwithcheck() {
		Long i = 0L;
		Long startts = System.currentTimeMillis();
		while (i < loopcount) {
			i++;
			if (stoped)
				return;
		}
		Long stopts = System.currentTimeMillis();
		System.out.println("countwithcheck finished. time cost = " + (stopts - startts));
	
	}
	
	
	public void countwithcheck2() {
		Long i = 0L;
		Long startts = System.currentTimeMillis();
		while (i < loopcount) {
			i++;
			if (stoped2)
				return;
		}
		Long stopts = System.currentTimeMillis();
		System.out.println("countwithcheck2 finished. time cost = " + (stopts - startts));
	
	}
	
	public void run(){
		try {
			Thread.sleep(1000);
			countwithcheck();
			Thread.sleep(1000);
			countwithcheck2();
			Thread.sleep(1000);
			countwithcheck();
			Thread.sleep(1000);
			countwithcheck2();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


public class volatileExample {

	public static void main(String[] args) throws InterruptedException {

		volatileCheckThread a = new volatileCheckThread();
		
		Long startts = System.currentTimeMillis();
		a.start();
		Thread.sleep(1000);
		a.stopme();
		a.join();
		Long stopts = System.currentTimeMillis();
		System.out.println("volatileCheckThread exited. time cost = " + (stopts - startts));
		
		
		LoopCountThread b = new LoopCountThread();
		b.start();
		b.join();
	}

}
