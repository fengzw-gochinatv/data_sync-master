package com.gochinatv.datasync.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shhao.
 * Date: 15-3-9
 * Time:上午10:18
 */
public class YoutubeShell {
    public static String url = "https://www.youtube.com/watch?v=%s";
   static Logger logger = LoggerFactory.getLogger(YoutubeShell.class);
    //parse youtube video real url
    public  static JSONObject parseUrl(String youtubId) {
        String result = null;
        JSONObject map=new JSONObject();
        try {
            String command = String.format(url, youtubId);
            logger.info("command=/usr/local/bin/youtube-dl -g {}",command);
            result = Shell.execCommand("/usr/local/bin/youtube-dl","-g",command);
            map.put("resourceUrl", result);
            map.put("duration",parseDuration(result));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(), e);
        }
        logger.info(map.toString());
        return map;
    }

    //parse youtube video real duation
    private static long parseDuration(String realUrl) {
        long duration = 0;
        try {
            String regex = "dur=[0-9]*(\\.[0-9]*){0,1}";
            Matcher m = Pattern.compile(regex).matcher(realUrl);
            String dst = null;
            if (m.find()) {
                dst = m.group();
            }
            duration = Long.valueOf(dst.replaceAll("(dur=)|(\\.[0-9]*)", ""));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(), e);
        }
       // logger.info("duration:" + duration);
        return duration;
    }

    public static void main(String args[]) {
        String u = "https://r7---sn-oguesnes.googlevideo.com/videoplayback?ratebypass=yes&expire=1425890594&requiressl=yes&mime=video%2Fmp4&mv=m&dur=3655.192&mt=1425868921&itag=18&ms=au&initcwndbps=3683750&fexp=904833%2C907263%2C912149%2C927622%2C929244%2C934954%2C936117%2C9405964%2C9406656%2C9406664%2C943917%2C948124%2C948703%2C950502%2C951703%2C952302%2C952612%2C952901%2C953000%2C955301%2C957201%2C959701&sparams=dur%2Cgcr%2Chightc%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Cmime%2Cmm%2Cms%2Cmv%2Cnh%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&id=o-AKhBxKx_Y9QtgUXzfvetNKcjBXY_mNHigngjzxomzUOd&ip=54.178.159.116&mm=31&pl=17&key=yt5&hightc=yes&sver=3&ipbits=0&gcr=jp&source=youtube&upn=IT9EKJdXV7w&nh=IgpwcjAyLm5ydDE5KgkxMjcuMC4wLjE&signature=1A3916E4691D98B34343A5E251A6BDF884AD8647.578C09155CE36A9AE444EDD4F33C39CC667A6EE4";
        String regex = "dur=[0-9]*(\\.[0-9]*){0,1}";
        Matcher m = Pattern.compile(regex).matcher(u);
        String dst = null;
        if (m.find()) {
            dst = m.group();
        }
        System.out.println(dst.replaceAll("(dur=)|(\\.[0-9]*)", ""));
    }
}
