/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jp.co.orangeright.crossheadofficesample2.entity.Keihi;

/**
 *
 * @author yosh
 */
@Stateless
public class KeihiFacade extends AbstractFacade<Keihi> {

    @PersistenceContext(unitName = "jp.co.orange-right_CrossHeadOfficeSample2_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KeihiFacade() {
        super(Keihi.class);
    }
    
}
