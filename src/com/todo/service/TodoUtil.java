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
				+ "========== 항목을 만들어 봅시다!\n"
				+ "제목을 입력해주세요~");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("제목은 중복해서 입력하지 말아주세요..");
			return;
		}
		
		System.out.println("이 항목에는 어떤 카테고리를 입력할까요?");
		category = sc.nextLine();
		
		System.out.println("이 항목에는 어떤 내용을 입력할까요?");
		desc = sc.nextLine();
		
		System.out.println("이 항목에는 마감일을 입력할까요?");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(count, title, category,desc,due_date);
		list.addItem(t);
		
		count++;
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
				count--;
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
		
		System.out.println("새로운 항목의 마감시간을 입력해주세요! ");
		String new_due_date = sc.nextLine().trim();
		
		
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem( count, new_title,new_description, new_due_date, new_due_date);
				l.addItem(t);
				System.out.println("항목이 수정되었어요!");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("<전체 목록을 봅시다!> : 총 "+ count +"개");
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
			System.out.println("모든 데이터가 저장되었습니다.");
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
			System.out.println(count + "개의 항목을 읽었습니다.");
		}catch (FileNotFoundException e) {
			System.out.println(filename+" 파일이 없습니다.");
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
		System.out.printf("총 %d개의 항목을 찾았습니다.\n",count);
	}
	
	
	public static void findCateList(TodoList l, String cate) {
		int count=0;
		for(int i=0; i<l.getCount(); i++) {
			if(l.getItem(i).getCategory().contains(cate)) { 
				System.out.println((i+1) + ". "+ l.getItem(i).toString());
			count++;
			}
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n",count);
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
		System.out.printf("\n 총 %d 개의 카테고리가 등록되어 있습니다.\n", clist.);
	}
	
	public static boolean isExistCategory(List<String> clist, String cate) {
		for(String c : clist) if(c.equals(cate))
	}
	
}













