package xgl.pojo;

public class User {
    private Integer id;

    private String mobile;

    private String name;

    private String password;

    private String salt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    //评价时匿名显示的方法
    public String getAnonymousName(){
        if (null==name)
            return null;
        if (name.length()<=1)
            return "*";
        if (name.length()==2){
            return name.substring(0,1)+"*";
        }
        char[] chars = name.toCharArray();
        for (int i=1;i<chars.length-1;i++){
            chars[i]='*';
        }
        return new String(chars);
    }
}