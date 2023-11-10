package datatodata.entity.ilas;


/**
 * 功能： <p></p>
 * 创建人：黄维
 * 时间：2023/5/10 16:39
 */

public class IlasUser {
    private String address;
    private String patronName;
    private String phone;
    private String sex;
    // 哪个馆的
    private String institutionId;
    private String libcode;
    // 读者类型
    private String patronCirculationType;
    // 读者证号
    private String patronIdentifier;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPatronName() {
        return patronName;
    }

    public void setPatronName(String patronName) {
        this.patronName = patronName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getLibcode() {
        return libcode;
    }

    public void setLibcode(String libcode) {
        this.libcode = libcode;
    }

    public String getPatronCirculationType() {
        return patronCirculationType;
    }

    public void setPatronCirculationType(String patronCirculationType) {
        this.patronCirculationType = patronCirculationType;
    }

    public String getPatronIdentifier() {
        return patronIdentifier;
    }

    public void setPatronIdentifier(String patronIdentifier) {
        this.patronIdentifier = patronIdentifier;
    }
}
