package com.daily.tool;

import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * @ClassName WordConvertTools
 * @Description word convert tools such html,text so on
 * https://github.com/mwilliamson/java-mammoth
 * @Author metropolis-long
 * @Date 2020/10/24 0:07
 * @VERSION 1.0.0
 */
public class WordConvertTools {
    /**
     * word convert to html.
     * @param path file full path
     * @return if convert successfully then return html string data
     */
    public static ResultBody<String> wordToHtml(final String path) {
        File file = new File(path);
        if (!file.exists()||path.indexOf(".docx")==-1){
            return new ResultBody(ResultCodeMsg.NO_SUCH_FILE.getCode(),ResultCodeMsg.NO_SUCH_FILE.getMsg());
        }
        DocumentConverter converter = new DocumentConverter();
        Result<String> result = null;
        try {
            result = converter.convertToHtml(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String html = result.getValue(); // The generated HTML
        Set<String> warnings = result.getWarnings(); // Any warnings during conversion
        return new ResultBody(html);
    }

    /**
     * word convert to html.
     * @param file word file
     * @return if convert successfully then return html string data
     */
    public static ResultBody<String> wordToHtml(final File file) {
        if (!file.exists()){
            return new ResultBody(ResultCodeMsg.NO_SUCH_FILE.getCode(),ResultCodeMsg.NO_SUCH_FILE.getMsg());
        }
        DocumentConverter converter = new DocumentConverter();
        Result<String> result = null;
        try {
            result = converter.convertToHtml(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String html = result.getValue(); // The generated HTML
        Set<String> warnings = result.getWarnings(); // Any warnings during conversion
        return new ResultBody(html);
    }
    /**
     * word convert to text.
     * @param path file full path
     * @return if convert successfully then return text string data
     */
    public static ResultBody<String> wordToText(final String path) {
        File file = new File(path);
        if (!file.exists()||path.indexOf(".docx")==-1){
            return new ResultBody(ResultCodeMsg.NO_SUCH_FILE.getCode(),ResultCodeMsg.NO_SUCH_FILE.getMsg());
        }
        DocumentConverter converter = new DocumentConverter();
        Result<String> result = null;
        try {
            result = converter.extractRawText(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String html = result.getValue(); // The generated HTML
        Set<String> warnings = result.getWarnings(); // Any warnings during conversion
        return new ResultBody(html);
    }

    /**
     * word convert to text.
     * @param file word file
     * @return if convert successfully then return text string data
     */
    public static ResultBody<String> wordToText(final File file) {
        if (!file.exists()){
            return new ResultBody(ResultCodeMsg.NO_SUCH_FILE.getCode(),ResultCodeMsg.NO_SUCH_FILE.getMsg());
        }
        DocumentConverter converter = new DocumentConverter();
        Result<String> result = null;
        try {
            result = converter.extractRawText(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String html = result.getValue(); // The generated HTML
        Set<String> warnings = result.getWarnings(); // Any warnings during conversion
        return new ResultBody(html);
    }
}
