/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import jp.co.orangeright.crossheadofficesample2.entity.Item;
import jp.co.orangeright.crossheadofficesample2.entity.Item_;
import jp.co.orangeright.crossheadofficesample2.entity.Schedule_;
import jp.co.orangeright.crossheadofficesample2.jsf.item.ItemSearchCondition;

/**
 *
 * @author yosh
 */
@Stateless
public class SearchItemFacade {

	// Add business logic below. (Right-click in editor and choose
	// "Insert Code > Add Business Method")
	@PersistenceContext(unitName = "jp.co.orange-right_CrossHeadOfficeSample2_war_1.0-SNAPSHOTPU")
	private EntityManager em;

	public SearchItemFacade() {
	}

	protected EntityManager getEntityManager() {
		return em;
	}

	public void create(Item entity) {
		getEntityManager().persist(entity);
	}

	public void edit(Item entity) {
		getEntityManager().merge(entity);
	}

	public void remove(Item entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	public Item find(Object id) {
		return getEntityManager().find(Item.class, id);
	}

	public List<Item> findAll(ItemSearchCondition condition) {
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Item> cq = cb.createQuery(Item.class);
		Root<Item> root = cq.from(Item.class);
		cq = this.getSearchQuery(condition, cb, cq, root);
		this.setOrderby(condition, cb, cq, root);
		Query q = this.getEntityManager().createQuery(cq);
		return q.getResultList();
	}

	public List<Item> findRange(int[] range, ItemSearchCondition condition) {
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Item> cq = cb.createQuery(Item.class);
		Root<Item> root = cq.from(Item.class);
		cq = this.getSearchQuery(condition, cb, cq, root);
		this.setOrderby(condition, cb, cq, root);
		Query q = this.getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0] + 1);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	public int count(ItemSearchCondition condition) {
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root<Item> root = cq.from(Item.class);
		cq = this.getSearchQuery(condition, cb, cq, root);

		cq.select(cb.count(root));
		Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

	private CriteriaQuery getSearchQuery(ItemSearchCondition condition, CriteriaBuilder cb, CriteriaQuery cq, Root root) {
		Predicate predicate;
		cq.select(root).where(cb.equal(root.get(Item_.validrow), true));
		predicate = cq.getRestriction();
		if (condition.getItemcd() != null) {
			cq.select(root).where(predicate, cb.like(root.get(Item_.itemcd).as(String.class), "%" + condition.getItemcd() + "%"));
			predicate = cq.getRestriction();
		}
		if (condition.getKeyword() != null) {
			cq.select(root).where(predicate, cb.like(root.get(Item_.detail).as(String.class), "%" + condition.getKeyword() + "%"));
			predicate = cq.getRestriction();
		}
		if (condition.getCustomer() != null) {
			cq.select(root).where(predicate, cb.equal(root.get(Item_.customerid), condition.getCustomer()));
			predicate = cq.getRestriction();
		}
		if (condition.getUser() != null) {
			cq.select(root).where(predicate, cb.equal(root.get(Item_.userid), condition.getUser()));
			predicate = cq.getRestriction();
		}
		if (condition.getAppoint() != null) {
			cq.select(root).where(predicate, cb.equal(root.get(Item_.appoint), condition.getAppoint()));
			predicate = cq.getRestriction();
		}
		if (condition.getWorked() != null) {
			cq.select(root).where(predicate, cb.equal(root.get(Item_.worked), condition.getWorked()));
			predicate = cq.getRestriction();
		}
		if (condition.getFinished() != null) {
			cq.select(root).where(predicate, cb.equal(root.get(Item_.finished), condition.getFinished()));
			predicate = cq.getRestriction();
		}
		if (condition.getOnhold() != null) {
			cq.select(root).where(predicate, cb.equal(root.get(Item_.onhold), condition.getOnhold()));
			predicate = cq.getRestriction();
		}
		if (condition.getCancel() != null) {
			cq.select(root).where(predicate, cb.equal(root.get(Item_.cancel), condition.getCancel()));
			predicate = cq.getRestriction();
		}
		if (condition.getAccounting() != null) {
			cq.select(root).where(predicate, cb.equal(root.get(Item_.accounting), condition.getAccounting()));
			predicate = cq.getRestriction();
		}
		if (condition.getTurnStart() != null) {
			cq.select(root).where(predicate, cb.greaterThanOrEqualTo(root.get(Item_.scheid).get(Schedule_.datefrom), condition.getTurnStart()));
			predicate = cq.getRestriction();
		}
		if (condition.getTurnEnd() != null) {
			Calendar upper = Calendar.getInstance();
			upper.setTime(condition.getTurnEnd());
			upper.add(Calendar.DATE, 1);
			Date turnEnd = upper.getTime();
			cq.select(root).where(predicate, cb.lessThan(root.get(Item_.scheid).get(Schedule_.datefrom), turnEnd));
			predicate = cq.getRestriction();
		}
		return cq;
	}

	private void setOrderby(ItemSearchCondition condition, CriteriaBuilder cb, CriteriaQuery cq, Root root) {
		if (condition.getOrderBy() != null) {
			if (condition.getAsc()) {
				cq.orderBy(cb.asc(root.get(condition.getOrderBy())));
			} else {
				cq.orderBy(cb.desc(root.get(condition.getOrderBy())));
			}
		}
	}
}
