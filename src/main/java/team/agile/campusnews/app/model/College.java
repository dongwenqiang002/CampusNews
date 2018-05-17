package team.agile.campusnews.app.model;

public class College {
    private Integer id;

    private String name;

    private Integer dean;

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

    public Integer getDean() {
        return dean;
    }

    public void setDean(Integer dean) {
        this.dean = dean;
    }
}