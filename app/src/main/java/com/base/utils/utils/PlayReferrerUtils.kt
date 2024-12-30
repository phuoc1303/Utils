package com.base.utils.utils

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * A utilityobject for retrieving and processing install referrer information from the Google Play Store.
 *
 * This object provides methods to initialize the install referrer client, obtain referrer details,
 * and log the information to Firebase Analytics.
 */
object PlayReferrerUtils {
    private lateinit var referrerClient: InstallReferrerClient

    /**
     * Initializes the install referrer client and establishes a connection to the Google Play Store.
     *
     * This method should be called early in the application's lifecycle, typically in the `onCreate`
     * method of your main activity or application class.
     *
     *@param context The application context.
     */
    fun init(context: Context) {
        referrerClient = InstallReferrerClient.newBuilder(context).build()
        referrerClient.startConnection(object : InstallReferrerStateListener {

            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when (responseCode) {
                    InstallReferrerClient.InstallReferrerResponse.OK -> {
                        // Connection established.
                        Log.d("PlayReferrer",
                            "Google Play connection established"
                        )
                        obtainReferrerDetails(context)
                    }
                    InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> {
                        // API not available on the current Play Store app.
                        Log.d("PlayReferrer",
                            "Feature not supported"
                        )
                    }
                    InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> {
                        Log.d("PlayReferrer",
                            "Service unavailable"
                        )
                        // Connection couldn't be established.
                    }
                }
            }

            override fun onInstallReferrerServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })
    }

    /**
     * Obtains the install referrer details from the Google Play Store and logs them to Firebase Analytics.
     *
     * This method retrieves the install referrer string, parses it into key-value pairs, and logs
     * the information as a Firebase Analytics event.
     *
     * @param context The application context.
     */
    private fun obtainReferrerDetails(context: Context) {
        val response = referrerClient.installReferrer
        val installReferrer: String = response.installReferrer
        val params = parseQueryString(installReferrer)
        Log.d("PlayReferrer",
            "installReferrer: $installReferrer"
        )

        val logParams = Bundle()
        params.forEach { (key, value) ->
            Log.d("PlayReferrer", "$key: $value")
            logParams.putString(key, value)
        }
        FirebaseAnalytics.getInstance(context).logEvent("app_install_source", logParams)

        // End connection after fetch to prevent memory leak
        referrerClient.endConnection()
    }

    /**
     * Parses a query string into a map of key-value pairs.
     *
     * This method splits the query string by '&' and then by '=' to extract the key-value pairs.
     *
     * @param queryString The query string to parse.
     * @return A map of key-value pairs.
     */
    private fun parseQueryString(queryString: String): Map<String, String> {
        return queryString.split("&").associate {
            val (key, value) = it.split("=")
            key to value
        }
    }

}