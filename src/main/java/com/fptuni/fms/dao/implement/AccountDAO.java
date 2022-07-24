package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IAccountDAO;
import com.fptuni.fms.mapper.AccountMapper;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.fms.utils.SecurityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NhatTan
 */
public class AccountDAO extends AbstractDAO<Account> implements IAccountDAO {

    private final AccountMapper mapper = new AccountMapper();

    @Override
    public Integer Create(String Username, String Password, String Fullname, int RoleID) {
        try {
            String sql = "INSERT INTO dbo.Account(Username, Password, Fullname, RoleID, IsDeleted) VALUES (?,?,?,?,0)";
            String hashPassword = SecurityUtils.createHash(Password, Username);
            return insert(sql, Username, hashPassword, Fullname, RoleID);
        } catch (Exception e) {
            System.out.println("Database query error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean Delete(String username) {
        String sql = "UPDATE dbo.Account SET IsDeleted=1 WHERE Username=? AND IsDeleted = 0";
        return update(sql, username);
    }

    @Override
    public boolean Update(String Username, String Password, String Fullname, int RoleID) {
        try {
            String sql = "UPDATE dbo.Account SET Password=?, Fullname=?, RoleID=? WHERE Username=? AND IsDeleted = 0";
            String hashPassword = SecurityUtils.createHash(Password, Username);
            return update(sql, hashPassword, Fullname, RoleID, Username);
        } catch (Exception e) {
            System.out.println("Database query error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Account checkLogin(String username, String password) {
        try {
            String sql = "SELECT ID, Username, FullName, RoleID FROM dbo.Account WHERE Username=? AND Password=? AND IsDeleted = 0";
            String hashPassword = SecurityUtils.createHash(password, username);
            List<Account> acc = query(sql, mapper, username, hashPassword);
            return acc == null ? null : (acc.isEmpty() ? null : acc.get(0));
        } catch (Exception e) {
            System.out.println("Database query error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Account> getListAccount(Pageable pageable) {
        // Sort theo field xong moi paging
        // Neu chon sortField khac thi cac Product moi trang se thay doi
        // Vi du: sortField = ID ==> list ID ASC ==> paging
        String sql = "SELECT * FROM \n"
                + "(SELECT Account.ID, Username, FullName, RoleID, Name, Account.IsDeleted "
                + "FROM Account Join Role On Account.RoleID = Role.ID "
                + "WHERE Account.IsDeleted = 0\n";
        String orderBy;
        if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
            orderBy = pageable.getSorter().isAscending() ? "ASC" : "DESC";
            sql += "ORDER BY " + pageable.getSorter().getSortField() + "  " + orderBy;
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql += " OFFSET " + pageable.getOffset() + " ROWS\n"
                    + " FETCH NEXT " + pageable.getLimit() + " ROWS ONLY ) AS A \n";
        }
        if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
            orderBy = pageable.getSorter().isAscending() ? "ASC" : "DESC";
            sql += "ORDER BY A." + pageable.getSorter().getSortField() + " " + orderBy;
        }

        List<Account> listAcc = query(sql, new AccountMapper());
        return listAcc;
    }

    public Account isAccountLinkToStore(String username){
        String sql = "select a.ID, a.Username, a.FullName, a.RoleID\n" +
                "from Account a join StoreAccount SA on a.ID = SA.AccountID\n" +
                "where Username = ?\n";
        List<Account> result = query(sql, new AccountMapper(), username);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<Account> search(Pageable pageable, int isDelete, String username, String fullName, int roleId) {
        // Sort theo field xong moi paging
        // Neu chon sortField khac thi cac Product moi trang se thay doi
        // Vi du: sortField = ID ==> list ID ASC ==> paging

        String sql = "";

        if(roleId == 0){
            sql = "SELECT * FROM \n"
                    + "(SELECT Account.ID, Username, FullName, RoleID, Name, Account.IsDeleted "
                    + "FROM Account Join Role On Account.RoleID = Role.ID "
                    + "WHERE Account.IsDeleted = ? AND Username LIKE ? AND FullName LIKE ?\n";
        }
        else {
            sql = "SELECT * FROM \n"
                    + "(SELECT Account.ID, Username, FullName, RoleID, Name, Account.IsDeleted "
                    + "FROM Account Join Role On Account.RoleID = Role.ID "
                    + "WHERE Account.IsDeleted = ? AND Username LIKE ? AND FullName LIKE ? AND RoleID = ?\n";
        }

        String orderBy;
        if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
            orderBy = pageable.getSorter().isAscending() ? "ASC" : "DESC";
            sql += "ORDER BY " + pageable.getSorter().getSortField() + "  " + orderBy;
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql += " OFFSET " + pageable.getOffset() + " ROWS\n"
                    + " FETCH NEXT " + pageable.getLimit() + " ROWS ONLY ) AS A \n";
        }
        if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
            orderBy = pageable.getSorter().isAscending() ? "ASC" : "DESC";
            sql += "ORDER BY A." + pageable.getSorter().getSortField() + " " + orderBy;
        }


        if(roleId == 0){
            return query(sql, new AccountMapper(), isDelete, "%" + username + "%", "%" + fullName + "%");
        }
        else {
            return query(sql, new AccountMapper(), isDelete, "%" + username + "%", "%" + fullName + "%", roleId);
        }

    }

    @Override
    public Account getAccount(int id) {
        String sql = "SELECT Account.ID, Username, FullName, RoleID, Name FROM Account Join Role On Account.RoleID = Role.ID WHERE Account.ID = ?";
        List<Account> listAcc = query(sql, new AccountMapper(), id);
        return listAcc == null ? null : listAcc.get(0);
    }

    public List<Account> getAvailableAccounts() {
        String sql = "select a.id as ID, a.username as Username,\n" +
                "       a.fullname as FullName, a.roleid as RoleID,\n" +
                "       r2.Name as [Name], a.isdeleted as IsDeleted\n" +
                "from Account a join Role R2 on a.RoleID = R2.ID\n" +
                "where r2.Name = 'Store Manager' OR r2.Name = 'Cashier'\n" +
                "EXCEPT (select AccountID, Username, FullName, RoleID, Role.Name, Account.IsDeleted\n" +
                "    from Account JOIN storeAccount on Account.ID = StoreAccount.AccountID\n" +
                "    join role on Account.RoleID = Role.ID)";
        List<Account> avaiAccount = query(sql, new AccountMapper());
        return avaiAccount;
    }

    @Override
    public Account getAccountUpdate(int id) {
        String sql = "SELECT Account.ID, Username, FullName, RoleID, Name FROM Account Join Role On Account.RoleID = Role.ID WHERE Account.ID = ? AND Account.IsDeleted = 0";
        List<Account> listAcc = query(sql, new AccountMapper(), id);
        return listAcc == null ? null : listAcc.get(0);
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(ID) FROM dbo.Account";
        return count(sql);
    }

    @Override
    public List<Account> getListStoreManager(){
        String sql = "SELECT Account.ID, Username, FullName, Role.Name AS Name\n" +
                "FROM Account JOIN Role on Account.RoleID = Role.ID\n" +
                "WHERE Role.Name = 'Store Manager' AND Account.IsDeleted = 0";
        List<Account> listAcc = query(sql, new AccountMapper());
        return listAcc.isEmpty() ? null : listAcc;
    }

    @Override
    public List<Account> getListStoreAccount(int storeID){
        String sql = "SELECT ID, FullName FROM Account a JOIN StoreAccount s ON a.ID = s.AccountID WHERE s.StoreID = ? AND s.IsDeleted = 0 AND s.IsDeleted = 0";
        List<Account> listAcc = query(sql, new AccountMapper(), storeID);
        return listAcc.isEmpty() ? null : listAcc;
    }
}
