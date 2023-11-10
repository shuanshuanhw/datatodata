package datatodata.entity.ilas;


/**
 * 功能： <p>查询时间范围内的读者办证列表 返回结果时有用到的一个数据结构</p>
 * 创建人：黄维
 * 时间：2023/5/12 11:57
 */
public class IlasRegisterReader {
    private String patronIdentifier;// 读者证号
    private String patronPassword;
    private String patronName;
    private String certify;
    private String sex;// 男：MALE 女：FEMALE
    private String patronCirculationType;
    private String address;
    private String mobile;
    private String libcode;
    private String startDate;
    private String stopDate;
    private String type;

    public String getPatronIdentifier() {
        return patronIdentifier;
    }

    public void setPatronIdentifier(String patronIdentifier) {
        this.patronIdentifier = patronIdentifier;
    }

    public String getPatronPassword() {
        return patronPassword;
    }

    public void setPatronPassword(String patronPassword) {
        this.patronPassword = patronPassword;
    }

    public String getPatronName() {
        return patronName;
    }

    public void setPatronName(String patronName) {
        this.patronName = patronName;
    }

    public String getCertify() {
        return certify;
    }

    public void setCertify(String certify) {
        this.certify = certify;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPatronCirculationType() {
        return patronCirculationType;
    }

    public void setPatronCirculationType(String patronCirculationType) {
        this.patronCirculationType = patronCirculationType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLibcode() {
        return libcode;
    }

    public void setLibcode(String libcode) {
        this.libcode = libcode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
