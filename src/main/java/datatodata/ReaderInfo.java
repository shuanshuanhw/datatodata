package datatodata;



/**
 * 查询读者信息返回的实体类
 * @author ilas-pc
 *
 */
public class ReaderInfo {
	
	/**
	 * 证有效期	String	长度8，	必须	格式：YYYYMMDD
	 */
	private String cardLimit;
	/**
	 * 开通日期	String	长度8，	必须	格式：YYYYMMDD
	 */
	private String dueTime;
	/**
	 * 	押金	String	可变长度
	 */
	private String deposit;
	/**
	 * 	工本费	String	可变长度
	 */
	private String idFee;
	/**
	 * 	服务费	String	可变长度
	 */
	private String serviceFee;
	/**
	 * 开户馆代码	String	长度5，	必须	
	 */
	private String libcode;	
	/**
	 * 开户馆名称	String	可变长度	--	图书馆中文名称
	 */
	private String institutionId;	
	/**
	 * 读者证号	String	可变长度	必须
	 */
	private String patronIdentifier;	
	/**
	 * 	读者密码	String	可变长度
	 */
	private String patronPassword;		
	/**
	 * 读者姓名	String	可变长度	必须	
	 */
	private String patronName;	
	/**
	 * 证件号码	String	可变长度
	 */
	private String certify;			
	/**
	 * 证件类型	String	可变长度
	 */
	private String cettype;			
	/**
	 * 读者流通类型名称	String	可变长度	必须	自定义类型，不超过20个汉字。如：全局免费证、集体外借证、志愿者专有证等。
	 */
	private String patronCirculationType;
	/**
	 * 生日	String	长度8		YYYYMMDD
	 */
	private String bornDate;	
	/**
	 * 性别	String	长度6		男：MALE  女：FEMALE
	 */
	private String sex;	
	/**
	 * 电话号码	String	可变长度
	 */
	private String phone;	
	/**
	 * 邮件	String	可变长度
	 */
	private String email;	
	/**
	 * 联系地址	String	可变长度
	 */
	private String address;	
	/**
	 * 备注	String	可变长度
	 */
	private String notes;  	
	/**
	 * 认证类型	String	长度1，	0 一卡通 ,1 身份证
	 */
	private String idType;	
	/**
	 * 读者状态	String	长度1，	必须	
	 * 0 有效
	 * 1 过期
	 * 2 挂失
	 * 3 暂停
	 * 4 注销
	 */
	private String patronStatus;	
	/**
	 * 总可借书	String	可变长度	必须	
	 */
	private String holdItemsCount;	
	/**
	 * 在借数	String	可变长度	必须	
	 */
	private String chargedItemsCount;	
	/**
	 * 照片	String	可变长度		读者照片信息采用base64编码
	 */
	private String photo;

	/**
	 * 违约金欠款总额
	 */
	private String debt;

	private String mpTime;

	public String getDebt() {
		return debt;
	}

	public void setDebt(String debt) {
		this.debt = debt;
	}

	public String getCardLimit() {
		return cardLimit;
	}
	public void setCardLimit(String cardLimit) {
		this.cardLimit = cardLimit;
	}
	public String getDueTime() {
		return dueTime;
	}
	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getIdFee() {
		return idFee;
	}
	public void setIdFee(String idFee) {
		this.idFee = idFee;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getLibcode() {
		return libcode;
	}
	public void setLibcode(String libcode) {
		this.libcode = libcode;
	}
	public String getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
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
	public String getCettype() {
		return cettype;
	}
	public void setCettype(String cettype) {
		this.cettype = cettype;
	}
	public String getPatronCirculationType() {
		return patronCirculationType;
	}
	public void setPatronCirculationType(String patronCirculationType) {
		this.patronCirculationType = patronCirculationType;
	}
	public String getBornDate() {
		return bornDate;
	}
	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getPatronStatus() {
		return patronStatus;
	}
	public void setPatronStatus(String patronStatus) {
		this.patronStatus = patronStatus;
	}
	public String getHoldItemsCount() {
		return holdItemsCount;
	}
	public void setHoldItemsCount(String holdItemsCount) {
		this.holdItemsCount = holdItemsCount;
	}
	public String getChargedItemsCount() {
		return chargedItemsCount;
	}
	public void setChargedItemsCount(String chargedItemsCount) {
		this.chargedItemsCount = chargedItemsCount;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getMpTime() {
		return mpTime;
	}

	public void setMpTime(String mpTime) {
		this.mpTime = mpTime;
	}
}
