package com.light.ac.common.configuration;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.light.ac.common.util.DESUtil;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	// 需要解密的字段
	private String[] encryptPropNames = { "jdbc.username", "jdbc.password" };

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptProp(propertyName)) {
			String decryptValue = DESUtil.getDecryptString(propertyValue);
			return decryptValue;
		} else {
			return propertyValue;
		}
	}

	private boolean isEncryptProp(String propertyName) {
		for (String encryptpropertyName : encryptPropNames) {
			if (encryptpropertyName.equals(propertyName))
				return true;
		}
		return false;
	}
}
