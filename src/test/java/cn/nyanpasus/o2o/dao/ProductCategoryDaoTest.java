package cn.nyanpasus.o2o.dao;

import cn.nyanpasus.o2o.BaseTest;
import cn.nyanpasus.o2o.entity.ProductCategory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    @Order(1)
    public void testQueryByShopId() throws Exception {
        long shopId = 1;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println(productCategoryList.size());
    }

    @Test
    @Order(2)
    public void BatchInsertProductCategoryTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("测试类别1");
        productCategory.setPriority(1);
        productCategory.setCreateTime(new Date());
        productCategory.setShopId(1L);
        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("测试类别2");
        productCategory2.setPriority(2);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(1L);
        List<ProductCategory> productCategories = new ArrayList<>();
        productCategories.add(productCategory);
        productCategories.add(productCategory2);
        int i = productCategoryDao.batchInsertProductCategory(productCategories);
        assertEquals(2, i);
    }

    @Test
    @Order(3)
    public void deleteProductCategoryTest()throws Exception {
        long shopId = 1;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        for (ProductCategory productCategory : productCategoryList) {
            if ("123123".equals(productCategory.getProductCategoryName()) || "2233".equals(productCategory.getProductCategoryName())) {
                int i = productCategoryDao.deleteProductCategory(productCategory.getProductCategoryId(), shopId);
                assertEquals(1, i);
            }

        }
    }
}
