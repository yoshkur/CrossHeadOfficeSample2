/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import jp.co.orangeright.crossheadofficesample2.entity.Customer;
import jp.co.orangeright.crossheadofficesample2.entity.Customer_;
import jp.co.orangeright.crossheadofficesample2.jsf.customer.CustomerSearchCondition;

/**
 *
 * @author yosh
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {

	@PersistenceContext(unitName = "jp.co.orange-right_CrossHeadOfficeSample2_war_1.0-SNAPSHOTPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CustomerFacade() {
		super(Customer.class);
	}

	public List<Customer> findAll(CustomerSearchCondition condition) {
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		Root<Customer> root = cq.from(Customer.class);
		cq = this.getSearchQuery(condition, cb, cq, root);
		this.setOrderby(condition, cb, cq, root);
		Query q = this.getEntityManager().createQuery(cq);
		return q.getResultList();
	}

	public List<Customer> findRange(int[] range, CustomerSearchCondition condition) {
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		Root<Customer> root = cq.from(Customer.class);
		cq = this.getSearchQuery(condition, cb, cq, root);
		this.setOrderby(condition, cb, cq, root);
		Query q = this.getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0] + 1);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	public int count(CustomerSearchCondition condition) {
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root<Customer> root = cq.from(Customer.class);
		cq = this.getSearchQuery(condition, cb, cq, root);

		cq.select(cb.count(root));
		Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

	private CriteriaQuery getSearchQuery(CustomerSearchCondition condition, CriteriaBuilder cb, CriteriaQuery cq, Root root) {
		Predicate predicate;
		cq.select(root).where(cb.equal(root.get(Customer_.validrow), true));
		predicate = cq.getRestriction();
		return cq;
	}

	private void setOrderby(CustomerSearchCondition condition, CriteriaBuilder cb, CriteriaQuery cq, Root root) {
		if (condition.getOrderBy() != null) {
			if (condition.getAsc()) {
				cq.orderBy(cb.asc(root.get(condition.getOrderBy())));
			} else {
				cq.orderBy(cb.desc(root.get(condition.getOrderBy())));
			}
		}
	}
}
