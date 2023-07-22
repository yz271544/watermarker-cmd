package com.bonc.watermark.cmd.util;

import com.bonc.watermark.cmd.options.FontAndLocate;
import org.opencv.core.Point;
import org.opencv.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.opencv.core.Core.flip;
import static org.opencv.imgproc.Imgproc.FONT_HERSHEY_COMPLEX;
import static org.opencv.imgproc.Imgproc.putText;

public class WaterMarkUtil {

    private static final Logger log = LoggerFactory.getLogger(WaterMarkUtil.class);


    public static void addWatermarkForLight(BufferedImage src, String watermark, Map<String, String> otherArgs) {

        FontAndLocate fontAndLocate = new FontAndLocate(otherArgs);
        int w = src.getWidth();
        int h = src.getHeight();
        Graphics2D graphics = src.createGraphics();
        // 抗锯齿
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        //Font font = new Font("宋体", Font.PLAIN, 80);
        Font font = new Font(fontAndLocate.getFontFamilyName(), fontAndLocate.getFontStyle(), fontAndLocate.getFontSize());
        graphics.setFont(font);
        FontMetrics fm = graphics.getFontMetrics(font);
        int ascent = fm.getAscent();
        /*int x = 15;
        int y = ascent + 10;*/
        int x = fontAndLocate.getLocateX();
        int y = ascent + fontAndLocate.getLocateY();
        log.info("image water add x:" + x + " y:" + y);
//        graphics.setColor(new Color(255, 255, 255, 100));
//        graphics.drawString(watermark, x - 2, y - 2);

        graphics.setColor(fontAndLocate.getFontColor());
        graphics.drawString(watermark, x, y);

//        graphics.setColor(new Color(255, 255, 255, 100));
//        graphics.drawString(watermark, x + 2, y + 2);
    }

    /**
     * add water mark for Mat
     * @param image
     * @param watermark
     * @return
     */
    public static Mat addWatermarkForDark(Mat image, String watermark) {
        // 分离通道， allPlanes[0]
        List<Mat> allPlanes = new ArrayList<>();
        Core.split(image, allPlanes);  //RGB通道分离
        image = allPlanes.get(0);
        // 必须转换为float 32的格式要求，必须
        image.convertTo(image, CvType.CV_32F);


        List<Mat> planes = new ArrayList<>();
        planes.add(image);
        planes.add(Mat.zeros(image.size(), CvType.CV_32F));

        Mat complexImage = new Mat();
        Core.merge(planes, complexImage);
        // 2.进行dft变换
        Core.dft(complexImage, complexImage);


        // 3.添加文本水印
        Scalar scalar = new Scalar(0, 0, 0);
        Point point = new Point(40, 40);
        putText(complexImage, watermark, point, FONT_HERSHEY_COMPLEX, 1.5, scalar,3, 20);
        flip(complexImage, complexImage, -1);
        putText(complexImage, watermark, point, FONT_HERSHEY_COMPLEX, 1.5, scalar,3, 20);
        flip(complexImage, complexImage, -1);
        // 3.idft逆变换成图片
        Mat invDFT = new Mat();
        Core.idft(complexImage, invDFT, Core.DFT_SCALE | Core.DFT_REAL_OUTPUT, 0);
        // 将得到的invDFT类型转换
        invDFT.convertTo(invDFT, CvType.CV_8U);
        // 将转换后的放入数组
        allPlanes.set(0, invDFT);
        // 将allPlanes数组合并成一个多通道的mat
        Core.merge(allPlanes, invDFT);
        return invDFT;
    }

    /**
     * extract water mark from Mat
     * @param image
     * @return
     */
    public static Mat extractWatermarkFromMat(Mat image) {
        // 2.分离通道， allPlanes[0]
        List<Mat> allPlanes = new ArrayList<>();
        Core.split(image, allPlanes);
        image = allPlanes.get(0);
        image.convertTo(image, CvType.CV_32F);
        // 3.Mat 数组，第一个为扩展后的图像，一个为空图像，
        List<Mat> planes = new ArrayList<>();
        planes.add(image);
        planes.add(Mat.zeros(image.size(), CvType.CV_32F));
        // 将planes数组组合合并成一个多通道的数组complexImage
        Mat complexImage = new Mat();
        Core.merge(planes, complexImage);
        // 4.进行dft
        Core.dft(complexImage, complexImage);
        // 5、进行对数尺度（logarithmic scale）缩放
        List<Mat> newPlanes = new ArrayList<>();
        Mat mag = new Mat();
        Core.split(complexImage, newPlanes);
        Core.magnitude(newPlanes.get(0), newPlanes.get(1), mag);
        Core.add(Mat.ones(mag.size(), CvType.CV_32F), mag, mag);
        Core.log(mag, mag);

        // 6、剪切和重分布图像限
        mag = mag.submat(new Rect(0, 0, mag.cols() & -2, mag.rows() & -2));
        int cx = mag.cols() / 2;
        int cy = mag.rows() / 2;

        Mat q0 = new Mat(mag, new Rect(0, 0, cx, cy));
        Mat q1 = new Mat(mag, new Rect(cx, 0, cx, cy));
        Mat q2 = new Mat(mag, new Rect(0, cy, cx, cy));
        Mat q3 = new Mat(mag, new Rect(cx, cy, cx, cy));
        Mat tmp = new Mat();
        q0.copyTo(tmp);
        q3.copyTo(q0);
        tmp.copyTo(q3);
        q1.copyTo(tmp);
        q2.copyTo(q1);
        tmp.copyTo(q2);
        // 7、归一化，用0到1之间的浮点值将矩阵变换为可视的图像格式
        mag.convertTo(mag, CvType.CV_8UC1);
        Core.normalize(mag, mag, 0, 255, Core.NORM_MINMAX, CvType.CV_8UC1);

        return mag;
    }

}
