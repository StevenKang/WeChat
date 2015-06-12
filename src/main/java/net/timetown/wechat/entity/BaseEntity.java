package net.timetown.wechat.entity;

import java.sql.Timestamp;

/**
 * 数据库基础实体
 * @author stevenkang
 *
 */
public class BaseEntity {

	// 主键ID
	private Integer id;
	
	// 实体创建时间
	private Timestamp createDate;
	
	// 实体修改时间
	private Timestamp modifyDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
