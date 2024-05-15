# B站客户端缓存导出工具

个人造的Java轮子，目前在Linux下能用，日常建议用[BBDown](https://github.com/nilaoda/BBDown)，
BBDown下载不了再用官方客户端缓存之后用这里的小玩意转成MP4

## 需要的环境

以Debian系为例

1. Java：`sudo apt install default-jdk maven`
2. ffmpeg：`sudo apt install ffmpeg`

## 编译

`mvn package`编译一下`jar`包
`target`目录下面会有`bilibili-cache-converter-1.0-SNAPSHOT-jar-with-dependencies.jar`

## 使用方法

目前B站PC客户端的缓存目录是`C:\Users\[用户名]\Videos\bilibili`，里面每一个文件夹对应一个视频:

- bilibili
  - `[itemId]`
    - `[视频].m4s`
    - `[音频].m4s`
  - `[itemId]`
    - `[视频].m4s`
    - `[音频].m4s`
  - ...

1. 新建一个目录把`bilibili`目录下面要导出的视频目录复制进去，当然你也可以直接用`bilibili`目录，
这里假设新建了一个`raw`目录，复制之后目录结构和`bilibili`目录应该一样，
2. 新建一个目录用来放转换后的源文件(其实就是去掉了文件开头8字节的前导0)，假设是`converted`
3. 运行编译好的`jar`包`java -jar bilibili-cache-converter-1.0-SNAPSHOT-jar-with-dependencies.jar`，输入`raw`目录路径和`converted`目录路径，建议这三个目录这样组织，inputDir和outputDir可以直接写`raw`和`converted`

   - workspace
     - converted
     - raw
     - bilibili-cache-converter-1.0-SNAPSHOT-jar-with-dependencies.jar

4. 把`ffmpeg.sh`放到`converted`目录下面，运行这个脚本就行
