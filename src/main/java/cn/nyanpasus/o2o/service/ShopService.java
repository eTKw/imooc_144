package cn.nyanpasus.o2o.service;

import cn.nyanpasus.o2o.dto.ShopExecution;
import cn.nyanpasus.o2o.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImg, String fileName);
}
