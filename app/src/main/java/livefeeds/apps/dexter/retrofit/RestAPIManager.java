package livefeeds.apps.dexter.retrofit;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.squareup.okhttp.ResponseBody;


/**
 * Created by kapilkapri on 10/12/15.
 */
public class RestAPIManager {
    public RestAPIManager() {
    }

    // private static boolean isCalledFromDialogFragment = false;
    private void showProgressDialog(Fragment fragment) {
       /* if (fragment instanceof BaseFragment) {
            ((BaseFragment) (fragment)).showGIFProgressBar();
        } else {
            ((BaseDialog) (fragment)).showGIFProgressBar();
        }*/
    }

    private void hideProgressDialog(Fragment fragment) {
       /* if (fragment instanceof BaseFragment) {
            ((BaseFragment) (fragment)).hideGIFProgressBar();
        } else {
            ((BaseDialog) (fragment)).hideGIFProgressBar();
        }*/
    }

    private void retrofitNetworkError(Fragment callingFragment, Context context, Throwable t) {
   /*     // handle execution failures like no internet connectivity
        //DO NETWORK ERROR HANDLING HERE
        Log.d("Retrofit", " :  Failure :" + t.getMessage());
        hideProgressDialog(callingFragment);
        try {
            FyndrUtility.displaySnackbar(context, true, context.getResources().getString(R.string.error_internetConnection));
        } catch (Resources.NotFoundException e) {
            e.getMessage();
            FyndrUtility.showToastMessage(context, "No internet connection !");
        }*/
    }

    private void retrofitHttpError(Context context, int errorCode, ResponseBody errorBody) {

      /*  Log.d("Retrofit", " :  No Success ->" + errorCode);

        try {
            String json = errorBody.string();
            JSONObject jsonObject = new JSONObject(json);
            String msg = jsonObject.getString("message");
            if (null != msg) {
                FyndrUtility.displaySnackbar(context, true, msg);
            }
        } catch (Exception e) {
            FyndrUtility.displaySnackbar(context, true, context.getResources().getString(R.string.error_commonErrorMsg));
        }*/
    }



    /*
    * ------- Login API-----------------
    *
    */

    public void callLogin(final Context context, final Fragment callingFragment, String user_name, String password, String imei) {
 /*       try {

            if (FyndrUtility.isNetworkAvailable(context)) {
                showProgressDialog(callingFragment);
                Call<LoginResponse> call = FyndrApplication.getRestClient().getObliviateService().logIn(Constants.LOGIN_URL, user_name, password, imei);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Response<LoginResponse> response, Retrofit retrofit) {
 hideProgressDialog(callingFragment);

                        // response.isSuccess() is true if the response code is 2xx
                        if (null != response && response.isSuccess()) {

                            LoginResponse responseObj = response.body();
                            Log.d("Retrofit", " :  Success :" + responseObj.toString());

                            if (responseObj.is_logged_in()) {
                                EventBus.getDefault().post(new EventLogin(responseObj.is_logged_in()));
                            } else {
                                FyndrUtility.displaySnackbar(context, true, responseObj.getMessage());
                            }
                        } else {
                            if (null != response) {
                                int statusCode = response.code();
                                ResponseBody errorBody = response.errorBody();
                                retrofitHttpError(context, statusCode, errorBody);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        retrofitNetworkError(callingFragment, context, t);

                    }
                });
            } else {
                Throwable t = new Throwable();
                retrofitNetworkError(callingFragment, context, t);
                try {
                    FyndrUtility.displaySnackbar(context, true, context.getResources().getString(R.string.error_internetConnection));
                } catch (Resources.NotFoundException e) {
                    e.getMessage();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }



    /*
    * ------- Logout API-----------------
    *
    */

    public void callLogout(final Context context, final Fragment callingFragment) {
      /*  try {
            if (FyndrUtility.isNetworkAvailable(context)) {

                showProgressDialog(callingFragment);

                Call<LogoutResponse> call = FyndrApplication.getRestClient().getObliviateService().logout(Constants.LOGOUT_URL);
                call.enqueue(new Callback<LogoutResponse>() {
                    @Override
                    public void onResponse(Response<LogoutResponse> response, Retrofit retrofit) {

                        hideProgressDialog(callingFragment);

                        // response.isSuccess() is true if the response code is 2xx
                        if (null != response && response.isSuccess()) {

                            LogoutResponse responseObj = response.body();
                            Log.d("Retrofit", " :  Success :" + responseObj.toString());
                            // FyndrUtility.displaySnackbar(context, true, responseObj.getMessage());
                            if (responseObj.is_logged_out()) {
                                EventBus.getDefault().post(new EventLogout(responseObj.is_logged_out()));

                            } else {
                                FyndrUtility.displaySnackbar(context, true, responseObj.getMessage());
                            }


                        } else {
                            if (null != response) {
                                int statusCode = response.code();
                                ResponseBody errorBody = response.errorBody();
                                retrofitHttpError(context, statusCode, errorBody);
                            }

                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        retrofitNetworkError(callingFragment, context, t);
                    }
                });
            } else {
                try {
                    FyndrUtility.displaySnackbar(context, true, context.getResources().getString(R.string.error_internetConnection));
                } catch (Resources.NotFoundException e) {
                    e.getMessage();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


}
