package controller;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Matthew on 10.09.2016.
 */
public class IndexHtmlGeneratorTest {
        private static IndexHtmlGenerator indexHtmlGenerator;

    @BeforeClass
    public static void setup() {
        indexHtmlGenerator = new IndexHtmlGenerator();

    }
    @Test
    public void songWithoutWhiteSpaces() throws Exception {
        comp("abcdrrrr", "abcd rrrr");
        comp("abcdrrrr", "ab  c  d rrrr");
        comp("abcdrrrr", "abc d rr  rr");
        comp("Aabcdrrrr", "   Aab cd  rr rr");
        comp("abcdrrrr", "abcdrrrr");
        comp("zazolcgeslajazn", "zażółć gęślą jaźń");
        comp("ZAZOLCGESLAJAZN", "ZAŻÓŁĆ GĘŚLĄ JAŹŃ");
        comp("aaaa", "ąąąą");

    }

    private void comp(String expected, String testing) {
        Assert.assertEquals(expected, indexHtmlGenerator.songTitleToFileName(testing));
    }

}