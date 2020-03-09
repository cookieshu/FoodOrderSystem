package xgl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import xgl.mapper.ReviewMapper;
import xgl.pojo.Review;
import xgl.pojo.ReviewExample;
import xgl.pojo.User;
import xgl.service.ReviewService;
import xgl.service.UserService;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;

    @Override
    public void add(Review c) {
        reviewMapper.insert(c);
    }

    @Override
    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Review c) {
        reviewMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Review get(int id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    //设置user
    private void setUser(Review review){
        User user = userService.get(review.getUid());
        review.setUser(user);
    }
    private void setUser(List<Review>reviews){
        for (Review review:reviews)
            setUser(review);
    }
    @Override
    public List list(int fid) {
        ReviewExample example=new ReviewExample();
        example.createCriteria().andFidEqualTo(fid);
        example.setOrderByClause("id desc");
        List<Review> reviews = reviewMapper.selectByExample(example);
        //设置User
        setUser(reviews);
        return reviews;
    }

    @Override
    public int getCount(int fid) {
        return list(fid).size();
    }
}
