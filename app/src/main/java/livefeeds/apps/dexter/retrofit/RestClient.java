package livefeeds.apps.dexter.retrofit;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import livefeeds.apps.dexter.application.MyApplication;
import okio.Buffer;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by kapilkapri on 15/10/15.
 */
public class RestClient {


    private RetroRestService apiService;
    private CookieManager cookieManager;


    public RestClient() {

        cookieManager = new CookieManager(new PersistentCookieStore
                (MyApplication.getAppContext()), CookiePolicy.ACCEPT_ALL);
        OkHttpClient client = new OkHttpClient();

        client.setReadTimeout(80, TimeUnit.SECONDS);
        client.setConnectTimeout(90, TimeUnit.SECONDS);

        client.setCookieHandler(cookieManager);
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Customize the request
                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                                // .header("Authorization", getToken())
                        .method(original.method(), original.body())
                        .build();

                Response response = chain.proceed(request);

                // Customize or return the response
                return response;
            }
        });
        /**
         *  Interceptor for logs
         */

        //  client.interceptors().add(new LoggingInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(RetroRestService.class);
    }

    public RetroRestService getObliviateService() {
        return apiService;
    }

    public void clearCookies() {
        if (cookieManager != null) {
            cookieManager.getCookieStore().removeAll();
        }
    }

    public static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            String requestLog = String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers());
            //YLog.d(String.format("Sending request %s on %s%n%s",
            //        request.url(), chain.connection(), request.headers()));
            if (request.method().compareToIgnoreCase("post") == 0) {
                requestLog = "\n" + requestLog + "\n" + bodyToString(request);
            }
            Log.d("TAG", "request" + "\n" + requestLog);

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();

            String responseLog = String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers());

            String bodyString = response.body().string();

            Log.d("TAG", "response" + "\n" + responseLog + "\n" + bodyString);

            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), bodyString))
                    .build();
            //return response;
        }
    }

    public static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
