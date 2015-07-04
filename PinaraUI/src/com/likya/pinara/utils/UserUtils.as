package com.likya.pinara.utils
{
	import com.likya.comps.crud.user.ChangePass;
	import com.likya.comps.crud.user.UserEditWindow;
	
	import mx.resources.IResourceManager;
	import mx.resources.ResourceManager;

	public class UserUtils
	{
		
		public static function initChangePassWindow(changePassWindow:ChangePass):ChangePass {
			
			var resourceManager:IResourceManager = ResourceManager.getInstance();
			
			changePassWindow.title =  resourceManager.getString('messages', 'windowTitle');
			
			changePassWindow.userNameLbl = resourceManager.getString('messages', 'userName');
			changePassWindow.oldPassLbl = resourceManager.getString('messages', 'oldPassword');
			changePassWindow.newPassLbl = resourceManager.getString('messages', 'newPassword');
			changePassWindow.confNewPassLbl = resourceManager.getString('messages', 'confirmNewPassword');
			changePassWindow.okBtnLbl = resourceManager.getString('messages', 'change');
			changePassWindow.cnclBtnLbl = resourceManager.getString('messages', 'cancel');
			
			return changePassWindow;
		}

		public static function initCommonWindow(resourceManager:IResourceManager):void {
			
		}

		public static function initUserAddWindow(userAddWindow:UserEditWindow):UserEditWindow {
			
			var resourceManager:IResourceManager = ResourceManager.getInstance();
			
			userAddWindow.title =  resourceManager.getString('messages', 'ucauWindowTitle');
			
			userAddWindow.userNameLbl = resourceManager.getString('messages', 'userName');
			userAddWindow.newPassLbl = resourceManager.getString('messages', 'newPassword');
			userAddWindow.confNewPassLbl = resourceManager.getString('messages', 'confirmNewPassword');
			userAddWindow.roleInfoLbl = resourceManager.getString('messages', 'ucRoleInfo');
			userAddWindow.statuInfoLbl = resourceManager.getString('messages', 'ucStatuInfo');
			
			userAddWindow.okBtnLbl = resourceManager.getString('messages', 'ucSave');
			userAddWindow.cnclBtnLbl = resourceManager.getString('messages', 'cancel');
			
			return userAddWindow;
		}

		public static function initUserEditWindow(resourceManager:IResourceManager, userEditWindow:UserEditWindow):UserEditWindow {

			userEditWindow.title =  resourceManager.getString('messages', 'uceuWindowTitle');
			
			userEditWindow.userNameLbl = resourceManager.getString('messages', 'userName');
			userEditWindow.roleInfoLbl = resourceManager.getString('messages', 'ucRoleInfo');
			
			userEditWindow.okBtnLbl = resourceManager.getString('messages', 'ucSave');
			userEditWindow.cnclBtnLbl = resourceManager.getString('messages', 'cancel');

			userEditWindow.isMode = 1;

			return userEditWindow;
		}
	}
}