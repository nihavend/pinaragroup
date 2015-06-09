package com.likya.pinara.gui.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.likya.pinara.Pinara;
import com.likya.pinara.gui.WebManager;
import com.likya.pinara.model.PinaraAuthorization;
import com.likya.pinara.model.User;
import com.likya.pinara.utils.xml.mappers.UserMapper;

public class RestUserOps extends PinaraRestHandler {

	public static final String CMD_USERLIST = "userlist";
	public static final String CMD_USERREAD = "userread";
	public static final String CMD_USERADD = "useradd";
	public static final String CMD_USERUPDATE = "userupdate";
	public static final String CMD_USERDELETE = "userdelete";
	
	public byte[] doWork(boolean isPost, String uriTxt, InputStream inputStream) throws IOException {

		byte responseBytes[];

		if (isPost) {
			responseBytes = parsePost(uriTxt, inputStream);
		} else {
			// responseBytes = parse(uriTxt);
			responseBytes = "NA".getBytes();
		}

		return responseBytes;
	}
	
	@Override
	public String removeBaseUrl(String uriTxt) throws IOException {
		return uriTxt.replace("/" + WebManager.RESTUSEROPS_CTX + "/", "");
	}

	public static byte[] parsePost(String uriTxt, InputStream inputStream) throws IOException {
		
		byte responseBytes[] = new byte[0];

		StringBuffer paramBuff = new StringBuffer();
		
		String switchId = GenericRestParser.getSwitchId(paramBuff, uriTxt, inputStream);
		
		if (switchId == null) {
			return responseBytes;
		}
		
		PinaraAuthorization pinaraAuthorization = Pinara.getInstance().getConfigurationManager().getPinaraAuthorization();

		String retStr = "";
		
		switch (switchId) {
		
		case CMD_USERLIST:

			ArrayList<User> userList = pinaraAuthorization.getUserList();
			
			if (userList.size() == 0) {
				retStr = "<result>NOK : " + "No user in list, it is abnormal !</result>";
			} else {
				retStr = UserMapper.getMapped(userList);
			}
			
			break;
			
		case CMD_USERREAD:

			responseBytes = retStr.getBytes();
			
			String paramName = paramBuff.toString().split("=")[0];
			String paramValue = paramBuff.toString().split("=")[1];
			
			User user = null;
			
			switch (paramName) {
			
			case "id":
				int userId = Integer.parseInt(paramValue);
				user = pinaraAuthorization.readUser(userId);
				break;

			case "username":
				String userName = paramValue;
				user = pinaraAuthorization.readUser(userName);
				break;
			default:
				break;
			}
			
			if (user == null) {
				retStr = "<result>NOK : " + "User " + paramName + " not defined or invalid : " + paramValue + "</result>";
			} else {
				retStr = UserMapper.getMapped(user);
			}
			
			break;
			
		case CMD_USERADD:
			
			user = UserMapper.parseUser(paramBuff.toString());
			user = pinaraAuthorization.addUser(user);
			
			if (user == null) {
				retStr = "<result>NOK : " + "UserInfo not well formed or invalid : " + paramBuff + "</result>";
			} else {
				retStr = UserMapper.getMapped(user);
			}
			
			break;

		case CMD_USERUPDATE:
			
			user = UserMapper.parseUser(paramBuff.toString());
			user = pinaraAuthorization.updateUser(user);
			
			if (user == null) {
				retStr = "<result>NOK : " + "UserInfo not well formed or invalid : " + paramBuff + "</result>";
			} else {
				retStr = UserMapper.getMapped(user);
			}
			
			break;

		case CMD_USERDELETE:
			
			paramValue = paramBuff.toString().split("=")[1];
			int userId = Integer.parseInt(paramValue);
			user = pinaraAuthorization.deleteUser(userId);
			
			if (user == null) {
				retStr = "<result>NOK : " + "User id not defined or invalid : " + paramValue + "</result>";
			} else {
				retStr = UserMapper.getMapped(user);
			}
			
			break;
			
		default:
			retStr = "<result>NOK : " + "Command not found : " + switchId + "</result>";

			break;
		}
		
		responseBytes = retStr.getBytes();

		return responseBytes;
	}

}
