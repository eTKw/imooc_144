package cn.nyanpasus.o2o.service;

import cn.nyanpasus.o2o.dto.ShopExecution;
import cn.nyanpasus.o2o.entity.Shop;
import cn.nyanpasus.o2o.exception.ShopOperationException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImg, String fileName) throws ShopOperationException;

    Shop getByShopId(long shopId);

    ShopExecution modifyShop(Shop shop, InputStream shopImg, String fileName) throws ShopOperationException;
}
