package xgl.pojo;

import java.util.Date;
import java.util.List;

public class Food {
    private Integer id;

    private String name;

    private String subTitle;

    private Float price;

    private Date createDate;

    private Integer sid;

    //非数据库字段
    //1，菜品所属店铺
    private Shop shop;
    //2，菜品图片
    private FoodImage firstFoodImage;

    //单个菜品图片集合·
    private List<FoodImage>foodSingleImages;
    //详情图片集合
    private List<FoodImage>foodDetailImages;
    //销量=排号
    private int saleCount;
    //评价
    private int reviewCount;

    public List<FoodImage> getFoodSingleImages() {
        return foodSingleImages;
    }

    public void setFoodSingleImages(List<FoodImage> foodSingleImages) {
        this.foodSingleImages = foodSingleImages;
    }

    public List<FoodImage> getFoodDetailImages() {
        return foodDetailImages;
    }

    public void setFoodDetailImages(List<FoodImage> foodDetailImages) {
        this.foodDetailImages = foodDetailImages;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public FoodImage getFirstFoodImage() {
        return firstFoodImage;
    }

    public void setFirstFoodImage(FoodImage firstFoodImage) {
        this.firstFoodImage = firstFoodImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}