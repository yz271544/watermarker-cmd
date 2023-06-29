# watermarker-cmd


## example
```shell
java -jar .\watermarker-cmd-1.0.jar --pool.size=2 --watermark=官方水印 --inputFileFullPath=D:\iProject\javapath\things-watermarker\doc\excel\test.xlsx --outputFileFullPath=D:\iProject\javapath\th
ings-watermarker\doc\excel\test-water.xlsx

```

## architect
we are register the component of file type and handler to the `com.bonc.watermark.cmd.handle.FileTypeEnum`

```java
public enum FileTypeEnum {
    XLSX("xlsx", new XlsxWaterMakerHandler()),
    XLS("xls", new XlsWaterMakerHandler()),
    PDF("pdf", new PdfWaterMakerHandler()),
    DOCX("docx", new DocxWaterMakerHandler()),
    DOC("doc", new DocWaterMakerHandler()),
    PNG("png", new PngWaterMakerHandler()),
    JPG("jpg", new JpgWaterMakerHandler()),
    MP4("mp4", new Mp4WaterMakerHandler());

    private final String typeValue;

    private final WaterMakerHandler waterMakerHandler;
}
```

yes, as you can see, we have to write an adaptation handler to process the corresponding file type;

## deal with linux zh fonts issue

1. 检查字体是否已经安装：

```shell
fc-list
fc-list : lang=zh ---检查中文字体库
```

2. 到 C:\windows\fonts 复制对应字体库，微软雅黑、宋体、黑体等，各文件后缀可能不一样，有的为ttf，有的为ttc，不影响使用

3. 创建/usr/share/fonts/chinese目录，上传刚才复制的字体库到此目录，命令：
```shell
mkdir /usr/share/fonts/chinese          # --创建文件夹
chmod -R 777 /usr/share/fonts/chinese   # --修改字体权限，使root以外的用户可以使用这些字体：使用777 赋予全部权限
```

4. 建立字体缓存：

```shell
mkfontscale //字体扩展, 建立字体索引, 若提示命令找不到, 执行 yum install mkfontscale 安装
mkfontdir //新增字体目录
fc-cache -fv //刷新缓存
```


