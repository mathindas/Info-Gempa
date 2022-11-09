package com.rivaldo.informasigempa.network

import com.rivaldo.informasigempa.BuildConfig
import com.rivaldo.informasigempa.utils.Constant.Companion.API_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    /**
     * COMPANION OBJECT
     * Companion object adalah kemampuan membuat inner object di dalam class, tanpa harus menggunakan nama object
     * Companion object secara otomatis akan menggunakan nama Companion, atau bisa langsung diakses lewat nama class nya
     * Tujuan : Dapat diakses ditempat lain dengan menggunakan ApiConfig.getApiService()
     */
    companion object {
        /**
        Kelas ini akan membuat kode Anda menjadi lebih efektif
        karena Anda tidak perlu membuat konfigurasi Retrofit baru setiap kali membutuhkannya,
        tetapi cukup memanggil fungsi yang ada di dalam class ini saja.
         */

        /**
         *In which places should the by lazy be used?
        They should be used where you don't need unnecessary initialization of the variables.
        These are val properties, so after initialization, that same object would be used in the whole run of the class.

         *Why should it be used?
        lazy should be used when any of your objects are heavy and they take a long time to initialize.
        Here lazy properties can help to skip that delay which could be caused by the initialization of those objects.
        As lazy would only initialize the variable when it is called, otherwise it won't be created.

         *How does it make the code better
        By lazily loading heavy objects, it increases the performance of the code, as any delay in the loading,
        which could've been caused due to the initialization of these heavy variables is now eliminated.
        It keeps the memory free, as these won't be initialized until the moment, they are called by the code.
         */
        private val retrofit by lazy {

            /**
             * Saat menggunakan logging interceptor untuk aplikasi yang sudah dipublikasikan,
             * pastikan kembali pesan log hanya akan tampil pada mode debug.
             * Saat informasi sensitif dapat mudah lihat di jendela logcat dan ini
             * membuat penerapan security menyebabkan vulnerability di mana data yang tampil
             * dapat dimanfaatkan oleh pihak yang tidak bertanggung jawab.
             */
            val loggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            /**
             * Membuat retrofit :
             * baseUrl yang akan diakses adalah API_URL
             * Converter menggunakan GSON
             * Client menggunakan client yang sudah dibuat diatas
             */
            Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        }

        val api by lazy {
            retrofit.create(ApiService::class.java)
        }
    }
}