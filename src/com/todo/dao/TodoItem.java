package com.todo.dao;

//import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TodoItem {
    private String title;
    private String desc;
    private Date current_date;


    public TodoItem(String title, String desc){
        this.title=title;
        this.desc=desc;
        this.current_date=new Date();
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCurrent_date() {
        return current_date;
    }

  public String setCurrent_date(Date current_date) {
       this.current_date = current_date;
	return "current_date";
  }
        
    //Calendar calendar = Calendar.getInstance();
    //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    
    public String toSaveString() {
    	return title+"##"+desc+"## " + current_date+"\n";
    }
    
}
