package com.acer.iplant.ui.shopcompose.model

import com.acer.iplant.R

object ProductData {
    fun imageCarousel(): ArrayList<Int> = arrayListOf(
        R.drawable.carousel_iplant_1, R.drawable.carousel_iplant_2, R.drawable.carousel_iplant_3,
        R.drawable.carousel_iplant_4, R.drawable.carousel_iplant_5,
    )

    val product = listOf(
        Product(
            1,
            R.drawable.product_1,
            "Insektisida pestisida Bamex 18ec 100ml",
            128000,
            "Insektisida BAMEX 18 EC yang berbahan aktip Abamektin untuk dapat mengendalikan hama pada tanaman Cabai, padi, semangka, melon, Bawang Merah, Jeruk, Kubis Seperti hama Trip, kutu daun/Aphis sp,kutu daun myzus sp, Penggerek daun (Lyriomiza sp),Perusak Daun (Plutella Xylostellah."
        ),
        Product(
            2,
            R.drawable.product_2,
            "Demolish 18 EC 50 ML Insektisida Abimektin",
            51000,
            "Demolish 18EC untuk mengendalikan hama Thrips sp, Kutu Daun, Tungau, Ulat daun, dan wereng coklat. Thrips merupakan hama utama pada tanaman terutama tanaman Cabai, Tomat. Demolish 18EC mampu membrantas hama trips dan sejenisnya secara tuntas dan cepat"
        ),
        Product(
            3,
            R.drawable.product_3,
            "Insektisida Demolish 18 EC 100 ML Pestisida Kontak Obat Abamektin Hama",
            124000,
            "Demolish 18EC untuk mengendalikan hama Thrips sp, Kutu Daun, Tungau, Ulat daun, dan wereng coklat. Thrips merupakan hama utama pada tanaman terutama tanaman Cabai, Tomat."
        ),
        Product(
            4,
            R.drawable.product_4,
            "Pupuk NPK Pak Tani 16-16-16 Biru - Repack 500gr",
            10250,
            "NPK PAK TANI 16-16-16 merupakan pupuk majemuk yang diproduksi dengan teknologi modern dengan komposisi unsur hara seimbang. Berbentuk granular mudah larut dalam air."
        ),
        Product(
            5,
            R.drawable.product_5,
            "Pupuk Urea Nitrea 5kg Kemasan Pabrik",
            57500,
            "Pupuk Urea sangat bagus untuk tanaman masa pertumbuhan karena mempunyai kandungan nitrogen 46%"
        ),
        Product(
            6,
            R.drawable.product_6,
            "Pembasmi Hama / Insektisida Agrimec 50 ml",
            100000,
            "insektisida Agrimec 18EC merupakan racun yang bersifat kontak dan perut yang juga bersifat akarisida. bentuk dari insektisida ini adalah Emulsifiable Concentrate atau disingkat dengan EC atau dalam bahasa Indonesianya adalah pekatan yang dapat di emulsikan dalam air"
        ),
        Product(
            7,
            R.drawable.product_7,
            "Insektisida Pegasus 500 Sc 80 Ml - Pestisida Pembasmi Hama Tanaman",
            162000,
            "Insektisida Pegasus 500 Sc 80 Ml - Berguna untuk membasmi hama pada tanaman"
        ),
        Product(
            8,
            R.drawable.product_8,
            "Decis 250ml Insektisida Hama Kutu Daun",
            75500,
            "Banyak yang menanyakan kepada saya obat untuk tanaman yang terkena hama keriting daun.saya sendiri menganjurkan untuk menggunakan Insetisida. karena di Benihkita waktu itu belum menyediakan obat tersebut saya hanya bisa menyarankan untuk membeli obat apa yang cocok untuk penyakit tanaman mereka."
        ),
        Product(
            9,
            R.drawable.product_9,
            "Gunting Ranting/Dahan Teleskopik",
            179750,
            "Gunting ini merupakan peralatan berkebun yang digunakan untuk memangkas ranting pohon, rumput yang tinggi, dan tanaman liar. Mata pisau terbuat dari material yang sangat tajam sehingga mudah untuk memangkas segala jenis tanaman atau ranting"
        ),
        Product(
            10,
            R.drawable.product_10,
            "Gunting Ranting/Dahan Tanaman W238",
            76850,
            "Gunting ini digunakan untuk menggunting tumbuhan seperti ranting pohon, ranting bunga dan lainnya. Cocok digunakan untuk menjaga tumbuhan ditaman Anda akan rapi dan tidak merambat kemana-mana."
        )
    )

    val productFavorite = product.shuffled()
}