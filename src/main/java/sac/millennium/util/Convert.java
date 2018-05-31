package sac.millennium.util;

import java.sql.Date;

public class Convert {

	public static Date conertirUtilDate_SqlDate(java.util.Date date) {
		return new Date(date.getTime());
	}

}
