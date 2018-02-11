package com.likya.pinara.gui.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.likya.pinara.Pinara;
import com.likya.pinara.PinaraBase;
import com.likya.pinara.gui.WebManager;
import com.likya.pinara.model.PinaraAuthorization;
import com.likya.pinara.model.User;
import com.likya.pinara.model.User.RoleInfo;
import com.likya.pinara.utils.UTF8String;
import com.likya.pinara.utils.xml.mappers.UserMapper;

public class RestUserOps extends PinaraRestHandler {

	public static final String CMD_USERLIST = "userlist";
	public static final String CMD_USERREAD = "userread";
	public static final String CMD_USERADD = "useradd";
	public static final String CMD_USERUPDATE = "userupdate";
	public static final String CMD_USERDELETE = "userdelete";
	public static final String CMD_CHANGEPASS = "changepass";
	public static final String CMD_CHANGEPASSADM = "changepassadm";
	
	public byte[] doWork(User reqUserInfo, boolean isPost, String uriTxt, InputStream inputStream) throws IOException {

		byte responseBytes[];

		if (isPost) {
			responseBytes = parsePost(reqUserInfo, uriTxt, inputStream);
		} else {
			// responseBytes = parse(uriTxt);
			responseBytes = "NA".getBytes();
		}

		return responseBytes;
	}
	
	private static boolean isAdmin(User user) {
		return user.getRoleInfo().equals(RoleInfo.ADMIN) ? true : false; 
	}
	
	@Override
	public String removeBaseUrl(String uriTxt) throws IOException {
		return uriTxt.replace("/" + WebManager.RESTUSEROPS_CTX + "/", "");
	}

	public static byte[] parsePost(User reqUserInfo, String uriTxt, InputStream inputStream) throws IOException {
		
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
				retStr = "<message><result>NOK</result><desc>" + "No user in list, it is abnormal !</desc></message>";
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
				String userName = UTF8String.getValue(paramValue.getBytes()); // Serkan : paramValue;
				user = pinaraAuthorization.readUser(userName);
				break;
			default:
				break;
			}
			
			if (user == null) {
				retStr = "<message><result>NOK</result><desc>" + "User " + paramName + " not defined or invalid : " + paramValue + "</desc></message>";
			} else {
				retStr = UserMapper.getMapped(user);
			}
			
			break;
			
		case CMD_USERADD:
			
			user = UserMapper.parseUser(paramBuff.toString());
			user = pinaraAuthorization.addUser(user);
			
			if (user == null) {
				retStr = "<message><result>NOK</result><desc>" + "UserInfo not well formed or invalid : " + paramBuff + "</desc></message>";
			} else {
				retStr = UserMapper.getMapped(user);
			}
			
			break;

		case CMD_USERUPDATE:
			
			user = UserMapper.parseUser(paramBuff.toString());
			
			if(PinaraBase.authTxt.equals(user.getUsername())) {
				retStr = "<message><result>NOK</result><desc>main user modification is not allowed</desc></message>";
				break;
			}
			
			user = pinaraAuthorization.updateUser(user);
			
			if (user == null) {
				retStr = "<message><result>NOK</result><desc>" + "UserInfo not well formed or invalid : " + paramBuff + "</desc></message>";
			} else {
				retStr = UserMapper.getMapped(user);
			}
			
			break;

		case CMD_USERDELETE:
			
			paramValue = paramBuff.toString(); // paramBuff.toString().split("=")[1];

			int userId = Integer.parseInt(paramValue);

			user = pinaraAuthorization.readUser(userId);
			if(PinaraBase.authTxt.equals(user.getUsername())) {
				retStr = "<message><result>NOK</result><desc>main user modification is not allowed</desc></message>";
				break;
			}

			user = pinaraAuthorization.deleteUser(userId);
			
			if (user == null) {
				retStr = "<message><result>NOK</result><desc>" + "User id not defined or invalid : " + paramValue + "</desc></message>";
			} else {
				retStr = UserMapper.getMapped(user);
			}
			
			break;
			
		case CMD_CHANGEPASS:
			
			String [] dataArray = UserMapper.parsePassChangeDataWithUsername(paramBuff.toString());
			
			user = pinaraAuthorization.changePassword(dataArray[0], dataArray[1], dataArray[2]);
			
			if (user == null) {
				retStr = "<message><result>NOK</result><desc>" + "password change is unsuccessful : " + paramBuff + "</desc></message>";
			} else {
				retStr = UserMapper.getMapped(user);
			}
			
			break;

		case CMD_CHANGEPASSADM:

			if (isAdmin(reqUserInfo)) {

				dataArray = UserMapper.parsePassChangeDataWithUsername(paramBuff.toString());

				user = pinaraAuthorization.readUser(dataArray[0]);
				if (PinaraBase.authTxt.equals(user.getUsername())) {
					retStr = "<message><result>NOK</result><desc>main user modification is not allowed</desc></message>";
					break;
				}

				user = pinaraAuthorization.changePasswordAdm(dataArray[0], dataArray[1]);

				if (user == null) {
					retStr = "<message><result>NOK</result><desc>" + "password change is unsuccessful : " + paramBuff + "</desc></message>";
				} else {
					retStr = UserMapper.getMapped(user);
				}

			} else {
				retStr = "<message><result>NOK</result><desc>" + "password change is unsuccessful : Only " + RoleInfo.ADMIN + " can use this service + </desc></message>";
			}

			break;
			
		default:
			retStr = "<message><result>NOK</result><desc>" + "Command not found : " + switchId + "</desc></message>";

			break;
		}
		
		responseBytes = retStr.getBytes("utf8");

		return responseBytes;
	}

}
