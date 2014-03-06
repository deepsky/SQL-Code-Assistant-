package example.velocity.client;
//import example.annotations.beaninfo.BeanInfo;


import com.deepsky.lang.plsql.completion.syntaxTreePath.annotations.BeanInfo;

@BeanInfo
public class Article {
    @BeanInfo private String id;
    @BeanInfo private int department;
    @BeanInfo
    private String status;
    public Article() {
        super();
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getDepartment() {
        return department;
    }
    public void setDepartment(int department) {
        this.department = department;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @BeanInfo public void activate() {
        setStatus("active");
    }
    @BeanInfo public void deactivate() {
        setStatus("inactive");
    }
}