package datatodata.entity.ilas;

/**
 * 功能： <p></p>
 * 创建人：黄维
 * 时间：2023/5/11 15:28
 */
public class IlasFeeInfo {
    // 费用记录号
    private String feeid;
    // 产生日期
    private String duetime;

    private String duelocal;

    private String note;

    // 费用，整数，单位为分
    private int fee;

    public String getFeeid() {
        return feeid;
    }

    public void setFeeid(String feeid) {
        this.feeid = feeid;
    }

    public String getDuetime() {
        return duetime;
    }

    public void setDuetime(String duetime) {
        this.duetime = duetime;
    }

    public String getDuelocal() {
        return duelocal;
    }

    public void setDuelocal(String duelocal) {
        this.duelocal = duelocal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
}
