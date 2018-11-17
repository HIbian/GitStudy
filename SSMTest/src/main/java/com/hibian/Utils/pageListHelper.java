package com.hibian.Utils;

public class pageListHelper {
    //获取页码列表
    public static int[] getPageIndexList(int pageIndex,int pageSize,int totalCount) {
        int maxPage = totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize+1);
        if (maxPage<=10) {
            int[] pageIndexList = new int[maxPage];
            for (int i = 0; i <maxPage; i++) {
                pageIndexList[i] = i+1;
            }
            return pageIndexList;
        }
        int[] pageindexList = new int[10];
        if (pageIndex<=5) {
            for (int i = 0; i < pageindexList.length; i++) {
                pageindexList[i] = i+1;
            }
            return pageindexList;
        }
        if (maxPage-pageIndex<=5) {
            for (int i = 0; i < pageindexList.length; i++) {
                pageindexList[i] = maxPage-pageindexList.length+1+i;
            }
            return pageindexList;
        }
        for (int i = 0; i < pageindexList.length; i++) {
            pageindexList[i] = pageIndex-5+i;
        }
        return pageindexList;
    }
}
