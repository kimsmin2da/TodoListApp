package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
    	System.out.println();
        System.out.println("<ToDoList> ���� ��ɾ� ����");
        System.out.println("1. �׸� �߰��� add �� �Է����ּ���.");
        System.out.println("2. �׸� ���Ŵ� del �� �Է����ּ���.");
        System.out.println("3. �׸� ������ edit �� �Է����ּ���.");
        System.out.println("4. �׸� �߰��� ls �� �Է����ּ���.");
        System.out.println("5. �׸� ��ü ����� ls_name_asc �� �Է����ּ���.");
        System.out.println("6. �׸��� ����� ������ ls_name_desc �� �Է����ּ���.");
        System.out.println("7. �׸��� ���񿪼� ������ ls_date �� �Է����ּ���.");
        System.out.println("8. ����� exit �� �Է����ּ���.");
    }
    
    public static void prompt() {
    	System.out.print("\nCommand > ");
    }
    
}
