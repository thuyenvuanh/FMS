package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.IProductDAO;
import com.fptuni.fms.dao.IStoreDAO;
import com.fptuni.fms.dao.implement.ProductDAO;
import com.fptuni.fms.dao.implement.StoreDAO;
import com.fptuni.fms.model.*;
import com.fptuni.fms.service.ICategoryService;
import com.fptuni.fms.service.IOrderDetailService;
import com.fptuni.fms.service.IProductService;
import com.fptuni.fms.paging.PageRequest;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.fms.sort.Sorter;
import com.fptuni.fms.utils.RequestUtils;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.websocket.Session;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class ProductService implements IProductService {
    private IStoreDAO storeDAO = new StoreDAO();
    private IProductDAO productDAO = new ProductDAO();
    private ICategoryService categoryService = new CategoryService();
    private IOrderDetailService orderDetailService = new OrderDetailService();

    @Override
    public List<Product> getProducts(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Store store = (Store) session.getAttribute("store");
        int pageIndex = 1;
        int pageSize = 5;
        String sortField = "ID";
        boolean isAsc = true;
        if (request.getParameter("currentPage") != null) {
            pageIndex = Integer.parseInt(request.getParameter("currentPage"));
        }
        if (request.getParameter("sortField") != null) {
            sortField = request.getParameter("sortField");
        }
        if (request.getParameter("isAscending") != null) {
            isAsc = Boolean.parseBoolean(request.getParameter("isAscending"));
        }
        Sorter sorter = new Sorter(sortField, isAsc);
        Pageable pageable = new PageRequest(pageIndex, pageSize, sorter);
        Map<String, String> searcher = new HashMap<>(); // key: param name; value: param value
        // Search field
        searcher.put("storeID", String.valueOf(store.getId()));
        searcher.put("categoryID", request.getParameter("categoryID"));
        searcher.put("productName", request.getParameter("productName"));
        searcher.put("minPrice", request.getParameter("minPrice"));
        searcher.put("maxPrice", request.getParameter("maxPrice"));
        searcher.put("quantity", request.getParameter("quantity"));
        // status = enable when quantity > 0
        if (request.getParameter("status") != null) searcher.put("status", request.getParameter("status"));
        List<Product> products = productDAO.getProducts(pageable, searcher);
        request.setAttribute("currentPage", pageIndex);
        request.setAttribute("sortField", sortField);
        // Tu dong dao nguoc khi nhan nhieu lan vao sortField
        request.setAttribute("isAscending", !isAsc);
        // forward map input param so as not to reset entered field in searching
        for (Map.Entry<String, String> entries : searcher.entrySet()) {
            if (entries.getValue() != null && !entries.getValue().trim().isEmpty()) {
                // param name= key | param value=map value
                request.setAttribute(entries.getKey(), entries.getValue());
            }
        }

        return products;
    }

    @Override
    public Product getProductById(String productId) {
        if (productId == null || productId.isEmpty())
            return null;
        IProductDAO productDAO = new ProductDAO();
        return productDAO.getProduct(productId);
    }

    @Override
    public List<Product> getProductByOrderID(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = null;
        try {
            int orderID = 0;
            HttpSession session = request.getSession();
            Store store = (Store) session.getAttribute("store");
            if (request.getParameter("orderID") != null) orderID = Integer.parseInt(request.getParameter("orderID"));
            products = productDAO.getProductByOrderID(orderID, store);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Integer insertProduct(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Store store = (Store) session.getAttribute("store");
        String id = "";
        String name = "";
        BigDecimal price = BigDecimal.valueOf(0.0);
        String imgPath = "";
        int cateID = 1;
        short quantity = 1;
        // get this store id
        int storeID = store.getId();
        Category category = new Category();
        if (request.getParameter("name") != null) {
            name = request.getParameter("name");
        }
        if (request.getParameter("price") != null) {
            price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
        }
        imgPath = saveUploadFile(request, response);
        if (request.getParameter("categoryID") != null) {
            cateID = Integer.parseInt(request.getParameter("categoryID"));
            // get category info by id
            category = categoryService.getCategory(cateID);
            // count the number of exist foods which have the same category
            List<Product> products = productDAO.getProductsByCategory(cateID, null);
            int subID = products.size() + 1;
            // concat short name and the next Id
            id = category.getShortName() + subID;
        }
        if (request.getParameter("quantity") != null) {
            quantity = Short.parseShort(request.getParameter("quantity"));
        }

        Product product = new Product(id, name, imgPath, price, quantity, category, new Store(storeID));
        // Set input request attribute to forward to create page if not success
        request.setAttribute("product", product);
        // force all of these param not null except Image
        // Key = param name | Value = param value
//        Map<String, String> paramMap = RequestUtils.getParameters(request.getQueryString());
//        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
//            if (entry.getKey().equals("imagePath"))
//                continue;
//            else if (entry.getValue().isEmpty())
//                return 0;
//        }
        return productDAO.insertProduct(product);
    }

    @Override
    public boolean updateProduct(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Store store = (Store) session.getAttribute("store");
        String id = "";
        String name = "";
        String imgPath = "";
        BigDecimal price = BigDecimal.valueOf(0.0);
        int cateID = 1;
        short quantity = 1;
        // get this store id
        int storeID = store.getId();
        Category category = new Category();
        try {


            if (request.getParameter("id") != null) {
                id = request.getParameter("id");
            }
            if (request.getParameter("name") != null) {
                name = request.getParameter("name");
            }
            if (request.getParameter("price") != null) {
                price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
            }
            imgPath = saveUploadFile(request, response);

            if (request.getParameter("categoryID") != null) {
                cateID = Integer.parseInt(request.getParameter("categoryID"));
                // get category info by id
                category = categoryService.getCategory(cateID);
                List<Category> categories = categoryService.getCategories();
                // if change category: short cate name in id != choosen cate then create new proid
                // else not change pro id
                if (!id.contains(category.getShortName())) {
                    int subID = 1;
                    // count the number of exist foods which have the same category
                    for (Category c : categories) {
                        if (c.getShortName().contains(category.getShortName())) {
                            subID++;
                            break;
                        }
                    }
                    // concat short name and the next ID
                    id = category.getShortName() + (subID + 1);
                }
            }
            if (request.getParameter("quantity") != null) {
                quantity = Short.parseShort(request.getParameter("quantity"));
            }
            Product product = new Product(id, name, imgPath, price, quantity, category, new Store(storeID));
            // Set input request attribute to forward to create page if not success
            request.setAttribute("product", product);
            // force all of these param not null except Image
            // Key = param name | Value = param value
//            Map<String, String> paramMap = RequestUtils.getParameters(request.getQueryString());
//            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
//                if (!entry.getKey().equals("imagePath") && entry.getValue().isEmpty()){
//                    return false;
//                }
//            }
            return productDAO.updateProduct(product);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
        return true;
    }

    private String saveUploadFile(HttpServletRequest request, HttpServletResponse response) {
        String UPLOAD_DIR = "images/product";
        Part part = null;
        try {
            int index = request.getServletContext().getRealPath("").indexOf("target");
            String uploadPath = request.getServletContext().getRealPath("").substring(0, index) + "src\\main\\webapp\\" + UPLOAD_DIR;
            final Part filePart = request.getPart("imagePath");
            final String fileName = getFileName(filePart);
            OutputStream out = null;
            InputStream filecontent = null;
            try {
                out = new FileOutputStream(new File(uploadPath + File.separator + fileName));
                filecontent = filePart.getInputStream();
                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }

            } catch (FileNotFoundException fne) {
                System.out.println("You either did not specify a file to upload or are "
                        + "trying to upload a file to a protected or nonexistent "
                        + "location." + fne.getMessage());
            } finally {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
                return UPLOAD_DIR + File.separator + fileName;
            }
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    @Override
    public boolean deleteProduct(String productId) {
        if (productId == null)
            return false;
        return productDAO.deleteProduct(productId);
    }

    @Override
    public int countProduct() {
        return productDAO.count();
    }

    @Override
    public int countProductBySearch(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Store store = (Store) session.getAttribute("store");
        int pageIndex = 1;
        int pageSize = 5;
        String sortField = "ID";
        boolean isAsc = true;
        if (request.getParameter("currentPage") != null) {
            pageIndex = Integer.parseInt(request.getParameter("currentPage"));
        }
        if (request.getParameter("sortField") != null) {
            sortField = request.getParameter("sortField");
        }
        if (request.getParameter("isAscending") != null) {
            isAsc = Boolean.parseBoolean(request.getParameter("isAscending"));
        }
        Map<String, String> searcher = new HashMap<>(); // key: param name; value: param value
        // Search field
        searcher.put("storeID", String.valueOf(store.getId()));
        searcher.put("categoryID", request.getParameter("categoryID"));
        searcher.put("productName", request.getParameter("productName"));
        searcher.put("minPrice", request.getParameter("minPrice"));
        searcher.put("maxPrice", request.getParameter("maxPrice"));
        searcher.put("quantity", request.getParameter("quantity"));
        // status = enable when quantity > 0
        if (request.getParameter("status") != null) searcher.put("status", request.getParameter("status"));
        int count = productDAO.countBySearch(searcher);
        return count;
    }

    @Override
    public List<Product> getTop5ProductsOrderByAmount(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Store store = (Store) session.getAttribute("store");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        List<Product> products = null;
        try {
            Calendar calendar = Calendar.getInstance();
            Date end = calendar.getTime();
            calendar.add(Calendar.MONTH, -1);
            calendar.add(Calendar.DATE, +1);
            Date start = calendar.getTime();
            if (request.getParameter("startDate") != null && request.getParameter("endDate") != null) {
                start = sdf.parse(request.getParameter("startDate"));
                end = sdf.parse(request.getParameter("endDate"));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                request.setAttribute("startDateFmt", simpleDateFormat.format(start));
                request.setAttribute("endDateFmt", simpleDateFormat.format(end));
                if (start.after(end)) {
                    throw new Exception("Start date must be before end date");
                }
            }
            products = productDAO.getTop5ProductsOrderByAmount(store, start, end);
        } catch (Exception exception) {
            request.setAttribute("dateError", exception.getMessage());
            return null;
        }
        return products;
    }

    @Override
    public List<Double> getPercentageOfProductInCategory(HttpServletRequest request, HttpServletResponse response) {
        List<Double> result = new ArrayList<>();
        List<Category> categories = categoryService.getCategories();
        int n = 0;
        int totalSale = 0;
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailInDateRange(request, response);
        for (OrderDetail orderDetail : orderDetails) {
            totalSale += orderDetail.getQuantity();
        }
        for (Category category : categories) {
            for (OrderDetail orderDetail : orderDetails) {
                if (Objects.equals(orderDetail.getProduct().getCateID().getId(), category.getId())) {
                    n += orderDetail.getQuantity();
                }
            }
            double percentage = (double) n / totalSale;
            n = 0;
            result.add(percentage);
        }
        return result;
    }
}
