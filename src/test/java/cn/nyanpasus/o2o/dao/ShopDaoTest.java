package cn.nyanpasus.o2o.dao;

import cn.nyanpasus.o2o.BaseTest;
import cn.nyanpasus.o2o.entity.Area;
import cn.nyanpasus.o2o.entity.PersonInfo;
import cn.nyanpasus.o2o.entity.Shop;
import cn.nyanpasus.o2o.entity.ShopCategory;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopDaoTest extends BaseTest {

    @Autowired
    private ShopDao shopDao;

    @Test
    @Ignore
    public void testInsertShop() {
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
        shop.setShopName("测试店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("测试数据");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    @Ignore
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("测试描述");
        shop.setShopAddr("测试地址");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQueryByShopId() {
        long shopId = 1;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println("areaId" + shop.getArea().getAreaId());
        System.out.println("areaName" + shop.getArea().getAreaName());
    }

    @Test
    public void testQueryShopListAndCount() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shop.setOwner(owner);
        List<Shop> shopList = shopDao.queryShopList(shop, 0, 5);
        int i = shopDao.queryShopCount(shop);
        System.out.println(shopList.size());
        System.out.println(i);
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(3L);
        shop.setShopCategory(shopCategory);
        shopList = shopDao.queryShopList(shop, 0, 2);
        i = shopDao.queryShopCount(shop);
        System.out.println(shopList.size());
        System.out.println(i);
    }
}
