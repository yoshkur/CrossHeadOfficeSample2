/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.jsf.userm;

import java.util.List;
import jp.co.orangeright.crossheadofficesample2.entity.GroupM;
import jp.co.orangeright.crossheadofficesample2.jsf.util.SearchCondition;

/**
 *
 * @author yosh
 */
public class UserMSearchCondition extends SearchCondition {

	private String userName;
	private GroupM group;
	private List<GroupM> groupList;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public GroupM getGroup() {
		return group;
	}

	public void setGroup(GroupM group) {
		this.group = group;
	}

	public List<GroupM> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<GroupM> groupList) {
		this.groupList = groupList;
	}

}
