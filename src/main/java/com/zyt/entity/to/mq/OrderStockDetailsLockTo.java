package com.zyt.entity.to.mq;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.to.mq
 * @ClassName: OrderStockDetailsLockTo
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 13:42 2021/3/3
 * @Version: 1.0
 */
public class OrderStockDetailsLockTo {
    private Integer id;
    private Integer prod_id;
    private String prod_name;
    private Integer prod_num;
    private Integer task_id;
    private Integer lock_stauts;

    public OrderStockDetailsLockTo(){
        super();
    }

    public OrderStockDetailsLockTo(Integer id, Integer prod_id, String prod_name, Integer prod_num, Integer task_id, Integer lock_stauts) {
        this.id = id;
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.prod_num = prod_num;
        this.task_id = task_id;
        this.lock_stauts = lock_stauts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getProd_num() {
        return prod_num;
    }

    public void setProd_num(Integer prod_num) {
        this.prod_num = prod_num;
    }

    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }

    public Integer getLock_stauts() {
        return lock_stauts;
    }

    public void setLock_stauts(Integer lock_stauts) {
        this.lock_stauts = lock_stauts;
    }

    @Override
    public String toString() {
        return "OrderTaskDetail{" +
                "id=" + id +
                ", prod_id=" + prod_id +
                ", prod_name='" + prod_name + '\'' +
                ", prod_num=" + prod_num +
                ", task_id=" + task_id +
                ", lock_stauts=" + lock_stauts +
                '}';
    }
}
