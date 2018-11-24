package com.likya.pinara.utils {
	
	import com.likya.pinara.comps.configuration.MailConfigurationForm;
	
	import mx.formatters.DateFormatter;
	
	public class ConfigurationsUtil {
		
		namespace lik = "http://www.likyateknoloji.com/likya-gen";
		use namespace lik;
		
		namespace myra_stateinfo ="http://www.likyateknoloji.com/myra-stateinfo";
		use namespace myra_stateinfo;
		
		public static function getXML(mailConfigForm:MailConfigurationForm):XML {
			
			var mailConfigInfo:XML = 
				<mailInfo xmlns:lik="http://www.likyateknoloji.com/likya-gen" 
						  xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo" 
						  xmlns="http://www.likyateknoloji.com/pinara-config">
				</mailInfo>;
			var counter:int = 0;
			var dateTimeString:String = null;
			
			if(mailConfigForm.mcToggle.selected) {
				mailConfigInfo.@enabled = "true";
			} else {
				mailConfigInfo.@enabled = "false";
			}
			
			mailConfigInfo.from = mailConfigForm.fromText.text;
			
			mailConfigInfo.appendChild(<lik:EmailList xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);
			mailConfigInfo.appendChild(<myra-stateinfo:stateInfos xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo"/>);
			mailConfigInfo.myra_stateinfo::stateInfos.appendChild(<myra-stateinfo:LiveStateInfos xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo"/>);
			
			for each (var mailItem:Object in mailConfigForm.mailListGrid.dataProvider.toArray()) {
				mailConfigInfo.lik::EmailList.appendChild(<lik:email xmlns:lik="http://www.likyateknoloji.com/likya-gen">{"" + mailItem.mailinfo} </lik:email>);
			}
			
			if(mailConfigForm.mailStateInfoGrid.dataProvider != null && mailConfigForm.mailStateInfoGrid.dataProvider.length != 0) {
				dateTimeString = getformatteddatetime();
				for each (var stateItem:Object in mailConfigForm.mailStateInfoGrid.dataProvider.toArray()) {
					var sssTmp:SSS = new SSS(stateItem.stateinfo);
					
					mailConfigInfo.myra_stateinfo::stateInfos.myra_stateinfo::LiveStateInfos.appendChild(<myra-stateinfo:LiveStateInfo xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo"/>);
					
					mailConfigInfo.myra_stateinfo::stateInfos.myra_stateinfo::LiveStateInfos.myra_stateinfo::LiveStateInfo[counter].appendChild(<myra-stateinfo:StateName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{sssTmp.state}</myra-stateinfo:StateName>);
					mailConfigInfo.myra_stateinfo::stateInfos.myra_stateinfo::LiveStateInfos.myra_stateinfo::LiveStateInfo[counter].@LSIDateTime = dateTimeString;
					
					if(sssTmp.substate != null && sssTmp.substate != "") {
						mailConfigInfo.myra_stateinfo::stateInfos.myra_stateinfo::LiveStateInfos.myra_stateinfo::LiveStateInfo[counter].appendChild(<myra-stateinfo:SubstateName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{sssTmp.substate}</myra-stateinfo:SubstateName>);
					}
					
					if(sssTmp.status != null && sssTmp.status != "") {
						mailConfigInfo.myra_stateinfo::stateInfos.myra_stateinfo::LiveStateInfos.myra_stateinfo::LiveStateInfo[counter].appendChild(<myra-stateinfo:StatusName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{sssTmp.status}</myra-stateinfo:StatusName>);
					}
					
					counter ++;
				}
			}
			
			mailConfigInfo.userName = mailConfigForm.userNameText.text;
			mailConfigInfo.appendChild(<lik:userPassword xmlns:lik="http://www.likyateknoloji.com/likya-gen">{"" + mailConfigForm.passwordText.text} </lik:userPassword>);
			
			mailConfigInfo.appendChild(<mailProps/>);
			for each (var propItem:Object in mailConfigForm.mailPrpListGrid.dataProvider.toArray()) {
				mailConfigInfo.mailProps.appendChild(<prop>{"" + propItem.mailprop} </prop>);
			}
			
			return mailConfigInfo;
		}
		
		private static function getformatteddatetime():String {
			
			var currentDateTime:Date = new Date();
			var currentDF:DateFormatter = new DateFormatter(); 
			currentDF.formatString = "YYYY-MM-DD";
			var dateString:String = currentDF.format(currentDateTime);
			currentDF.formatString = "JJ:NN:SS"; //"LL:NN:SS";
			var timeString:String = currentDF.format(currentDateTime);
			
			var dateTimeString:String = dateString + "T" + timeString + ".000+03:00";	
			
			return dateTimeString;
		}
		
	}
}