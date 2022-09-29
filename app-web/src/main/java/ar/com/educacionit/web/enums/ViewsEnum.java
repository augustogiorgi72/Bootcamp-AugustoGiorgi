package ar.com.educacionit.web.enums;

public enum ViewsEnum {
	
	REGISTRO_OK("/registroOk.jsp"),
	REGISTRO_FAIL("/registroFail.jsp"),
	MENU("/menu.jsp");
	
	private String value;
	
	private ViewsEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}