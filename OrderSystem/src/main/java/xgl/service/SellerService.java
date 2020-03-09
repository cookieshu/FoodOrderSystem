package xgl.service;

import xgl.pojo.Seller;

import java.util.List;

public interface SellerService {
    void add(Seller seller);
    void delete(int id);
    void update(Seller seller);
    Seller get(int id);

    //查询该shop下的seller
    List<Seller> list(int sid);
}
