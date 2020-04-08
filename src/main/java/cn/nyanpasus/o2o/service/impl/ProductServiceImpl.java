package cn.nyanpasus.o2o.service.impl;

import cn.nyanpasus.o2o.dao.ProductDao;
import cn.nyanpasus.o2o.dao.ProductImgDao;
import cn.nyanpasus.o2o.dto.ImageHolder;
import cn.nyanpasus.o2o.dto.ProductExecution;
import cn.nyanpasus.o2o.entity.Product;
import cn.nyanpasus.o2o.entity.ProductImg;
import cn.nyanpasus.o2o.enums.ProductStateEnum;
import cn.nyanpasus.o2o.exception.ProductOperationException;
import cn.nyanpasus.o2o.service.ProductService;
import cn.nyanpasus.o2o.util.ImageUtil;
import cn.nyanpasus.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImgDao productImgDao;

    /**
     * 1. 处理缩略图
     * 2. 写入tb_product商品信息，获取productId
     * 3. 结合productId批量处理商品详情
     * 4. 将商品详情图列表批量插入tb_product_img
     *
     * @param product
     * @param thumbnail
     * @param productImgList
     * @return
     * @throws ProductOperationException
     */
    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(1);
            if (thumbnail != null) {
                addThumbnail(product, thumbnail);
            }
            try {
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品失败" + e.toString());
            }
            if (productImgList != null && productImgList.size() > 0) {
                addProductImgList(product, productImgList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }

    @Override
    @Transactional
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setLastEditTime(new Date());
            if (thumbnail != null) {
                Product productTemp = productDao.queryProductById(product.getProductId());
                if (productTemp.getImgAddr() != null) {
                    ImageUtil.deleteFileOrPath(productTemp.getImgAddr());
                }
                addThumbnail(product, thumbnail);
            }

            if (productImgList != null && productImgList.size() > 0) {
                deleteProductImgList(product.getProductId());
                addProductImgList(product, productImgList);
            }
            try {
                int effectedNum = productDao.updateProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("更新商品详情图片失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new ProductOperationException("更新商品详情图片失败" + e.toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    private void deleteProductImgList(Long productId) {
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        for (ProductImg productImg : productImgList) {
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }

        productImgDao.deleteProductImgByProductId(productId);
    }

    private void addThumbnail(Product product, ImageHolder thumbnail) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(thumbnailAddr);
    }

    private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        for (ImageHolder imageHolder : productImgHolderList) {
            String imgAddr = ImageUtil.generateNormalImg(imageHolder, dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        if (productImgList.size() > 0) {
            try {
                int i = productImgDao.batchInsertProductImg(productImgList);
                if (i <= 0) {
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情图片失败" + e.toString());
            }
        }
    }
}
