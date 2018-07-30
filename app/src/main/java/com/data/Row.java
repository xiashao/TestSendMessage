package com.data;

import android.text.TextUtils;

public class Row {
    public String rowStr = "";
    public Row() {
    }
    public Row(String str) {
        rowStr = dealRowStr(str);
    }
    private String dealRowStr(String rowStr) {
        if (TextUtils.isEmpty(rowStr)) return rowStr;
        String temp = rowStr;
        if (temp.endsWith(Separator.semicolon)) {
            temp = temp.replace(Separator.semicolon, "");
        }
        if (!temp.endsWith(Separator.Comma)) {
            temp = temp + Separator.Comma;
        }
        return temp;
    }
    public void setVal(String colName, String value) {
        int i1 = rowStr.indexOf(colName + Separator.Sign);
        if (i1 == -1) {
            if (value != null) {
                rowStr = dealRowStr(rowStr);
                rowStr += colName + Separator.Sign + value + Separator.Comma;
            }
        } else {
            int i2 = rowStr.indexOf(Separator.Comma, i1) == -1 ? rowStr.length() : rowStr.indexOf(Separator.Comma, i1);
            String s = rowStr.substring(i1, i2);
            String str = "";
            if (value != null) str = colName + Separator.Sign + value;
            rowStr = rowStr.replace(s, str);
        }
    }
    public String getVal(String colName) {
        int i1 = rowStr.indexOf(colName + Separator.Sign);
        if (i1 == -1)
            return "";
        int i2 = -1;
        if (rowStr.indexOf(Separator.Comma, i1) != -1) {
            i2 = rowStr.indexOf(Separator.Comma, i1);
        } else if (rowStr.indexOf(Separator.semicolon, i1) != -1) {
            i2 = rowStr.indexOf(Separator.semicolon, i1);
        } else {
            i2 = rowStr.length();
        }
        String s = rowStr.substring(i1, i2).replace(colName + Separator.Sign, "");
        return s;
    }
}
