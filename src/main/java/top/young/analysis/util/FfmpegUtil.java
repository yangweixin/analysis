package top.young.analysis.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Method: top.young.analysis.util
 * @Desc:
 * @Author: Yang Weixin
 * @Date: 2018/1/5 18:21
 */
public class FfmpegUtil {

    private static final String FFMPEG_EXE_PATH = "G:\\ffmpeg.exe";

    public static boolean convertMP42GIF(String mp4Path) throws IOException {
        int exitValue = 0;
        InputStream ins = null;
        BufferedReader reader = null;
        String gifPath = mp4Path.replace(".mp4",".gif");
        String cmdString = FFMPEG_EXE_PATH +" -i "+ mp4Path +" "+ gifPath +" -y -f git -vframes 10 -r 10 -s 272x480";

        /*
            cmd /c dir 是执行完dir命令后关闭命令窗口
            cmd /k dir 是执行完dir命令后不关闭命令窗口
            cmd /c start dir  会打开一个新窗口后执行dir命令，原窗口会关闭
            cmd /k start dir  会打开一个新窗口后执行dir命令，原窗口不会关闭
            cmd /?  查看帮助信息
        */
        String[] cmd = new String[] { "cmd.exe", "/C", cmdString };  // 命令
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            ins = process.getInputStream();  // 获取执行cmd命令后的信息
            reader = new BufferedReader(new InputStreamReader(ins));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // 输出
            }
            exitValue = process.waitFor();
            System.out.println("返回值：" + exitValue);
            process.getOutputStream().close();  // 不要忘记了一定要关
        } catch (Exception e) {
            e.printStackTrace();
            exitValue = 1;
        } finally {
            if(reader != null)
                reader.close();
            if(ins != null)
                ins.close();
            return exitValue == 0 ? true : false;
        }
    }

    public static void main(String[] args) throws IOException {
        String mp4Path = "E:\\ffmpeg-20171115-ff8f40a-win64-static\\bin\\1.mp4";
        System.out.println(convertMP42GIF(mp4Path));
        mp4Path = "E:\\ffmpeg-20171115-ff8f40a-win64-static\\bin\\2.mp4";
        System.out.println(convertMP42GIF(mp4Path));
    }

}
