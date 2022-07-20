package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.IIdentityCardDAO;
import com.fptuni.fms.dao.implement.IdentityCardDAO;
import com.fptuni.fms.model.Customer;
import com.fptuni.fms.service.IIdentityCardService;

public class IdentityCardService implements IIdentityCardService {
    @Override
    public Integer createIdentityCard(Customer customer) {
        IIdentityCardDAO identityCardDAO = new IdentityCardDAO();
        return identityCardDAO.createIdentityCard(customer);
    }
}
