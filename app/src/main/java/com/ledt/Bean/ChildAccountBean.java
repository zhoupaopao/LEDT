package com.ledt.Bean;

import java.util.List;

/**
 * Created by 13126 on 2017/9/6.
 */

public class ChildAccountBean extends BaseApiResponse {

    private String UserID;
    private String CustomerName;
    private int TempQty;
    private int AleaQty;
    private int CarQty;

    private List<ChildAccountBean> children;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public int getTempQty() {
        return TempQty;
    }

    public void setTempQty(int tempQty) {
        TempQty = tempQty;
    }

    public int getAleaQty() {
        return AleaQty;
    }

    public void setAleaQty(int aleaQty) {
        AleaQty = aleaQty;
    }

    public int getCarQty() {
        return CarQty;
    }

    public void setCarQty(int carQty) {
        CarQty = carQty;
    }

    public List<ChildAccountBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildAccountBean> children) {
        this.children = children;
    }
}
