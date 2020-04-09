package cn.nyanpasus.o2o.service;

import cn.nyanpasus.o2o.dto.ImageHolder;
import cn.nyanpasus.o2o.dto.ProductExecution;
import cn.nyanpasus.o2o.entity.Product;
import cn.nyanpasus.o2o.exception.ProductOperationException;

import java.io.InputStream;
import java.util.List;

public interface ProductService {
    ProductExecution addProduct(Product product,
                                ImageHolder thumbnail,
//                                String thumbnailName,
                                List<ImageHolder> productImgList) throws ProductOperationException;

    Product getProductById(long productId);

    ProductExecution modifyProduct(Product product,
                                   ImageHolder thumbnail,
                                   List<ImageHolder> productImgList) throws ProductOperationException;

    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);
}
