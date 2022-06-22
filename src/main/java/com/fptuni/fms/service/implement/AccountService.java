package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.AccountDAO;
import com.fptuni.fms.dao.implement.RoleDAO;
import com.fptuni.fms.dao.implement.StoreDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Role;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.paging.PageRequest;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.fms.service.IAccountService;
import com.fptuni.fms.sort.Sorter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;

public class AccountService implements IAccountService {

    private static final AccountDAO accountDAO = new AccountDAO();

    @Override
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("message");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + " " + password);
        String url = null;
        try {
            Account account = accountDAO.checkLogin(username, password);
            System.out.println(account == null ? "Account not found" : "Account found");
            if (account != null) {
                //kiem tra role va dieu huong vao man hinh
                account.setRole(new RoleDAO().getRole(account.getRole().getId()));
                request.getSession().setAttribute("account", account);
                System.out.println("Account role: "+account.getRole().getName());
                Store store = new StoreDAO().getStoreByAccount(account);
                if (store != null) {
                    request.getSession().setAttribute("store", store);
                    System.out.println("Store Name: " + store.getName());
                }
                switch (account.getRole().getName()) {
                    case "Admin":
                        //response toi link cua admin
                        url = request.getContextPath() + "/account/list";
                        break;
                    case "Cashier":
                        //response toi link cua cashier
                        url = request.getContextPath() + "/order/index";
                        break;
                    case "Counter":
                        //respone toi link cua counter
                        url = request.getContextPath() + "/counter/index";
                        break;
                    case "Store Manager":
                        url = request.getContextPath() + "/product/list";
                        //response toi link cua store manager
                        break;
                    default:
                        //chuyen huong den trang error
                        break;
                }
            } else {
                url = request.getContextPath();
                //tra ve man hinh dang nhap va hien thong bao loi
                request.getSession().setAttribute("message", "Incorrect email or password");
            }
        } catch (Exception e) {
            // fail login
            //chuyen huong den trang error
        }
        return url;
    }

    @Override
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        System.out.println("Sign out successful");
        return request.getContextPath();
    }

    @Override
    public String create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("createStatus");
        String userName = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String cfPassword = request.getParameter("cfPassword");
        String roleId = request.getParameter("roleId");
        if (!password.equals(cfPassword)) {
            session.setAttribute("createStatus", "fail");
            return request.getContextPath() + "/account/create";
        }
        Integer check = accountDAO.Create(userName, password, fullName, Integer.parseInt(roleId));
        if (check == null) {
            session.setAttribute("createStatus", "fail");
            return request.getContextPath() + "/account/create";
        }
        session.setAttribute("createStatus", "success");
        return "/account/list";
    }

    @Override
    public String getRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleDAO role = new RoleDAO();
        List<Role> listRole = role.getListRole();
        request.setAttribute("listRole", listRole);
        return "/view/admin/accountCreate.jsp";
    }

    @Override
    public String getListAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        List<Account> listAcc = accountDAO.getListAccount(pageable);
        request.setAttribute("currentPage", pageIndex);
        request.setAttribute("sortField", sortField);
        // Tu dong dao nguoc khi nhan nhieu lan vao sortField
        request.setAttribute("isAsc", !isAsc);

        int totalPages = accountDAO.count() / pageSize;
        if (accountDAO.count() % pageSize != 0) {
            totalPages++;
        }
        RoleDAO role = new RoleDAO();
        List<Role> listRole = role.getListRole();
        request.setAttribute("roleList", listRole);
        request.setAttribute("accountList", listAcc);
        request.setAttribute("totalPages", totalPages);
        return "/view/admin/accountList.jsp";
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
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        List<Account> listAcc = accountDAO.search(pageable, isDelete, username, fullName, roleId);
        request.setAttribute("currentPage", pageIndex);
        request.setAttribute("sortField", sortField);
        // Tu dong dao nguoc khi nhan nhieu lan vao sortField
        request.setAttribute("isAsc", !isAsc);

        int totalPages = accountDAO.count() / pageSize;
        if (accountDAO.count() % pageSize != 0) {
            totalPages++;
        }
        request.setAttribute("status", isDelete);
        request.setAttribute("username", username);
        request.setAttribute("fullName", fullName);
        request.setAttribute("roleId", roleId);

        RoleDAO role = new RoleDAO();
        List<Role> listRole = role.getListRole();
        request.setAttribute("roleList", listRole);
        request.setAttribute("accountList", listAcc);
        request.setAttribute("totalPages", totalPages);
        return "/view/admin/accountList.jsp";
    }

    @Override
    public String getAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleDAO role = new RoleDAO();
        List<Role> listRole = role.getListRole();
        String accountID = request.getParameter("accountId");
        if (accountID == null || accountID.isEmpty()) {
            return "/account/list";
        }
        Account acc = accountDAO.getAccount(Integer.parseInt(accountID));
        request.setAttribute("account", acc);
        return "/view/admin/accountView.jsp";
    }

    @Override
    public String getAccountUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleDAO role = new RoleDAO();
        List<Role> listRole = role.getListRole();
        String accountID = request.getParameter("accountId");
        if (accountID == null || accountID.isEmpty()) {
            return "/account/list";
        }
        Account acc = accountDAO.getAccountUpdate(Integer.parseInt(accountID));
        request.setAttribute("roleList", listRole);
        request.setAttribute("account", acc);
        return "/view/admin/accountUpdate.jsp";
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("updateStatus");
        String userName = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String cfPassword = request.getParameter("cfPassword");
        String roleId = request.getParameter("roleId");
        if (!password.equals(cfPassword)) {
            session.setAttribute("updateStatus", "fail");
            return request.getContextPath() + "/account/create";
        }
        boolean check = accountDAO.Update(userName, password, fullName, Integer.parseInt(roleId));
        if (check == false) {
            session.setAttribute("updateStatus", "fail");
            return request.getContextPath() + "/account/create";
        }
        session.setAttribute("updateStatus", "success");
        return "/account/list";
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("deleteStatus");
        String username = request.getParameter("username");
        if (username == null || username.isEmpty()) {
            session.setAttribute("deleteStatus", "fail");
        }
        if (!accountDAO.Delete(username)) {
            session.setAttribute("deleteStatus", "fail");
        }
        return "/account/list";
    }
}
