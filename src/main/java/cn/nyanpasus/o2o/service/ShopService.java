package cn.nyanpasus.o2o.service;

import cn.nyanpasus.o2o.dto.ImageHolder;
import cn.nyanpasus.o2o.dto.ShopExecution;
import cn.nyanpasus.o2o.entity.Shop;
import cn.nyanpasus.o2o.exception.ShopOperationException;

import java.awt.*;
import java.io.File;
import java.io.InputStream;

public interface ShopService {
//    ShopExecution addShop(Shop shop, InputStream shopImg, String fileName) throws ShopOperationException;
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

    Shop getByShopId(long shopId);

//    ShopExecution modifyShop(Shop shop, InputStream shopImg, String fileName) throws ShopOperationException;
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

    /**
     * 根据shopcondition 分页返回相应店铺列表
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
}
