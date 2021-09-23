package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== 항목을 만들어 봅시다!\n"
				+ "제목을 입력해주세요~");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("제목은 중복해서 입력하지 말아주세요..");
			return;
		}
		
		System.out.println("이 항목에는 어떤 내용을 입력할까요?");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		
		System.out.println("\n"
				+ "========== 항목을 삭제해볼게요!\n"
				+ "삭제할 항목의 이름을 입력해주세요~\n"
				+ "\n");
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== 항목을 수정해볼게요!\n"
				+ "수정이 필요한 항목의 이름을 입력해주세요!\n"
				+ "\n");
		String title = sc.nextLine().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("이제 항목이 존재하지 않을거에요...");
			return;
		}

		System.out.println("새로운 항목의 이름을 입력해주세요!");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목은 중복해서 입력하지 말아주세요..");
			return;
		}
		
		System.out.println("새로운 항목의 내용을 입력해주세요! ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("항목이 수정되었어요!");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("<전체 목록을 봅시다!>");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toSaveString());
		}
	}

	public static void saveList(TodoList l, String todolist.txt) {
		// TODO Auto-generated method stub
		
	}
}
