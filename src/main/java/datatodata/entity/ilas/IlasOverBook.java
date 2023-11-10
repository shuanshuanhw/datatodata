package datatodata.entity.ilas;

/**
 * 功能： <p></p>
 * 创建人：黄维
 * 时间：2023/5/11 11:52
 */
public class IlasOverBook {
    private String barCode;
    private String callno;
    private String identifier;
    private String loanCount;
    private String loanDate;
    private String orgLib;
    private String orgLocal;
    private String phone;
    private String returnTime;
    private String title;

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getCallno() {
        return callno;
    }

    public void setCallno(String callno) {
        this.callno = callno;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(String loanCount) {
        this.loanCount = loanCount;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getOrgLib() {
        return orgLib;
    }

    public void setOrgLib(String orgLib) {
        this.orgLib = orgLib;
    }

    public String getOrgLocal() {
        return orgLocal;
    }

    public void setOrgLocal(String orgLocal) {
        this.orgLocal = orgLocal;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
