package cn.edu.sdust.crm.domain;

import java.sql.Timestamp;

public class Sheet {
	private int id;
	private String title;
	private String status;//状态
	private int template;//模板
	private int importance;//重要度
	private String originator;//发起人
	private String associates;//关联人
	private Timestamp createtime;//创建时间
	private Timestamp accepttime;//截止时间
	private String opration;//操作
	
	public Sheet() {	}
	public Sheet(String title, String status, int template, int importance, String originator,
			String associates, Timestamp createtime, String opration) {
		this.title = title;
		this.status = status;
		this.template = template;
		this.importance = importance;
		this.originator = originator;
		this.associates = associates;
		this.createtime = createtime;
		this.opration = opration;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTemplate() {
		return template;
	}
	public void setTemplate(int template) {
		this.template = template;
	}
	public int getImportance() {
		return importance;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}
	public String getOriginator() {
		return originator;
	}
	public void setOriginator(String originator) {
		this.originator = originator;
	}
	public String getAssociates() {
		return associates;
	}
	public void setAssociates(String associates) {
		this.associates = associates;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp date) {
		this.createtime = date;
	}
	public Timestamp getAccepttime() {
		return accepttime;
	}
	public void setAccepttime(Timestamp accept) {
		this.accepttime = accept;
	}
	public String getOpration() {
		return opration;
	}
	public void setOpration(String opration) {
		this.opration = opration;
	}
}
