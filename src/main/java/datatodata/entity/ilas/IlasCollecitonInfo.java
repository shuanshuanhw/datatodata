package datatodata.entity.ilas;



/**
 * 功能： <p>读者通过接口查询图书馆藏信息，图书馆返回图书的相关简单信息用以页面列表显示，接口返回
 * 图书列表。</p>
 * 创建人：黄维
 * 时间：2023/11/16 9:26
 */

public class IlasCollecitonInfo {
    private String bookRecNO;// 书目号
    private String barCode;// 文献条码
    private String orgLib;
    private String orgLibName;
    private String orgLocal;
    private String orgLocalName;
    private String curLib; // 当前所在馆
    private String curLibName;
    private String curLocal;
    private String curLocalName;
    private String volInfo;
    private String cirType;// 流通类型 通借通还、通借不通还、不流通
    private String status;// 馆藏状态 在馆、借出、锁定、被预约
    private String title;
    private String patronIdentifier;// 读者证号
    private String loanTime;// 借出时间 YYYYMMDDZZZZHHMMSS

    @Override
    public String toString() {
        return "IlasCollecitonInfo{" +
                "bookRecNO='" + bookRecNO + '\'' +
                ", barCode='" + barCode + '\'' +
                ", orgLib='" + orgLib + '\'' +
                ", orgLibName='" + orgLibName + '\'' +
                ", orgLocal='" + orgLocal + '\'' +
                ", orgLocalName='" + orgLocalName + '\'' +
                ", curLib='" + curLib + '\'' +
                ", curLibName='" + curLibName + '\'' +
                ", curLocal='" + curLocal + '\'' +
                ", curLocalName='" + curLocalName + '\'' +
                ", volInfo='" + volInfo + '\'' +
                ", cirType='" + cirType + '\'' +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", patronIdentifier='" + patronIdentifier + '\'' +
                ", loanTime='" + loanTime + '\'' +
                '}';
    }

    public String getBookRecNO() {
        return bookRecNO;
    }

    public void setBookRecNO(String bookRecNO) {
        this.bookRecNO = bookRecNO;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getOrgLib() {
        return orgLib;
    }

    public void setOrgLib(String orgLib) {
        this.orgLib = orgLib;
    }

    public String getOrgLibName() {
        return orgLibName;
    }

    public void setOrgLibName(String orgLibName) {
        this.orgLibName = orgLibName;
    }

    public String getOrgLocal() {
        return orgLocal;
    }

    public void setOrgLocal(String orgLocal) {
        this.orgLocal = orgLocal;
    }

    public String getOrgLocalName() {
        return orgLocalName;
    }

    public void setOrgLocalName(String orgLocalName) {
        this.orgLocalName = orgLocalName;
    }

    public String getCurLib() {
        return curLib;
    }

    public void setCurLib(String curLib) {
        this.curLib = curLib;
    }

    public String getCurLibName() {
        return curLibName;
    }

    public void setCurLibName(String curLibName) {
        this.curLibName = curLibName;
    }

    public String getCurLocal() {
        return curLocal;
    }

    public void setCurLocal(String curLocal) {
        this.curLocal = curLocal;
    }

    public String getCurLocalName() {
        return curLocalName;
    }

    public void setCurLocalName(String curLocalName) {
        this.curLocalName = curLocalName;
    }

    public String getVolInfo() {
        return volInfo;
    }

    public void setVolInfo(String volInfo) {
        this.volInfo = volInfo;
    }

    public String getCirType() {
        return cirType;
    }

    public void setCirType(String cirType) {
        this.cirType = cirType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPatronIdentifier() {
        return patronIdentifier;
    }

    public void setPatronIdentifier(String patronIdentifier) {
        this.patronIdentifier = patronIdentifier;
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }
}
