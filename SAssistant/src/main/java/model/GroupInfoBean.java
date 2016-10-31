package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GROUPINFO")
public class GroupInfoBean {

	@Id
	private String groupName;
	private String groupId;
	
	@Override
	public String toString() {
		return "GroupInfoBean [groupName=" + groupName + ", groupId=" + groupId + "]";
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}

