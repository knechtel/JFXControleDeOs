package util;

import bean.Aparelho;

public class Session {
	
	private static Aparelho aparelho;

	public static Aparelho getAparelho() {
		return aparelho;
	}

	public static void setAparelho(Aparelho aparelho) {
		Session.aparelho = aparelho;
	}
	
	

}
