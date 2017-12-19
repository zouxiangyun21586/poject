package com.yr.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageCut {
    /**
     * 切图工具类
     * 
     * @param srcImageFile
     *            原图片地址
     * @param x
     *            截取时的x坐标
     * @param y
     *            截取时的y坐标
     * @param desWidth
     *            截取的宽度
     * @param desHeight
     *            截取的高度
     * @param srcWidth
     *            页面图片的宽度
     * @param srcHeight
     *            页面图片的高度
     * 
     */
    public static void imgCut(String srcImageFile, int x, int y, int destWidth, int destHeight) {
        try {
            Image img;
            ImageFilter cropFilter;
            // 读取源图像
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getWidth(); // 源图宽度
            int srcHeight = bi.getHeight(); // 源图高度

            if (srcWidth >= destWidth && srcHeight >= destHeight) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);

                int x1 = x * srcWidth / 420;
                int y1 = y * srcWidth / 420;
                int w = destWidth * srcWidth / 420;
                int h = destHeight * srcWidth / 420;

                // 四个参数分别为图像起点坐标和宽高
                // 即: CropImageFilter(int x,int y,int width,int height)
                cropFilter = new CropImageFilter(x1, y1, w, h);
                img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null); // 绘制缩小后的图
                g.dispose();
                // 输出为文件
                ImageIO.write(tag, "JPEG", new File(srcImageFile));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}