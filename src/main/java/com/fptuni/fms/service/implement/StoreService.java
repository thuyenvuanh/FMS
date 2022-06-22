/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.IStoreDAO;
import com.fptuni.fms.dao.implement.AccountDAO;
import com.fptuni.fms.dao.implement.StoreDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.paging.PageRequest;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.fms.service.IStoreService;
import com.fptuni.fms.sort.Sorter;
import java.io.IOException;
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

    @Override
    public String create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("createStatus");
        String name = request.getParameter("storeName");
        Account accLogin = (Account) session.getAttribute("account");
//        Account acc = new Account(accLogin.getId());
        Account acc = new Account(1);
        Store store = new Store();
        store.setName(name);
        store.setAccountID(acc);
        Integer check = storeDAO.insertStore(store);
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
        request.setAttribute("currentPage", pageIndex);
        request.setAttribute("sortField", sortField);
        // Tu dong dao nguoc khi nhan nhieu lan vao sortField
        request.setAttribute("isAsc", !isAsc);

        int totalPages = storeDAO.count() / pageSize;
        if (storeDAO.count() % pageSize != 0) {
            totalPages++;
        }
        request.setAttribute("storeList", listStore);
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
        request.setAttribute("store", store);
        return "/view/admin/storeUpdate.jsp";
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("updateStatus");
        String id = request.getParameter("storeId");
        String name = request.getParameter("name");
        boolean check = storeDAO.updateStore(Integer.parseInt(id), name);
        if (check == false) {
            session.setAttribute("updateStatus", "fail");
            return request.getContextPath() + "/store/list";
        }

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
}
