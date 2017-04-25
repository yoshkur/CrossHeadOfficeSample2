/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import jp.co.orangeright.crossheadofficesample2.entity.Item;
import jp.co.orangeright.crossheadofficesample2.entity.Item_;
import jp.co.orangeright.crossheadofficesample2.entity.Schedule_;
import jp.co.orangeright.crossheadofficesample2.jsf.item.ItemSearchCondition;

/**
 *
 * @author yosh
 */
@Stateless
public class ItemFacade extends AbstractFacade<Item> {

    @PersistenceContext(unitName = "jp.co.orange-right_CrossHeadOfficeSample2_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemFacade() {
        super(Item.class);
    }

    @Override
    public void create(Item entity) {
        super.create(entity);
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

    public int countItemid(ItemSearchCondition condition) {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Item> root = cq.from(Item.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get(Item_.validrow), true));
        if (condition.getItemcd() != null) {
            predicates.add(cb.like(root.get(Item_.itemcd).as(String.class), "%" + condition.getItemcd() + "%"));
        }
        if (condition.getCustomer() != null) {
            predicates.add(cb.equal(root.get(Item_.customerid), condition.getCustomer()));
        }
        cq.select(root).where(cb.and(predicates.toArray(new Predicate[]{})));
        cq.groupBy(root.get(Item_.itemcd));
        cq.select(cb.count(root));
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList().size();
    }

    private CriteriaQuery getSearchQuery(ItemSearchCondition condition, CriteriaBuilder cb, CriteriaQuery cq, Root root) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get(Item_.validrow), true));
        if (condition.getItemcd() != null) {
            predicates.add(cb.like(root.get(Item_.itemcd).as(String.class), "%" + condition.getItemcd() + "%"));
        }
        if (condition.getKeyword() != null) {
            predicates.add(cb.or(cb.like(root.get(Item_.detail).as(String.class), "%" + condition.getKeyword() + "%"), cb.like(root.get(Item_.memo).as(String.class), "%" + condition.getKeyword() + "%")));
        }
        if (condition.getCustomer() != null) {
            predicates.add(cb.equal(root.get(Item_.customerid), condition.getCustomer()));
        }
        if (condition.getUser() != null) {
            predicates.add(cb.equal(root.get(Item_.userid), condition.getUser()));
        }
        if (condition.getAppoint() != null) {
            predicates.add(cb.equal(root.get(Item_.appoint), condition.getAppoint()));
        }
        if (condition.getWorked() != null) {
            predicates.add(cb.equal(root.get(Item_.worked), condition.getWorked()));
        }
        if (condition.getFinished() != null) {
            predicates.add(cb.equal(root.get(Item_.finished), condition.getFinished()));
        }
        if (condition.getOnhold() != null) {
            predicates.add(cb.equal(root.get(Item_.onhold), condition.getOnhold()));
        }
        if (condition.getCancel() != null) {
            predicates.add(cb.equal(root.get(Item_.cancel), condition.getCancel()));
        }
        if (condition.getAccounting() != null) {
            predicates.add(cb.equal(root.get(Item_.accounting), condition.getAccounting()));
        }
        if (condition.getTurnStart() != null) {
            predicates.add(cb.equal(root.get(Item_.scheid).get(Schedule_.validrow), true));
            predicates.add(cb.greaterThanOrEqualTo(root.get(Item_.scheid).get(Schedule_.datefrom), condition.getTurnStart()));
        }
        if (condition.getTurnEnd() != null) {
            predicates.add(cb.equal(root.get(Item_.scheid).get(Schedule_.validrow), true));
            Calendar upper = Calendar.getInstance();
            upper.setTime(condition.getTurnEnd());
            upper.add(Calendar.DATE, 1);
            Date turnEnd = upper.getTime();
            predicates.add(cb.lessThan(root.get(Item_.scheid).get(Schedule_.datefrom), turnEnd));
        }
        if (condition.getAddDateStart() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(Item_.adddate), condition.getAddDateStart()));
        }
        if (condition.getAddDateEnd() != null) {
            Calendar upper = Calendar.getInstance();
            upper.setTime(condition.getAddDateEnd());
            upper.add(Calendar.DATE, 1);
            Date turnEnd = upper.getTime();
            predicates.add(cb.lessThan(root.get(Item_.adddate), turnEnd));
        }
        if (condition.getUpdateDateStart() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(Item_.updatedate), condition.getUpdateDateStart()));
        }
        if (condition.getUpdateDateEnd() != null) {
            Calendar upper = Calendar.getInstance();
            upper.setTime(condition.getUpdateDateEnd());
            upper.add(Calendar.DATE, 1);
            Date turnEnd = upper.getTime();
            predicates.add(cb.lessThan(root.get(Item_.updatedate), turnEnd));
        }
        cq.select(root).where(cb.and(predicates.toArray(new Predicate[]{})));
        return cq;
    }

    private void setOrderby(ItemSearchCondition condition, CriteriaBuilder cb, CriteriaQuery cq, Root root) {
        if (condition.getOrderBy() != null) {
            if (condition.getAsc()) {
                cq.orderBy(cb.asc(this.makeOrderBy(condition, cb, cq, root)));
            } else {
                cq.orderBy(cb.desc(this.makeOrderBy(condition, cb, cq, root)));
            }
        }
    }

    private Expression makeOrderBy(ItemSearchCondition condition, CriteriaBuilder cb, CriteriaQuery cq, Root root) {
        if (condition.getOrderBy().equals(Schedule_.datefrom)) {
            return root.get(Item_.scheid).get(Schedule_.datefrom);
        } else {
            return root.get(condition.getOrderBy());
        }
    }
}
