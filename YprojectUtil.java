package com.icia.ex.yut;

import java.util.Scanner;
	//
	public class YprojectUtil {
		public int numberCheck() {
			Scanner sc = new Scanner(System.in);
			if (sc.hasNextInt()) {
				return sc.nextInt();
			} else {
				sc.nextLine();
				return -1;
			}
			
		}
	}