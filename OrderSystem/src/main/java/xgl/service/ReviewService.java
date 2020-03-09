package xgl.service;

import xgl.pojo.Review;

import java.util.List;

public interface ReviewService {

    void add(Review c);

    void delete(int id);
    void update(Review c);
    Review get(int id);
    //查询food的评价
    List list(int fid);
    int getCount(int fid);
}
