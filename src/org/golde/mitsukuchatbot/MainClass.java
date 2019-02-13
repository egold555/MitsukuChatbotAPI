package org.golde.mitsukuchatbot;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		
		//http://www.square-bear.co.uk/mitsuku/nfchat.htm  -> Inspect element, Network, see the /talk request, variables under Query String Paraneters
		Mitsuku bot = new Mitsuku("cw168e54c4f53", 403034225, 6);
		
		boolean running = true;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		log("started.");
		while(running) {
			String in = scanner.nextLine();
			if(in.equalsIgnoreCase("exit")) {
				running = false;
				return;
			}
			MitsukuResponce mitsukuResponce = bot.getResponce(in);
			log("BOT > " + mitsukuResponce.getSanatizedResponce());
		}
		log("exited.");
		scanner.close();
		
	}
	
	private static void log(String msg) {
		System.out.println(msg);
	}

}
