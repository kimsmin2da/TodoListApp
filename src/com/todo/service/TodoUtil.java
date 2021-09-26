package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
		static int count=0;	
	public static void createItem(TodoList list) {
		

		String title, category,desc,due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== �׸��� ����� ���ô�!\n"
				+ "������ �Է����ּ���~");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��ؼ� �Է����� �����ּ���..");
			return;
		}
		
		System.out.println("�� �׸񿡴� � ī�װ��� �Է��ұ��?");
		category = sc.nextLine();
		
		System.out.println("�� �׸񿡴� � ������ �Է��ұ��?");
		desc = sc.nextLine();
		
		System.out.println("�� �׸񿡴� �������� �Է��ұ��?");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(count, title, category,desc,due_date);
		list.addItem(t);
		
		count++;
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
				count--;
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
		
		System.out.println("���ο� �׸��� �����ð��� �Է����ּ���! ");
		String new_due_date = sc.nextLine().trim();
		
		
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem( count, new_title,new_description, new_due_date, new_due_date);
				l.addItem(t);
				System.out.println("�׸��� �����Ǿ����!");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("<��ü ����� ���ô�!> : �� "+ count +"��");
		for (TodoItem item : l.getList()) {
			System.out.print(item.toSaveString());
		}
	}
		//for(int i=0; i<l.getCount(); i++) {
		//	System.out.println((i+1)+". "+ l.getItem(i));
		//}
       //  	}

	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter("todolist.txt");
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("��� �����Ͱ� ����Ǿ����ϴ�.");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("todolist.txt"));
			String line;
			int count =0;
			while((line = br.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				String category = st.nextToken();		
				String title = st.nextToken();
				String description = st.nextToken();
				String due_date = st.nextToken();	
				String current_date = st.nextToken();
				TodoItem item = new TodoItem(count, title, category, description, due_date);
				item.setCurrent_date(current_date);
				l.addItem(item);
				count++;
			}
			br.close();
			System.out.println(count + "���� �׸��� �о����ϴ�.");
		}catch (FileNotFoundException e) {
			System.out.println(filename+" ������ �����ϴ�.");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void findList(TodoList l, String keyword) {
		int count =0;
		for(int i =0; i<l.getCount(); i++) {
			if(l.getItem(i).getTitle().contains(keyword)||l.getItem(i+1).getTitle().contains(keyword)) {
				System.out.println((i+1)+". "+l.getItem(i).toString());
			count++;
		   }
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n",count);
	}
	
	
	public static void findCateList(TodoList l, String cate) {
		int count=0;
		for(int i=0; i<l.getCount(); i++) {
			if(l.getItem(i).getCategory().contains(cate)) { 
				System.out.println((i+1) + ". "+ l.getItem(i).toString());
			count++;
			}
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n",count);
	}
	
	public static void listCateAll(TodoList l) {
		Set<String> clist = new HashSet<String>();
		
		for(TodoItem c : l.getList()) {
			clist.add(c.getCategory());
		}
		
		Iterator it = clist.iterator();
		while(it.hasNext()) {
			String s = (String)it.next();
			System.out.print(s);
			if(it.hasNext()) System.out.print(" / ");
		}
		System.out.printf("\n �� %d ���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.\n", clist.);
	}
	
	public static boolean isExistCategory(List<String> clist, String cate) {
		for(String c : clist) if(c.equals(cate))
	}
	
}













