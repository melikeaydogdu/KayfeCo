package com.company.kayfeco.data

data class ProductWithCategoryId(
    val title: String,
    val extra: String,
    val price: Int,
    val imageUrl: String,
    val description: String,
    val categoryId: String
)
fun getHotCoffees(): List<ProductWithCategoryId> {
    return listOf(
        ProductWithCategoryId(
            title = "Pumpkin Latte",
            extra = "Espresso, buharda pişirilmiş süt, kabak baharatı şurubu, tarçın.",
            price = 85,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/project209-1.appspot.com/o/Pumpkin%20Latte.jpg?alt=media&token=3ecbd58d-1d7b-4e70-9386-931b6cec55eb",
            categoryId = "1",
            description =  "Mevsimin favorisi olan bu rahatlatıcı latte, espressonun zenginliğini balkabağı baharatının, sütün ve bir tutam tarçının kremsi tatlılığıyla birleştiriyor. Sonbahar veya herhangi bir sıcak an için mükemmel."
        ),
        ProductWithCategoryId(
            title = "Macchiato",
            extra = "Espresso, süt köpüğü",
            price = 70,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/project209-1.appspot.com/o/Macchiato.png?alt=media&token=b66fbc1f-0dfb-4e3d-ae2a-aa4f261be349",
            categoryId = "1",
            description = "Üzerine az miktarda süt köpüğü eklenmiş cesur bir espresso içeceği. Espressonun yoğunluğunu seven ancak daha yumuşak bir bitiş isteyenler için mükemmel."
        ),
        ProductWithCategoryId(
            title = "Matcha Latte",
            extra = "Matcha tozu, buharda pişirilmiş süt, bir miktar tatlandırıcı",
            price = 80,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/project209-1.appspot.com/o/Matcha%20Latte.png?alt=media&token=c3f61d4f-d78b-4c23-b4b0-4f1979f010a5",
            categoryId = "1",
            description = "Canlı ve sağlıklı bir seçenek olan Matcha Latte, yüksek kaliteli matcha yeşil çayını buharla ısıtılmış sütle birleştirerek kremsi ve ferahlatıcı bir içecek sunuyor ve geleneksel lattelere benzersiz bir dokunuş katıyor."
        ),
        ProductWithCategoryId(
            title = "Cortado",
            extra = "Espresso, buharda pişirilmiş süt",
            price = 80,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/project209-1.appspot.com/o/Cortado.png?alt=media&token=1bad17fb-9cb9-4bcc-9673-4f13c56474bb",
            categoryId = "1",
            description = "Espresso ve ılık sütün dengeli bir karışımı olan cortado, tam doğru yoğunluk seviyesinde yumuşak bir kahve deneyimi sunar. Biraz daha az süt tercih eden kahve severler için harika bir seçim."
        )
    )
}
fun getColdCoffees(): List<ProductWithCategoryId> {
    return listOf(
        ProductWithCategoryId(
            title = "Iced Flat White",
            extra = "Espresso, soğutulmuş süt köpüğü, buz parçacıkları",
            price = 85,
            imageUrl = "",
            categoryId = "2",
            description = "Ice Flat White, tek veya çift shot espresso üzerine mikro köpükle hazırlanmış, dengeli ve yoğun aromalı, buzla ferahlatılmış bir kahve içeceğidir. Mikro köpük, sürahinin altından buharda pişirilmiş soğutulmuş süt ile elde edilir ve kahvenin üzerine pürüzsüz bir doku kazandırır. Flat White ile Latte arasındaki temel fark, süt ve espresso oranıdır."
        ),
        ProductWithCategoryId(
            title = "Cold Brew",
            extra = "Buz küpleri, demlenmiş kalın öğütülmüş espresso, su",
            price = 75,
            imageUrl = "",
            categoryId = "2",
            description = "Yavaş demleme ve hazırlama yöntemi sayesinde Cold Brew daha yumuşak içimli bir kahvedir. Her zaman içmeye alışık olduğumuz bir fincan klasik kahveye göre serinletici yaz içeceği olarak sert kahve aromasından uzaktır.Yaklaşık 24 saatlik bir demlenme süresine sahiptir.  Kahve içmeyi çok fazla sevmeyenler de Cold Brew kahvesini rahatlıkla deneyebilir."
        ),
        ProductWithCategoryId(
            title = "Iced Americano",
            extra = "Buz küpleri, soğutulmuş espresso, su",
            price = 60,
            imageUrl = "",
            categoryId = "2",
            description = "Iced Caffè Americano olarak da bilinen buzlu Americano, espresso temelli kahvelerden birisidir. Espresso'nun üzerine su ve buz eklenmesiyle yapılır. Bu yüzden buzlu Americano'yu aslında seyreltilmiş espresso olarak da tanımlayabiliriz."


        ),
        ProductWithCategoryId(
            title = "Iced Mocha",
            extra = "Buz küpleri, soğutulmuş espresso, süt, çikolata şurubu, krema",
            price = 80,
            imageUrl = "",
            categoryId = "2",
            description = "Ice Mocha, soğutulmuş espresso, çikolata şurubu ve soğuk sütle hazırlanan zengin ve kremalı bir içecektir. Buz eklenerek serinletilen bu içecek, çikolatanın tatlı ve zengin notalarıyla kahvenin güçlü aromalarını bir araya getirir. Üzerine eklenen krema ve çikolata sosu, içeceğe ekstra tatlılık ve dokunuş katar."
        ),
        ProductWithCategoryId(
            title = "Iced Latte",
            extra = "Buz küpleri, espresso, soğuk süt",
            price = 80,
            imageUrl = "",
            categoryId = "2",
            description = "Yaz mevsiminin favorisi olan ice latte, soğuk süt ve buzla hazırlanan espresso bazlı bir içecektir. Kahve severler arasında popüler olan bu içecek, özellikle sıcak yaz günlerinde serinlemek isteyenler için mükemmeldir."
        )
    )
}

fun getHotDrinks(): List<ProductWithCategoryId> {
    return listOf(
        ProductWithCategoryId(
            title = "Çay",
            extra = "Siyah çay yaprakları, sıcak su",
            price = 25,
            imageUrl = "", // drawable kullanılacak
            categoryId = "3",
            description = "Duyularınızı ısıtmak için sıcak servis edilen klasik, ferahlatıcı has rize siyah çayı. Rahat bir öğleden sonra molası veya sabah enerjinizi artırmak için mükemmel"
        ),
        ProductWithCategoryId(
            title = "Queen Berry Bitki Çayı",
            extra = "Hibiscus, mürver, böğürtlen yaprakları, kuşburnu, kurutulmuş meyveler, doğal aromalar",
            price = 50,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/project209-1.appspot.com/o/Queen%20Berry%20Herbal%20Tea.jpg?alt=media&token=93e50b89-d50e-41cb-8982-0632ef09a53a",
            categoryId = "3",
            description = "Zengin, meyveli bir tada sahip, meyveli bitki çayı, meyve severler için mükemmel. Bu kafeinsiz karışım, tatlı ve ekşi notaları ferahlatıcı bir demlemede birleştiriyor. "
        ),
        ProductWithCategoryId(
            title = "Victoria Sunset Bitki Çayı",
            extra = "Hibiscus, kuşburnu, papatya, portakal kabuğu, kurutulmuş elma, doğal aromalar",
            price = 55,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/project209-1.appspot.com/o/Victoria%20Sunset%20Herbal%20Tea.jpg?alt=media&token=fd3c4448-6fbe-43dd-857e-9ca543e21df9",
            categoryId = "3",
            description = "Güzel bir gün batımını anımsatan, tatlı bir dokunuşa sahip, çiçeksi ve meyveli bir bitki çayı. Bu kafeinsiz karışım sakinleştirici ve rahatlatıcı bir deneyim sunar."
        ),
        ProductWithCategoryId(
            title = "Apple Paradise Bitki Çayı",
            extra = "Kurutulmuş elma, tarçın, hibiscus, kuşburnu, karanfil, doğal aromalar",
            price = 50,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/project209-1.appspot.com/o/Apple%20Paradise%20Herbal%20Tea.jpg?alt=media&token=ebe1d8cf-48d3-4fb4-9e81-21d2ac15d2b4",
            categoryId = "3",
            description = "Bu çay, tarçın ve baharatlarla bir tutam tatlı elmanın sıcak, rahatlatıcı tadını sunar, rahat bir sonbahar günü için mükemmeldir. Kafeinsizdir ve meyveli tatlarla doludur"
        ),
        ProductWithCategoryId(
            title = "Yeşil Çay",
            extra = "Yeşil çay yaprakları, sıcak su",
            price = 40,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/project209-1.appspot.com/o/Green%20Tea.jpg?alt=media&token=4bd736f4-373d-4c9e-ba56-19f3d52201bf",
            categoryId = "3",
          description =   "Hafif ve aromatik olan bu yeşil çay antioksidanlar açısından zengindir ve sakinleştirici, doğal bir enerji artışı sağlar. Gününüze başlamak veya bitirmek için sağlıklı bir seçim."

        )
    )
}

fun getColdDrinks(): List<ProductWithCategoryId> {
    return listOf(
        ProductWithCategoryId(
            title = "Mojito",
            extra = "Taze nane yaprakları, limon suyu, şeker, soda, buz",
            price = 60,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/project209-1.appspot.com/o/Mojito.png?alt=media&token=b5ac2baf-f639-4c31-af5d-2d818c222fd5",
            categoryId = "4",
            description = "Bu klasik mojito, nane, limon ve maden suyunun bir miktar tatlılıkla ferahlatıcı bir karışımıdır. Soğuk servis edildiğinde, hafif ve canlı tatları sevenler için ideal bir seçimdir."
        ),
        ProductWithCategoryId(
            title = "Limonata",
            extra =  "Taze limon suyu, şeker, su, buz",
            price = 50,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/project209-1.appspot.com/o/Lemonade.png?alt=media&token=54443307-df4f-4d8d-b0da-52b0ffd7b4ed",
            categoryId = "4",
            description = "Zamansız bir favori olan limonatamız, taze sıkılmış limonlardan ve tam kıvamında şekerden yapılır. Serinlemek için mükemmel, canlı, tatlı ve keskin bir içecektir."
        ),
        ProductWithCategoryId(
            title = "Green Ginger",
            extra = "Taze zencefil, limon suyu, bal, soda, buz",
            price = 60,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/project209-1.appspot.com/o/Green%20Ginger.png?alt=media&token=b27be272-b083-4ace-a9de-2e44c5e70fd5",
            categoryId = "4",
            description = "Yeşil Zencefil içeceği, topraksı ve ferahlatıcı bir dokunuşla zencefil ve limonun canlı bir birleşimidir. Canlandırıcı zencefil sıcaklık sağlarken, limon taze bir narenciye aroması patlaması ekler."
        ),
        ProductWithCategoryId(
            title = "Portakal Suyu",
            extra = "Taze sıkılmış portakal suyu, buz",
            price = 40,
            imageUrl = "", // boş kalacak, drawable'dan gösterilecek
            categoryId = "4",
            description = "Taze sıkılmış portakal suyu, doğal ve besleyici bir içecek olarak sabah kahvaltılarında veya günün herhangi bir saatinde keyifle tüketilebilir. Buz gibi ferahlığıyla güne başlamak için muhteşem bir tercih."
        ),
        ProductWithCategoryId(
            title = "Çilekli Limonata",
            extra = "Taze limon suyu, çilek aroması, şeker, su, buz",
            price = 55,
            imageUrl = "", // boş kalacak, drawable'dan gösterilecek
            categoryId = "4",
            description = "Bu lezzetli içecek, serinletici limonatanın canlandırıcı dokunuşunu taze çileklerle buluşturarak sizi unutulmaz bir dansa davet ediyor. Çilekli Limonata, sadece lezzetiyle değil, düşük kalorisiyle de dikkat çekiyor."
        )

    )

}
fun getShakes(): List<ProductWithCategoryId> {
    return listOf(
        ProductWithCategoryId(
            title = "Naranja",
            extra = "Taze portakal suyu, şeker, su, buz",
            price = 65,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/project209-1.appspot.com/o/Naranja.png?alt=media&token=0101b29a-6ce8-4498-922c-f82248431100",
            categoryId = "5",
            description = "Taze portakal suyundan yapılan ferahlatıcı bir narenciye karışımı olan bu içecek, tatlılık ve ekşiliğin mükemmel bir dengesini sunar. Özellikle sıcak bir günde, susuzluğunuzu gideren harika bir içecektir."
        ),
        ProductWithCategoryId(
            title = "Estra Astar",
            extra = "Yeşil çay, yıldız anason, tarçın, bal, limon",
            price = 75,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/project209-1.appspot.com/o/Estra%20Astar.png?alt=media&token=28e44a36-c309-436a-b9fb-328f360b409d",
            categoryId = "5",
            description = "Egzotik otlar ve baharatların bir füzyonu olan Estra Astar, hafif çiçeksi bir tatlılıkla aromatik ve rahatlatıcı bir içecektir. Günün herhangi bir saatinde rahatlatıcı bir an için idealdir."
        ),
        ProductWithCategoryId(
            title = "Çilekli Milkshake",
            extra = "Çilek, kırılmış buz, krema, soğutulmuş süt",
            price = 60,
            imageUrl = "", // drawable'dan gösterilecek
            categoryId = "5",
            description = "Sütten gelen yoğun kıvamı ile çilek ve beyaz çikolatanın mükemmel uyumu ile anın enerjisini yükseltmeye hazır.Milkshake, süt, ezilmiş buz, çilek, çırpılmış krema, krem şanti, yoğunlaştırılmış süt, buharlaştırılmış süt veya vanilya gibi aromaların mikser yardımı ile karıştırılması ile yapılan soğuk bir içecek."
        ),
        ProductWithCategoryId(
            title = "Mangolu Frozen",
            extra = "Mango püresi, soğuk su, kırılmış buz",
            price = 70,
            imageUrl = "", // drawable'dan gösterilecek
            categoryId = "5",
            description = "Taze mango ile hazırlanan frozen, serinletici bir içecek tercih edenler için ideal! Kayfe Co. menüsünde yer alan Mango Frozen sizi eşsiz lezzet yolculuklarına davet ediyor. Tadına doyulmaz mango lezzetinin serinletici etkisi bir tık uzağınızda!"
        )
    )
}
fun getDesserts(): List<ProductWithCategoryId> {
    return listOf(
        ProductWithCategoryId(
            title = "Trileçe",
            extra = "Akışkan karamel sosu, yumuşacık kek, süt",
            price = 70,
            imageUrl = "", // drawable kullanılacak
            categoryId = "6",
            description =  "Trileçe, 1930'lardan itibaren Meksika'da yaygınlaşan ve tüm dünyaya yayılan bir sütlü tatlıdır. Trileçe ismi İspanyolca'da “üç” ve “süt” anlamına gelen “tres” ve “leches” kelimelerinden türemiştir. Karamel sosu ile zenginleştirilen bu tatlı kahvenizin en güzel eşlikçilerinden!"
        ),
        ProductWithCategoryId(
            title = "Fırında Sütlaç",
            extra = "İnce pirinç taneleri, taze süt, şeker",
            price = 80,
            imageUrl = "", // drawable kullanılacak
            categoryId = "6",
            description = "Türk mutfağının vazgeçilmez tatlılarından biri olan sütlaç; ince pirinç taneleri, taze süt, şeker ve vanilin ile hazırlanır. Sofralarımızın baş köşesine yerleşmiştir. Hem göz alıcı görüntüsü hem de eşsiz lezzetiyle sütlaç, her özel günü ya da günlük yaşantımızın tatlı molasını süsleyen bir hazine niteliğindedir."
        ),
        ProductWithCategoryId(
            title = "Magnolia",
            extra = "Yumuşak krema, çilek/muz, parçalanmış kurabiye",
            price = 70,
            imageUrl = "", // drawable kullanılacak
            categoryId = "6",
            description = "Oldukça hafif bir tatlı olan Magnolia tatlısı, lezzetli ve hafif bir tatlı türüdür. Genellikle krema, süt ve yumurta gibi ana malzemelerle hazırlanır."
        ),
        ProductWithCategoryId(
            title = "Cheesecake",
            extra = "Peynir, frambuaz sos, tereyağlı kurabiye",
            price = 90,
            imageUrl = "", // drawable kullanılacak
            categoryId = "6",
            description = "Peynirin en tatlı hali sizlerle… Her mevsimin vazgeçilmez tadı olan Cheesecake’in eşsiz lezzetini deneyimlemeniz mutlaka sizi tatmin edecektir."
        )
    )
}
fun getAllProducts(): List<ProductWithCategoryId> {
    return getColdDrinks() +
            getHotDrinks() +
            getColdCoffees() +
            getHotCoffees() +
            getShakes() +
            getDesserts()
}


