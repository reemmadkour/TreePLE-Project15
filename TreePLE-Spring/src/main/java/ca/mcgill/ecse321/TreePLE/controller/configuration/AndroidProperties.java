package ca.mcgill.ecse321.TreePLE.controller.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "client.android")
public class AndroidProperties {

	/**
	 * The IP adress of the Android client
	 */
	private String ip = "ecse-14.ece.mcgill.ca";
	/**
	 * The port on which the Android client listens
	 */
	private int port = 8080;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
