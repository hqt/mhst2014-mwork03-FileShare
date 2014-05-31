package com.fpt.mobile.solutions.helper;

/** interface stimulate AsyncTask. Use this interface for Callback method */
interface ITaskCallback {
    void onPreExecute();
    Integer doInBackground();
    void onProgressUpdate(Integer... values);
    void onCancel();
    void onPostExecute(int status);
}
