package cn.nyanpasus.o2o.util;

public class PathUtil {
    private static String separator = System.getProperty("file.separator");
    public static String getImgBasePath() {

        String os = System.getProperty("os.name");
        String basePath;
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:\\project\\imooc_144\\image\\";
        } else {
            basePath = "/home/master/image/";
        }

        basePath = basePath.replace("/", separator);
        return basePath;
    }

    public static String getShopImagePath(long shopId) {
        String imagePath = "/upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);
    }
}
