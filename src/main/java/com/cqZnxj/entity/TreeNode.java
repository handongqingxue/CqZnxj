package com.cqZnxj.entity;

import java.util.List;

//tree²Î¿¼Á´½Ó:https://www.cnblogs.com/new-life/p/9059499.html
//https://www.cnblogs.com/yj716716yj/p/5620269.html
public class TreeNode {

	private Integer id;
	private String text;
	private List<TreeNode> children;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
}
