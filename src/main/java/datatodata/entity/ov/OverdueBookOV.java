package datatodata.entity.ov;


/**
 * 功能： <p></p>
 * 创建人：黄维
 * 时间：2024/1/15 14:26
 */

public class OverdueBookOV {
    private String barCode;
    private String orgLib;
    private String orgLocal;

    private String title;

    private String callno;
    private String returnTime;
    private String identifier;
    private String phone;
    private String loanCount;
    private String loanDate;

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

    public String getOrgLocal() {
        return orgLocal;
    }

    public void setOrgLocal(String orgLocal) {
        this.orgLocal = orgLocal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCallno() {
        return callno;
    }

    public void setCallno(String callno) {
        this.callno = callno;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
