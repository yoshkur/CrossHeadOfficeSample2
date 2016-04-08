/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.jsf.util;

import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author yosh
 */
public class SearchCondition {

	private Boolean asc;
	private SingularAttribute orderBy;

	public Boolean getAsc() {
		return asc;
	}

	public void setAsc(Boolean asc) {
		this.asc = asc;
	}

	public SingularAttribute getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(SingularAttribute orderBy) {
		this.orderBy = orderBy;
	}

}
