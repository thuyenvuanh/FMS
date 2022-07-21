package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.TransactionSharedDAO;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.paging.PageRequest;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.fms.service.ITransactionService;
import com.fptuni.fms.sort.Sorter;
import com.fptuni.fms.utils.SecurityUtils;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionService implements ITransactionService {


    private final TransactionSharedDAO dao = new TransactionSharedDAO();

    @Override
    public int insertNewTransaction(TransactionShared transaction) {
        return dao.insertTransaction(transaction);
    }

    @Override
    public TransactionShared getLatestTransactionSharedByWalletID(Integer walletID) {
        TransactionShared transactionShared = null;
        // TransactionSharedDAO transactionSharedDAO = new TransactionSharedDAO();
        String hashString = "";
        if (walletID != null) {
            transactionShared = dao.getLatestTransactionOf(walletID);
            if (transactionShared != null) {
                try {
                    transactionShared.setAmount(transactionShared.getAmount().stripTrailingZeros());
                    transactionShared.setPreviousBalance(transactionShared.getPreviousBalance().stripTrailingZeros());
                    String target = transactionShared.toString();
                    String salt = String.valueOf(transactionShared.getCreatedDate().getTime());
                    String compareString = transactionShared.getHashValue();
                    boolean isValid = SecurityUtils.validateHash(target, salt, compareString);
                    if (isValid) {
                        return transactionShared;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public TransactionShared getLatestTransaction() {
        TransactionShared transactionShared = null;
        transactionShared = dao.getLatestTransaction();
        if (transactionShared != null) {
            transactionShared.setPreviousBalance(transactionShared.getPreviousBalance().stripTrailingZeros());
            transactionShared.setAmount(transactionShared.getAmount().stripTrailingZeros());
            String target = transactionShared.toString();
            String salt = String.valueOf(transactionShared.getCreatedDate().getTime());
            String compareString = transactionShared.getHashValue();
            try {
                if (SecurityUtils.validateHash(target, salt, compareString)) {
                    return transactionShared;
                }
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Cannot hash");
            } catch (InvalidKeySpecException e) {
                System.out.println("Invalid Key Spec");
            }
        }
        return null;
    }

    @Override
    public BigDecimal getCustomerBalance(TransactionShared transactionShared) {
        BigDecimal amount = BigDecimal.ZERO.add(transactionShared.getPreviousBalance());
        // if payment == null => is moneyTransaction transaction for top up => add
        // amount
        // otherwise transaction for buy product => subtract amount
        if (transactionShared.getPaymentID() == null) {
            amount = amount.add(transactionShared.getAmount());
        } else {
            amount = amount.subtract(transactionShared.getAmount());
        }
        return amount.stripTrailingZeros();
    }

    public List<TransactionShared> getTransactionSharedByStore(HttpServletRequest request, Store store) {
        String customerPhone = null;
        String status = null;
        String dateSearch = null;
        String amount = null;
        String sortField = "ID";
        boolean isAscending = true;
        int page = 1;
        int pageSize = 2;
        Map<String, String> searcher = new HashMap<>();
        if (request.getParameter("customerPhone") != null) {
            customerPhone = request.getParameter("customerPhone").replaceAll("[() -]+", "");
        }
        if (request.getParameter("status") != null && !request.getParameter("status").isEmpty()) {
            status = request.getParameter("status");
        }
        if (request.getParameter("dateSearch") != null) {
            dateSearch = request.getParameter("dateSearch");

        }
        if (request.getParameter("amount") != null) {
            amount = request.getParameter("amount").replaceAll(",", "");
        }
        if (request.getParameter("currentPage") != null) {
            page = Integer.parseInt(request.getParameter("currentPage"));
        }

        searcher.put("customerPhone", customerPhone);
        searcher.put("status", status);
        searcher.put("dateSearch", dateSearch);
        searcher.put("amount", amount);
        Sorter sorter = new Sorter(sortField, isAscending);
        Pageable pageable = new PageRequest(page, pageSize, sorter);
        List<TransactionShared> transactionShareds = dao.searchTransactionShare(store, searcher);
        int totalPages = transactionShareds.size() / pageSize;
        if (transactionShareds.size() % pageSize != 0) {
            totalPages++;
        }

        for (Map.Entry<String,String> searchParam : searcher.entrySet()){
            request.setAttribute(searchParam.getKey(),searchParam.getValue());
        }
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage",page);
        return dao.getTransactionSharedByStore(store, searcher, pageable);
    }

    private List<TransactionShared> countTransactionSharedByStore(HttpServletRequest request, Store store) {
        String customerPhone = null;
        String status = null;
        String dateSearch = null;
        String amount = null;
        Map<String, String> searcher = new HashMap<>();
        if (request.getParameter("customerPhone") != null) {
            customerPhone = request.getParameter("customerPhone").replaceAll("[() -]+", "");
        }
        if (request.getParameter("status") != null) {
            status = request.getParameter("status");
        }
        if (request.getParameter("dateSearch") != null) {
            dateSearch = request.getParameter("dateSearch");

        }
        if (request.getParameter("amount") != null) {
            amount = request.getParameter("amount").replaceAll(",", "");
        }
        searcher.put("customerPhone", customerPhone);
        searcher.put("status", status);
        searcher.put("dateSearch", dateSearch);
        searcher.put("amount", amount);
        return dao.searchTransactionShare(store, searcher);
    }
}
