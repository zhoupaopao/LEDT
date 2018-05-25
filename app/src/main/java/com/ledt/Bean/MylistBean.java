package com.ledt.Bean;

import java.util.List;

/**
 * Created by Lenovo on 2017/11/23.
 */

public class MylistBean extends BaseApiResponse {
    /**
     * allCount : 93
     * onLineCount : 43
     * offLineCount : 50
     * allBindCount : 40
     * onLineBindCount : 26
     * offLineBindCount : 14
     * carCount : 0
     * searchCount : 93
     * DataList : [{"PledgerID":"13a6fac23a5c48b1b57e33b5342d4d76","PledgerName":"沙阳阳测试","CarInfoID":"43c92be791264db7b4bb925784202e62","GroupID":0,"GroupName":"默认组","CarNumber":"123213","CarBrandID":"","CarBrandName":"","CarBrandImgUrl":"","CarRemark":"","BindMonth":"2016.05.03","CarDeviceID":"370fe0e11f854b0d8b0f1f77c63e053d","ValidFrom":"/Date(1453651200000)/","ValidEnd":"/Date(1642953600000)/","SimNum":"","TrdTime":"/Date(-62135596800000)/","DeviceID":"693502000003902","Model":"久劲 OBD2号","DeviceMode":1,"InternalNum":"T6112","EqpName":null,"IMEI":"693502000003902","Lng":120.45967864990234,"Lat":33.194305419921875,"BLng":120.47091404990235,"BLat":33.198887429921875,"Address":"","Speed":0,"Direction":null,"PositionTime":"2016-07-15 20:29:43","Type":4000,"BL":99,"BS":"正常","IsOnline":true,"Status":0,"Status2":1,"StatusInfo":"静止(7分钟)","IsBindCar":true,"YdayMileage":7,"IsTrack":false,"obdField":{"mileage":0,"acc":0,"templh":0,"averagelh":0,"totaloil":0,"enginec":86,"carc":0,"pipekpa":0,"voltage":13,"ecm":1,"abs":0,"srs":0,"errenginoil":0,"errtirepress":0,"errmaintain":0,"rpm":0},"HighFrequency":true,"Result":0,"ErrorMsg":null},{"PledgerID":"b5d86409f96a4096beaed5ca0d6f74b0","PledgerName":"11","CarInfoID":"ad8be1daced9469892b5b6c001625286","GroupID":0,"GroupName":"默认组","CarNumber":"212","CarBrandID":"","CarBrandName":"","CarBrandImgUrl":"","CarRemark":"","BindMonth":"2016.06.06","CarDeviceID":"da59e9f971a4416fb7e92f3ea24c3363","ValidFrom":"/Date(1453824000000)/","ValidEnd":"/Date(1737820800000)/","SimNum":"","TrdTime":"/Date(-62135596800000)/","DeviceID":"693502000004603","Model":"久劲 OBD2号","DeviceMode":1,"InternalNum":"T6119","EqpName":null,"IMEI":"693502000004603","Lng":121.44872283935547,"Lat":31.22505760192871,"BLng":121.45976543935546,"BLat":31.22955042192871,"Address":"","Speed":37,"Direction":null,"PositionTime":"2016-07-15 20:29:59","Type":4000,"BL":99,"BS":"正常","IsOnline":true,"Status":0,"Status2":1,"StatusInfo":"行驶,速度:37km/h","IsBindCar":true,"YdayMileage":49,"IsTrack":false,"obdField":{"mileage":0,"acc":1,"templh":0,"averagelh":0,"totaloil":100,"enginec":90,"carc":0,"pipekpa":0,"voltage":13,"ecm":1,"abs":0,"srs":0,"errenginoil":0,"errtirepress":0,"errmaintain":0,"rpm":806},"HighFrequency":true,"Result":0,"ErrorMsg":null}]
     */
    private int allCount;
    private int onLineCount;
    private int offLineCount;
    private int allBindCount;
    private int onLineBindCount;
    private int offLineBindCount;
    private int carCount;
    private int searchCount;
    /**
     * PledgerID : 13a6fac23a5c48b1b57e33b5342d4d76
     * PledgerName : 沙阳阳测试
     * CarInfoID : 43c92be791264db7b4bb925784202e62
     * GroupID : 0
     * GroupName : 默认组
     * CarNumber : 123213
     * CarBrandID :
     * CarBrandName :
     * CarBrandImgUrl :
     * CarRemark :
     * BindMonth : 2016.05.03
     * CarDeviceID : 370fe0e11f854b0d8b0f1f77c63e053d
     * ValidFrom : /Date(1453651200000)/
     * ValidEnd : /Date(1642953600000)/
     * SimNum :
     * TrdTime : /Date(-62135596800000)/
     * DeviceID : 693502000003902
     * Model : 久劲 OBD2号
     * DeviceMode : 1
     * InternalNum : T6112
     * EqpName : null
     * IMEI : 693502000003902
     * Lng : 120.45967864990234
     * Lat : 33.194305419921875
     * BLng : 120.47091404990235
     * BLat : 33.198887429921875
     * Address :
     * Speed : 0
     * Direction : null
     * PositionTime : 2016-07-15 20:29:43
     * Type : 4000
     * BL : 99
     * BS : 正常
     * IsOnline : true
     * Status : 0
     * Status2 : 1
     * StatusInfo : 静止(7分钟)
     * IsBindCar : true
     * YdayMileage : 7
     * IsTrack : false
     * obdField : {"mileage":0,"acc":0,"templh":0,"averagelh":0,"totaloil":0,"enginec":86,"carc":0,"pipekpa":0,"voltage":13,"ecm":1,"abs":0,"srs":0,"errenginoil":0,"errtirepress":0,"errmaintain":0,"rpm":0}
     * HighFrequency : true
     * Result : 0
     * ErrorMsg : null
     */
    private List<carbean> DataList;
    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public int getOnLineCount() {
        return onLineCount;
    }

    public void setOnLineCount(int onLineCount) {
        this.onLineCount = onLineCount;
    }

    public int getOffLineCount() {
        return offLineCount;
    }

    public void setOffLineCount(int offLineCount) {
        this.offLineCount = offLineCount;
    }

    public int getAllBindCount() {
        return allBindCount;
    }

    public void setAllBindCount(int allBindCount) {
        this.allBindCount = allBindCount;
    }

    public int getOnLineBindCount() {
        return onLineBindCount;
    }

    public void setOnLineBindCount(int onLineBindCount) {
        this.onLineBindCount = onLineBindCount;
    }

    public int getOffLineBindCount() {
        return offLineBindCount;
    }

    public void setOffLineBindCount(int offLineBindCount) {
        this.offLineBindCount = offLineBindCount;
    }

    public int getCarCount() {
        return carCount;
    }

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }

    public List<carbean> getDataList() {
        return DataList;
    }

    public void setDataList(List<carbean> dataList) {
        DataList = dataList;
    }




    public static class carbean {
        private String PledgerID;
        private String PledgerName = "暂无";
        private String CarInfoID;
        private int GroupID;
        private String GroupName = "默认";
        private String CarNumber;
        private String CarBrandID;
        private String CarBrandName;
        private String CarBrandImgUrl;
        private String CarRemark;
        private String BindMonth;
        private String CarDeviceID;
        private String ValidFrom;
        private String ValidEnd;
        private String SimNum;
        private String TrdTime;
        private String DeviceID;
        private String Model;
        private int DeviceMode;
        private String InternalNum;
        private Object EqpName;
        private String IMEI;
        private double Lng;
        private double Lat;
        private double BLng;
        private double BLat;
        private String Address = "暂无";
        private int Speed;
        private Object Direction;
        private String PositionTime;
        private int Type;
        private int BL;
        private String BS = "暂无";
        private boolean IsOnline;
        private int Status;
        private int Status2;
        private String StatusInfo;
        private boolean IsBindCar;
        private int YdayMileage;
        private boolean IsTrack;
        private String ProblemTypeName = "231";
        private String CarStateName;
        private ObdFieldBean obdField;
        private boolean HighFrequency;
        public String getPledgerID() {
            return PledgerID;
        }

        public void setPledgerID(String pledgerID) {
            PledgerID = pledgerID;
        }

        //        private int position=0;//车辆列表中用到 因为分组后list中的顺序改变了  用于将原来的顺序保存下来


        public String getPledgerName() {
            return PledgerName;
        }

        public void setPledgerName(String pledgerName) {
            PledgerName = pledgerName;
        }

        public String getCarInfoID() {
            return CarInfoID;
        }

        public void setCarInfoID(String carInfoID) {
            CarInfoID = carInfoID;
        }

        public int getGroupID() {
            return GroupID;
        }

        public void setGroupID(int groupID) {
            GroupID = groupID;
        }

        public String getGroupName() {
            return GroupName;
        }

        public void setGroupName(String groupName) {
            GroupName = groupName;
        }

        public String getCarNumber() {
            return CarNumber;
        }

        public void setCarNumber(String carNumber) {
            CarNumber = carNumber;
        }

        public String getCarBrandID() {
            return CarBrandID;
        }

        public void setCarBrandID(String carBrandID) {
            CarBrandID = carBrandID;
        }

        public String getCarBrandName() {
            return CarBrandName;
        }

        public void setCarBrandName(String carBrandName) {
            CarBrandName = carBrandName;
        }

        public String getCarBrandImgUrl() {
            return CarBrandImgUrl;
        }

        public void setCarBrandImgUrl(String carBrandImgUrl) {
            CarBrandImgUrl = carBrandImgUrl;
        }

        public String getCarRemark() {
            return CarRemark;
        }

        public void setCarRemark(String carRemark) {
            CarRemark = carRemark;
        }

        public String getBindMonth() {
            return BindMonth;
        }

        public void setBindMonth(String bindMonth) {
            BindMonth = bindMonth;
        }

        public String getCarDeviceID() {
            return CarDeviceID;
        }

        public void setCarDeviceID(String carDeviceID) {
            CarDeviceID = carDeviceID;
        }

        public String getValidFrom() {
            return ValidFrom;
        }

        public void setValidFrom(String validFrom) {
            ValidFrom = validFrom;
        }

        public String getValidEnd() {
            return ValidEnd;
        }

        public void setValidEnd(String validEnd) {
            ValidEnd = validEnd;
        }

        public String getSimNum() {
            return SimNum;
        }

        public void setSimNum(String simNum) {
            SimNum = simNum;
        }

        public String getTrdTime() {
            return TrdTime;
        }

        public void setTrdTime(String trdTime) {
            TrdTime = trdTime;
        }

        public String getDeviceID() {
            return DeviceID;
        }

        public void setDeviceID(String deviceID) {
            DeviceID = deviceID;
        }

        public String getModel() {
            return Model;
        }

        public void setModel(String model) {
            Model = model;
        }

        public int getDeviceMode() {
            return DeviceMode;
        }

        public void setDeviceMode(int deviceMode) {
            DeviceMode = deviceMode;
        }

        public String getInternalNum() {
            return InternalNum;
        }

        public void setInternalNum(String internalNum) {
            InternalNum = internalNum;
        }

        public Object getEqpName() {
            return EqpName;
        }

        public void setEqpName(Object eqpName) {
            EqpName = eqpName;
        }

        public String getIMEI() {
            return IMEI;
        }

        public void setIMEI(String IMEI) {
            this.IMEI = IMEI;
        }

        public double getLng() {
            return Lng;
        }

        public void setLng(double lng) {
            Lng = lng;
        }

        public double getLat() {
            return Lat;
        }

        public void setLat(double lat) {
            Lat = lat;
        }

        public double getBLng() {
            return BLng;
        }

        public void setBLng(double BLng) {
            this.BLng = BLng;
        }

        public double getBLat() {
            return BLat;
        }

        public void setBLat(double BLat) {
            this.BLat = BLat;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public int getSpeed() {
            return Speed;
        }

        public void setSpeed(int speed) {
            Speed = speed;
        }

        public Object getDirection() {
            return Direction;
        }

        public void setDirection(Object direction) {
            Direction = direction;
        }

        public String getPositionTime() {
            return PositionTime;
        }

        public void setPositionTime(String positionTime) {
            PositionTime = positionTime;
        }

        public int getType() {
            return Type;
        }

        public void setType(int type) {
            Type = type;
        }

        public int getBL() {
            return BL;
        }

        public void setBL(int BL) {
            this.BL = BL;
        }

        public String getBS() {
            return BS;
        }

        public void setBS(String BS) {
            this.BS = BS;
        }

        public boolean isOnline() {
            return IsOnline;
        }

        public void setOnline(boolean online) {
            IsOnline = online;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
            Status = status;
        }

        public int getStatus2() {
            return Status2;
        }

        public void setStatus2(int status2) {
            Status2 = status2;
        }

        public String getStatusInfo() {
            return StatusInfo;
        }

        public void setStatusInfo(String statusInfo) {
            StatusInfo = statusInfo;
        }

        public boolean isBindCar() {
            return IsBindCar;
        }

        public void setBindCar(boolean bindCar) {
            IsBindCar = bindCar;
        }

        public int getYdayMileage() {
            return YdayMileage;
        }

        public void setYdayMileage(int ydayMileage) {
            YdayMileage = ydayMileage;
        }

        public boolean isTrack() {
            return IsTrack;
        }

        public void setTrack(boolean track) {
            IsTrack = track;
        }

        public String getProblemTypeName() {
            return ProblemTypeName;
        }

        public void setProblemTypeName(String problemTypeName) {
            ProblemTypeName = problemTypeName;
        }

        public String getCarStateName() {
            return CarStateName;
        }

        public void setCarStateName(String carStateName) {
            CarStateName = carStateName;
        }

        public ObdFieldBean getObdField() {
            return obdField;
        }

        public void setObdField(ObdFieldBean obdField) {
            this.obdField = obdField;
        }

        public boolean isHighFrequency() {
            return HighFrequency;
        }

        public void setHighFrequency(boolean highFrequency) {
            HighFrequency = highFrequency;
        }


        /**
         * mileage : 0
         * acc : 0
         * templh : 0
         * averagelh : 0
         * totaloil : 0
         * enginec : 86
         * carc : 0
         * pipekpa : 0
         * voltage : 13
         * ecm : 1
         * abs : 0
         * srs : 0
         * errenginoil : 0
         * errtirepress : 0
         * errmaintain : 0
         * rpm : 0
         */




    }


    public static class ObdFieldBean {
        private int mileage;
        private int acc;
        private int templh;
        private int averagelh;
        private int totaloil;
        private int enginec;
        private int carc;
        private int pipekpa;
        private int voltage;
        private int ecm;
        private int abs;
        private int srs;
        private int errenginoil;
        private int errtirepress;
        private int errmaintain;
        private int rpm;

        public int getAbs() {
            return abs;
        }

        public void setAbs(int abs) {
            this.abs = abs;
        }

        public void setAcc(int acc) {
            this.acc = acc;
        }

        public int getAcc() {
            return acc;
        }

        public void setAveragelh(int averagelh) {
            this.averagelh = averagelh;
        }

        public int getAveragelh() {
            return averagelh;
        }

        public void setCarc(int carc) {
            this.carc = carc;
        }

        public int getCarc() {
            return carc;
        }

        public void setEcm(int ecm) {
            this.ecm = ecm;
        }

        public int getEcm() {
            return ecm;
        }

        public void setEnginec(int enginec) {
            this.enginec = enginec;
        }

        public int getEnginec() {
            return enginec;
        }

        public void setErrenginoil(int errenginoil) {
            this.errenginoil = errenginoil;
        }

        public int getErrenginoil() {
            return errenginoil;
        }

        public void setErrmaintain(int errmaintain) {
            this.errmaintain = errmaintain;
        }

        public int getErrmaintain() {
            return errmaintain;
        }

        public int getErrtirepress() {
            return errtirepress;
        }

        public void setErrtirepress(int errtirepress) {
            this.errtirepress = errtirepress;
        }

        public void setMileage(int mileage) {
            this.mileage = mileage;
        }

        public int getMileage() {
            return mileage;
        }

        public void setPipekpa(int pipekpa) {
            this.pipekpa = pipekpa;
        }

        public int getPipekpa() {
            return pipekpa;
        }

        public void setRpm(int rpm) {
            this.rpm = rpm;
        }

        public int getRpm() {
            return rpm;
        }

        public void setSrs(int srs) {
            this.srs = srs;
        }

        public int getSrs() {
            return srs;
        }

        public void setTemplh(int templh) {
            this.templh = templh;
        }

        public int getTemplh() {
            return templh;
        }

        public void setTotaloil(int totaloil) {
            this.totaloil = totaloil;
        }

        public int getTotaloil() {
            return totaloil;
        }

        public void setVoltage(int voltage) {
            this.voltage = voltage;
        }

        public int getVoltage() {
            return voltage;
        }
    }

}
