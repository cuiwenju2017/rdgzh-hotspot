package com.shanjing.mymeishi.model;

import java.util.List;

public class Aftershxqbean {

    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : {"id":"1134714417078996992","returnOrderNo":"201906011452536639540","orderStatus":"1","returnType":"0","orderStatus_name":"待售后审核","orderStatus_info":"您的申请已提交,请等待售后审核","expressCompany":"","expressCompanycode":"","expressNo":"","createDate":"2019-06-01 14:52:53","expressTime":"0001-01-01 00:00:00","orderList":[{"memberId":1130170916874096640,"returnOrderId":"1134714417078996992","orderProduceId":1134703728234991616,"goodsId":1087622087465635840,"goodsTitle":"肌美精3D面膜（粉色保湿款）","produceId":1087622087549521920,"produceName":"标准规格","icon":"http://imgs.weekfan.com/images/3/2018/12/rWCsI7NXWgw9SPitE16195Pp36sWW7.jpeg?x-oss-process=style/zhoufan2018","numBuy":3,"num":3,"priceBuy":45,"id":"1134714417162883072"}]}
     */

    private String status;
    private String errorCode;
    private String errorMsg;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1134714417078996992
         * returnOrderNo : 201906011452536639540
         * orderStatus : 1
         * returnType : 0
         * orderStatus_name : 待售后审核
         * orderStatus_info : 您的申请已提交,请等待售后审核
         * expressCompany :
         * expressCompanycode :
         * expressNo :
         * createDate : 2019-06-01 14:52:53
         * expressTime : 0001-01-01 00:00:00
         * orderList : [{"memberId":1130170916874096640,"returnOrderId":"1134714417078996992","orderProduceId":1134703728234991616,"goodsId":1087622087465635840,"goodsTitle":"肌美精3D面膜（粉色保湿款）","produceId":1087622087549521920,"produceName":"标准规格","icon":"http://imgs.weekfan.com/images/3/2018/12/rWCsI7NXWgw9SPitE16195Pp36sWW7.jpeg?x-oss-process=style/zhoufan2018","numBuy":3,"num":3,"priceBuy":45,"id":"1134714417162883072"}]
         */

        private String id;
        private String returnOrderNo;
        private String orderStatus;
        private String returnType;
        private String orderStatus_name;
        private String orderStatus_info;
        private String expressCompany;
        private String expressCompanycode;
        private String expressNo;
        private String createDate;
        private String expressTime;
        private List<OrderListBean> orderList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReturnOrderNo() {
            return returnOrderNo;
        }

        public void setReturnOrderNo(String returnOrderNo) {
            this.returnOrderNo = returnOrderNo;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getReturnType() {
            return returnType;
        }

        public void setReturnType(String returnType) {
            this.returnType = returnType;
        }

        public String getOrderStatus_name() {
            return orderStatus_name;
        }

        public void setOrderStatus_name(String orderStatus_name) {
            this.orderStatus_name = orderStatus_name;
        }

        public String getOrderStatus_info() {
            return orderStatus_info;
        }

        public void setOrderStatus_info(String orderStatus_info) {
            this.orderStatus_info = orderStatus_info;
        }

        public String getExpressCompany() {
            return expressCompany;
        }

        public void setExpressCompany(String expressCompany) {
            this.expressCompany = expressCompany;
        }

        public String getExpressCompanycode() {
            return expressCompanycode;
        }

        public void setExpressCompanycode(String expressCompanycode) {
            this.expressCompanycode = expressCompanycode;
        }

        public String getExpressNo() {
            return expressNo;
        }

        public void setExpressNo(String expressNo) {
            this.expressNo = expressNo;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getExpressTime() {
            return expressTime;
        }

        public void setExpressTime(String expressTime) {
            this.expressTime = expressTime;
        }

        public List<OrderListBean> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderListBean> orderList) {
            this.orderList = orderList;
        }

        public static class OrderListBean {
            /**
             * memberId : 1130170916874096640
             * returnOrderId : 1134714417078996992
             * orderProduceId : 1134703728234991616
             * goodsId : 1087622087465635840
             * goodsTitle : 肌美精3D面膜（粉色保湿款）
             * produceId : 1087622087549521920
             * produceName : 标准规格
             * icon : http://imgs.weekfan.com/images/3/2018/12/rWCsI7NXWgw9SPitE16195Pp36sWW7.jpeg?x-oss-process=style/zhoufan2018
             * numBuy : 3
             * num : 3
             * priceBuy : 45.0
             * id : 1134714417162883072
             */

            private long memberId;
            private String returnOrderId;
            private long orderProduceId;
            private long goodsId;
            private String goodsTitle;
            private long produceId;
            private String produceName;
            private String icon;
            private int numBuy;
            private int num;
            private double priceBuy;
            private String id;

            public long getMemberId() {
                return memberId;
            }

            public void setMemberId(long memberId) {
                this.memberId = memberId;
            }

            public String getReturnOrderId() {
                return returnOrderId;
            }

            public void setReturnOrderId(String returnOrderId) {
                this.returnOrderId = returnOrderId;
            }

            public long getOrderProduceId() {
                return orderProduceId;
            }

            public void setOrderProduceId(long orderProduceId) {
                this.orderProduceId = orderProduceId;
            }

            public long getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(long goodsId) {
                this.goodsId = goodsId;
            }

            public String getGoodsTitle() {
                return goodsTitle;
            }

            public void setGoodsTitle(String goodsTitle) {
                this.goodsTitle = goodsTitle;
            }

            public long getProduceId() {
                return produceId;
            }

            public void setProduceId(long produceId) {
                this.produceId = produceId;
            }

            public String getProduceName() {
                return produceName;
            }

            public void setProduceName(String produceName) {
                this.produceName = produceName;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getNumBuy() {
                return numBuy;
            }

            public void setNumBuy(int numBuy) {
                this.numBuy = numBuy;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public double getPriceBuy() {
                return priceBuy;
            }

            public void setPriceBuy(double priceBuy) {
                this.priceBuy = priceBuy;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
