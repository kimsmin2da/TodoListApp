package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
    	System.out.println();
        System.out.println("<ToDoList> 관리 명령어 사용법");
        System.out.println("1. 항목 추가는 add 를 입력해주세요.");
        System.out.println("2. 항목 제거는 del 을 입력해주세요.");
        System.out.println("3. 항목 수정은 edit 을 입력해주세요.");
        System.out.println("4. 전체 목록은 ls 를 입력해주세요.");
        System.out.println("5. 항목 제목순 정렬은 ls_name_asc 를 입력해주세요.");
        System.out.println("6. 항목의 제목역순 정렬은 ls_name_desc 를 입력해주세요.");
        System.out.println("7. 항목의 날짜순 정렬은 ls_date 를 입력해주세요.");
        System.out.println("8. 항목의 날짜역순 정렬은 ls_date_desc 를 입력해주세요.");
        System.out.println("9. 항목을 찾고 싶으시다면 find 단어를 함께 입력해주세요 <ex) find 과목>");
        System.out.println("10. 종료는 exit 을 입력해주세요.");
    }
    
    public static void prompt() {
    	System.out.print("\nCommand > ");
    }
    
}
