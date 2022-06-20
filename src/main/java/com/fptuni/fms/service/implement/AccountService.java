package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.AccountDAO;
import com.fptuni.fms.dao.implement.RoleDAO;
import com.fptuni.fms.dao.implement.StoreDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.service.IAccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountService implements IAccountService {

    private static final AccountDAO accountDAO = new AccountDAO();

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
                System.out.println(account.getRole().getName());
                switch (account.getRole().getName()) {
                    case "Admin":
                        //response toi link cua admin

                        break;
                    case "Cashier":
                        //response toi link cua cashier
                        Store store = new StoreDAO().getStoreByAccount(account);
                        System.out.println("Store: " + store);
                        if (store != null) request.getSession().setAttribute("store", store);
                        url = request.getContextPath() + "/order/index";
                        break;
                    case "Counter":
                        //respone toi link cua counter
                        url = request.getContextPath() + "/customer/index";
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

    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        System.out.println("Sign out successful");
        return request.getContextPath();
    }
}
