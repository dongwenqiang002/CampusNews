package team.agile.campusnews.app.model;

public class PublishNews {
    private Integer id;

    private Integer newsId;

    private Integer schoolosId;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getSchoolosId() {
        return schoolosId;
    }

    public void setSchoolosId(Integer schoolosId) {
        this.schoolosId = schoolosId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}