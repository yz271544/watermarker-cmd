# watermarker-cmd


## example
```shell
## add watermark for xlsx
java -jar .\watermarker-cmd-1.0.jar --pool.size=2 --darkType=solid --watermark=官方水印 --inputFileFullPath=D:\iProject\javapath\things-watermarker\doc\excel\test.xlsx --outputFileFullPath=D:\iProject\javapath\th
ings-watermarker\doc\excel\test-water.xlsx

## add watermark for jpg in windows cmd ( not powershell )
java -Dfile.encoding=UTF-8 -jar .\watermarker-cmd-1.2.jar --waterList=D:\iProject\javapath\watermarker-cmd\doc\jpg\input\input.txt  --template=D:\iProject\javapath\watermarker-cmd\doc\jpg\temp.jpg --outputPath=D:\iProject\javapath\watermarker-cmd\doc\jpg\batch --darkType=light -DfontFamily=华文行楷 -DfontStyle=bold -DfontSize=80 -DfontColor=black -DstartX=700 -DstartY=1030


```

## explanation
- `pool.size` The number of concurrent parsing excel files, each file is only processed by a single thread, this parameter can be used to improve efficiency when there are multiple files.
- `darkType`
  - `dark` hidden watermark in the header for xlsx, use normal view.
  - `light` explicit watermark in the header for xlsx, use layout view.
  - `solid` solidified inserted picture is in the xlsx file.
- `watermark` show watermark in the picture.
- `inputFileFullPath` input file full path for single file.
- `outputFileFullPath` output file full path for single file.
- `inputPath` input files path for multiple file.
- `outputPath` output files path for multiple file.


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


