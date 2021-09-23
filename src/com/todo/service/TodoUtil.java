package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== �׸��� ����� ���ô�!\n"
				+ "������ �Է����ּ���~");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��ؼ� �Է����� �����ּ���..");
			return;
		}
		
		System.out.println("�� �׸񿡴� � ������ �Է��ұ��?");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		
		System.out.println("\n"
				+ "========== �׸��� �����غ��Կ�!\n"
				+ "������ �׸��� �̸��� �Է����ּ���~\n"
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
				+ "========== �׸��� �����غ��Կ�!\n"
				+ "������ �ʿ��� �׸��� �̸��� �Է����ּ���!\n"
				+ "\n");
		String title = sc.nextLine().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("���� �׸��� �������� �����ſ���...");
			return;
		}

		System.out.println("���ο� �׸��� �̸��� �Է����ּ���!");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("������ �ߺ��ؼ� �Է����� �����ּ���..");
			return;
		}
		
		System.out.println("���ο� �׸��� ������ �Է����ּ���! ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("�׸��� �����Ǿ����!");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("<��ü ����� ���ô�!>");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toSaveString());
		}
	}

	public static void saveList(TodoList l, String todolist.txt) {
		// TODO Auto-generated method stub
		
	}
}
