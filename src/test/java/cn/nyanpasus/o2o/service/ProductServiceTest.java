package cn.nyanpasus.o2o.service;

import cn.nyanpasus.o2o.BaseTest;
import cn.nyanpasus.o2o.dao.ProductDao;
import cn.nyanpasus.o2o.dto.ImageHolder;
import cn.nyanpasus.o2o.dto.ProductExecution;
import cn.nyanpasus.o2o.entity.Product;
import cn.nyanpasus.o2o.entity.ProductCategory;
import cn.nyanpasus.o2o.entity.Shop;
import cn.nyanpasus.o2o.enums.ProductStateEnum;
import cn.nyanpasus.o2o.exception.ProductOperationException;
import cn.nyanpasus.o2o.exception.ShopOperationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTest extends BaseTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testAddProduct() throws ShopOperationException, FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品1");
        product.setPriority(20);
        product.setCreateTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());

        File thumbnailFile = new File("D:\\project\\imooc_144\\o2o\\src\\main\\resources\\h2.png");
        FileInputStream inputStream = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), inputStream);
        File productImg1 = new File("D:\\project\\imooc_144\\o2o\\src\\main\\resources\\h1.png");
        FileInputStream inputStream1 = new FileInputStream(productImg1);
        File productImg2 = new File("D:\\project\\imooc_144\\o2o\\src\\main\\resources\\h2.png");
        FileInputStream inputStream2 = new FileInputStream(productImg1);

        ArrayList<ImageHolder> imageHolders = new ArrayList<>();
        imageHolders.add(new ImageHolder(productImg1.getName(), inputStream1));
        imageHolders.add(new ImageHolder(productImg2.getName(), inputStream2));

        ProductExecution productExecution = productService.addProduct(product, thumbnail, imageHolders);
        assertEquals(ProductStateEnum.SUCCESS.getState(), productExecution.getState());
    }

    @Test
    public void testModifyProduct() throws ProductOperationException, FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);
        product.setProductId(1L);
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("测试的商品");
        product.setProductDesc("测试的商品");

        File thumbnailFile = new File("D:\\project\\imooc_144\\o2o\\src\\main\\resources\\h2.png");
        InputStream inputStream = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), inputStream);
        File productImg1 = new File("D:\\project\\imooc_144\\o2o\\src\\main\\resources\\h1.png");
        FileInputStream inputStream1 = new FileInputStream(productImg1);
        File productImg2 = new File("D:\\project\\imooc_144\\o2o\\src\\main\\resources\\h2.png");
        FileInputStream inputStream2 = new FileInputStream(productImg1);

        List productImgList = new ArrayList<ImageHolder>();
        productImgList.add(new ImageHolder(productImg1.getName(), inputStream1));
        productImgList.add(new ImageHolder(productImg2.getName(), inputStream2));

        ProductExecution productExecution = productService.modifyProduct(product, thumbnail, productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(), productExecution.getState());
    }
}
