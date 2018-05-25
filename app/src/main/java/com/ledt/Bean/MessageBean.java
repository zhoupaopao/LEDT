package com.ledt.Bean;

/**
 * Created by 13126 on 2017/9/8.
 */

public class MessageBean {
    public static class messageBean{
        /*       private String ID;*/
        private String UserID="";
        private String CustomerName="";
        private int TempQty=0;
        private int AleaQty=0;

//        public String getID() {
//            return ID;
//        }
//
//        public void setID(String ID) {
//            this.ID = ID;
//        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerName(String CustomerName) {
            this.CustomerName = CustomerName;
        }

        public int getTempQty() {
            return TempQty;
        }

        public void setTempQty(int TempQty) {
            this.TempQty = TempQty;
        }

        public int getAleaQty() {
            return AleaQty;
        }

        public void setAleaQty(int AleaQty) {
            this.AleaQty = AleaQty;
        }
    }
}
