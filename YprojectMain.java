package com.icia.ex.yut;

import java.util.Scanner;

//
	public class YprojectMain {
		public static void main(String[] args) {
			YprojectService service = new YprojectService();
			Scanner sc = new Scanner(System.in);
			YprojectUtil util = new YprojectUtil();
			//
			boolean play = false;// 참가자 순서를 바꿔 줄 변수
			int gamecntA = 0;// A말의 점수를 저장 할 변수
			int gamecntB = 0;// B말의 점수를 저장 할 변수
			//
			service.clearScreen(5);
			System.out.println("\u001B[36m ■■■■■■■■■■■■■■■■■■■■■■■■■ YUT RACE GAME !! ■■■■■■■■■■■■■■■■■■■■■■■■\u001B[0m");
			service.clearScreen(6);
			service.StartYut1();
			service.clearScreen(5);
			System.out.println("  '4개의 엿가락'을 던져 20칸 '죽음의 도로'를 달려 GOAL에 먼저 도달하면 '승리' 합니다.");
			System.out.println("     도:1칸, 개:2칸, 걸:3칸, 윷:4칸(한번더), 모:5칸(한번더),앞사람잡으면(한번더)");
			System.out.println("            게임은 총 3판을 진행해서 두 번을 먼저 이기면 승리입니다.  ");
			System.out.println("         먼저 시작하실 분을 정하시고 참가자 A가 되신분은 '1'을 눌러 시작하세요\n");
			System.out.println(
					"\u001B[31m" + " ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■" + "\u001B[0m");
			for (int i = 0; i < 20; i = i + 1) {
				if (i == 0) {
					System.out.print("\u001B[32m (A\u001B[0m\u001B[33mB) \u001B[0m");
				} else {
					System.out.print(" o ");
				}
			}
			System.out.println(" Goal");
			System.out.println(
					"\u001B[31m" + " ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■" + "\u001B[0m");
			System.out.println(" ■■■■■■■■■■■■■■■■■■■■■■■ YUT GAME Start!!! ■■■■■■■■■■■■■■■■■■■■■■■■■\n");
			while (true) {// palyer A 와 player B 번갈아 가며 윷을 던집니다.
				System.out.println();// player의 점수를 출력 합니다.
				//
				if (gamecntA >= 20) {
					service.clearSpace(23);
					System.out.println("축하합니다!! 참가자 A 승리!!");
					service.ShowResult("A");
					break;
				} else if (gamecntB >= 20) {
					service.clearSpace(23);
					System.out.println("축하합니다!!. 참가자 B 승리!!");
					service.ShowResult("B");
					break;
				}
				//
				if (play) { // 게임을 시작 합니다.
					service.clearSpace(22);
					System.out.print("참가자 B press '2' enter> ");
				} else {
					service.clearSpace(22);
					System.out.print("참가자 A press '1' enter> ");
				}
				int menu = util.numberCheck();
				service.clearScreen(80);
				if (menu == 1 && play == false) {
					YprojectDTO resultThrow = service.throwYut("A");// 윷을 던지는 메소드 호출 하여 DTO타입 변수에 리턴값을 담는다.
					gamecntA = resultThrow.getSumPositionCnt();// A의 위치값을 받아 변수에 담는다.
					if (resultThrow.getRetryChkno() == 1) {// 윷이나 모가 나오거나 앞 플레이어를 잡을 경우 다시 한 번 던집니다.
						continue;
					}
					play = true;// turn 전환
					continue;
				} else if (menu == 2 && play == true) {
					YprojectDTO resultThrow = service.throwYut("B");
					gamecntB = resultThrow.getSumPositionCnt();// B 위치값을 받아 변수에 담는다.
					if (resultThrow.getRetryChkno() == 1) {// 윷이나 모가 나오거나 앞 플레이어를 잡을 경우 다시 한 번 던집니다.
						continue;
					}
					play = false;// turn 전환
					continue;
				} else {
					service.reinput();
				}
			}
			service.clearScreen(2);
			service.clearSpace(28);
			System.out.print("<< 게임  종료 >>");
		}
	}