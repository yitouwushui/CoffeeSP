package com.cjmex.coffeesp.bean;

import java.util.List;

/**
 * Created by ding on 2018/4/2.
 */

public class MachineGson extends BaseGson {


    /**
     * list : [{"batchId":1,"code":"1234","createDate":1521043199000,"firm":"天平","firmId":1,"id":1,"position":"xxxx","positionX":1,"positionY":1,"status":"1"},{"batchId":11,"code":"H220I300","createDate":1522139050000,"firm":"天平","firmId":1,"id":14,"position":"楼外楼","positionX":76.23,"positionY":57.46,"status":"1"},{"batchId":11,"code":"H230I7N6","createDate":1522139050000,"firm":"天平","firmId":1,"id":15,"positionX":0,"positionY":0,"status":"1"},{"batchId":11,"code":"9521V210A","createDate":1522139050000,"firm":"天平","firmId":1,"id":16,"positionX":0,"positionY":0,"status":"1"},{"batchId":11,"code":"G530I0F0","createDate":1522139050000,"firm":"天平","firmId":1,"id":17,"position":"小魔仙小黑屋","positionX":123.23,"positionY":13.34,"status":"1"},{"batchId":11,"code":"E630I5XK","createDate":1522139050000,"firm":"天平","firmId":1,"id":18,"positionX":0,"positionY":0,"status":"1"},{"batchId":11,"code":"E630I3OM","createDate":1522139050000,"firm":"天平","firmId":1,"id":19,"positionX":0,"positionY":0,"status":"1"}]
     * pageSize : 6
     * responseCode : 0
     * responseMsg : 查询数据成功!
     * total : 7
     */

    private String pageSize;
    private int total;
    private List<Machine> list;

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Machine> getList() {
        return list;
    }

    public void setList(List<Machine> list) {
        this.list = list;
    }


}
