package cn.nyanpasus.o2o.util;

import jdk.nashorn.internal.ir.CallNode;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("fr.png").getPath();
    private static final SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
        File originFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(originFile);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return originFile;
    }

    public static void main(String[] args) throws Exception{
//        String basePath = ImageUtil.class.getResource("").getPath();
        System.out.println(basePath);
        Thumbnails.of(new File("D:\\project\\imooc_144\\o2o\\src\\main\\resources\\h1.png"))
                .size(200, 200)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath)),0.25f)
                .outputQuality(0.8f)
                .toFile("D:\\project\\imooc_144\\o2o\\src\\main\\resources\\h11.png");
    }

    /**
     * 处理缩略图，并返回新生成图片的相对路径
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(File thumbnail, String  targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("相对路径：" + relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("绝对路径：" + PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail)
                    .size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath)),0.25f)
                    .outputQuality(0.8f)
                    .toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 创建路径所车技的目录
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * 获取扩展名
     * @param file
     * @return
     */
    private static String getFileExtension(File file) {
        String originalFileName = file.getName();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名，毫秒+五伪随机数
     * @return
     */
    private static String getRandomFileName() {
//        获取随机的五位数
        int randomNum = r.nextInt(89999) + 10000;
        String currentTimeStr = simpleDateFormat.format(new Date());
        return currentTimeStr + randomNum;
    }


}
