package datatodata.entity.ov;

import java.util.List;

/**
 * 功能： <p></p>
 * 创建人：黄维
 * 时间：2024/1/15 15:55
 */
public class OverdueBookOVStatus {
    private String code;
    private String message;
    private String count;
    private String page;
    private List<OverdueBookOV> datas;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<OverdueBookOV> getDatas() {
        return datas;
    }

    public void setDatas(List<OverdueBookOV> datas) {
        this.datas = datas;
    }
}
