package com.zyt.entity;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: OrderItems
 * @Author: zhou_yangtao@yeah.net
 * @Description:订单详情页
 * @Date: 15:43 2021/2/25
 * @Version: 1.0
 */
public class OrderItems {
    private Integer order_item_id;
    private Integer order_id;
    private String order_sn;
    private Integer prod_id;
    private String prod_name;
    private String prod_pic;
    private String prod_brand;
    private Integer prod_quantity;
    private String prod_Store;
    private Integer gift_integration;
    private Integer gift_growth;
    private String prod_status;
    private Integer prod_price;

    public  OrderItems(){
        super();
    }

    public OrderItems(Integer order_item_id, Integer order_id, String order_sn, Integer prod_id, String prod_name, String prod_pic, String prod_brand, Integer prod_quantity, String prod_Store, Integer gift_integration, Integer gift_growth, String prod_status, Integer prod_price) {
        this.order_item_id = order_item_id;
        this.order_id = order_id;
        this.order_sn = order_sn;
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.prod_pic = prod_pic;
        this.prod_brand = prod_brand;
        this.prod_quantity = prod_quantity;
        this.prod_Store = prod_Store;
        this.gift_integration = gift_integration;
        this.gift_growth = gift_growth;
        this.prod_status = prod_status;
        this.prod_price = prod_price;
    }

    public Integer getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(Integer order_item_id) {
        this.order_item_id = order_item_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getProd_id() {
        return prod_id;
    }

    public void setProd_id(Integer prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getProd_pic() {
        return prod_pic;
    }

    public void setProd_pic(String prod_pic) {
        this.prod_pic = prod_pic;
    }

    public String getProd_brand() {
        return prod_brand;
    }

    public void setProd_brand(String prod_brand) {
        this.prod_brand = prod_brand;
    }

    public Integer getProd_quantity() {
        return prod_quantity;
    }

    public void setProd_quantity(Integer prod_quantity) {
        this.prod_quantity = prod_quantity;
    }

    public String getProd_Store() {
        return prod_Store;
    }

    public void setProd_Store(String prod_Store) {
        this.prod_Store = prod_Store;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }


    public Integer getGift_integration() {
        return gift_integration;
    }

    public void setGift_integration(Integer gift_integration) {
        this.gift_integration = gift_integration;
    }

    public Integer getGift_growth() {
        return gift_growth;
    }

    public void setGift_growth(Integer gift_growth) {
        this.gift_growth = gift_growth;
    }

    public String getProd_status() {
        return prod_status;
    }

    public void setProd_status(String prod_status) {
        this.prod_status = prod_status;
    }

    public Integer getProd_price() {
        return prod_price;
    }

    public void setProd_price(Integer prod_price) {
        this.prod_price = prod_price;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "order_item_id=" + order_item_id +
                ", order_id=" + order_id +
                ", order_sn='" + order_sn + '\'' +
                ", prod_id=" + prod_id +
                ", prod_name='" + prod_name + '\'' +
                ", prod_pic='" + prod_pic + '\'' +
                ", prod_brand='" + prod_brand + '\'' +
                ", prod_quantity=" + prod_quantity +
                ", prod_Store='" + prod_Store + '\'' +
                ", gift_integration=" + gift_integration +
                ", gift_growth=" + gift_growth +
                ", prod_status='" + prod_status + '\'' +
                ", prod_price=" + prod_price +
                '}';
    }
}
