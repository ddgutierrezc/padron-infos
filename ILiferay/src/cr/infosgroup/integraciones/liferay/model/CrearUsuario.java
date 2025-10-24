package cr.infosgroup.integraciones.liferay.model;

import java.lang.reflect.Field;
import java.util.Locale;

public class CrearUsuario {

	private long companyId;
	private boolean autoPassword;
	private String password1;
	private String password2;
	private boolean autoScreenName;
	private String screenName;
	private String emailAddress;
	private long facebookId;
	private String openId;
	private Locale locale;
	private String firstName;
	private String middleName;
	private String lastName;
	private long prefixId;
	private long suffixId;
	private boolean male;
	private int birthdayMonth;
	private int birthdayDay;
	private int birthdayYear;
	private String jobTitle;
	private long[] groupIds;
	private long[] organizationIds;
	private long roleIds;
	private long[] userGroupIds;
	private boolean sendEmail;
	//private com.liferay.portal.kernel.service.ServiceContext  serviceContext;
	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(this.getClass().getSimpleName());
		str.append(" [ ");
		
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			str.append(field.getName());
			str.append(":");
			try {
				str.append(field.get(this));
			} catch (Exception e) {
				str.append("*");
			}
			str.append(" ");
		}
		str.append("]");
	
		return str.toString();
	}
	
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public boolean isAutoPassword() {
		return autoPassword;
	}
	public void setAutoPassword(boolean autoPassword) {
		this.autoPassword = autoPassword;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public boolean isAutoScreenName() {
		return autoScreenName;
	}
	public void setAutoScreenName(boolean autoScreenName) {
		this.autoScreenName = autoScreenName;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public long getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(long facebookId) {
		this.facebookId = facebookId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getPrefixId() {
		return prefixId;
	}
	public void setPrefixId(long prefixId) {
		this.prefixId = prefixId;
	}
	public long getSuffixId() {
		return suffixId;
	}
	public void setSuffixId(long suffixId) {
		this.suffixId = suffixId;
	}
	public boolean isMale() {
		return male;
	}
	public void setMale(boolean male) {
		this.male = male;
	}
	public int getBirthdayMonth() {
		return birthdayMonth;
	}
	public void setBirthdayMonth(int birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}
	public int getBirthdayDay() {
		return birthdayDay;
	}
	public void setBirthdayDay(int birthdayDay) {
		this.birthdayDay = birthdayDay;
	}
	public int getBirthdayYear() {
		return birthdayYear;
	}
	public void setBirthdayYear(int birthdayYear) {
		this.birthdayYear = birthdayYear;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public long[] getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(long[] groupIds) {
		this.groupIds = groupIds;
	}
	public long[] getOrganizationIds() {
		return organizationIds;
	}
	public void setOrganizationIds(long[] organizationIds) {
		this.organizationIds = organizationIds;
	}
	public long getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(long roleIds) {
		this.roleIds = roleIds;
	}
	public long[] getUserGroupIds() {
		return userGroupIds;
	}
	public void setUserGroupIds(long[] userGroupIds) {
		this.userGroupIds = userGroupIds;
	}
	public boolean isSendEmail() {
		return sendEmail;
	}
	public void setSendEmail(boolean sendEmail) {
		this.sendEmail = sendEmail;
	}
	
	
}
