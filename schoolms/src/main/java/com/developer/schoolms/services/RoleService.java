package com.developer.schoolms.services;

import com.developer.schoolms.dao.RoleDao;
import com.developer.schoolms.entity.Role;
import com.developer.schoolms.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<Role> getSearchedRole(SearchCriteria searchCriteria) {
        return roleDao.getSearchedRole(searchCriteria);
    }

    @Transactional(readOnly = false)
    public void saveRole(Role role) {
        roleDao.save(role);
    }

    @Transactional(readOnly = false)
    public void updateRole(Role role) {
        roleDao.update(role);
    }

    @Transactional(readOnly = false)
    public void deleteRole(Long id) {
        roleDao.delete(id);
    }

}
