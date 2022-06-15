package com.fptuni.fms.dao.implement;

import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.IdentityCard;
import com.fptuni.fms.paging.PageRequest;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.fms.sort.Sorter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class IdentityCardDAOTest {
    IdentityCardDAO identityCardDAO;

    public IdentityCardDAOTest() {
    }

    @Before
    public void setup() {identityCardDAO = new IdentityCardDAO();}

    @After
    public void tearDown() {
        identityCardDAO = null;
    }

    @Test
    public void TestGetListWithoutParams(){
        IdentityCard card1 = new IdentityCard(new Integer(1));
        card1.setCustomerID(new Customer(1));
        IdentityCard card2 = new IdentityCard(new Integer(2));
        card2.setCustomerID(new Customer(2));
        IdentityCard card3 = new IdentityCard(new Integer(3));
        card3.setCustomerID(new Customer(5));
        IdentityCard card4 = new IdentityCard(new Integer(4));
        List<IdentityCard> expecteds = new ArrayList<>();
        expecteds.add(card1);
        expecteds.add(card2);
        expecteds.add(card3);
        expecteds.add(card4);
        List<IdentityCard> list = identityCardDAO.getList(null, new PageRequest());
        assertArrayEquals(expecteds.toArray(), list.toArray());
    }

    @Test
    public void TestGetListWithStatus_InUser(){
        IdentityCard card1 = new IdentityCard(new Integer(1));
        card1.setCustomerID(new Customer(1));
        IdentityCard card2 = new IdentityCard(new Integer(2));
        card2.setCustomerID(new Customer(2));
        IdentityCard card3 = new IdentityCard(new Integer(3));
        card3.setCustomerID(new Customer(5));

        List<IdentityCard> expecteds = new ArrayList<>();
        expecteds.add(card1);
        expecteds.add(card2);
        expecteds.add(card3);
        List<IdentityCard> list = identityCardDAO.getList(true, null);
        assertArrayEquals(expecteds.toArray(), list.toArray());
    }

    @Test
    public void TestGetListWithStatus_Available(){

        IdentityCard card4 = new IdentityCard(new Integer(4));

        List<IdentityCard> expecteds = new ArrayList<>();
        expecteds.add(card4);
        List<IdentityCard> list = identityCardDAO.getList(false, new PageRequest());
        assertArrayEquals(expecteds.toArray(), list.toArray());
    }

    @Test
    public void TestGetListWithPageable_DESC(){
        IdentityCard card1 = new IdentityCard(new Integer(3));
        card1.setCustomerID(new Customer(5));
        IdentityCard card2 = new IdentityCard(new Integer(2));
        card2.setCustomerID(new Customer(2));
        List<IdentityCard> expecteds = new ArrayList<>();
        expecteds.add(card1);
        expecteds.add(card2);
        List<IdentityCard> list = identityCardDAO.getList(true, new PageRequest(1, 2, new Sorter("ID", false)));
        assertArrayEquals(expecteds.toArray(), list.toArray());
    }

    @Test
    public void TestGetListWithPageable_ASC(){
        IdentityCard card1 = new IdentityCard(new Integer(1));
        card1.setCustomerID(new Customer(1));
        IdentityCard card2 = new IdentityCard(new Integer(2));
        card2.setCustomerID(new Customer(2));
        List<IdentityCard> expecteds = new ArrayList<>();
        expecteds.add(card1);
        expecteds.add(card2);
        List<IdentityCard> list = identityCardDAO.getList(null, new PageRequest(1, 2, new Sorter("ID", true)));
        assertArrayEquals(expecteds.toArray(), list.toArray());
    }
}
