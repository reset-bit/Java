package cn.edu.sdust.crm.domain;

import java.util.ArrayList;
import java.util.List;

public class Enums {
	private List<String> status;
	private List<String> op;
	
	public Enums() {
		status = new ArrayList<String>() {{
			add("已提交");
			add("已分配");
			add("已受理");
			add("已回复");
			add("已完结");
		}};
		
		op = new ArrayList<String>() {{
			add("提醒分配");
			add("提醒受理");
			add("进行留言");
			add("申请完结");
			add("    ");
		}};
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public List<String> getOp() {
		return op;
	}

	public void setOp(List<String> op) {
		this.op = op;
	}
	
}
