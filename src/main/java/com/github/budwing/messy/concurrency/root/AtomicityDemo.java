package com.github.budwing.messy.concurrency.root;

public class AtomicityDemo {
	int i = 0;
	public void increase() {
        i++;
        i++;
    }

	public int getValue() {
        return i;
    }

	public static void main(String[] args) {
		final AtomicityDemo demo = new AtomicityDemo();
		new Thread() {
		    public void run() {
                while (true) {
                    int val = demo.getValue();
                    if (val%2 != 0) {
                        System.out.println();
                        System.out.println("Program exit at " + val);
                        System.exit(0);
                    }
			    }
		    }
		}.start();

		while (true) {
            demo.increase();
        }
	}
}
