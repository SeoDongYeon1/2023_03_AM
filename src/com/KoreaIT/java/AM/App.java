package com.KoreaIT.java.AM;

import java.util.Scanner;

import com.KoreaIT.java.AM.Controller.ArticleController;
import com.KoreaIT.java.AM.Controller.Controller;
import com.KoreaIT.java.AM.Controller.MemberController;

public class App {
	
	public App() {
	}

	public void start() {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		Controller controller;
		
		articleController.maketestData();
		memberController.maketestData();

		while (true) {
			System.out.print("명령어 > ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) 
			{
				System.out.println("명령어를 입력해주세요.");
				continue;
			}

			if (command.equals("exit")) 
			{
				break;
			}
			
			String[] commandDiv = command.split(" ");
					
			String controllerName = commandDiv[0];
			
			if(commandDiv.length == 1) {
				System.out.println("명령어를 확인해주세요.");
				continue;
			}
			
			String actionMethodName = commandDiv[1];
			
			String forLoginCheck = controllerName + "/" + actionMethodName;
			
			controller = null;
			
			if(controllerName.equals("article")) {
				controller = articleController;
			} 
			else if(controllerName.equals("member")) {
				controller = memberController;
			} 
			else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
			switch(forLoginCheck) {
			case "article/write":
			case "article/delete":
			case "article/modify":
			case "member/logout":
			case "member/profile":
				if(Controller.isLogined() == false) {
					System.out.println("로그인 후 이용해주세요.");
					continue;
				}
				break;
			}
			
			switch(forLoginCheck) {
			case "member/login":
			case "member/join":
				if(Controller.isLogined()) {
					System.out.println("로그아웃 후 이용해주세요.");
					continue;
				}
				break;
			}
			
			controller.doAction(actionMethodName, command);
			
		}
		System.out.println("==프로그램 끝==");

		sc.close();
		
	}
}
