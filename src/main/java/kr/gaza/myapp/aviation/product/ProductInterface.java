package kr.gaza.myapp.aviation.product;

public interface ProductInterface {
    ProductVO productSelect(int productNum);
    void productCntIncrease(int productNum);
}
