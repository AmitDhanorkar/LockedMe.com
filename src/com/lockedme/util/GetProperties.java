/**

 *@ClassName : GetProperties.java

 *

 *@author :  Amit Dhanorkar

 *

 *@Version :  1.0

 *

 *@Date :  Dec 14,2021

 *

 */

package com.lockedme.util;

import java.util.Properties;

public class GetProperties {

	public static Properties getProperties() {
		return new GetProperties().getProps();
	}

	private Properties getProps() {

		final Properties pr = new Properties();
		try {
			pr.load(this.getClass().getResourceAsStream("/resources/application.properties"));
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return pr;
	}
}
