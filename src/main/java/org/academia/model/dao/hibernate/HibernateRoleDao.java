package org.academia.model.dao.hibernate;

import org.academia.model.Role;
import org.academia.model.dao.Dao;
import org.academia.model.dao.RoleDao;
import org.academia.persistence.TransactionException;
import org.academia.persistence.hibernate.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class HibernateRoleDao extends HibernateDao<Role> implements RoleDao {

    public HibernateRoleDao(HibernateSessionManager hibernateSessionManager) {
        super(Role.class,hibernateSessionManager);
    }

    /**
     * @see Dao#findByName(String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public Role findByName(String name){

        try {

            List<Role> roles = getHibernateSessionManager().getSession().createCriteria(Role.class)
                    .add(Restrictions.eq("name", name)).list();

            return roles.isEmpty() ? null : roles.get(0);

        } catch (HibernateException hex) {
            throw new TransactionException(hex);
        }

    }
}
