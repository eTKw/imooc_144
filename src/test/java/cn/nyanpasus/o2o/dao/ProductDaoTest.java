package cn.nyanpasus.o2o.dao;

import cn.nyanpasus.o2o.BaseTest;
import cn.nyanpasus.o2o.entity.Product;
import cn.nyanpasus.o2o.entity.ProductCategory;
import cn.nyanpasus.o2o.entity.ProductImg;
import cn.nyanpasus.o2o.entity.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testInsertProduct() throws Exception {
        Shop shop1 = new Shop();
        shop1.setShopId(1L);
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryId(1L);
        Product product1 = new Product();
        product1.setProductName("测试1");
        product1.setProductDesc("测试Desc1");
        product1.setImgAddr("test1");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setLastEditTime(new Date());
        product1.setShop(shop1);
        product1.setProductCategory(productCategory1);

        Product product2 = new Product();
        product2.setProductName("测试2");
        product2.setProductDesc("测试Desc2");
        product2.setImgAddr("test2");
        product2.setPriority(1);
        product2.setEnableStatus(1);
        product2.setCreateTime(new Date());
        product2.setLastEditTime(new Date());
        product2.setShop(shop1);
        product2.setProductCategory(productCategory1);

        Product product3 = new Product();
        product3.setProductName("test3");
        product3.setProductDesc("测试Desc3");
        product3.setImgAddr("test3");
        product3.setPriority(1);
        product3.setEnableStatus(1);
        product3.setCreateTime(new Date());
        product3.setLastEditTime(new Date());
        product3.setShop(shop1);
        product3.setProductCategory(productCategory1);

        int effectedNum = productDao.insertProduct(product1);
        assertEquals(1, effectedNum);
        effectedNum = productDao.insertProduct(product2);
        assertEquals(1, effectedNum);
        effectedNum = productDao.insertProduct(product3);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQueryProductByProductId() throws Exception {
        long productId = 1;
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("测试图片1");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(productId);

        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片2");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(productId);
        List<ProductImg> productImgList = new ArrayList<>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int i = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(2, i);
        Product product = productDao.queryProductById(productId);
        System.out.println(product.getShop().getShopName());
        assertEquals(2, product.getProductImgList().size());
        i = productImgDao.deleteProductImgByProductId(productId);
        assertEquals(2,i);

    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product();
        ProductCategory productCategory = new ProductCategory();
        Shop shop = new Shop();
        shop.setShopId(1L);
        productCategory.setProductCategoryId(5L);
        product.setProductId(1L);
        product.setShop(shop);
        product.setProductName("第一个测试产品");
        product.setProductCategory(productCategory);
        int i = productDao.updateProduct(product);
        assertEquals(1, i);
    }
}
