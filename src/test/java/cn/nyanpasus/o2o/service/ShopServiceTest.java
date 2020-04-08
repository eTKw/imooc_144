package cn.nyanpasus.o2o.service;

import cn.nyanpasus.o2o.BaseTest;
import cn.nyanpasus.o2o.dto.ImageHolder;
import cn.nyanpasus.o2o.dto.ShopExecution;
import cn.nyanpasus.o2o.entity.Area;
import cn.nyanpasus.o2o.entity.PersonInfo;
import cn.nyanpasus.o2o.entity.Shop;
import cn.nyanpasus.o2o.entity.ShopCategory;
import cn.nyanpasus.o2o.enums.ShopStateEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() throws FileNotFoundException {
        System.out.println("中文测试");
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺4");
        shop.setShopDesc("测试3");
        shop.setShopAddr("测试3");
        shop.setPhone("测试3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("D:\\project\\imooc_144\\o2o\\src\\main\\resources\\h2.png");
        FileInputStream fileInputStream = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder(shopImg.getName(), fileInputStream);
        ShopExecution shopExecution = shopService.addShop(shop, imageHolder);
        assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());
    }

    @Test
    public void testModifyShop() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("测试过的店铺");
        File shopImg = new File("D:\\project\\imooc_144\\o2o\\src\\main\\resources\\h2.png");
        FileInputStream inputStream = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder(shopImg.getName(), inputStream);
        ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder);
        //存在延迟
        System.out.println("图片地址 " + shopExecution.getShop().getShopImg());
    }

    @Test
    public void testGetShopList() {
        Shop shop = new Shop();
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(2L);
        shop.setShopCategory(shopCategory);
        ShopExecution shopExecution = shopService.getShopList(shop, 2, 2);
        System.out.println(shopExecution.getShopList().size());
        System.out.println(shopExecution.getCount());
    }
}
