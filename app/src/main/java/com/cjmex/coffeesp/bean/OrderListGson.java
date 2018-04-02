package com.cjmex.coffeesp.bean;

import java.util.List;

/**
 * @author ding
 * @date 2018/4/2
 */

public class OrderListGson extends BaseGson {


    /**
     * list : [{"createDate":1522140351000,"currency":"RMB","depositRate":0,"firm":"天平","firmId":1,"id":2,"machineCode":"E630I5XK","orderDetailCount":1,"orderNo":"20180312160044288240","orgAmount":0.01,"payDate":1520841659000,"payType":"1","realAmount":0.01},{"createDate":1522140406000,"currency":"RMB","depositRate":0,"firm":"天平","firmId":1,"id":3,"machineCode":"E630I5XK","orderDetailCount":1,"orderNo":"20180312164844782241","orgAmount":0.01,"payDate":1520844551000,"payType":"1","realAmount":0.01},{"createDate":1522140434000,"currency":"RMB","depositRate":0,"firm":"天平","firmId":1,"id":4,"machineCode":"E630I5XK","orderDetailCount":1,"orderNo":"20180312165215695242","orgAmount":0.01,"payDate":1520844749000,"payType":"1","realAmount":0.01}]
     * pageSize : 6
     * total : 3
     */

    private String pageSize;
    private int total;
    private List<OrderList> list;

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

    public List<OrderList> getList() {
        return list;
    }

    public void setList(List<OrderList> list) {
        this.list = list;
    }
}
