package datatodata.entity.ilas;


/**
 * 功能： <p></p>
 * 创建人：黄维
 * 时间：2023/5/11 10:43
 */

public class IlasBook {
    private String author;
    private String bookRecNO;
    private String callNO;
    private String classNO;
    private String publisher;
    private String titleIdentifier;
    private String volInfo;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookRecNO() {
        return bookRecNO;
    }

    public void setBookRecNO(String bookRecNO) {
        this.bookRecNO = bookRecNO;
    }

    public String getCallNO() {
        return callNO;
    }

    public void setCallNO(String callNO) {
        this.callNO = callNO;
    }

    public String getClassNO() {
        return classNO;
    }

    public void setClassNO(String classNO) {
        this.classNO = classNO;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitleIdentifier() {
        return titleIdentifier;
    }

    public void setTitleIdentifier(String titleIdentifier) {
        this.titleIdentifier = titleIdentifier;
    }

    public String getVolInfo() {
        return volInfo;
    }

    public void setVolInfo(String volInfo) {
        this.volInfo = volInfo;
    }
}
