package kr.gaza.myapp.aviation.product;

public class ProductVO {
    private int productNum;
    private String depart;
    private String arrive;
    private int price;
    private int productCnt;
    private float ProductSale;
    private String regDate;

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductCnt() {
        return productCnt;
    }

    public void setProductCnt(int productCnt) {
        this.productCnt = productCnt;
    }

    public float getProductSale() {
        return ProductSale;
    }

    public void setProductSale(float ProductSale) {
        this.ProductSale = ProductSale;
    }

    public void setProductSale(int ProductSale) {
        this.ProductSale = ProductSale;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
}
