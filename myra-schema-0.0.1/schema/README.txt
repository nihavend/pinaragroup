Bu dizinde bulunan şema dosyalarını kullanabilmek için, myra scehma projesi altında bulunan;

1. myra-joblist.xsd dosyasına aşağıdaki satır eklenmiştir.

	<xs:import namespace="http://www.likyateknoloji.com/pinara-config" schemaLocation="/Users/serkan/programlar/dev/workspace/Pinara-0.0.1/schema/pinara-config.xsd" />
	
2. myconfig.xsdconfig dosyasına aşağıdaki satır eklenmiştir.

	<xb:namespace
		uri="http://www.likyateknoloji.com/pinara-config">
		<xb:package>com.likya.xsd.pinara.model.config</xb:package>
	</xb:namespace>	
	


Not : 

Myra JEF'i kullanmak isteyen projeler, kendi xsd lerini myra-schema projesi içinde derlemeliler.