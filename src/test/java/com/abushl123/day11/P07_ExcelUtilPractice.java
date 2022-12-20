package com.abushl123.day11;

import com.abushl123.utilities.utils.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class P07_ExcelUtilPractice {

    @Test
    public void test1() {

        ExcelUtil excelUtil = ExcelUtil.excelUtil("src/test/resources/Library.xlsx", "Library1-short");

        List<Map<String, String>> allData = excelUtil.getDataList();

        for (Map<String, String> allDatum : allData) {
            System.out.println(allData);
        }

    }
}
