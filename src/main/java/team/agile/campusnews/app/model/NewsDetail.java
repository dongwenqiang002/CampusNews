package team.agile.campusnews.app.model;

import java.util.Date;

public class NewsDetail {
    private Integer id;

    private String typeName;

    private Integer importance;

    private Date deadline;

    private Date importanceDeadline;

    private Integer newsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getImportanceDeadline() {
        return importanceDeadline;
    }

    public void setImportanceDeadline(Date importanceDeadline) {
        this.importanceDeadline = importanceDeadline;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }
}