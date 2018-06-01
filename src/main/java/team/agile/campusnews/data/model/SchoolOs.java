package team.agile.campusnews.data.model;

public class SchoolOs {
    private Integer id;

    private String name;

    //private Integer parentId;
    private SchoolOs parentSchoolOs;

    private String remark;

    private String code;

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
        this.name = name;
    }

    public SchoolOs getParentSchoolOs() {
        return parentSchoolOs;
    }

    public void setParentSchoolOs(SchoolOs parentSchoolOs) {
        this.parentSchoolOs = parentSchoolOs;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}