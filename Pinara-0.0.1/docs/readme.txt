Tanım dosyaları:


Pinara tanım-config dosyası, uygulamanın bulunduğu dizinde açılmış olan conf dizininde tutulacaktır.

path : conf
ad : pinaraConfig.xml

Eğer böyle bir dizin yoksa, ya da bu dizinde bu isimde bir dosya yok ise, uygulama çalışmaycak,
hata uyarısı verip sonlanacaktır. Aynı zamanda conf dizini myra config dosyasının da yeri olarak kabul edilecektir.
Myra çalışmaya başladığında conf dizini altında myraConfig.xml dosyasını arayacaktır. Eğer dizin içinde bu isimli bir 
dosya bulursa bunu okuyacak, bulamaz ise bu dizine myraConfig.xml adı ile varsayılan değerleri içeren bir dosya açacaktır.

Pinara ve myra çalışma esnası oluşan dosyalarını data adlı dizinde tutacaktır. Eğer bu dizin yok ise oluşturulacaktır.
Bu dizinde Pinara, Pinara.authorization adlı dosyasını ve senaryo dosyasını tutacaktır.

Myra ayrıca, data dizini altında myra adlı bir dizin daha isteyecektir, yok ise olutşuracaktır. Bu dizin altında 
recover, persist işlemleri ile ilgili dosyalar bulunacaktır. 

Myra ve pinara log dosyalarını log dizine atacaklardır. Bu dizin log4j.xml'de tanımlıdır. Başka bir işleme gerek yoktur.
log4j, log dizini yok ise oluşturacaktır.