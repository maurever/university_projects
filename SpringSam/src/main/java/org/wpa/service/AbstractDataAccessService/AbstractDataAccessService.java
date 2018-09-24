package org.wpa.service.AbstractDataAccessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.wpa.DAOImpl.GenericDAO;

/**
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
public abstract class AbstractDataAccessService {

    @Autowired
    protected GenericDAO genericDao;

    public void setGenericDao(GenericDAO genericDao) {
        this.genericDao = genericDao;
    }

    public GenericDAO getGenericDao() {
        return genericDao;
    }

}
