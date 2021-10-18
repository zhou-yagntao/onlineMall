package com.zyt.exception;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.exception
 * @ClassName: NoStockException
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 19:30 2021/2/27
 * @Version: 1.0
 */
public class NoStockException extends RuntimeException{

    private Integer prodId;

    public NoStockException(){
        super("没有足够的库存量");
    }


    public NoStockException(Integer prodId){
        super("商品id:"+prodId+"没有足够的库存量");
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }
}
