/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.AccountDAO;
import com.fptuni.fms.dao.implement.StoreAccountDAO;
import com.fptuni.fms.dao.implement.StoreDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.model.StoreAccount;
import com.fptuni.fms.paging.PageRequest;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.fms.service.IStoreService;
import com.fptuni.fms.sort.Sorter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NhatTan
 */
public class StoreService implements IStoreService {

    private static final StoreDAO storeDAO = new StoreDAO();
    private static final AccountDAO accountDAO = new AccountDAO();

    private static final StoreAccountDAO storeAccountDAO = new StoreAccountDAO();

    @Override
    public String create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("createStatus");
        String name = request.getParameter("storeName");
        String storeManager = request.getParameter("select_storeManager");
        Store store = new Store();
        store.setName(name);
//        store.setAccountID(accountDAO.getAccount(Integer.parseInt(storeManager)));
        Integer check = storeDAO.insertStore(store);
        store.setId(check);
        storeAccountDAO.insert(new Account(Integer.parseInt(storeManager)), store);
        if (check == null) {
            session.setAttribute("createStatus", "fail");
            return "/store/createPage";
        }
        session.setAttribute("createStatus", "success");
        return "/store/list";
    }

//    @Override
//    public String getAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        AccountDAO acc = new AccountDAO();
//        List<Account> listAcc = acc.getAccount();
//        request.setAttribute("listAccount", listAcc);
//        return "/view/admin/accountCreate.jsp";
//    }
    @Override
    public String getListStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageIndex = 1;
        int pageSize = 10;
        String sortField = "ID";
        boolean isAsc = true;
        if (request.getParameter("page") != null) {
            pageIndex = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("sortField") != null) {
            sortField = request.getParameter("sortField");
        }
        if (request.getParameter("isAscending") != null) {
            isAsc = Boolean.parseBoolean(request.getParameter("isAscending"));
        }
        Sorter sorter = new Sorter(sortField, isAsc);
        Pageable pageable = new PageRequest(pageIndex, pageSize, sorter);
        List<Store> listStore = storeDAO.getListStore(pageable);
        HashMap<Store, List<Account>> accountsMap = new HashMap<>();

        for (Store s : listStore) {
            //s.setAccount(accountDAO.getListStoreAccount(s.getId()));
            //lay danh sach account thuoc ve store
            accountsMap.put(s, new ArrayList<>());
            for (Account account :
                    storeAccountDAO.getAccountsByStoreID(s.getId())) {
                accountsMap.get(s).add(accountDAO.getAccount(account.getId()));
            }
        }


        request.setAttribute("currentPage", pageIndex);
        request.setAttribute("sortField", sortField);
        // Tu dong dao nguoc khi nhan nhieu lan vao sortField
        request.setAttribute("isAsc", !isAsc);

        int totalPages = storeDAO.count() / pageSize;
        if (storeDAO.count() % pageSize != 0) {
            totalPages++;
        }
        request.setAttribute("storeList", listStore);
        request.setAttribute("map", accountsMap);
        request.setAttribute("totalPages", totalPages);
        return "/view/admin/storeList.jsp";
    }

    @Override
    public String getStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String storeID = request.getParameter("storeId");
        if (storeID == null || storeID.isEmpty()) {
            return "/store/list";
        }
        Store store = storeDAO.getStore(Integer.parseInt(storeID));
//        store.setAccountID(accountDAO.getListStoreAccount(store.getId()));

        List<Account> accounts = new ArrayList<>();
        for (Account account :
                storeAccountDAO.getAccountsByStoreID(store.getId())) {
            accounts.add(accountDAO.getAccount(account.getId()));
        }
        request.setAttribute("accountList", accounts);
        request.setAttribute("store", store);
        return "/view/admin/storeView.jsp";
    }

    @Override
    public String getStoreUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String storeID = request.getParameter("storeId");
        if (storeID == null || storeID.isEmpty()) {
            return "/store/list";
        }
        Store store = storeDAO.getStore(Integer.parseInt(storeID));
//        store.setAccountID(accountDAO.getListStoreAccount(store.getId()));

        Account manager = null, cashier = null;
        for (Account account :
                storeAccountDAO.getAccountsByStoreID(store.getId())) {
            Account temp = accountDAO.getAccount(account.getId());
            if (temp != null && temp.getRoleID().getName().equalsIgnoreCase("store manager"))
                manager = temp;
            if (temp != null && temp.getRoleID().getName().equalsIgnoreCase("cashier"))
                cashier = temp;
        }

        List<Account> avaiAccounts = accountDAO.getAvailableAccounts();
        List<Account> avaiManager = new ArrayList<>(), avaiCashier = new ArrayList<>();
        for (Account avaiAccount : avaiAccounts){
            if (avaiAccount.getRoleID().getName().equals("Store Manager"))
                avaiManager.add(avaiAccount);
            else
                avaiCashier.add(avaiAccount);
        }

        request.setAttribute("manager", manager);
        request.setAttribute("cashier", cashier);
        request.setAttribute("avManager", avaiManager);
        request.setAttribute("avCashier", avaiCashier);
        request.setAttribute("store", store);
        return "/view/admin/storeUpdate.jsp";
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //get parameters from requests
        session.removeAttribute("updateStatus");
        Store store = storeDAO.getStore(Integer.parseInt(request.getParameter("storeId")));
        store.setName(request.getParameter("name"));
        String manager_id = request.getParameter("manager_id");
        String cashier_id = request.getParameter("cashier_id");

        boolean isSuccess = storeDAO.updateStore(store.getId(), store.getName());
        if (manager_id != null && !manager_id.isEmpty()){
            Account manager = accountDAO.getAccount(Integer.parseInt(manager_id));
            isSuccess = storeAccountDAO.insert(manager, store) > 0;
        }

        if (cashier_id != null && !cashier_id.isEmpty()){
            Account cashier = accountDAO.getAccount(Integer.parseInt(request.getParameter("cashier_id")));
            isSuccess = storeAccountDAO.insert(cashier, store) > 0;
        }

        if (isSuccess == false) {
            session.setAttribute("updateStatus", "fail");
            return "/store/list";
        }
        session.setAttribute("updateStatus", "success");

        return "/store/list";
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("deleteStatus");
        String id = request.getParameter("storeId");
        if (id == null || id.isEmpty()) {
            session.setAttribute("deleteStatus", "fail");
        }
        if (!storeDAO.Delete(Integer.parseInt(id))) {
            session.setAttribute("deleteStatus", "fail");
        }
        session.setAttribute("deleteStatus", "success");
        return "/store/list";
    }

    @Override
    public String search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageIndex = 1;
        int pageSize = 10;
        String sortField = "ID";
        boolean isAsc = true;
        if (request.getParameter("page") != null) {
            pageIndex = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("sortField") != null) {
            sortField = request.getParameter("sortField");
        }
        if (request.getParameter("isAscending") != null) {
            isAsc = Boolean.parseBoolean(request.getParameter("isAscending"));
        }
        Sorter sorter = new Sorter(sortField, isAsc);
        Pageable pageable = new PageRequest(pageIndex, pageSize, sorter);
        int isDelete = Integer.parseInt(request.getParameter("status"));
        String name = request.getParameter("name");
        String storeManager = request.getParameter("storeManager");
        List<Store> listStore = storeDAO.search(pageable, isDelete, name, storeManager);
        HashMap<Store, List<Account>> storeAccountsMap = new HashMap<>();
        for (Store s : listStore) {
//            s.setAccountID(accountDAO.getListStoreAccount(s.getId()));
            storeAccountsMap.put(s, new ArrayList<>());
            for (Account account :
                    storeAccountDAO.getAccountsByStoreID(s.getId())) {
                storeAccountsMap.get(s).add(accountDAO.getAccount(account.getId()));
            }
        }
        request.setAttribute("map", storeAccountsMap);
        request.setAttribute("currentPage", pageIndex);
        request.setAttribute("sortField", sortField);
        // Tu dong dao nguoc khi nhan nhieu lan vao sortField
        request.setAttribute("isAsc", !isAsc);

        int totalPages = storeDAO.count() / pageSize;
        if (storeDAO.count() % pageSize != 0) {
            totalPages++;
        }
        request.setAttribute("status", isDelete);
        request.setAttribute("name", name);
        request.setAttribute("storeManager", storeManager);

        request.setAttribute("storeList", listStore);
        request.setAttribute("totalPages", totalPages);
        return "/view/admin/storeList.jsp";
    }

    @Override
    public String getStoreManager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Account> avaiAccounts = accountDAO.getAvailableAccounts();
        request.setAttribute("avaiAccounts", avaiAccounts);
        //        List<Account> listAcc = accountDAO.getListStoreManager();
//        request.setAttribute("listStoreManager", listAcc);
        return "/view/admin/storeCreate.jsp";
    }
}
