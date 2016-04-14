package org.academia.model.dao;

import org.academia.model.Role;

public interface RoleDao extends Dao<Role> {

    public Role findByName(String name);

}
