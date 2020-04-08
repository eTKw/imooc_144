package cn.nyanpasus.o2o.service;

import cn.nyanpasus.o2o.dto.ProductCategoryExecution;
import cn.nyanpasus.o2o.entity.ProductCategory;
import cn.nyanpasus.o2o.exception.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getProductCategoryList(long shopId);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException;
}
