package cn.nyanpasus.o2o.dao;

import cn.nyanpasus.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("pageSize") int pageSize);

    int insertProduct(Product product);

    Product queryProductById(long productId);

    int updateProduct(Product product);
}
