/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import jp.co.orangeright.crossheadofficesample2.entity.Nextdayschedule_;
import jp.co.orangeright.crossheadofficesample2.entity.Nextdayschedule;
import jp.co.orangeright.crossheadofficesample2.jsf.nextdayschedule.NextdayscheduleSearchCondition;

/**
 *
 * @author yosh
 */
@Stateless
public class NextdayscheduleFacade extends AbstractFacade<Nextdayschedule> {

    @PersistenceContext(unitName = "jp.co.orange-right_CrossHeadOfficeSample2_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NextdayscheduleFacade() {
        super(Nextdayschedule.class);
    }

    public List<Nextdayschedule> findAll(NextdayscheduleSearchCondition condition) {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Nextdayschedule> cq = cb.createQuery(Nextdayschedule.class);
        Root<Nextdayschedule> root = cq.from(Nextdayschedule.class);
        cq = this.getSearchQuery(condition, cb, cq, root);
        this.setOrderby(condition, cb, cq, root);
        Query q = this.getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    public List<Nextdayschedule> findRange(int[] range, NextdayscheduleSearchCondition condition) {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Nextdayschedule> cq = cb.createQuery(Nextdayschedule.class);
        Root<Nextdayschedule> root = cq.from(Nextdayschedule.class);
        cq = this.getSearchQuery(condition, cb, cq, root);
        this.setOrderby(condition, cb, cq, root);
        Query q = this.getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count(NextdayscheduleSearchCondition condition) {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Nextdayschedule> root = cq.from(Nextdayschedule.class);
        cq = this.getSearchQuery(condition, cb, cq, root);

        cq.select(cb.count(root));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    private CriteriaQuery getSearchQuery(NextdayscheduleSearchCondition condition, CriteriaBuilder cb, CriteriaQuery cq, Root root) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get(Nextdayschedule_.validrow), true));
        if (condition.getReturnflg() != null) {
            predicates.add(cb.equal(root.get(Nextdayschedule_.returnflg), condition.getReturnflg()));
        }
        if (condition.getReturnmessage() != null) {
            predicates.add(cb.like(root.get(Nextdayschedule_.returnmessage).as(String.class), "%" + condition.getReturnmessage() + "%"));
        }
        cq.select(root).where(cb.and(predicates.toArray(new Predicate[]{})));
        return cq;
    }

    private void setOrderby(NextdayscheduleSearchCondition condition, CriteriaBuilder cb, CriteriaQuery cq, Root root) {
        if (condition.getOrderBy() != null) {
            if (condition.getAsc()) {
                cq.orderBy(cb.asc(this.makeOrderBy(condition, cb, cq, root)));
            } else {
                cq.orderBy(cb.desc(this.makeOrderBy(condition, cb, cq, root)));
            }
        }
    }

    private Expression makeOrderBy(NextdayscheduleSearchCondition condition, CriteriaBuilder cb, CriteriaQuery cq, Root root) {
        if (condition.getOrderBy().equals(Nextdayschedule_.adddate)) {
            return root.get(Nextdayschedule_.adddate);
        } else {
            return root.get(condition.getOrderBy());
        }
    }
}
